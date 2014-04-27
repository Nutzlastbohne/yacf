package org.waagh.yacf.app.model;

import java.util.LinkedList;
import java.util.List;

public class NoteSystem {

	List<BasicNote> chromaticScale;

	public NoteSystem() {
		chromaticScale = new LinkedList<>();
		chromaticScale.add(BasicNote.A);
		chromaticScale.add(BasicNote.A_SHARP);
		chromaticScale.add(BasicNote.B);
		chromaticScale.add(BasicNote.C);
		chromaticScale.add(BasicNote.C_SHARP);
		chromaticScale.add(BasicNote.D);
		chromaticScale.add(BasicNote.D_SHARP);
		chromaticScale.add(BasicNote.E);
		chromaticScale.add(BasicNote.F);
		chromaticScale.add(BasicNote.F_SHARP);
		chromaticScale.add(BasicNote.G);
		chromaticScale.add(BasicNote.G_SHARP);
	}

	public BasicNote nextBasicNote(BasicNote basicNote) {
		return getBasicNoteAt(basicNote, 1);
	}

	public BasicNote previousBasicNote(BasicNote basicNote) {
		return getBasicNoteAt(basicNote, -1);
	}

	public BasicNote getBasicNoteAt(BasicNote basicNote, int steps) {
		int nextIndex;
		int currentIndex = chromaticScale.indexOf(basicNote);
		steps = steps % chromaticScale.size();                    // prevent stepping out of bounds

		if (currentIndex + steps < 0) {
			nextIndex = chromaticScale.size() - steps;
		} else {
			nextIndex = (currentIndex + steps) % chromaticScale.size();
		}

		return chromaticScale.get(nextIndex);
	}

	public Note previousNote(Note note) {
		return getNoteAt(note, -1);
	}

	public Note nextNote(Note note) {
		return getNoteAt(note, +1);
	}

	public Note getNoteAt(Note note, int halfToneSteps) {
		int nextRelativeIndex;
		int octaveSteps;
		int currentIndex = chromaticScale.indexOf(note.basicNote);
		int nextIndex = currentIndex + halfToneSteps;

		if (nextIndex < 0) {
			octaveSteps = (int) Math.floor(nextIndex / (double) chromaticScale.size());
			nextRelativeIndex = chromaticScale.size() + (nextIndex % chromaticScale.size());
		} else {
			octaveSteps = nextIndex / chromaticScale.size();
			nextRelativeIndex = nextIndex % chromaticScale.size();
		}

		BasicNote nextBasicNote = chromaticScale.get(nextRelativeIndex);
		int octave = note.octave + octaveSteps;

		return new Note(nextBasicNote, octave);
	}
}
