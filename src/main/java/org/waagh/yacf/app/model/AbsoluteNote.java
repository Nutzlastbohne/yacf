package org.waagh.yacf.app.model;

public class AbsoluteNote implements IAbsoluteNote {

	private int octave;
	private INote basicNote;

	public AbsoluteNote(INote basicNote, int octave) {
		this.octave = octave;
		this.basicNote = basicNote;
	}

	@Override public int getOctave() {
		return octave;
	}

	@Override public INote getBasicNote() {
		return basicNote;
	}

	@Override public IAbsoluteNote getNext() {
		INote nextBasicNote = basicNote.getNext();
		int nextOctave = nextBasicNote.getOrdinal() == 0 ? octave+1 : octave;

		return new AbsoluteNote(nextBasicNote, nextOctave);
	}

	@Override public IAbsoluteNote getPrevious() {
		INote prevBasicNote = basicNote.getPrevious();
		int prevOctave = basicNote.getOrdinal() == 0 ? octave-1 : octave;

		return new AbsoluteNote(prevBasicNote, prevOctave);
	}

	@Override public IAbsoluteNote getNoteXStepsAway(int steps) {
		IAbsoluteNote distantNote = this;
		boolean moveBackwards = steps < 0;
		steps = Math.abs(steps);

		while (steps > 0) {
			distantNote = moveBackwards ? distantNote.getPrevious() : distantNote.getNext();
			steps--;
		}

		return distantNote;
	}
}
