package com.tsunderebug.mazestuff.algorithms;

import com.tsunderebug.mazestuff.Algorithm;
import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.TriangularCell;

import java.util.*;

public class RecursiveBacktracker implements Algorithm {

	@Override
	public Maze apply(Maze in) {
		Deque<Cell> path = new ArrayDeque<>();
		Set<Cell> visited = new HashSet<>();
		Cell current = in.startCell();
		path.push(current);
		visited.add(current);
		while(!path.isEmpty()) {
			if(visited.containsAll(current.neighbors())) {
				current = path.pop();
			} else {
				ArrayList<Cell> n = new ArrayList<>(current.neighbors());
				n.removeAll(visited);
				Collections.shuffle(n);
				Cell newCell = n.get(0);
				path.push(newCell);
				visited.add(newCell);
				in.connect(current, newCell);
				current = newCell;
			}
		}
		return in;
	}

}
