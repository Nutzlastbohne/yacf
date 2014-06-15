package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.Notes.IAbsoluteNote;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbsoluteChord extends AbstractChord<IAbsoluteNote> implements IAbsoluteChord {

	IAbsoluteNote rootNote;

	public AbsoluteChord() {

	}

	public AbsoluteChord(String name, List<IAbsoluteNote> absoluteNotes) {
		super(name);
		super.putAll(absoluteNotes);
		rootNote = absoluteNotes.get(0);
	}

	public IAbsoluteNote getRootNote() {
		return rootNote;
	}

	public void setRootNote(IAbsoluteNote rootNote) {
		this.rootNote = rootNote;
	}

	@Override public String toString() {
		Comparator<Map.Entry<IAbsoluteNote, Boolean>> byKey = (e1, e2) -> Integer.compare(e1.getKey().getPosition(), e2.getKey().getPosition());

		String notesAsString = chordNotes.entrySet().parallelStream().sorted(byKey).map(
				k -> {
					if (k.getValue()) {
						return "(" + k.getKey().getBasicNote() + ")";
					} else {
						return String.valueOf(k.getKey().getBasicNote());
					}
				}
		).collect(Collectors.joining(" - "));

		return String.format("IChord {%s}", notesAsString);
	}

	public String toString2() {
		String notesAsString = chordNotes.entrySet().parallelStream().map(
				k -> {
					if (k.getValue()) {
						return "(" + k.getKey().getBasicNote() + ")";
					} else {
						return String.valueOf(k.getKey().getBasicNote());
					}
				}
		).collect(Collectors.joining(" - "));

		return String.format("IChord {%s}", notesAsString);
	}

	@Override public String getAbsolutePositions() {
		String notesAsString = chordNotes.entrySet().parallelStream().map(
				k -> {
					if (k.getValue()) {
						return "(" + k.getKey().getPosition() + ")";
					} else {
						return String.valueOf(k.getKey().getPosition());
					}
				}
		).collect(Collectors.joining(" - "));

		return String.format("IChord {%s}", notesAsString);
	}
}
