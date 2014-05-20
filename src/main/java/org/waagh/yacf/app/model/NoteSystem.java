package org.waagh.yacf.app.model;

import org.waagh.yacf.app.controller.ChordUtils;
import org.waagh.yacf.app.model.Notes.BasicNote;
import org.waagh.yacf.app.model.Notes.INote;
import org.waagh.yacf.app.model.Notes.RelativeNote;
import org.waagh.yacf.app.model.chords.ChordFormula;
import org.waagh.yacf.app.model.chords.IChord;
import org.waagh.yacf.app.model.chords.RelativeChord;

import java.util.*;

public class NoteSystem implements INoteSystem {

	List<INote> notes;
	Map<String, List<Integer>> scales;
	List<IChordFormula> chordFormulas;
	List<Integer> baseScale;
	private final ChordUtils chordUtils;

	public NoteSystem() {
		notes = initNotes();
		scales = initScales();
		chordFormulas = initChordFormulas();

		baseScale = scales.get("Major");	// TODO: move into a factory or something...
		chordUtils = new ChordUtils(baseScale);
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
		chordFormulas.add(new ChordFormula("#11", "Misc", "1-5-#11"));;

		return chordFormulas;
	}

	private List<INote> initNotes() {    //TODO Abstract Candidate?
		List<INote> basicNotes = new ArrayList<>();
		List<String> basicNoteNames = Arrays.asList("C", "C#", "D", "D#", "E", "F", "G", "G#", "A", "A#", "B"); // TODO: this stuff has to come from a file, DB or User Input

		//init
		int ordinal = 0;
		for (String noteName : basicNoteNames) {
			INote note = new BasicNote(noteName, ordinal);
			basicNotes.add(note);
			ordinal++;
		}

		//link notes together
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

	@Override public List<Integer> getNormalisedScalePattern(String scaleName) {
		return null;
	}

	@Override public void addScalePattern(String name, List<Integer> scalePattern) {
		scales.put(name, scalePattern);
	}

	public IChord<IRelativeNote> buildRelativeChord(String rootNote, String chordName){
		IChordFormula chordFormula = getChordFormula(chordName);
		return chordUtils.getRelativeNotesFromFormula(chordFormula.getOriginalFormula());
	}

	private IChordFormula getChordFormulaByName(String formulaName) {
		for (IChordFormula chordFormula : chordFormulas) {
			if (chordFormula.getChordName().equalsIgnoreCase(formulaName)) {
				return chordFormula;
			}
		}
		return null;
	}

	@Override public double getStandardPitch() {
		return 0;
	}

	@Override public double getFrequency(INote note) {
		return 0;
	}

	@Override public int getAbsoluteIndex(INote note) {
		return 0;
	}

	@Override public int getRelativeIndex(INote note) {
		return 0;
	}
}
