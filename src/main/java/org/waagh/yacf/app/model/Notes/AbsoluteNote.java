package org.waagh.yacf.app.model.Notes;

import org.apache.commons.lang3.StringUtils;

public class AbsoluteNote extends AbstractNote<IAbsoluteNote> implements IAbsoluteNote {

	private int octave;
	private int baseNoteCount;
	private INote<INote> basicNote;

	public AbsoluteNote(INote basicNote, int octave) {
		super(basicNote.getName(), basicNote.getOrdinal());
		this.octave = octave;
		this.basicNote = basicNote;
		this.baseNoteCount = getBaseNoteCount();
	}

	/*
	TODO: this is really ugly... I shoudln't need to count basic notes for every AbsoluteNote instance.
	However, I do need the total number of 'basic' notes to calculate the absolute position of a note.
	So, either I rebuild my model, or I need to move the getPosition() and toString() method out of here
	*/
	private int getBaseNoteCount() {
		int count = 0;
		int currentIndex = getOrdinal();
		INote<INote> currentBaseNote = basicNote;
		do {
			count++;
			currentBaseNote = currentBaseNote.getNext();
		} while (currentIndex != currentBaseNote.getOrdinal());

		return count;
	}

	@Override public INote getBasicNote() {
		return basicNote;
	}

	@Override public void setBasicNote(INote basicNote) {
		this.basicNote = basicNote;
	}

	@Override public IAbsoluteNote getNext() {
		INote nextBasicNote = basicNote.getNext();
		int nextOctave = nextBasicNote.getOrdinal() == 0 ? octave + 1 : octave;

		return new AbsoluteNote(nextBasicNote, nextOctave);
	}

	@Override public IAbsoluteNote getPrevious() {
		INote prevBasicNote = basicNote.getPrevious();
		int prevOctave = basicNote.getOrdinal() == 0 ? octave - 1 : octave;

		return new AbsoluteNote(prevBasicNote, prevOctave);
	}

	@Override public int getOctave() {
		return octave;
	}

	@Override public void setOctave(int octave) {
		this.octave = octave;
	}

	@Override public int getPosition() {
		return getOctave() * baseNoteCount + this.getOrdinal();
	}

	@Override public String toString() {
		if (getOctave() < 4) {
			String prefix = StringUtils.repeat(",", Math.abs(getOctave() - 3));
			return prefix + getName().toUpperCase();
		} else {
			String suffix = StringUtils.repeat("'", getOctave() - 4);
			return getName().toLowerCase() + suffix;
		}
	}
}
