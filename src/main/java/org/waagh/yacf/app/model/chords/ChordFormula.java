package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.IChordFormula;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChordFormula implements IChordFormula {

	private String name;
	private String originalFormula;
	private String type;

	public ChordFormula(String name, String type, int[] relativeNotePositions) {
		this.name = name;
		this.type = type;
	}

	public ChordFormula(String name, String type, String formula) {
		this.name = name;
		this.type = type;
		this.originalFormula = formula;
	}

	@Override public String getChordName() {
		return name;
	}

	@Override public String getOriginalFormula() {
		return originalFormula;
	}

	@Override public String getType() {
		return type;
	}
}
