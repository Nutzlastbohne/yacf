package org.waagh.yacf.app.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.waagh.yacf.app.model.NoteSystem;
import org.waagh.yacf.app.model.Notes.AbsoluteNote;
import org.waagh.yacf.app.model.Notes.IAbsoluteNote;
import org.waagh.yacf.app.model.Notes.INote;
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
		test("C", "Maj", expectedPositions);
	}

	@Test
	public void buildMinorMaj13() {
		List<Integer> expectedPositions = Arrays.asList(0, 3, 7, 11, 14, 17, 21);
		test("C", "m/Maj13", expectedPositions);
	}

	@Test
	public void buildCMaj() {
//		IAbsoluteNote rootNote = new AbsoluteNote(null, 0);
	}

	private boolean test(String root, String chord, List<Integer> expected) {
		IChord<Integer> actualPositions = noteSystem.buildRelativeChord(chord);
		boolean chordsMatch = chordMatches(expected, actualPositions);
		Assert.assertTrue(String.format(error, root + chord, expected, actualPositions), chordsMatch);
		return false;
	}

	private boolean chordMatches(Collection<Integer> expected, IChord<Integer> actual) {
		boolean isMatch = false;

		for (int note : actual.getNotes().keySet()) {
			isMatch = expected.contains(note);
			if (!isMatch) {
				break;
			}
		}

		return isMatch;
	}

}
