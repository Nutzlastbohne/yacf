package org.waagh.yacf.app.model;

import org.waagh.yacf.app.model.Notes.IAbsoluteNote;
import org.waagh.yacf.app.model.Notes.INote;

import java.util.List;

public interface INoteSystem {

	List<INote> mapScaleToRoot(IScale scale, INote root);

	List<Integer> getScalePattern(String scaleName);
	List<Integer> getNormalizedScalePattern(String scaleName);

	void addScalePattern(String name, List<Integer> scalePattern);

	double getStandardPitch();

	double getFrequency(INote note);

	int getAbsoluteIndex(IAbsoluteNote note);

	int getRelativeIndex(INote note);

}
