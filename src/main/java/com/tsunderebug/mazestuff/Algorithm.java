package com.tsunderebug.mazestuff;

public interface Algorithm<M extends Maze<C>, C extends Cell<C>> {

	M apply(M in);

}
