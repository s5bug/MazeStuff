package com.tsunderebug.mazestuff.cells;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.mazes.tri.TriangularCellMaze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TriangularCell implements Cell<TriangularCell> {

	private final TriangularCellMaze m;
	private final int x;
	private final int y;
	private final boolean upwards;
	private final List<TriangularCell> connections = new ArrayList<>();

	public TriangularCell(TriangularCellMaze m, int x, int y) {
		this(m, x, y, (x + y) % 2 == 0);
	}

	public TriangularCell(TriangularCellMaze m, int x, int y, boolean upwards) {
		this.m = m;
		this.x = x;
		this.y = y;
		this.upwards = upwards;
	}

	public TriangularCell north() {
		if (upwards) {
			return null;
		} else {
			return m.position(x, y - 1);
		}
	}

	public TriangularCell northWest() {
		if(upwards) {
			return m.position(x - 1, y);
		} else {
			return null;
		}
	}

	public TriangularCell southWest() {
		if(upwards) {
			return null;
		} else {
			return m.position( x - 1, y);
		}
	}

	public TriangularCell south() {
		if(upwards) {
			return m.position(x, y + 1);
		} else {
			return null;
		}
	}

	public TriangularCell southEast() {
		if(upwards) {
			return null;
		} else {
			return m.position(x + 1, y);
		}
	}

	public TriangularCell northEast() {
		if (upwards) {
			return m.position(x + 1, y);
		} else {
			return null;
		}
	}

	@Override
	public List<TriangularCell> neighbors() {
		List<TriangularCell> n = new ArrayList<>(Arrays.asList(north(), south(), northEast(), northWest(), southEast(), southWest()));
		n.removeIf(Objects::isNull);
		return n;
	}

	@Override
	public List<TriangularCell> connections() {
		return connections;
	}

	@Override
	public void connect(TriangularCell c) {
		connections.add(c);
	}

	public boolean isUpwards() {
		return upwards;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
