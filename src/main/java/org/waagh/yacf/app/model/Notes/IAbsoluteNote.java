package org.waagh.yacf.app.model.Notes;

public interface IAbsoluteNote extends INote<IAbsoluteNote> {
	int getOctave();
	void setOctave(int octave);
	INote getBasicNote();
	void setBasicNote(INote basicNote);
	IAbsoluteNote getNext();
//	IAbsoluteNote setNext();
	IAbsoluteNote getPrevious();
//	IAbsoluteNote setPrevious();

}
