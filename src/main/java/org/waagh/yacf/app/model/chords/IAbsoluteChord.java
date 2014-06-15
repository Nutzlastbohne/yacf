package org.waagh.yacf.app.model.chords;

import org.waagh.yacf.app.model.Notes.IAbsoluteNote;

public interface IAbsoluteChord extends IChord<IAbsoluteNote> {

	String getAbsolutePositions();

}
