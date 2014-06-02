package org.waagh.yacf.app.model;

import org.waagh.yacf.app.controller.ChordBuilder;
import org.waagh.yacf.app.model.Notes.*;
import org.waagh.yacf.app.model.chords.AbsoluteChord;
import org.waagh.yacf.app.model.chords.ChordFormula;
import org.waagh.yacf.app.model.chords.IChord;
import org.waagh.yacf.app.model.chords.RelativeChord;

import java.util.*;

public class NoteSystem implements INoteSystem {

	List<INote> basicNotes;
	Map<String, List<Integer>> scales;
	List<IChordFormula> chordFormulas;
	List<Integer> baseScale;
	private final ChordBuilder chordBuilder;

	public NoteSystem() {
		basicNotes = initNotes();
		scales = initScales();
		chordFormulas = initChordFormulas();

		baseScale = scales.get("Major");    // TODO: move into a factory or something...
		chordBuilder = new ChordBuilder(baseScale);
	}

	private List<IChordFormula> initChordFormulas() {
		List<IChordFormula> chordFormulas = new ArrayList<>();

		chordFormulas.add(new ChordFormula("unknown", "Undefined", "-"));
		chordFormulas.add(new ChordFormula("Maj", "Major", "1-3-5"));
		chordFormulas.add(new ChordFormula("add4", "Major", "1-3-4-5"));
		chordFormulas.add(new ChordFormula("6", "Major", "1-3-5-6"));
		chordFormulas.add(new ChordFormula("6/9", "Major", "1-3-5-6-9"));
		chordFormulas.add(new ChordFormula("Maj7", "Major", "1-3-5-7"));
		chordFormulas.add(new ChordFormula("Maj9", "Major", "1-3-5-7-9"));
		chordFormulas.add(new ChordFormula("Maj11", "Major", "1-3-5-7-(9)-11"));
		chordFormulas.add(new ChordFormula("Maj13", "Major", "1-3-5-7-(9)-(11)-13"));
		chordFormulas.add(new ChordFormula("Maj7#11", "Major", "1-3-5-7-#11"));
		chordFormulas.add(new ChordFormula("-", "Major", "1-3-b5"));

		chordFormulas.add(new ChordFormula("m", "Minor", "1-b3-5"));
		chordFormulas.add(new ChordFormula("m/MajAdd4", "Minor", "1-b3-4-5"));
		chordFormulas.add(new ChordFormula("m6", "Minor", "1-b3-5-6"));
		chordFormulas.add(new ChordFormula("m7", "Minor", "1-b3-5-b7"));
		chordFormulas.add(new ChordFormula("madd9", "Minor", "1-b3-5-9"));
		chordFormulas.add(new ChordFormula("m6/9", "Minor", "1-b3-5-6-9"));
		chordFormulas.add(new ChordFormula("m9", "Minor", "1-b3-5-b7-9"));
		chordFormulas.add(new ChordFormula("m11", "Minor", "1-b3-5-b7-(9)-11"));
		chordFormulas.add(new ChordFormula("m13", "Minor", "1-b3-5-b7-(9)-(11)-13"));
		chordFormulas.add(new ChordFormula("m/Maj7", "Minor", "1-b3-5-7"));
		chordFormulas.add(new ChordFormula("m/Maj9", "Minor", "1-b3-5-7-9"));
		chordFormulas.add(new ChordFormula("m/Maj11", "Minor", "1-b3-5-7-(9)-11"));
		chordFormulas.add(new ChordFormula("m/Maj13", "Minor", "1-b3-5-7-(9)-(11)-13"));
		chordFormulas.add(new ChordFormula("m7-5", "Minor", "1-b3-b5-b7"));

		chordFormulas.add(new ChordFormula("7", "Dominant", "1-3-5-b7"));
		chordFormulas.add(new ChordFormula("9", "Dominant", "1-3-5-b7-9"));
		chordFormulas.add(new ChordFormula("11", "Dominant", "1-(3)-5-b7-(9)-11"));
		chordFormulas.add(new ChordFormula("13", "Dominant", "1-3-5-b7-(9)-(11)-13"));
		chordFormulas.add(new ChordFormula("7#5", "Dominant", "1-3-#5-b7"));
		chordFormulas.add(new ChordFormula("7b5", "Dominant", "1-3-b5-b7"));
		chordFormulas.add(new ChordFormula("7b9", "Dominant", "1-3-5-b7-b9"));
		chordFormulas.add(new ChordFormula("7#9", "Dominant", "1-3-5-b7-#9"));
		chordFormulas.add(new ChordFormula("9#5", "Dominant", "1-3-#5-b7-9"));
		chordFormulas.add(new ChordFormula("9b5", "Dominant", "1-3-b5-b7-9"));
		chordFormulas.add(new ChordFormula("7#5#9", "Dominant", "1-3-#5-b7-#9"));
		chordFormulas.add(new ChordFormula("7#5b9", "Dominant", "1-3-#5-b7-b9"));
		chordFormulas.add(new ChordFormula("7b5#9", "Dominant", "1-3-b5-b7-#9"));
		chordFormulas.add(new ChordFormula("7b5b9", "Dominant", "1-3-b5-b7-b9"));
		chordFormulas.add(new ChordFormula("7#11", "Dominant", "1-3-5-b7-#11"));

		chordFormulas.add(new ChordFormula("dim(°)", "Symmetrical", "1-b3-b5"));
		chordFormulas.add(new ChordFormula("dim7(°7)", "Symmetrical", "1-b3-b5-bb7"));
		chordFormulas.add(new ChordFormula("aug(+)", "Symmetrical", "1-3-#5"));

		chordFormulas.add(new ChordFormula("5", "Misc", "1-5"));
		chordFormulas.add(new ChordFormula("-5", "Misc", "1-b5"));
		chordFormulas.add(new ChordFormula("sus4", "Misc", "1-4-5"));
		chordFormulas.add(new ChordFormula("sus2", "Misc", "1-2-5"));
		chordFormulas.add(new ChordFormula("#11", "Misc", "1-5-#11"));
		;

		return chordFormulas;
	}

