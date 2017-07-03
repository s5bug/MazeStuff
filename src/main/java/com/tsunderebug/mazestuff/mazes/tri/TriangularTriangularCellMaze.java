package com.tsunderebug.mazestuff.mazes.tri;

import com.tsunderebug.mazestuff.cells.TriangularCell;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TriangularTriangularCellMaze implements TriangularCellMaze {

	private final int l;
	private final TriangularCell[][] cells;

	public TriangularTriangularCellMaze(int l) {
		this.l = l;
		cells = new TriangularCell[l][];
		for(int r = 0; r < l; r++) {
			cells[r] = new TriangularCell[r * 2 - 1];
			for(int c = 0; c < r * 2 - 1; c++) {
				cells[r][c] = new TriangularCell(this, c + l - r, r);
			}
		}
	}

	@Override
	public TriangularCell position(int x, int y) {
		try {
			return cells[y][x + y - l];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public Set<TriangularCell> cells() {
		return new HashSet<>(Arrays.stream(cells).flatMap(Arrays::stream).collect(Collectors.toList()));
	}

	@Override
	public TriangularCell startCell() {
		return cells[0][0];
	}

}
