package com.tsunderebug.mazestuff;

import java.util.List;

/**
 * @param <SELF> The cell that it is. For example, <code>class SquareCell implements Cell&lt;SquareCell&gt;</code>
 */
public interface Cell<SELF extends Cell<SELF>> {

	/**
	 * @return A list of neighboring cells
	 */
	List<SELF> neighbors();

	/**
	 * @return The list of cells this cell is connected to
	 */
	List<SELF> connections();

	/**
	 * @param c Another cell
	 * @return Whether this cell can be connected to another
	 */
	default boolean canConnect(SELF c) {
		return neighbors().contains(c);
	}

	/**
	 * @param c The cell to add to the connection list
	 */
	void connect(SELF c);

}
