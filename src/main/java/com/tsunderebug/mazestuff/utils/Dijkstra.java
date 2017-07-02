package com.tsunderebug.mazestuff.utils;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.Maze;

import java.util.*;

public class Dijkstra {

	private Dijkstra(){}

	public static Map<Cell, Integer> forMaze(Maze maze) {
		Map<Cell, Integer> m = new HashMap<>();
		int distance = 0;
		Deque<Cell> path = new ArrayDeque<>();
		Set<Cell> visited = new HashSet<>();
		Cell current = maze.startCell();
		path.push(current);
		visited.add(current);
		m.put(current, distance);
		while(!path.isEmpty()) {
			if(visited.containsAll(current.neighbors())) {
				current = path.pop();
				distance--;
			} else {
				ArrayList<Cell> n = new ArrayList<>(current.connections());
				n.removeAll(visited);
				Collections.shuffle(n);
				Cell newCell = n.get(0);
				distance++;
				m.put(newCell, distance);
				path.push(newCell);
				visited.add(newCell);
				current = newCell;
			}
		}
		return m;
	}

}
