package org.waagh.yacf.app.model;

public interface IAbsoluteNote {

	int getOctave();
	INote getBasicNote();
	IAbsoluteNote getNext();
	IAbsoluteNote getPrevious();
	IAbsoluteNote getNoteXStepsAway(int steps);

}
