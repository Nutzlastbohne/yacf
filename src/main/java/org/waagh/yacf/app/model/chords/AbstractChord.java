package org.waagh.yacf.app.model.chords;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractChord<T> implements IChord<T> {
	private Map<T, Boolean> chordNotes;
	private String name;

	protected AbstractChord() {
		chordNotes = new HashMap<>();
	}

	public AbstractChord(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override public void setNoteState(T note, boolean isOptional) {
		chordNotes.entrySet().stream().filter(chordNote -> chordNote == note).forEach(chordNote -> {
			chordNote.setValue(isOptional);
		});
	}

	@Override public Map<T, Boolean> getNotes() {
		return chordNotes;
	}

	@Override public void putAll(Collection<T> newNotes) {
		for (T newNote : newNotes) {
			chordNotes.put(newNote, false);
		}
	}

	@Override public void putAll(Map<T, Boolean> notes) {
		chordNotes = notes;
	}

	@Override public void put(T note, boolean isOptional) {
		chordNotes.put(note, isOptional);
	}

	//TODO: Refactor... to many returns
	@Override public boolean chordMatches(IChord<T> otherChord) {
		if (otherChord == null || this.chordNotes.size() != otherChord.getNotes().size()) {
			return false;
		}

		for (Map.Entry<T, Boolean> otherEntry : otherChord.getNotes().entrySet()) {
			boolean keyPresent = chordNotes.containsKey(otherEntry.getKey());
			if (keyPresent) {
				boolean thisValue = chordNotes.get(otherEntry);
				boolean thatValue = otherEntry.getValue();

				if (thisValue == thatValue) {
					continue;
				}
			}
			return false;
		}

		return true;
	}

	@Override public Collection<T> getMandatoryNotes() {
		return chordNotes.keySet().parallelStream().filter(note -> !chordNotes.get(note)).collect(Collectors.toList());
	}

	@Override public Collection<T> getOptionalNotes() {
		return chordNotes.keySet().parallelStream().filter(chordNotes::get).collect(Collectors.toList());
	}

	@Override public String toString() {
		Comparator<Map.Entry<T, Boolean>> byKey = (e1, e2) -> Integer.compare(Integer.parseInt(e1.getKey().toString()), Integer.parseInt(e2.getKey().toString()));

		String notesAsString = chordNotes.entrySet().parallelStream().sorted(byKey).map(
				k -> {
					if (k.getValue()) {
						return "(" + k.getKey().toString() + ")";
					} else {
						return String.valueOf(k.getKey().toString());
					}
				}
		).collect(Collectors.joining(" - "));

		return String.format("RelativeChord {%s}", notesAsString);
	}
}
