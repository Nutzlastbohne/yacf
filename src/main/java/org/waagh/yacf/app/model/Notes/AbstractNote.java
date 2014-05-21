package org.waagh.yacf.app.model.Notes;


public abstract class AbstractNote<T> implements INote {

	private String name;
	private INote previousNote;
	private INote nextNote;
	private int ordinal;

	public AbstractNote(String name, int ordinal) {
		this.name = name;
		this.ordinal = ordinal;
	}

	@Override public String getName() {
		return name;
	}

	@Override public int getOrdinal() {
		return ordinal;
	}

	@Override public INote getNext() {
		return nextNote;
	}

	@Override public INote getPrevious() {
		return previousNote;
	}

	@Override public void setNext(INote nextNote) {
		this.nextNote = nextNote;
	}

	@Override public void setPrevious(INote previousNote) {
		this.previousNote = previousNote;
	}

	@Override public INote getNoteXStepsAway(int steps) {
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
