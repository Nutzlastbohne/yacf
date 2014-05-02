package org.waagh.yacf.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FretLane implements ICord {
	private List<Note> notes;
	private static final int DEFAULT_FRET_COUNT = 22;

	public FretLane(Note note) {
		this(note, DEFAULT_FRET_COUNT);
	}

	public FretLane(Note note, int fretCount) {
		initCordNotes(note, fretCount);
	}

	private void initCordNotes(Note tuning, int fretCount) {
		notes = new ArrayList<>(fretCount);
		Note currentNote = tuning;

		for (int i = 0; i < fretCount; i++) {
			notes.add(currentNote);
			currentNote = currentNote.getNextNote();
		}
	}

	@Override public Note getTuning() {
		return notes.get(0);
	}

	@Override public void resetTuning(Note note) {
		notes.set(0, note);
		initCordNotes(note, notes.size());
	}

	@Override public Note getNoteAt(int position) {
		return notes.get(position);
	}

	@Override public List<Integer> getNotePositions(Note note) {
		return getNotePositions(note.getBasicNote());
	}

	public List<Integer> getNotePositions(BasicNote basicNote) {
		return notes.stream().filter(note -> note.getBasicNote() == basicNote).map(notes::indexOf).collect(Collectors.toList());
	}

	@Override public int getNotePosition(Note note) {
		return notes.indexOf(note);
	}
}
