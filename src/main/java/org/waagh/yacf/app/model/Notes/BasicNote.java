package org.waagh.yacf.app.model.Notes;

public class BasicNote implements INote {

	private String name;
	private INote previousNote;
	private INote nextNote;
	private int ordinal;

	public BasicNote(String name, int ordinal) {
		this.name = name;
		this.ordinal = ordinal;
	}

	@Override public String getName() {
		return name;
	}

	@Override public int getOrdinal() {
		return 0;
	}

	@Override public INote getNext() {
		return nextNote;
	}

	@Override public INote getPrevious() {
		return previousNote;
	}

	@Override public INote setNext(INote nextNote) {
		return this.nextNote = nextNote;
	}

	@Override public INote setPrevious(INote previousNote) {
		return this.previousNote = previousNote;
	}

	@Override public INote getNoteXStepsAway(int steps) {	//TODO Abstract candidate?
		INote distantNote = this;
		boolean moveBackwards = steps < 0;
		steps = Math.abs(steps);

		while (steps > 0) {
			distantNote = moveBackwards ? distantNote.getPrevious() : distantNote.getNext();
			steps--;
		}

		return distantNote;
	}
}
