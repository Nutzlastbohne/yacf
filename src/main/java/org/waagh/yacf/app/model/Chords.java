package org.waagh.yacf.app.model;

import java.util.*;

/*
	TODO: Build formula from Chord Name
	TODO:
 */
public class Chords {

	private String formula;
	private int underlyingScalePattern[];
	private Map<String, String> replacements;

	private static String PRIMITIVE_NOTE_MATCH = "[CDEFGAB]";

	/**
	 * Initialize the Chords class with the default western scale (Major)
	 */
	public Chords() {
		underlyingScalePattern = new int[] { 2, 2, 1, 2, 2, 2, 1 };

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

	public Chords(int... underlyingScalePattern) {
		this();
		this.underlyingScalePattern = underlyingScalePattern;
	}

	ChordFormula getChordFormulaBySymbol(String symbol) {
		ChordFormula matchingFormula = getFormulaByExactSymbol(symbol);

		if (matchingFormula.equals(ChordFormula.UNKNOWN)) {
			matchingFormula = parseChordFormulaBySymbol(symbol);
		}

		return matchingFormula;
	}

	private ChordFormula getFormulaByExactSymbol(String symbol) {
		ChordFormula matchingFormula = ChordFormula.UNKNOWN;
		for (ChordFormula formula : ChordFormula.values()) {
			if (formula.symbol.equalsIgnoreCase(symbol)) {
				matchingFormula = formula;
			}
		}
		return matchingFormula;
	}

	/**
	 * Try harder finding a matching symbol
	 *
	 * @param symbol short notation of the chord name
	 * @return matching formula or UNKNOWN if none found
	 */
	private ChordFormula parseChordFormulaBySymbol(String symbol) {
		ChordFormula matchingFormula = ChordFormula.UNKNOWN;
		String fixedString = symbol;
		boolean matchFound = true;
		// replace long words with their abbreviations
		while (matchFound) {
			for (String entry : replacements.keySet()) {
				matchFound = fixedString.split(entry).length > 1;
				if (matchFound) {
					fixedString = fixedString.replaceAll(entry, replacements.get(entry));
					break;
				}
			}
		}

		matchingFormula = getFormulaByExactSymbol(fixedString);

		return matchingFormula;
	}

	List<BasicNote> mapScaleToRootNote(BasicNote rootNote) {
		List<BasicNote> scaleNotes = new ArrayList<>();
		BasicNote currentNote = rootNote;
		scaleNotes.add(currentNote);    // root note is always first

		for (int stepWidth : underlyingScalePattern) {
			currentNote = currentNote.getNoteAtDistance(stepWidth);
			scaleNotes.add(currentNote);
		}

		return scaleNotes;
	}

	public List<Note> getNotesForChord(String symbol) {
		//TODO must match PRIMITIVE_NOTE_MATCH
		List<Note> notes = new ArrayList<>();
		BasicNote baseNote;
		BasicNote rootNote = parseRootFromSymbol(symbol);
		List<BasicNote> scale = mapScaleToRootNote(rootNote);
		ChordFormula formula = getChordFormulaBySymbol(symbol.replace(rootNote.name, ""));
		String formulaParts[] = formula.formula.split("-");
		Stack<Character> stack = new Stack<>();
		int scaleSize = scale.size();

		for (String formulaPart : formulaParts) {
			int noteIndex = getIndexFromFormulaPart(formulaPart) - 1;
			int offset = getOffsetFromFormulaPart(formulaPart);
			int relativeOctave = noteIndex / scaleSize;
			boolean isOptional = isOptional(formulaPart);
			BasicNote basicNote = scale.get(noteIndex % scaleSize).getNoteAtDistance(offset);

			Note newNote = new Note(basicNote, relativeOctave, true);
			newNote.setOptional(isOptional);
			notes.add(newNote);
		}

		return notes;
	}

	private int getIndexFromFormulaPart(String part) {
		String indexAsString = part.replaceAll("\\D","");	//remove everything that is not a digit
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

	private BasicNote parseRootFromSymbol(String symbol) {
		String primitiveNote = symbol.substring(0, 1);
		int offset = getOffset(symbol.charAt(1));
		BasicNote rootNote = null;

		for (BasicNote note : BasicNote.values()) {
			if (primitiveNote.equalsIgnoreCase(note.name)) {
				rootNote = note.getNoteAtDistance(offset);
			}
		}
		return rootNote;
	}

	private int getOffset(char noteSuffix) {
		switch (noteSuffix) {
			case 'b':
				return -1;
			case '#':
				return 1;
			default:
				return 0;
		}
	}

	public boolean chordNotesMatch(Collection<Note> expectedNotes, Collection<Note> actualNotes) {
		boolean match = expectedNotes.size() == actualNotes.size();
		if (match) {
			for (Note expected : expectedNotes) {
				match = chordContainsBasicNote(actualNotes, expected);
			}
		}
		return match;
	}

	public boolean chordNotesMatchExactly(Collection<Note> expectedNotes, Collection<Note> actualNotes) {
		boolean match = expectedNotes.size() == actualNotes.size();
		if (match) {
			for (Note expected : expectedNotes) {
				match = chordContainsExactNote(actualNotes, expected);
			}
		}
		return match;
	}

	public boolean chordContainsBasicNote(Collection<Note> chord, Note note) {
		boolean match = false;
		for (Note chordNote : chord) {
			if (chordNote.isRelativeMatch(note)) {
				match = true;
				break;
			}
		}
		return match;
	}

	public boolean chordContainsExactNote(Collection<Note> chord, Note note) {
		boolean match = false;
		for (Note chordNote : chord) {
			if (chordNote.isExactMatch(note)) {
				match = true;
				break;
			}
		}
		return match;
	}

	public List<Note> buildChord(BasicNote... notes) {
		List<Note> chordNotes = new ArrayList<>();
		for (BasicNote note : notes) {
			Note newChordNote = new Note(note, 0, true);
			while (chordContainsExactNote(chordNotes, newChordNote)) {
				newChordNote.setOctave(newChordNote.getOctave() + 1);
			}
			chordNotes.add(newChordNote);
		}
		return chordNotes;
	}
}
