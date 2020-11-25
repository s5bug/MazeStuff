package com.tsunderebug.mazestuff.algorithms;

import com.tsunderebug.mazestuff.Algorithm;
import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.Maze;

import java.util.*;

public class ConnectAll<M extends Maze<C>, C extends Cell<C>> implements Algorithm<M, C> {
    @Override
    public M apply(M in) {
        Deque<C> path = new ArrayDeque<>();
        Set<C> visited = new HashSet<>();
        C current = in.startCell();
        path.push(current);
        visited.add(current);
        while(!path.isEmpty()) {
            current = path.pop();
            visited.add(current);
            ArrayList<C> neighbors = new ArrayList<>(current.neighbors());
            neighbors.forEach(current::connect);
            neighbors.removeAll(visited);
            path.addAll(neighbors);
        }
        return in;
    }
}
