package com.tsunderebug.mazestuff;

import java.util.Set;

public interface Maze<C extends Cell<C>> {

	/**
	 * @return A set of all the cells in the maze
	 */
	Set<C> cells();

	/**
	 * @return The cell where an algorithm should start
	 */
	C startCell();

	/**
	 * @param c1 One cell
	 * @param c2 Another cell
	 * @return Whether these cells can be connected
	 */
	default boolean canConnect(C c1, C c2) {
		return c1.canConnect(c2) && c2.canConnect(c1);
	}

	/**
	 * Connects two cells
	 *
	 * @param c1 One cell
	 * @param c2 Another cell
	 */
	default void connect(C c1, C c2) {
		c1.connect(c2);
		c2.connect(c1);
	}

}
