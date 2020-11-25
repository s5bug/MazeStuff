package com.tsunderebug.mazestuff.utils;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.Maze;

import java.util.*;

public class Dijkstra {

	private Dijkstra(){}

	public static <M extends Maze<C>, C extends Cell<C>> Map<C, Integer> forMaze(M maze) {
		Map<C, Integer> m = new HashMap<>();
		Deque<AbstractMap.SimpleImmutableEntry<C, Integer>> todo = new ArrayDeque<>();

		todo.offer(new AbstractMap.SimpleImmutableEntry<>(maze.startCell(), 0));

		while(!todo.isEmpty()) {
			AbstractMap.SimpleImmutableEntry<C, Integer> current = todo.poll();
			C curCell = current.getKey();
			Integer curDist = current.getValue();

			m.put(curCell, curDist);

			ArrayList<C> connections = new ArrayList<>(curCell.connections());
			for(C neighbor : connections) {
				if(m.containsKey(neighbor)) {
					if(m.get(neighbor) > curDist + 1) {
						todo.offer(new AbstractMap.SimpleImmutableEntry<>(neighbor, curDist + 1));
					}
				} else {
					todo.offer(new AbstractMap.SimpleImmutableEntry<>(neighbor, curDist + 1));
				}
			}
		}

		return m;
	}

}
