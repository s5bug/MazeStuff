package com.tsunderebug.mazestuff.mazes;

import com.tsunderebug.mazestuff.DrawableMaze;
import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.SquareCell;

public interface SquareCellMaze extends DrawableMaze<SquareCell> {

	SquareCell position(int x, int y);

}
