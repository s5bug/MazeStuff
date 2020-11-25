package com.tsunderebug.mazestuff.mazes;

import com.tsunderebug.mazestuff.cells.SquareCell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MaskedRectangularSquareCellMaze extends RectangularSquareCellMaze {

	protected final boolean[][] mask;
	protected int startX = -1;
	protected int startY = -1;

	public MaskedRectangularSquareCellMaze(boolean[][] mask) {
		super(mask[0].length, mask.length);
		this.mask = mask;
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				if(!mask[y][x]) {
					cells[x][y] = null;
				} else {
					if(startX == -1 || startY == -1) {
						startX = x;
						startY = y;
					}
				}
			}
		}
	}

	@Override
	public Set<SquareCell> cells() {
		return super.cells().stream().filter(Objects::nonNull).collect(Collectors.toSet());
	}

	@Override
	public SquareCell startCell() {
		return position(startX, startY);
	}
}
