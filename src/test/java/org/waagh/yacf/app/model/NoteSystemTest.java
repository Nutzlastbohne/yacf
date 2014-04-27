package org.waagh.yacf.app.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoteSystemTest {

	/**
	 * Object under Test
	 */
	NoteSystem noteSystem;

	private Note NoteG4 = new Note(BasicNote.G, 4);
	private Note NoteGSharp4 = new Note(BasicNote.G_SHARP, 4);
	private Note NoteA5 = new Note(BasicNote.A, 5);
	private Note NoteASharp5 = new Note(BasicNote.A_SHARP, 5);
	private Note NoteG5 = new Note(BasicNote.G, 5);
	private Note NoteC6 = new Note(BasicNote.C, 6);
	private Note NoteB7 = new Note(BasicNote.B, 7);

	private double g4Frequency = 391.995;
	private double g6Frequency = 783.991;
	private double b7Frequency = 3951.07;

	@Before
	public void before() {
		noteSystem = new NoteSystem();
	}

	@Test
	public void getNextNote() {
		Note result;

		result = noteSystem.nextNote(NoteA5);
		Assert.assertTrue("Next note doesn't match - expected: " + NoteASharp5 + " | actual: " + result, result.exactMatch(NoteASharp5));

		result = noteSystem.previousNote(NoteA5);
		Assert.assertTrue("Next note doesn't match - expected: " + NoteGSharp4 + " | actual: " + result, result.exactMatch(NoteGSharp4));

		result = noteSystem.getNoteAt(NoteA5, 15);
		Assert.assertTrue("Next note doesn't match - expected: " + NoteC6 + " | actual: " + result, result.exactMatch(NoteC6));
	}

	@Test
	public void getFrequency() {
		double result = NoteG4.getNoteFrequency();
		Assert.assertTrue("Frequency of " + NoteG4 + " doesn't match - expected: " + g4Frequency + " | actual: " + result, deviateWithinBounds(result, g4Frequency, 0.005));

		result = NoteG5.getNoteFrequency();
		Assert.assertTrue("Frequency of " + NoteG5 + " doesn't match - expected: " + g6Frequency + "  | actual: " + result, deviateWithinBounds(result, g6Frequency, 0.005));

		result = NoteB7.getNoteFrequency();
		Assert.assertTrue("Frequency of " + NoteB7 + " doesn't match - expected: " + b7Frequency + "  | actual: " + result, deviateWithinBounds(result, b7Frequency, 0.005));
	}

	private boolean deviateWithinBounds(double a, double b, double deviation) {
		return Math.abs(a - b) < deviation;
	}
}