	private List<INote> initNotes() {    //TODO Abstract Candidate?
		List<INote> basicNotes = new ArrayList<>();
		List<String> basicNoteNames = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"); // TODO: this stuff has to come from a file, DB or User Input

		//init
		int ordinal = 0;
		for (String noteName : basicNoteNames) {
			INote note = new BasicNote(noteName, ordinal);
			basicNotes.add(note);
			ordinal++;
		}

		//link basicNotes together
		for (INote basicNote : basicNotes) {
			int previous = basicNotes.indexOf(basicNote) - 1;
			int next = basicNotes.indexOf(basicNote) + 1;

			if (previous < 0) {
				previous = basicNotes.size() - 1;
			}
			if (next >= basicNotes.size()) {
				next = 0;
			}

			basicNote.setPrevious(basicNotes.get(previous));
			basicNote.setNext(basicNotes.get(next));
		}

		return basicNotes;
	}

	private Map<String, List<Integer>> initScales() {
		Map<String, List<Integer>> scales = new HashMap<>();
		scales.put("Major", Arrays.asList(2, 2, 1, 2, 2, 2, 1));    // TODO: this stuff has to come from a file, DB or User Input

		return scales;
	}

	@Override public List<INote> mapScaleToRoot(IScale scale, INote root) {
		return null;
	}

	@Override public List<Integer> getScalePattern(String scaleName) {
		return scales.get(scaleName);
	}

	public IChordFormula getChordFormula(String chordName) {
		IChordFormula chordFormula = null;

		for (IChordFormula formula : chordFormulas) {
			if (formula.getChordName().equalsIgnoreCase(chordName)) {
				chordFormula = formula;
				break;
			}
		}

		return chordFormula;
	}

	@Override public List<Integer> getNormalizedScalePattern(String scaleName) {
		return null;
	}

	@Override public void addScalePattern(String name, List<Integer> scalePattern) {
		scales.put(name, scalePattern);
	}

	public IChord<IRelativeNote> buildRelativeChord(String chordName) {
		IChordFormula chordFormula = getChordFormula(chordName);
		IChord<IRelativeNote> relativeChord = new RelativeChord();
		relativeChord.setName(chordName);
		relativeChord.putAll(chordBuilder.getRelativeNotesFromFormula(chordFormula.getOriginalFormula()));
		return relativeChord;
	}

	public IChord<IAbsoluteNote> buildAbsoluteChord(IAbsoluteNote rootNote, String chordName) {
		IChord<IRelativeNote> relativeChord = buildRelativeChord(chordName);
		IChord<IAbsoluteNote> absoluteChord = new AbsoluteChord();

		for (Map.Entry<IRelativeNote, Boolean> relativeNoteEntry : relativeChord.getNotes().entrySet()) {
			INote baseNote = rootNote.getNoteXStepsAway(relativeNoteEntry.getKey().getPosition());
			int octave = relativeNoteEntry.getKey().getPosition() / basicNotes.size() + rootNote.getOctave();

			IAbsoluteNote absoluteNote = new AbsoluteNote(baseNote, octave);
			absoluteChord.put(absoluteNote, relativeNoteEntry.getValue());
		}

		return absoluteChord;
	}

	private IChordFormula getChordFormulaByName(String formulaName) {
		for (IChordFormula chordFormula : chordFormulas) {
			if (chordFormula.getChordName().equalsIgnoreCase(formulaName)) {
				return chordFormula;
			}
		}
		return null;
	}

	public List<INote> getBasicNotes() {
		return basicNotes;
	}

	public INote getBasicNoteByName(String name) {
		INote returnNote = null;

		for (INote basicNote : basicNotes) {
			if (basicNote.getName().equalsIgnoreCase(name)) {
				returnNote = basicNote;
			}
		}

		return returnNote;
	}

	@Override public double getStandardPitch() {
		return 0;
	}

	@Override public double getFrequency(INote note) {
		return 0;
	}

	@Override public int getAbsoluteIndex(IAbsoluteNote note) {
		return note.getPosition() + note.getOctave()*basicNotes.size();
	}

	@Override public int getRelativeIndex(INote note) {
		return 0;
	}
}
