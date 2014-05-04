package org.waagh.yacf.app.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ChordUtilsTest {

	/**
	 * Object under test
	 */
	ChordUtils chordUtils;

	List<BasicNote> cMajor;    // C D E F G A B C
	List<BasicNote> eMajor;    // E F# G# A B C# D# E

	ChordFormula maj7 = ChordFormula.MajorSeventh;
	ChordFormula minor7flat5 = ChordFormula.MinorMajorSevenFlatFifth;

	@Before
	public void before() {
		chordUtils = new ChordUtils();

		cMajor = new ArrayList<>();
		cMajor.add(BasicNote.C);
		cMajor.add(BasicNote.D);
		cMajor.add(BasicNote.E);
		cMajor.add(BasicNote.F);
		cMajor.add(BasicNote.G);
		cMajor.add(BasicNote.A);
		cMajor.add(BasicNote.B);
		cMajor.add(BasicNote.C);

		eMajor = new ArrayList<>();
		eMajor.add(BasicNote.E);
		eMajor.add(BasicNote.F_SHARP);
		eMajor.add(BasicNote.G_SHARP);
		eMajor.add(BasicNote.A);
		eMajor.add(BasicNote.B);
		eMajor.add(BasicNote.C_SHARP);
		eMajor.add(BasicNote.D_SHARP);
		eMajor.add(BasicNote.E);
	}

	@Test
	public void getChordFormulaForM7() {
		ChordFormula expectedChordFormula = ChordFormula.MajorSeventh;
		String symbol = expectedChordFormula.symbol;
		String errorMessage = "unexpected formula for symbol '%s'!\nexpected:\t%s\nactual:\t%s";
		ChordFormula actualChordFormula = chordUtils.getChordFormulaBySymbol(symbol);

		boolean formulasMatch = actualChordFormula == expectedChordFormula;
		Assert.assertTrue(String.format(errorMessage, symbol, expectedChordFormula, actualChordFormula), formulasMatch);
	}

	@Test
	public void getChordFormulaForM7b5() {
		ChordFormula expectedChordFormula = ChordFormula.MinorMajorSevenFlatFifth;
		String symbol = expectedChordFormula.symbol;
		String errorMessage = "unexpected formula for symbol '%s'!\nexpected:\t%s\nactual:\t%s";
		ChordFormula actualChordFormula = chordUtils.getChordFormulaBySymbol(symbol);

		boolean formulasMatch = actualChordFormula == expectedChordFormula;
		Assert.assertTrue(String.format(errorMessage, symbol, expectedChordFormula, actualChordFormula), formulasMatch);
	}

	@Test
	public void parseChordFormulaBySymbol() {
		String symbol;
		String errorMessage = "unexpected formula for symbol '%s'!\nexpected:\t%s\nactual:\t\t%s";
		boolean formulasMatch;
		ChordFormula expectedChordFormula;
		ChordFormula actualChordFormula;

		Map<String, ChordFormula> symbolToFormulaMap = new HashMap<>();
		symbolToFormulaMap.put("", ChordFormula.UNKNOWN);
		symbolToFormulaMap.put("m/MajAdd4", ChordFormula.MinorAddedFourth);
		symbolToFormulaMap.put("minor/MajorAdd4", ChordFormula.MinorAddedFourth);
		symbolToFormulaMap.put("m/MAdd4", ChordFormula.MinorAddedFourth);
		symbolToFormulaMap.put("mMajorAdd4", ChordFormula.MinorAddedFourth);

		for (String testSymbol : symbolToFormulaMap.keySet()) {
			expectedChordFormula = symbolToFormulaMap.get(testSymbol);
			actualChordFormula = chordUtils.getChordFormulaBySymbol(testSymbol);

			formulasMatch = actualChordFormula == expectedChordFormula;
			Assert.assertTrue(String.format(errorMessage, testSymbol, expectedChordFormula, actualChordFormula), formulasMatch);
		}
	}

	@Test
	public void getCmajorScale() {
		List<BasicNote> actualCmaj = chordUtils.mapScaleToRootNote(BasicNote.C);
		String errorMessage = "Scale notes for root %s don't match: \nexpected:\t %s \nactual:\t %s";
		boolean scalesMatch = actualCmaj.containsAll(cMajor);

		Assert.assertTrue(String.format(errorMessage, BasicNote.C, cMajor, actualCmaj), scalesMatch);
	}

	@Test
	public void getEmajorScale() {
		List<BasicNote> actualEmaj = chordUtils.mapScaleToRootNote(BasicNote.E);
		String errorMessage = "Scale notes for root %s don't match: \nexpected:\t %s \nactual:\t %s";
		boolean scalesMatch = actualEmaj.containsAll(eMajor);
		Assert.assertTrue(String.format(errorMessage, BasicNote.E, eMajor, actualEmaj), scalesMatch);
	}

	@Test
	public void getEmajorChord() {
		String errorMessage = "ChordNotes for symbol %s don't match: \nexpected:\t %s \nactual:\t %s";
		List<Note> expectedEmajor = chordUtils.buildChord(BasicNote.E, BasicNote.G_SHARP, BasicNote.B);
		String symbol = "EMaj";
		List<Note> actual = chordUtils.getNotesForChord(symbol);

		boolean notesMatch = chordUtils.chordNotesMatch(expectedEmajor, actual);
		Assert.assertTrue(String.format(errorMessage, symbol, expectedEmajor, actual), notesMatch);
	}

	@Test
	public void getCsharpMinorChord() {
		String errorMessage = "ChordNotes for symbol %s don't match: \nexpected:\t %s \nactual:\t %s";
		String symbol = "C#m";
		List<Note> expectedCminor = chordUtils.buildChord(BasicNote.C_SHARP, BasicNote.E, BasicNote.G_SHARP);
		List<Note> actual = chordUtils.getNotesForChord(symbol);

		boolean notesMatch = chordUtils.chordNotesMatch(expectedCminor, actual);
		Assert.assertTrue(String.format(errorMessage, symbol, expectedCminor, actual), notesMatch);
	}

	@Test
	public void getBmaj13() {
		String errorMessage = "ChordNotes for symbol %s don't match: \nexpected:\t %s \nactual:\t %s";
		String symbol = "Bmaj13";
		List<Note> expectedBmaj13 = chordUtils.buildChord(BasicNote.B, BasicNote.D_SHARP, BasicNote.F_SHARP, BasicNote.A_SHARP, BasicNote.B, BasicNote.D_SHARP, BasicNote.F_SHARP);
		List<Note> actual = chordUtils.getNotesForChord(symbol);

		boolean notesMatch = chordUtils.chordNotesMatchExactly(expectedBmaj13, actual);
		Assert.assertTrue(String.format(errorMessage, symbol, expectedBmaj13, actual), notesMatch);
	}

	@Test
	public void getFSharpDim7() {
		String errorMessage = "ChordNotes for symbol %s don't match: \nexpected:\t %s \nactual:\t %s";
		String symbol = "F#Dim7(°7)";
		List<Note> expectedFsharpDim7 = chordUtils.buildChord(BasicNote.F_SHARP, BasicNote.A, BasicNote.C, BasicNote.D_SHARP);
		List<Note> actual = chordUtils.getNotesForChord(symbol);

		boolean notesMatch = chordUtils.chordNotesMatchExactly(expectedFsharpDim7, actual);
		Assert.assertTrue(String.format(errorMessage, symbol, expectedFsharpDim7, actual), notesMatch);
	}
}
