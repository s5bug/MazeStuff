package com.tsunderebug.mazestuff.mazes.tri;

import com.tsunderebug.mazestuff.DrawableMaze;
import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.TriangularCell;

public interface TriangularCellMaze extends DrawableMaze<TriangularCell> {

	TriangularCell position(int x, int y);

}
