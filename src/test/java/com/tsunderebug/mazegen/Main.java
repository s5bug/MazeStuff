package com.tsunderebug.mazegen;

import com.tsunderebug.mazestuff.algorithms.RecursiveBacktracker;
import com.tsunderebug.mazestuff.mazes.RectangularSquareCellMaze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		RectangularSquareCellMaze m = new RectangularSquareCellMaze(21600, 19250);
		new RecursiveBacktracker().apply(m);
		ImageIO.write(m.toImage(1, 1, 1, false, Color.WHITE), "png", new File("youdidthistoyourself.png"));
	}

}
