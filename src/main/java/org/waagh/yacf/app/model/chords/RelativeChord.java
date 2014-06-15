package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.Notes.IRelativeNote;

import java.util.List;
import java.util.Map;

public class RelativeChord extends AbstractChord<IRelativeNote> implements IRelativeChord {

	String name;

	public RelativeChord() {
		super();
	}

	public RelativeChord(String name, Map<IRelativeNote, Boolean> chordNotes) {
		super(name);
		putAll(chordNotes);
	}

	public RelativeChord(String name, List<IRelativeNote> chordNotes) {
		super(name);
		putAll(chordNotes);
	}
}
