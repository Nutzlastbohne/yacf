package org.waagh.yacf.app.model;

import java.util.List;

public interface INoteSystem {

//	public List<AbstractNote> defineNotes();
	public UniqueNote getNextLowerNote(UniqueNote note);
	public UniqueNote getNextHigherNote(UniqueNote note);
	public UniqueNote getNoteXStepsAway(UniqueNote baseNote, int steps);
	public int getRelativeNoteIndex(AbstractNote note);
	public int getAbsoluteNoteIndex(AbstractNote note);
	public double calculateFrequency(AbstractNote note);

	public void setStandardPitch(double standardPitch);
	public double getStandardPich();

}
