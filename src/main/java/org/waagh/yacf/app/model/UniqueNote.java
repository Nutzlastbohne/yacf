package org.waagh.yacf.app.model;

public class UniqueNote extends AbstractNote {

	public int octave;

	public UniqueNote(String name, String altName, int octave) {
		this.name = name;
		this.altName = altName;
		this.octave = octave;
	}

	public UniqueNote(String name, int octave) {
		this(name, name, octave);
	}

	public UniqueNote(String name) {
		this(name, 0);
	}

}
