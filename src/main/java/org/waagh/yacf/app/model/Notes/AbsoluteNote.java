package org.waagh.yacf.app.model.Notes;

public class AbsoluteNote extends AbstractNote<IAbsoluteNote> implements IAbsoluteNote {

	private int octave;
	private INote<INote> basicNote;

	public AbsoluteNote(INote basicNote, int octave) {
		super(basicNote.getName(), basicNote.getOrdinal());
		this.octave = octave;
		this.basicNote = basicNote;
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
}
