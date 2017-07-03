package com.tsunderebug.mazestuff.mazes.tri;

import com.tsunderebug.mazestuff.cells.TriangularCell;

public class MaskedRectangularTriangularCellMaze extends RectangularTriangularCellMaze {

	protected int startX = -1;
	protected int startY = -1;

	public MaskedRectangularTriangularCellMaze(boolean[][] mask) {
		super(mask.length / 2, mask[0].length);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (!mask[x][y]) {
					cells[x][y] = null;
				} else {
					if (startX == -1 || startY == -1) {
						startX = x;
						startY = y;
					}
				}
			}
		}
	}

	@Override
	public TriangularCell startCell() {
		return cells[startX][startY];
	}
}
