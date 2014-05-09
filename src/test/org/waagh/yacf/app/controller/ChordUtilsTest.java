package org.waagh.yacf.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.waagh.yacf.app.model.IChordFormula;
import org.waagh.yacf.app.model.IRelativeNote;
import org.waagh.yacf.app.model.NoteSystem;
import org.waagh.yacf.app.model.RelativeNote;

import java.util.List;
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
	public void getRelativeNotesFromFormula(){
		IChordFormula formula = ns.getChordFormula("Maj7");
		Map<IRelativeNote, Boolean> chordNotes = chordUtils.getRelativeNotesFromFormula(formula.getOriginalFormula());
		System.out.println("Got " + chordNotes);
	}
}
