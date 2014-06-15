package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.Notes.IAbsoluteNote;
import org.waagh.yacf.app.model.Notes.NotePosition;

import java.util.HashMap;
import java.util.Map;

public class ChordTab {

	IAbsoluteChord chord;
	/* optional notes keep me from using an 'unlinked' collection.
	* Because optional notes may not appear in a tab, we can't rely on Chord <=> NotePosition Order*/
	Map<IAbsoluteNote, NotePosition> notePositions;

	public ChordTab(IAbsoluteChord chord) {
		this.chord = chord;
		this.notePositions = new HashMap<>();
	}

	public IAbsoluteChord getChord() {
		return chord;
	}

	public Map<IAbsoluteNote, NotePosition> getNotePositions() {
		return notePositions;
	}

	public NotePosition getNotePosition(IAbsoluteNote note) {
		for (IAbsoluteNote currentNote : notePositions.keySet()) {
			if (currentNote.getBasicNote().equals(note.getBasicNote()) && currentNote.getOctave() == note.getOctave()) {
				return notePositions.get(currentNote);
			}
		}

		return new NotePosition(-1, -1);
	}

	public void putNotePosition(IAbsoluteNote note, int stringNumber, int fretNumber) {
		putNotePosition(note, new NotePosition(stringNumber, fretNumber));
	}

	public void putNotePosition(IAbsoluteNote note, NotePosition position) {
		notePositions.put(note, position);
	}

	@Override public String toString() {
		StringBuffer tabStringBuffer = new StringBuffer();

		for (NotePosition position : notePositions.values()) {
			tabStringBuffer.append(position.string);
			tabStringBuffer.append(System.lineSeparator());
		}

		return tabStringBuffer.toString();
	}
}
