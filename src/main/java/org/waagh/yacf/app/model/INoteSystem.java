package org.waagh.yacf.app.model;

import java.util.List;

public interface INoteSystem {

	List<INote> mapScaleToRoot(IScale scale, INote root);

	List<Integer> getScalePattern(String scaleName);
	List<Integer> getNormalisedScalePattern(String scaleName);

	void addScalePattern(String name, List<Integer> scalePattern);

	double getStandardPitch();

	double getFrequency(INote note);

	int getAbsoluteIndex(INote note);

	int getRelativeIndex(INote note);

}
