package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.Notes.INote;
import org.waagh.yacf.app.model.Notes.INotePosition;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IChord<T extends INotePosition> {

	String getName();

	void setName(String name);

	void putAll(Collection<T> newNotes);

	void putAll(Map<T, Boolean> newNotes);

	void put(T note, boolean isOptional);

	void setNoteState(T note, boolean isOptional);

	Map<T, Boolean> getNotes();

	Collection<T> getMandatoryNotes();

	Collection<T> getOptionalNotes();

	boolean chordMatches(IChord<T> otherChord);
}
