package org.waagh.yacf.app.model.Notes;

public interface IAbsoluteNote extends INote{
	int getOctave();
	int setOctave();
	INote getBasicNote();
	INote setBasicNote();
	IAbsoluteNote getNext();
	IAbsoluteNote setNext();
	IAbsoluteNote getPrevious();
	IAbsoluteNote setPrevious();

}
