package org.waagh.yacf.app.model.chords;

import java.util.List;
import java.util.Map;

public class RelativeChord extends AbstractChord<Integer> {

	String name;

	public RelativeChord() {
		super();
	}

	public RelativeChord(String name, Map<Integer, Boolean> chordNotes) {
		super(name);
		chordNotes = chordNotes;
	}

	public RelativeChord(String name, List<Integer> chordNotes) {
		super(name);
		putAll(chordNotes);
	}
}
