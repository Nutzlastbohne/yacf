package org.waagh.yacf.app.controller;

import org.waagh.yacf.app.model.Notes.IAbsoluteNote;
import org.waagh.yacf.app.model.IChordFormula;

import java.util.*;

/*
	TODO: Build formula from Chord Name
	TODO:
 */
public class ChordUtils {

	private List<Integer> baseScale;
	private Map<String, String> replacements;

	private static String PRIMITIVE_NOTE_MATCH = "[CDEFGAB]";
	private static final char FLAT = 'b';
	private static final char SHARP = '#';

	/**
	 * Initialize the ChordUtils class with the default western scale (Major)
	 */
	public ChordUtils() {
		baseScale = Arrays.asList(2, 2, 1, 2, 2, 2, 1);

		//TODO: Throw Away
		replacements = new HashMap<>();
		replacements.put("(?i)(Major)", "Maj");
		replacements.put("M(?!aj)", "Maj");
		replacements.put("(?i)(Min(or)?)", "m");
		//replacements.put("(?i)(m[/\\\\]?m(?!aj))", "m/M");
		replacements.put("m/m", "m/M");
		replacements.put("m\\\\m", "m/M");
		replacements.put("(?i)mm", "m/M");
		replacements.put("(?i)(Flat)", "b");
		replacements.put("(?i)(Sharp)", "#");
		replacements.put("(?i)(dom)|(dominant)", "");
		replacements.put("(?i)(augmented)", "");
		replacements.put("(?i)(suspended)", "sus");
		replacements.put("(?i)(four(th)?)", "4");
		replacements.put("(?i)(five(th)?)", "5");
		replacements.put("(?i)(six(th)?)", "6");
		replacements.put("(?i)(nine(th)?)", "9");
	}

	public ChordUtils(int... baseScale) {
		this();
		this.baseScale = new ArrayList<>();
		for (int scalePos : baseScale) {
			this.baseScale.add(scalePos);
		}
	}

	public ChordUtils(List<Integer> baseScale) {
		this();
		this.baseScale = baseScale;
	}

	List<IAbsoluteNote> mapChordToRootNote(IAbsoluteNote rootNote, IChordFormula formula) {
		List<IAbsoluteNote> scaleNotes = new ArrayList<>();
		IAbsoluteNote currentNote = rootNote;
		scaleNotes.add(currentNote);    // root note is always first

		for (int stepWidth : baseScale) {
			currentNote = currentNote.getNoteXStepsAway(stepWidth);
			scaleNotes.add(currentNote);
		}

		return scaleNotes;
	}

	public Map<Integer, Boolean> getRelativeNotesFromFormula(String formula) {
		//TODO must match PRIMITIVE_NOTE_MATCH
		Map<Integer, Boolean> chordNotes = new HashMap<>();
		String formulaParts[] = formula.split("-");
		int scaleSize = baseScale.size();

		for (String formulaPart : formulaParts) {
			int relativeIndex = getIndexFromFormulaPart(formulaPart) - 1;
			int offset = getOffsetFromFormulaPart(formulaPart);
			boolean isOptional = isOptional(formulaPart);

//			IRelativeNote chordNote = new RelativeNote(relativeIndex + offset);
			chordNotes.put(relativeIndex + offset, isOptional);
		}

		return chordNotes;
	}

	private int getIndexFromFormulaPart(String part) {
		String indexAsString = part.replaceAll("\\D", "");    //remove everything that is not a digit
		return Integer.parseInt(indexAsString);
	}

	private int getOffsetFromFormulaPart(String part) {
		int offset = 0;
		if (part.contains("#")) {
			offset = part.replaceAll("[^#]", "").length();
		} else if (part.contains("b")) {
			offset = part.replaceAll("[^b]", "").length() * -1;
		}

		return offset;
	}

	private boolean isOptional(String part) {
		return part.contains("(");
	}

	private int getOffset(char noteSuffix) {
		switch (noteSuffix) {
			case FLAT:
				return -1;
			case SHARP:
				return 1;
			default:
				return 0;
		}
	}
}
