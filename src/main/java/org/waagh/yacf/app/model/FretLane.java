package org.waagh.yacf.app.model;

import java.util.ArrayList;
import java.util.List;

public class FretLane implements ICord {
	List<Note> notes;

	public FretLane(Note note) {
		int defaulFretCount = 22;

	}

	public FretLane(Note note, int fretCount) {

	}

	@Override public Note getTuning() {
		return notes.get(0);
	}

	@Override public void setTuning(Note note) {
		notes.set(0, note);
	}

	@Override public Note getNoteAt(int position) {
		return notes.get(position);
	}

	@Override public List<Integer> getNotePositions(Note note) {
		List<Integer> noteOccurences = new ArrayList<>();

		for (Note cordNote : notes) {
			if (cordNote.simpleMatch(note)) {
				noteOccurences.add(getNotePosition(cordNote));
			}
		}

		return noteOccurences;
	}

	@Override public int getNotePosition(Note note) {
		return notes.indexOf(note);
	}

}
