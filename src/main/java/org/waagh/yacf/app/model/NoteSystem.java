package org.waagh.yacf.app.model;

import java.util.LinkedList;
import java.util.List;

public class NoteSystem {

	private List<BasicNote> baseScale;

	public NoteSystem() {
		baseScale = new LinkedList<>();
		baseScale.add(BasicNote.C);
		baseScale.add(BasicNote.C_SHARP);
		baseScale.add(BasicNote.D);
		baseScale.add(BasicNote.D_SHARP);
		baseScale.add(BasicNote.E);
		baseScale.add(BasicNote.F);
		baseScale.add(BasicNote.F_SHARP);
		baseScale.add(BasicNote.G);
		baseScale.add(BasicNote.G_SHARP);
		baseScale.add(BasicNote.A);
		baseScale.add(BasicNote.A_SHARP);
		baseScale.add(BasicNote.B);
	}

	public BasicNote nextBasicNote(BasicNote basicNote) {
		return getBasicNoteAt(basicNote, 1);
	}

	public BasicNote previousBasicNote(BasicNote basicNote) {
		return getBasicNoteAt(basicNote, -1);
	}

	public BasicNote getBasicNoteAt(BasicNote basicNote, int steps) {
		int nextIndex;
		int currentIndex = baseScale.indexOf(basicNote);
		steps = steps % baseScale.size();                    // prevent stepping out of bounds

		if (currentIndex + steps < 0) {
			nextIndex = baseScale.size() - steps;
		} else {
			nextIndex = (currentIndex + steps) % baseScale.size();
		}

		return baseScale.get(nextIndex);
	}

	public Note getLeftHandNote(Note note) {
		return getNoteAt(note, -1);
	}

	public Note getRightHandNote(Note note) {
		return getNoteAt(note, +1);
	}

	public Note getNoteAt(Note note, int halfToneSteps) {
		int nextRelativeIndex;
		int octaveSteps;
		int currentIndex = baseScale.indexOf(note.getBasicNote());
		int nextIndex = currentIndex + halfToneSteps;

		if (nextIndex < 0) {
			octaveSteps = (int) Math.floor(nextIndex / (double) baseScale.size());
			nextRelativeIndex = baseScale.size() + (nextIndex % baseScale.size());
		} else {
			octaveSteps = nextIndex / baseScale.size();
			nextRelativeIndex = nextIndex % baseScale.size();
		}

		BasicNote nextBasicNote = baseScale.get(nextRelativeIndex);
		int octave = note.getOctave() + octaveSteps;

		return new Note(nextBasicNote, octave);
	}

	public List<BasicNote> getBaseScale() {
		return baseScale;
	}

	public void setBaseScale(List<BasicNote> baseScale) {
		this.baseScale = baseScale;
	}
}
