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

	String name;
	String altName;

	BasicNote(String name, String altName) {
		this.name = name;
		this.altName = altName;
	}

	BasicNote(String name) {
		this.name = name;
		this.altName = name;
	}
}
