package com.tsunderebug.mazegen;

import com.tsunderebug.mazestuff.algorithms.RecursiveBacktracker;
import com.tsunderebug.mazestuff.mazes.hex.RectangularHexagonalCellMaze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		RectangularHexagonalCellMaze m = new RectangularHexagonalCellMaze(10, 10);
		new RecursiveBacktracker().apply(m);
		ImageIO.write(m.toImage(10, 200, false, Color.cyan), "png", new File("test.png"));
		ImageIO.write(m.toImage(10, 200, true, Color.cyan), "png", new File("testc.png"));

	}

}
