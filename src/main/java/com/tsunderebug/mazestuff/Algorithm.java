package com.tsunderebug.mazestuff;

public interface Algorithm<M extends Maze<C>, C extends Cell<C>> {

	/**
	 * Mutates a maze in a certain way
	 *
	 * @param in The input maze
	 * @return The output maze
	 */
	M apply(M in);

}
