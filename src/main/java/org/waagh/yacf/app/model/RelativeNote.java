package org.waagh.yacf.app.model;

public class RelativeNote implements IRelativeNote{

	private int relativePosition;

	public RelativeNote(int relativePosition) {
		this.relativePosition = relativePosition;
	}

	public int getRelativePosition() {
		return relativePosition;
	}

	@Override public String toString() {
		return "RelativeNote(" + relativePosition +")";
	}
}
