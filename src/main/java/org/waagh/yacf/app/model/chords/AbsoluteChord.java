package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.Notes.IAbsoluteNote;

import java.util.List;

public class AbsoluteChord extends AbstractChord<IAbsoluteNote> {

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
}
