package org.waagh.yacf.app.model;

import org.apache.commons.lang.StringUtils;

public class Note {
	private static double STANDARD_PITCH_A = 440L;    // 440Hz = a'

	private BasicNote basicNote;
	private boolean isOptional;
	private boolean isRelativeOctave;
	private int octave;
	private double frequency;
	private double baseFrequency = STANDARD_PITCH_A;

	public Note(BasicNote basicNote, int octave, boolean isRelativeOctave) {
		this.basicNote = basicNote;
		this.octave = octave;
		this.isRelativeOctave = isRelativeOctave;
		this.isOptional = false;
		this.frequency = calculateNoteFrequency();
	}

	public Note(BasicNote basicNote, int octave) {
		this(basicNote, octave, false);
	}

	public Note(BasicNote basicNote) {
		this(basicNote, -1);
	}

	public boolean isRelativeMatch(Note otherNote) {
		boolean isSimpleMatch = false;

		if (this.basicNote.equals(otherNote.basicNote)) {
			isSimpleMatch = true;
		}

		return isSimpleMatch;
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
}
