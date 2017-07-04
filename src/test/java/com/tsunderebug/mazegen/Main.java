package com.tsunderebug.mazegen;

import com.tsunderebug.mazestuff.algorithms.RecursiveBacktracker;
import com.tsunderebug.mazestuff.mazes.MaskedRectangularSquareCellMaze;
import com.tsunderebug.mazestuff.mazes.RectangularSquareCellMaze;
import com.tsunderebug.mazestuff.mazes.hex.RectangularHexagonalCellMaze;
import com.tsunderebug.mazestuff.mazes.polar.PolarMaze;
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
		PolarMaze m = new PolarMaze(5);
		new RecursiveBacktracker().apply(m);
		ImageIO.write(m.toImage(10, 200, false, Color.cyan), "png", new File("test.png"));
		ImageIO.write(m.toImage(10, 200, true, Color.green), "png", new File("testc.png"));
	}

}
