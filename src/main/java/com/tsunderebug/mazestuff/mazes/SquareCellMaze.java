package com.tsunderebug.mazestuff.mazes;

import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.SquareCell;

public interface SquareCellMaze extends Maze<SquareCell> {

	SquareCell position(int x, int y);

}
