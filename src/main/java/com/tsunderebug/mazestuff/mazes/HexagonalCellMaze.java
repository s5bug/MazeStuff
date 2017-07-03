package com.tsunderebug.mazestuff.mazes;

import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.HexagonalCell;

public interface HexagonalCellMaze extends Maze<HexagonalCell> {

	HexagonalCell position(int x, int y);

}
