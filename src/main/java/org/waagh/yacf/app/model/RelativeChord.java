package org.waagh.yacf.app.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RelativeChord implements IChord<IRelativeNote> {

	Map<IRelativeNote, Boolean> chordNotes;

	public RelativeChord() {
		chordNotes = new ConcurrentHashMap<>();
	}

	public RelativeChord(List<IRelativeNote> chordNotes) {
		this();
		for (IRelativeNote chordNote : chordNotes) {
			this.chordNotes.put(chordNote, false);
		}

	}

	@Override public void setNoteState(int chordNoteIndex, boolean isOptional) {
//		chordNotes.get()
	}

	@Override public List<IRelativeNote> getNotes() {
		return null;
	}

	@Override public List<IRelativeNote> getMandatoryNotes() {
		return null;
	}

	@Override public List<IRelativeNote> getOptionalNotes() {
		return null;
	}
}
