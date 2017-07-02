package com.tsunderebug.mazegen;

import com.tsunderebug.mazestuff.algorithms.RecursiveBacktracker;
import com.tsunderebug.mazestuff.mazes.RectangularSquareCellMaze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		RectangularSquareCellMaze m = new RectangularSquareCellMaze(10, 10);
		new RecursiveBacktracker().apply(m);
		ImageIO.write(m.toImage(2, 10, 2, true, Color.green), "png", new File("test.png"));
	}

}
