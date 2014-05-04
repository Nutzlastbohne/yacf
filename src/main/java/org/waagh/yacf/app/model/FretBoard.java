package org.waagh.yacf.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FretBoard {
	private List<ICord> strings;
	private List<List<Note>> notes;

	public FretBoard() {
		this.strings = new ArrayList<>();
	}

	public FretBoard(List<ICord> strings) {
		this.strings = strings;
	}

	public void addString(ICord newString) {
		strings.add(newString);
	}

	public boolean removeString(ICord removeMe) {
		return strings.remove(removeMe);
	}

	public List<List<Integer>> findPositions(Note... notes) {
		List<List<Integer>> groups = new ArrayList<>();
		List<Note> noteList = Arrays.asList(notes);
		List<Integer> chordRoots = findRootPositions(noteList.get(0), notes.length);

		for (Integer root : chordRoots) {

		}

		return null;
	}

	private List<Integer> findRootPositions(Note rootNote, int chordSize) {
		List<ICord> possibleRootStrings = findPossibleRootStringsFor(rootNote, chordSize);
		List<Integer> rootNotePositions = new ArrayList<>();

		for (ICord string : possibleRootStrings) {
			rootNotePositions.addAll(string.getNotePositions(rootNote.getBasicNote()));
		}
		return rootNotePositions;
	}

	private List<ICord> findPossibleRootStringsFor(Note rootNote, int chordSize) {
		List<ICord> possibleRootStrings = new ArrayList<>();

		possibleRootStrings.add(strings.get(strings.size() - 1));    // always add lowest String
		for (int i = strings.size() - 1; i > 0 && i >= chordSize; --i) {
			possibleRootStrings.add(strings.get(i));
		}

		return possibleRootStrings;
	}

	private List<Note> findNoteGroup(List<Note> chordNotes, int rootIndex) {

		return null;
	}
}
