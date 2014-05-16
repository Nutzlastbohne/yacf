package org.waagh.yacf.app.model.chords;

import java.util.Collection;
import java.util.List;

public interface IChord<T> {

	void setNoteState(int chordNoteIndex, boolean isOptional);

	Collection<T> getNotes();

	Collection<T> addNotes(Collection<T> newNotes);

	Collection<T> addNote(T newNote, boolean isOptional);

	Collection<T> setNotes(Collection<T> newNotes);

	Collection<T> getMandatoryNotes();

	Collection<T> getOptionalNotes();
}
