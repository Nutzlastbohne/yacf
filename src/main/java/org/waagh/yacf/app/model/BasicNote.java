package org.waagh.yacf.app.model;

public enum BasicNote {
	C("C"),
	C_SHARP("C#", "Db"),
	D("D"),
	D_SHARP("D#", "Eb"),
	E("E"),
	F("F", "E#"),
	F_SHARP("F#", "Gb"),
	G("G"),
	G_SHARP("G#", "Ab"),
	A("A"),
	A_SHARP("A#", "Bb"),
	B("B", "Cb");

	public String name;
	public String altName;

	BasicNote(String name, String altName) {
		this.name = name;
		this.altName = altName;
	}

	BasicNote(String name) {
		this.name = name;
		this.altName = name;
	}

	private BasicNote getNoteAt(int index) {
		index = sanitizeIndex(this.ordinal() + index);
		return BasicNote.values()[index];
	}

	BasicNote getNextNote(BasicNote startpoint) {
		return getNoteAt(startpoint.ordinal() + 1);
	}

	BasicNote getPreviousNote(BasicNote startpoint) {
		return getNoteAt(startpoint.ordinal() - 1);
	}

	public BasicNote getNoteAtDistance(int distance) {
		int index = sanitizeIndex(this.ordinal() + distance);
		return BasicNote.values()[index];
	}

	private static int sanitizeIndex(int index) {
		int sanitizedIndex = index % BasicNote.values().length;

		if (index < 0) {
			sanitizedIndex = BasicNote.values().length + sanitizedIndex;
		}

		return sanitizedIndex;
	}
}
