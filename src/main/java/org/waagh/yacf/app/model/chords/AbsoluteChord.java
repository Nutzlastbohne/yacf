package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.Notes.IAbsoluteNote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbsoluteChord implements IChord<IAbsoluteNote> {

	List<IAbsoluteNote> absoluteNotes;

	public AbsoluteChord() {
		absoluteNotes = new ArrayList<>();
	}

	public AbsoluteChord(IAbsoluteNote rootNote, List<Integer> formula, List<Integer> baseScale) {
		// TODO
	}

	public AbsoluteChord(List<IAbsoluteNote> absoluteNotes) {
		this.absoluteNotes = absoluteNotes;
	}

	@Override public void setNoteState(int chordNoteIndex, boolean isOptional) {
		absoluteNotes.get(chordNoteIndex);
	}

	@Override public List<IAbsoluteNote> getNotes() {
		return null;
	}

	@Override public Collection<IAbsoluteNote> addNotes(Collection<IAbsoluteNote> newNotes) {
		return null;
	}

	@Override public Collection<IAbsoluteNote> addNote(IAbsoluteNote newNote, boolean isOptional) {
		return null;
	}

	@Override public Collection<IAbsoluteNote> setNotes(Collection<IAbsoluteNote> newNotes) {
		return null;
	}

	@Override public List<IAbsoluteNote> getMandatoryNotes() {
		return null;
	}

	@Override public List<IAbsoluteNote> getOptionalNotes() {
		return null;
	}
}
