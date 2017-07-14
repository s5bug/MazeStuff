package com.tsunderebug.mazestuff.mazes.hex;

import com.tsunderebug.mazestuff.DrawableMaze;
import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.HexagonalCell;

public interface HexagonalCellMaze extends DrawableMaze<HexagonalCell> {

	HexagonalCell position(int x, int y);

}
