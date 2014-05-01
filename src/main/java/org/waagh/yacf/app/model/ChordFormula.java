package org.waagh.yacf.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Source: http://www.smithfowler.org/music/Chord_Formulas.htm
 * Regexp to read from table:
 * Search	- 	(\w+)\t([#\w/-]+)\t([#-\w\(\)]+)
 * Replace	-	\1\(ChordType.Minor, "\2", "\3"\),
 */
public enum ChordFormula {

	UNKNOWN(ChordType.Undefined, "unknown", "-"),
	Major(ChordType.Major, "Maj", "1-3-5"),
	AddedFourth(ChordType.Major, "add4", "1-3-4-5"),
	Sixth(ChordType.Major, "6", "1-3-5-6"),
	SixNine(ChordType.Major, "6/9", "1-3-5-6-9"),
	MajorSeventh(ChordType.Major, "Maj7", "1-3-5-7"),
	MajorNinth(ChordType.Major, "Maj9", "1-3-5-7-9"),
	MajorEleventh(ChordType.Major, "Maj11", "1-3-5-7-(9)-11"),
	MajorThirteenth(ChordType.Major, "Maj13", "1-3-5-7-(9)-(11)-13"),
	MajorSevenSharpEleventh(ChordType.Major, "Maj7#11", "1-3-5-7-#11"),
	MajorFlatFive(ChordType.Major, "-", "1-3-b5"),

	Minor(ChordType.Minor, "m", "1-b3-5"),
	MinorAddedFourth(ChordType.Minor, "m/MajAdd4", "1-b3-4-5"),
	MinorSixth(ChordType.Minor, "m6", "1-b3-5-6"),
	MinorSeventh(ChordType.Minor, "m7", "1-b3-5-b7"),
	MinorAddedNinth(ChordType.Minor, "madd9", "1-b3-5-9"),
	MinorSixAddNine(ChordType.Minor, "m6/9", "1-b3-5-6-9"),
	MinorNinth(ChordType.Minor, "m9", "1-b3-5-b7-9"),
	MinorEleventh(ChordType.Minor, "m11", "1-b3-5-b7-(9)-11"),
	MinorThirteenth(ChordType.Minor, "m13", "1-b3-5-b7-(9)-(11)-13"),
	MinorMajorSeventh(ChordType.Minor, "m/Maj7", "1-b3-5-7"),
	MinorMajorNinth(ChordType.Minor, "m/Maj9", "1-b3-5-7-9"),
	MinorMajorEleventh(ChordType.Minor, "m/Maj11", "1-b3-5-7-(9)-11"),
	MinorMajorThirteenth(ChordType.Minor, "m/Maj13", "1-b3-5-7-(9)-(11)-13"),
	MinorMajorSevenFlatFifth(ChordType.Minor, "m7-5", "1-b3-b5-b7"),

	Seventh(ChordType.Dominant, "7", "1-3-5-b7"),
	Ninth(ChordType.Dominant, "9", "1-3-5-b7-9"),
	Eleventh(ChordType.Dominant, "11", "1-(3)-5-b7-(9)-11"),
	Thirteenth(ChordType.Dominant, "13", "1-3-5-b7-(9)-(11)-13"),
	SevenSharpFive(ChordType.Dominant, "7#5", "1-3-#5-b7"),
	SevenFlatFive(ChordType.Dominant, "7b5", "1-3-b5-b7"),
	SevenFlatNinth(ChordType.Dominant, "7b9", "1-3-5-b7-b9"),
	SevenSharpNinth(ChordType.Dominant, "7#9", "1-3-5-b7-#9"),
	NineSharpFive(ChordType.Dominant, "9#5", "1-3-#5-b7-9"),
	NineFlatFive(ChordType.Dominant, "9b5", "1-3-b5-b7-9"),
	SevenSharpFiveSharpNine(ChordType.Dominant, "7#5#9", "1-3-#5-b7-#9"),
	SevenSharpFiveFlatNine(ChordType.Dominant, "7#5b9", "1-3-#5-b7-b9"),
	SevenFlatFiveSharpNine(ChordType.Dominant, "7b5#9", "1-3-b5-b7-#9"),
	SevenFlatFiveFlatNine(ChordType.Dominant, "7b5b9", "1-3-b5-b7-b9"),
	SevenSharpEleven(ChordType.Dominant, "7#11", "1-3-5-b7-#11"),

	Diminished(ChordType.Symmetrical, "dim(°)", "1-b3-b5"),
	DiminishedSeventh(ChordType.Symmetrical, "dim7(°7)", "1-b3-b5-bb7"),
	Augmented(ChordType.Symmetrical, "aug(+)", "1-3-#5"),

	Fifth(ChordType.Misc, "5", "1-5"),
	FlatFifth(ChordType.Misc, "-5", "1-b5"),
	SuspendedFourth(ChordType.Misc, "sus4", "1-4-5"),
	SuspendedSecond(ChordType.Misc, "sus2", "1-2-5"),
	SharpEleven(ChordType.Misc, "#11", "1-5-#11"),;

	ChordType type;
	String symbol;
	String formula;

	ChordFormula(ChordType type, String symbol, String formula) {
		this.type = type;
		this.symbol = symbol;
		this.formula = formula;
	}

	static List<ChordFormula> getAllWithType(ChordType type) {
		List<ChordFormula> chordFormulas = new ArrayList<>();
		for (ChordFormula formula : values()) {
			if (formula.type == type) {
				chordFormulas.add(formula);
			}
		}

		return chordFormulas;
	}

	static List<ChordFormula> getAll() {
		List<ChordFormula> chordFormulas = new ArrayList<>();
		for (ChordFormula formula : values()) {
			chordFormulas.add(formula);
		}
		return chordFormulas;
	}

	public enum ChordType {
		Major,
		Minor,
		Dominant,
		Symmetrical,
		Misc,
		Undefined;
	}
}
