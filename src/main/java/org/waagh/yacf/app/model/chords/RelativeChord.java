package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.IRelativeNote;
import org.waagh.yacf.app.model.Notes.RelativeNote;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RelativeChord implements IChord<IRelativeNote> {

	Map<IRelativeNote, Boolean> chordNotes;

	public RelativeChord() {
		chordNotes = new ConcurrentHashMap<>();
	}

	public RelativeChord(Map<IRelativeNote, Boolean> chordNotes) {
		this.chordNotes = chordNotes;
	}

	public RelativeChord(List<Integer> chordNotes) {
		this();
		for (Integer chordNote : chordNotes) {
			this.chordNotes.put(new RelativeNote(chordNote), false);
		}
	}

	@Override public void setNoteState(int notePosition, boolean isOptional) {
		chordNotes.entrySet().stream().filter(chordNote -> chordNote.getKey().getRelativePosition() == notePosition).forEach(chordNote -> {
			chordNote.setValue(isOptional);
		});
	}

	@Override public Collection<IRelativeNote> getNotes() {
		return chordNotes.keySet();
	}

	@Override public Collection<IRelativeNote> addNotes(Collection<IRelativeNote> newNotes) {
		for (IRelativeNote newNote : newNotes) {
			chordNotes.put(newNote, false);
		}

		return null;
	}

	@Override public Collection<IRelativeNote> addNote(IRelativeNote newNote, boolean isOptional) {
		chordNotes.put(newNote, isOptional);
		return null;
	}

	@Override public Collection<IRelativeNote> setNotes(Collection<IRelativeNote> newNotes) {

		return null;
	}

	@Override public Collection<IRelativeNote> getMandatoryNotes() {
		return chordNotes.keySet().parallelStream().filter(notePos -> !chordNotes.get(notePos)).collect(Collectors.toList());
	}

	@Override public Collection<IRelativeNote> getOptionalNotes() {
		return chordNotes.keySet().parallelStream().filter(chordNotes::get).collect(Collectors.toList());
	}

	@Override public String toString() {
		String notesAsString = chordNotes.entrySet().parallelStream().sorted(
				Comparator.comparingInt(k -> k.getKey().getRelativePosition())).map(
					k -> {
						if (k.getValue()) {
							return "(" + k.getKey().getRelativePosition() + ")";
						} else {
							return String.valueOf(k.getKey().getRelativePosition());
						}
					}
				).collect(Collectors.joining(" - "));

		return String.format("RelativeChord {%s}", notesAsString);
	}
}
