package org.waagh.yacf.app.model.Notes;

public class BasicNote extends AbstractNote<INote> {

	public BasicNote(String name, int ordinal) {
		super(name, ordinal);
	}

	@Override public int getPosition() {
		return this.getOrdinal();
	}
}
