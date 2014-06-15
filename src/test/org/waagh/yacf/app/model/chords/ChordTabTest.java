package org.waagh.yacf.app.model.chords;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.waagh.yacf.app.model.NoteSystem;
import org.waagh.yacf.app.model.Notes.AbsoluteNote;
import org.waagh.yacf.app.model.Notes.IAbsoluteNote;
import org.waagh.yacf.app.model.Notes.NotePosition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ChordTabTest {

	ChordTab chordTab;
	NoteSystem ns;
	IAbsoluteChord dmMaj13Chord;

	@Before
	public void before() {
		ns = new NoteSystem();
		dmMaj13Chord = ns.buildAbsoluteChord(ns.buildAbsoluteNote("D", 3), "m/Maj13");
		chordTab = new ChordTab(dmMaj13Chord);

	}

	private ChordTab getDMaj13ChordTabOne() {
		List<IAbsoluteNote> notes = getAbsoluteNotesDmMaj13();
		ChordTab tab = chordTab = new ChordTab(dmMaj13Chord);
		tab.putNotePosition(notes.get(0), 0, 10);
		tab.putNotePosition(notes.get(1), 1, 12);
		tab.putNotePosition(notes.get(2), 2, 11);
		tab.putNotePosition(notes.get(3), 3, 10);
		tab.putNotePosition(notes.get(4), 4, 12);
		tab.putNotePosition(notes.get(5), 5, 10);
		return tab;
	}

	private List<IAbsoluteNote> getAbsoluteNotesDmMaj13(){
		List<IAbsoluteNote> noteList = new ArrayList<>();
		noteList.add(ns.buildAbsoluteNote("D", 3));
		noteList.add(ns.buildAbsoluteNote("A", 4));
		noteList.add(ns.buildAbsoluteNote("C#", 5));
		noteList.add(ns.buildAbsoluteNote("F", 5));
		noteList.add(ns.buildAbsoluteNote("B", 6));
		noteList.add(ns.buildAbsoluteNote("D", 7));
		return noteList;
	}

	private ChordTab getDMaj13ChordTabTwo() {
		ChordTab tab = chordTab = new ChordTab(ns.buildAbsoluteChord("D", "m/Maj13"));
//		tab.putNotePosition(0, 10);
//		tab.putNotePosition(1, 12);
//		tab.putNotePosition(2, 11);
//		tab.putNotePosition(3, 10);
//		tab.putNotePosition(3, 12);
//		tab.putNotePosition(3, 10);
		return tab;
	}

	@Test
	public void getNotePositions() {
		List<IAbsoluteNote> notes = getAbsoluteNotesDmMaj13();
		chordTab = getDMaj13ChordTabOne();
		NotePosition position = chordTab.getNotePosition(notes.get(0));
		Assert.assertTrue("Unexpected Position", position.string == 0 && position.fret == 10);
	}
}
