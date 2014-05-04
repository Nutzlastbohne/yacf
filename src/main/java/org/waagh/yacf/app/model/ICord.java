package org.waagh.yacf.app.model;

import java.util.List;

public interface ICord {

	/**
	 * The tuning of the String
	 *
	 * @return tuning of the string
	 */
	public Note getTuning();

	/**
	 * Sets the open tuning of the String
	 *
	 * @param note tuning of the string
	 */
	public void resetTuning(Note note);

	/**
	 * @param position
	 * @return
	 */
	public Note getNoteAt(int position);

	/**
	 * Returns all occurrences of that note, ignoring the octave
	 *
	 * @param note find this note
	 * @return all positions or < 0 if not present
	 */
	public List<Integer> getNotePositions(BasicNote note);

	/**
	 * Returns the position of that exact note, taking the octave into account
	 *
	 * @param note
	 * @return note positions or < 0 if not present
	 */
	public int getNotePosition(Note note);

}
