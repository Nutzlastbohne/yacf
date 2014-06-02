package org.waagh.yacf.app.model.Notes;

public interface INote<T> extends INotePosition {
	String getName();
	int getOrdinal();
	T getNext();
	T getPrevious();
	void setNext(T nextNote);
	void setPrevious(T previousNote);
	T getNoteXStepsAway(int steps);
}
