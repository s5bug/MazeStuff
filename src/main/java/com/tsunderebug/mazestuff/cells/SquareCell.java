package com.tsunderebug.mazestuff.cells;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.mazes.SquareCellMaze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SquareCell implements Cell<SquareCell> {

	private final SquareCellMaze m;
	private final int x;
	private final int y;
	private List<SquareCell> connections = new ArrayList<>();

	public SquareCell(SquareCellMaze m, int x, int y) {
		this.m = m;
		this.x = x;
		this.y = y;
	}

	@Override
	public List<SquareCell> neighbors() {
		List<SquareCell> n = new ArrayList<>(Arrays.asList(north(), south(), east(), west()));
		n.removeIf(Objects::isNull);
		return n;
	}

	public SquareCell north() {
		return m.position(x, y - 1);
	}

	public SquareCell south() {
		return m.position(x, y + 1);
	}

	public SquareCell east() {
		return m.position(x + 1, y);
	}

	public SquareCell west() {
		return m.position(x - 1, y);
	}

	@Override
	public List<SquareCell> connections() {
		return connections;
	}

	@Override
	public void connect(SquareCell c) {
		connections.add(c);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
