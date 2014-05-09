package org.waagh.yacf.app.model;

public interface INote {

	public String getName();
	public int getOrdinal();
	public INote getNext();
	public INote getPrevious();
	INote setNext(INote nextNote);
	INote setPrevious(INote previousNote);
	INote getNoteXStepsAway(int steps);


}
