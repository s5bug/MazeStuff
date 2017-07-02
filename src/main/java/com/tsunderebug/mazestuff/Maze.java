package com.tsunderebug.mazestuff;

import java.util.Set;

public interface Maze<C extends Cell<C>> {

	Set<C> cells();
	C startCell();
	default boolean canConnect(C c1, C c2) {
		return c1.canConnect(c2) && c2.canConnect(c1);
	}
	default void connect(C c1, C c2) {
		c1.connect(c2);
		c2.connect(c1);
	}

}
