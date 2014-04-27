package org.waagh.yacf.app.model;

public class Note {
	public BasicNote basicNote;
	public int frequency;
	public int octave;
	public double baseFrequency = 440L;        // 440Hz = a'

	public Note(BasicNote basicNote, int octave) {
		this.basicNote = basicNote;
		this.octave = octave;
	}

	public Note(BasicNote basicNote) {
		this(basicNote, -1);
	}

	public boolean simpleMatch(Note otherNote) {
		boolean isSimpleMatch = false;

		if (this.basicNote.equals(otherNote.basicNote)) {
			isSimpleMatch = true;
		}

		return isSimpleMatch;
	}

	public boolean exactMatch(Note otherNote) {
		boolean isExactMatch = false;

		if (this.simpleMatch(otherNote) && this.octave == otherNote.octave) {
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
		int distance = this.getAbsoluteHalfTone() - otherNote.getAbsoluteHalfTone();
		return distance;
	}

	public double getNoteFrequency() {
		Note standardPitchA1 = new Note(BasicNote.A, 4);
		int distance = getTotalDistance(standardPitchA1);
		double exponent = distance / (double) 12;

		return baseFrequency * Math.pow(2, exponent);
	}

	@Override public String toString() {
		return "Note{" +
			   "basicNote=" + basicNote +
			   ", octave=" + octave +
			   ", frequency=" + frequency +
			   '}';
	}
}
