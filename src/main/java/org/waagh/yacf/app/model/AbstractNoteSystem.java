package org.waagh.yacf.app.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNoteSystem implements INoteSystem {

	private List<AbstractNote> notes;

	public AbstractNoteSystem() {
		notes = new ArrayList<>();
		defineNotes();
	}

	protected abstract List<AbstractNote> defineNotes();

	@Override public UniqueNote getNextLowerNote(UniqueNote note) {
		return getNoteXStepsAway(note, -1);
	}

	@Override public UniqueNote getNextHigherNote(UniqueNote note) {
		return getNoteXStepsAway(note, 1);
	}

	@Override public UniqueNote getNoteXStepsAway(UniqueNote baseNote, int steps) {
		int nextRelativeIndex;
		int octaveSteps;
		int currentIndex = notes.indexOf(baseNote);
		int nextIndex = currentIndex + steps;

		if (nextIndex < 0) {
			octaveSteps = (int) Math.floor(nextIndex / (double) notes.size());
			nextRelativeIndex = notes.size() + (nextIndex % notes.size());
		} else {
			octaveSteps = nextIndex / notes.size();
			nextRelativeIndex = nextIndex % notes.size();
		}

		int octave = baseNote.octave + octaveSteps;
		UniqueNote nextNote = new UniqueNote(notes.get(nextRelativeIndex).name);

		return nextNote;
	}

	@Override public int getRelativeNoteIndex(AbstractNote note) {
		return 0;
	}

	@Override public int getAbsoluteNoteIndex(AbstractNote note) {
		return 0;
	}

	@Override public double calculateFrequency(AbstractNote note) {
		return 0;
	}

	@Override public void setStandardPitch(double standardPitch) {

	}

	@Override public double getStandardPich() {
		return 0;
	}
}
