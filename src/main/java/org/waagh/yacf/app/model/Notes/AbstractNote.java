package org.waagh.yacf.app.model.Notes;

public class AbstractNote<T extends INote> implements INote<T> {

	private String name;
	private T previousNote;
	private T nextNote;
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

	@Override public T getNext() {
		return nextNote;
	}

	@Override public T getPrevious() {
		return previousNote;
	}

	@Override public void setNext(T nextNote) {
		this.nextNote = nextNote;
	}

	@Override public void setPrevious(T previousNote) {
		this.previousNote = previousNote;
	}

	@Override public T getNoteXStepsAway(int steps) {
		T distantNote = (T) this;
		boolean moveBackwards = steps < 0;
		steps = Math.abs(steps);

		while (steps > 0) {
			distantNote = (T) (moveBackwards ? distantNote.getPrevious() : distantNote.getNext());
			steps--;
		}

		return distantNote;
	}
}
