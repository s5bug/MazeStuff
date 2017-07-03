package com.tsunderebug.mazestuff.cells;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.mazes.hex.HexagonalCellMaze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HexagonalCell implements Cell<HexagonalCell> {

	private final HexagonalCellMaze m;
	private final int x;
	private final int y;
	private final boolean higher;
	private final List<HexagonalCell> connections = new ArrayList<>();

	public HexagonalCell(HexagonalCellMaze m, int x, int y) {
		this(m, x, y, x % 2 == 0);
	}

	public HexagonalCell(HexagonalCellMaze m, int x, int y, boolean higher) {
		this.m = m;
		this.x = x;
		this.y = y;
		this.higher = higher;
	}

	@Override
	public List<HexagonalCell> neighbors() {
		List<HexagonalCell> n = new ArrayList<>(Arrays.asList(north(), south(), northEast(), northWest(), southEast(), southWest()));
		n.removeIf(Objects::isNull);
		return n;
	}

	public HexagonalCell north() {
		return m.position(x, y - 1);
	}

	public HexagonalCell northWest() {
		if(higher) {
			return m.position(x - 1, y - 1);
		} else {
			return m.position(x - 1, y);
		}
	}

	public HexagonalCell southWest() {
		if(higher) {
			return m.position(x - 1, y);
		} else {
			return m.position( x - 1, y + 1);
		}
	}

	public HexagonalCell south() {
		return m.position(x, y + 1);
	}

	public HexagonalCell southEast() {
		if(higher) {
			return m.position(x + 1, y);
		} else {
			return m.position(x + 1, y + 1);
		}
	}

	public HexagonalCell northEast() {
		if(higher) {
			return m.position(x + 1, y - 1);
		} else {
			return m.position(x + 1, y);
		}
	}

	@Override
	public List<HexagonalCell> connections() {
		return connections;
	}

	@Override
	public void connect(HexagonalCell c) {
		connections.add(c);
	}

	public boolean isHigher() {
		return higher;
	}
}
