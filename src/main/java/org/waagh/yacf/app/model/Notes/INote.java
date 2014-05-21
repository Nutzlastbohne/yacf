package org.waagh.yacf.app.model.Notes;

public interface INote {
	String getName();
	int getOrdinal();
	INote getNext();
	INote getPrevious();
	void setNext(INote nextNote);
	void setPrevious(INote previousNote);
	INote getNoteXStepsAway(int steps);
}
