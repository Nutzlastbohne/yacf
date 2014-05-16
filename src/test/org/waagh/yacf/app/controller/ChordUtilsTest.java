package org.waagh.yacf.app.controller;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.waagh.yacf.app.model.IChordFormula;
import org.waagh.yacf.app.model.NoteSystem;

import java.util.HashMap;
import java.util.Map;

public class ChordUtilsTest {

	/** Object under Test*/
	ChordUtils chordUtils;

	NoteSystem ns;

	@Before
	public void before(){
		ns = new NoteSystem();
		chordUtils = new ChordUtils(ns.getScalePattern("Major"));
	}

	@Test
	public void getMaj7Formula(){
		String error = "Chords for %s don't match!\nexp:\t%s\nact:\t%s";
		String formulaName = "Maj7";
		IChordFormula formula = ns.getChordFormula(formulaName);
		Map<Integer, Boolean> expected = new HashMap<>();
		expected.put(0, false);
		expected.put(2, false);
		expected.put(4, false);
		expected.put(6, false);

		Map<Integer, Boolean> actual = chordUtils.getRelativeNotesFromFormula(formula.getOriginalFormula());
		Assert.assertTrue(String.format(error, formulaName, expected, actual), expected.equals(actual));
	}

	@Test
	public void getDom13Formula(){
		String error = "Chords for %s don't match!\nexp:\t%s\nact:\t%s";
		String formulaName = "13";
		IChordFormula formula = ns.getChordFormula(formulaName);
		Map<Integer, Boolean> expected = new HashMap<>();
		expected.put(0, false);
		expected.put(2, false);
		expected.put(4, false);
		expected.put(5, false);
		expected.put(8, true);
		expected.put(10, true);
		expected.put(12, false);

		Map<Integer, Boolean> actual = chordUtils.getRelativeNotesFromFormula(formula.getOriginalFormula());
		Assert.assertTrue(String.format(error, formulaName, expected, actual), expected.equals(actual));
	}

	@Test
	public void getmFormula(){
		String error = "Chords for %s don't match!\nexp:\t%s\nact:\t%s";
		String formulaName = "13";
		IChordFormula formula = ns.getChordFormula(formulaName);
		Map<Integer, Boolean> expected = new HashMap<>();
		expected.put(0, false);
		expected.put(2, false);
		expected.put(4, false);
		expected.put(5, false);
		expected.put(8, true);
		expected.put(10, true);
		expected.put(12, false);

		Map<Integer, Boolean> actual = chordUtils.getRelativeNotesFromFormula(formula.getOriginalFormula());
		Assert.assertTrue(String.format(error, formulaName, expected, actual), expected.equals(actual));
	}
}
