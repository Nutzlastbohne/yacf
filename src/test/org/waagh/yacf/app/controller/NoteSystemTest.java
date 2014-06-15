package org.waagh.yacf.app.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.waagh.yacf.app.model.NoteSystem;
import org.waagh.yacf.app.model.Notes.*;
import org.waagh.yacf.app.model.chords.IAbsoluteChord;
import org.waagh.yacf.app.model.chords.IChord;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NoteSystemTest {

	/**
	 * 0  1  2  3  4  5  6  7  8  9  10 11
	 * 1  2  3  4  5  6  7  8  9  10 11 12
	 * C  C# D  D# E  F  F# G  G# A  A# B
	 * 1     2     3  4     5     6     7
	 */

	NoteSystem noteSystem;
	private static final String error = "Chord notes for %s dont match!\nexpected:\t%s\nactual\t\t%s";

	@Before
	public void before() {
		noteSystem = new NoteSystem();
	}

	@Test
	public void getNextNote() {
		INote<INote> testNote = noteSystem.getBasicNoteByName("D");
		String actualName = testNote.getNext().getName();
		String expectedName = "D#";
		Assert.assertEquals("Unexpected note after 'D'", expectedName, actualName);

		testNote = noteSystem.getBasicNoteByName("G#");
		actualName = testNote.getNext().getName();
		expectedName = "A";
		Assert.assertEquals("Unexpected note after 'G#'", expectedName, actualName);
	}

	@Test
	public void getPreviousNote() {
		INote<INote> testNote = noteSystem.getBasicNoteByName("D");
		String actualName = testNote.getPrevious().getName();
		String expectedName = "C#";
		Assert.assertEquals("Unexpected note before 'C#'", expectedName, actualName);

		testNote = noteSystem.getBasicNoteByName("A");
		actualName = testNote.getPrevious().getName();
		expectedName = "G#";
		Assert.assertEquals("Unexpected note before 'A#'", expectedName, actualName);
	}

	@Test
	public void buildMaj() {
		List<Integer> expectedPositions = Arrays.asList(0, 4, 7);
		testRelativeChord("Maj", expectedPositions);
	}

	@Test
	public void buildMinorMaj13() {
		List<Integer> expectedPositions = Arrays.asList(0, 3, 7, 11, 14, 17, 21);
		testRelativeChord("m/Maj13", expectedPositions);
	}

	@Test
	public void buildCMaj() {
		int basicNotes = noteSystem.getBasicNotes().size();
		int rootNoteOctave = 5;
		int noteOffset = basicNotes * rootNoteOctave;
		List<Integer> expectedPositions = Arrays.asList(noteOffset + 0, noteOffset + 4, noteOffset + 7);
		INote rootNote = noteSystem.getBasicNoteByName("C");
		IAbsoluteNote absoluteRootNote = new AbsoluteNote(rootNote, rootNoteOctave);
		testAbsoluteChord(absoluteRootNote, "Maj", expectedPositions);
	}

	@Test
	public void buildCMinorMaj13() {
		int basicNotes = noteSystem.getBasicNotes().size();
		int rootNoteOctave = 5;
		int noteOffset = basicNotes * rootNoteOctave;
		List<Integer> expectedPositions = Arrays.asList(noteOffset + 0, noteOffset + 3, noteOffset + 7, noteOffset + 11, noteOffset + 14, noteOffset + 17, noteOffset + 21);
		INote rootNote = noteSystem.getBasicNoteByName("C");
		IAbsoluteNote absoluteRootNote = new AbsoluteNote(rootNote, rootNoteOctave);
		testAbsoluteChord(absoluteRootNote, "m/Maj13", expectedPositions);
	}

	@Test
	public void buildDMinorMaj13() {
		INote rootNote = noteSystem.getBasicNoteByName("D");
		int basicNotes = noteSystem.getBasicNotes().size();
		int rootNoteOctave = 3;
		int noteOffset = basicNotes * rootNoteOctave + rootNote.getOrdinal();
		List<Integer> expectedPositions = Arrays.asList(noteOffset + 0, noteOffset + 3, noteOffset + 7, noteOffset + 11, noteOffset + 14, noteOffset + 17, noteOffset + 21);
		IAbsoluteNote absoluteRootNote = new AbsoluteNote(rootNote, rootNoteOctave);
		testAbsoluteChord(absoluteRootNote, "m/Maj13", expectedPositions);
	}

	private boolean testRelativeChord(String chord, List<Integer> expected) {
		IChord<IRelativeNote> actualPositions = noteSystem.buildRelativeChord(chord);
		boolean chordsMatch = chordMatches(expected, actualPositions);
		Assert.assertTrue(String.format(error, chord, expected, actualPositions), chordsMatch);
		return false;
	}

	private boolean testAbsoluteChord(IAbsoluteNote rootNote, String chord, List<Integer> expected) {
		IAbsoluteChord actualPositions = noteSystem.buildAbsoluteChord(rootNote, chord);
		boolean chordsMatch = chordMatches(expected, actualPositions);
		Assert.assertTrue(String.format(error, rootNote + chord, expected, actualPositions.getAbsolutePositions()), chordsMatch);
		System.out.println(actualPositions);
		return false;
	}

	private boolean chordMatches(Collection<Integer> expectedPosition, IChord<? extends INotePosition> actualPosition) {
		boolean isMatch = false;

		for (INotePosition note : actualPosition.getNotes().keySet()) {
			isMatch = expectedPosition.contains(note.getPosition());
			if (!isMatch) {
				break;
			}
		}

		return isMatch;
	}
}
