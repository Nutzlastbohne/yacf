package org.waagh.yacf.app.controller;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.waagh.yacf.app.model.IChordFormula;
import org.waagh.yacf.app.model.IRelativeNote;
import org.waagh.yacf.app.model.NoteSystem;
import org.waagh.yacf.app.model.Notes.RelativeNote;
import org.waagh.yacf.app.model.chords.IChord;
import org.waagh.yacf.app.model.chords.RelativeChord;

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
		IChord<IRelativeNote> expected = new RelativeChord();
		expected.put(new RelativeNote(0), false);
		expected.put(new RelativeNote(4), false);
		expected.put(new RelativeNote(7), false);
		expected.put(new RelativeNote(11), false);

		IChord<IRelativeNote> actual = chordUtils.getRelativeNotesFromFormula(formula.getOriginalFormula());
		Assert.assertTrue(String.format(error, formulaName, expected, actual), expected.equals(actual));
	}

	@Test
	public void getDom13Formula(){
		String error = "Chords for %s don't match!\nexp:\t%s\nact:\t%s";
		String formulaName = "13";
		IChordFormula formula = ns.getChordFormula(formulaName);
		IChord<IRelativeNote> expected = new RelativeChord();
		expected.put(new RelativeNote(0), false);
		expected.put(new RelativeNote(2), false);
		expected.put(new RelativeNote(4), false);
		expected.put(new RelativeNote(5), false);
		expected.put(new RelativeNote(8), true);
		expected.put(new RelativeNote(10), true);
		expected.put(new RelativeNote(12), false);

		IChord<IRelativeNote> actual = chordUtils.getRelativeNotesFromFormula(formula.getOriginalFormula());
		Assert.assertTrue(String.format(error, formulaName, expected, actual), expected.equals(actual));
	}

	@Test
	public void getmFormula(){
		String error = "Chords for %s don't match!\nexp:\t%s\nact:\t%s";
		String formulaName = "13";
		IChordFormula formula = ns.getChordFormula(formulaName);
		IChord<IRelativeNote> expected = new RelativeChord();
		expected.put(new RelativeNote(0), false);
		expected.put(new RelativeNote(2), false);
		expected.put(new RelativeNote(4), false);
		expected.put(new RelativeNote(5), false);
		expected.put(new RelativeNote(8), true);
		expected.put(new RelativeNote(10), true);
		expected.put(new RelativeNote(12), false);

		IChord<IRelativeNote> actual = chordUtils.getRelativeNotesFromFormula(formula.getOriginalFormula());
		Assert.assertTrue(String.format(error, formulaName, expected, actual), expected.equals(actual));
	}
}
