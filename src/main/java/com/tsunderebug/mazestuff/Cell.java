package com.tsunderebug.mazestuff;

import java.util.List;

public interface Cell<SELF extends Cell<SELF>> {

	List<SELF> neighbors();
	List<SELF> connections();
	default boolean canConnect(SELF c) {
		return neighbors().contains(c);
	}
	void connect(SELF c);

}
