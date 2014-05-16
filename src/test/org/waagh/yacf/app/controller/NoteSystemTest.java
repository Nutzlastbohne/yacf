package org.waagh.yacf.app.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.waagh.yacf.app.model.*;
import org.waagh.yacf.app.model.chords.IChord;

import java.util.*;

public class NoteSystemTest {

	NoteSystem noteSystem;
	private static final String error = "Chord notes for %s dont match!\nexpected:\t%s\nactual\t\t%s";

	@Before
	public void before() {
		noteSystem = new NoteSystem();
	}

	@Test
	public void buildMaj(){
		List<Integer> expectedPositions = Arrays.asList(0, 4, 7);
		test("C", "Maj", expectedPositions);
	}

	@Test
	public void buildMinorMaj13(){
		List<Integer> expectedPositions = Arrays.asList(0, 3, 7, 11, 14, 17, 21);
		test("C", "m/Maj13", expectedPositions);
	}

	private boolean test(String root, String chord, List<Integer> expected){
		IChord<IRelativeNote> actualPositions = noteSystem.buildChord(root, chord);
		boolean chordsMatch = chordMatches(expected, actualPositions);
		Assert.assertTrue(String.format(error, root + chord, expected, actualPositions), chordsMatch);
		return false;
	}

	private boolean chordMatches(Collection<Integer> expected, IChord<IRelativeNote> actual){
		boolean isMatch = false;

		for (IRelativeNote note : actual.getNotes()) {
			isMatch = expected.contains(note.getRelativePosition());
			if (!isMatch) break;
		}

		return isMatch;
	}

}
