package org.waagh.yacf.app.model.Notes;

public class RelativeNote implements IRelativeNote {

	private int position;

	public RelativeNote(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	@Override public String toString() {
		return String.valueOf(position);
	}
}
