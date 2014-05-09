package org.waagh.yacf.app.model;

import java.util.ArrayList;
import java.util.List;

public class AbsoluteChord implements IChord<IAbsoluteNote> {

	List<IAbsoluteNote> absoluteNotes;

	public AbsoluteChord() {
		absoluteNotes = new ArrayList<>();
	}

	public AbsoluteChord(List<IRelativeNote> relativeNotes, IAbsoluteNote rootNote) {
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

	@Override public List<IAbsoluteNote> getMandatoryNotes() {
		return null;
	}

	@Override public List<IAbsoluteNote> getOptionalNotes() {
		return null;
	}
}
