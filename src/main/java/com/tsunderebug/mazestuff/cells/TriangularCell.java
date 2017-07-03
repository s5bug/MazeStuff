package com.tsunderebug.mazestuff.cells;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.mazes.tri.TriangularCellMaze;

import java.util.List;

public class TriangularCell implements Cell<TriangularCell> {

	public TriangularCell(TriangularCellMaze m, int x, int y) {
		this(m, x, y, x + y % 2 == 0);
	}

	public TriangularCell(TriangularCellMaze m, int x, int y, boolean upwards) {

	}

	@Override
	public List<TriangularCell> neighbors() {
		return null;
	}

	@Override
	public List<TriangularCell> connections() {
		return null;
	}

	@Override
	public void connect(TriangularCell c) {

	}

}
