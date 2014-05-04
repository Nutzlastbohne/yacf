package org.waagh.yacf.app.model;

public enum Scale implements IScale {

	Major("Maj", 2, 2, 1, 2, 2, 2, 1);

	String alternateName;
	int pattern[];

	Scale(String altName, int... scalePattern) {
		this.alternateName = altName;
		this.pattern = scalePattern;
	}

}
