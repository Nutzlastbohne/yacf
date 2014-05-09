package org.waagh.yacf.app.model;

import java.util.List;

public interface IChord<T> {

	void setNoteState(int chordNoteIndex, boolean isOptional);

	List<T> getNotes();

	List<T> getMandatoryNotes();

	List<T> getOptionalNotes();
}
