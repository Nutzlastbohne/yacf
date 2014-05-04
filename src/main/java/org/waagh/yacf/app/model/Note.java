package org.waagh.yacf.app.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Note {

	public class Position {
		int stringIndex;
		int fretIndex;

		public Position(int stringIndex, int fretIndex) {
			this.stringIndex = stringIndex;
			this.fretIndex = fretIndex;
		}
	}

	private static double STANDARD_PITCH_A = 440L;    // 440Hz = a'

	private NoteSystem noteSystem;
	private BasicNote basicNote;
	private boolean isOptional;
	private boolean isRelativeOctave;
	private double frequency;
	private double baseFrequency = STANDARD_PITCH_A;
	private int octave;
	private Position notePosition;

	public Note(BasicNote basicNote, int octave, boolean isRelativeOctave) {
		this.basicNote = basicNote;
		this.octave = octave;
		this.isRelativeOctave = isRelativeOctave;
		this.isOptional = false;
		this.frequency = calculateNoteFrequency();
		this.noteSystem = new NoteSystem();
	}

	public Note(BasicNote basicNote, int octave, int stringIndex, int fretIndex) {
		this(basicNote, octave, false);
		this.notePosition = new Position(stringIndex, fretIndex);
	}

	public Note(BasicNote basicNote, int octave) {
		this(basicNote, octave, false);
	}

	public Note(BasicNote basicNote) {
		this(basicNote, -1);
	}

	public boolean isRelativeMatch(Note otherNote) {
		boolean isRelativeMatch = false;

		if (this.basicNote.equals(otherNote.basicNote)) {
			isRelativeMatch = true;
		}

		return isRelativeMatch;
	}

	public boolean isExactMatch(Note otherNote) {
		boolean isExactMatch = false;

		if (this.isRelativeMatch(otherNote) && this.octave == otherNote.octave) {
			isExactMatch = true;
		}

		return isExactMatch;
	}

	public int getAbsoluteHalfTone() {
		return octave * BasicNote.values().length + this.basicNote.ordinal();
	}

	public int getRelativeDistance(Note otherNote) {
		int distance = Math.abs(this.basicNote.ordinal() - otherNote.basicNote.ordinal());
		return distance;
	}

	public int getTotalDistance(Note otherNote) {
		int distance = otherNote.getAbsoluteHalfTone() - this.getAbsoluteHalfTone();
		return distance;
	}

	public boolean isHigherThan(Note otherNote) {
		return getTotalDistance(otherNote) > 0;
	}

	public boolean isLowerThan(Note otherNote) {
		return getTotalDistance(otherNote) < 0;
	}

	private double calculateNoteFrequency() {
		if (this.basicNote == BasicNote.A && octave == 4) {
			return baseFrequency;
		}

		Note standardPitchA1 = new Note(BasicNote.A, 4);
		int distance = standardPitchA1.getTotalDistance(this);
		double exponent = distance / (double) 12;

		return baseFrequency * Math.pow(2, exponent);
	}

	public BasicNote getBasicNote() {
		return basicNote;
	}

	public int getOctave() {
		return octave;
	}

	public double getFrequency() {
		return frequency;
	}

	public boolean isOptional() {
		return isOptional;
	}

	public boolean isRelativeOctave() {
		return isRelativeOctave;
	}

	public void setRelativeOctave(boolean isRelativeOctave) {
		this.isRelativeOctave = isRelativeOctave;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}

	public Position getNotePosition() {
		return notePosition;
	}

	public void setNotePosition(Position notePosition) {
		this.notePosition = notePosition;
	}

	public void setNotePosition(int stringIndex, int fretIndex) {
		this.notePosition = new Position(stringIndex, fretIndex);
	}

	@Override public String toString() {
		String noteAsString = getClassicNoteSymbol();

		if (isOptional) {
			noteAsString = "(" + noteAsString + ")";
		}

		return noteAsString;
	}

	private String getOctaveSymbol() {
		StringBuilder sb = new StringBuilder();
		String octaveSymbol = "'";
		String subOctaveSymbol = ",";
		String symbol = "";
		if (octave > 0) {
			symbol = StringUtils.repeat(octaveSymbol, octave);
		} else if (octave < 0) {
			symbol = StringUtils.repeat(subOctaveSymbol, Math.abs(octave));
		}
		return symbol;
	}

	public String getClassicNoteSymbol() {
		String noteName = basicNote.name;
		if (octave > 0) {
			noteName = noteName + getOctaveSymbol();
		} else if (octave < 0) {
			noteName = getOctaveSymbol() + noteName;
		}
		return noteName;
	}

	public Note getPreviousNote() {
		return getNoteAtDistance(-1);
	}

	public Note getNextNote() {
		return getNoteAtDistance(1);
	}

	public Note getNoteAtDistance(int halfToneSteps) {
		int nextRelativeIndex;
		int octaveSteps;
		List<BasicNote> baseScale = noteSystem.getBaseScale();
		int currentIndex = baseScale.indexOf(this.getBasicNote());
		int nextIndex = currentIndex + halfToneSteps;

		if (nextIndex < 0) {
			octaveSteps = (int) Math.floor(nextIndex / (double) baseScale.size());
			nextRelativeIndex = baseScale.size() + (nextIndex % baseScale.size());
		} else {
			octaveSteps = nextIndex / baseScale.size();
			nextRelativeIndex = nextIndex % baseScale.size();
		}

		BasicNote nextBasicNote = baseScale.get(nextRelativeIndex);
		int octave = this.getOctave() + octaveSteps;

		return new Note(nextBasicNote, octave);
	}
}
