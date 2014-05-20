package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.IRelativeNote;
import org.waagh.yacf.app.model.Notes.RelativeNote;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RelativeChord extends AbstractChord {

	public RelativeChord() {
		chordNotes = new ConcurrentHashMap<>();
	}

	public RelativeChord(Map<IRelativeNote, Boolean> chordNotes) {
		this.chordNotes = chordNotes;
	}

	public RelativeChord(List<Integer> chordNotes) {
		this();
		for (Integer chordNote : chordNotes) {
			this.chordNotes.put(new RelativeNote(chordNote), false);
		}
	}

	@Override public Collection addNote(Object newNote, boolean isOptional) {
		return null;
	}

	@Override public void put(Object note, boolean isOptional) {

	}
}
