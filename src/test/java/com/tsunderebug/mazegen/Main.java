package com.tsunderebug.mazegen;

import com.tsunderebug.mazestuff.algorithms.RecursiveBacktracker;
import com.tsunderebug.mazestuff.mazes.MaskedRectangularSquareCellMaze;
import com.tsunderebug.mazestuff.mazes.RectangularSquareCellMaze;
import com.tsunderebug.mazestuff.mazes.hex.RectangularHexagonalCellMaze;
import com.tsunderebug.mazestuff.mazes.tri.MaskedRectangularTriangularCellMaze;
import com.tsunderebug.mazestuff.mazes.tri.RectangularTriangularCellMaze;
import com.tsunderebug.mazestuff.mazes.tri.TriangularTriangularCellMaze;
import com.tsunderebug.mazestuff.utils.Mask;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		TriangularTriangularCellMaze m = new TriangularTriangularCellMaze(333);
		new RecursiveBacktracker().apply(m);
		ImageIO.write(m.toImage(2, 10, false, Color.cyan), "png", new File("test.png"));
		ImageIO.write(m.toImage(2, 10, true, Color.green), "png", new File("testc.png"));
	}

}
