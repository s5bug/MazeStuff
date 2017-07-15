package com.tsunderebug.mazestuff.algorithms;

import com.tsunderebug.mazestuff.Algorithm;
import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.TriangularCell;

import java.util.*;

public class RecursiveBacktracker<M extends Maze<C>, C extends Cell<C>> implements Algorithm<M, C> {

	@Override
	public M apply(M in) {
		Deque<C> path = new ArrayDeque<>();
		Set<C> visited = new HashSet<>();
		C current = in.startCell();
		path.push(current);
		visited.add(current);
		while(!path.isEmpty()) {
			if(visited.containsAll(current.neighbors())) {
				current = path.pop();
			} else {
				ArrayList<C> n = new ArrayList<>(current.neighbors());
				n.removeAll(visited);
				Collections.shuffle(n);
				C newCell = n.get(0);
				path.push(newCell);
				visited.add(newCell);
				in.connect(current, newCell);
				current = newCell;
			}
		}
		return in;
	}

}
