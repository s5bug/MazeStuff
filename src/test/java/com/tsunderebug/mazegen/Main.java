package com.tsunderebug.mazegen;

import com.tsunderebug.mazestuff.algorithms.RecursiveBacktracker;
import com.tsunderebug.mazestuff.mazes.MaskedRectangularSquareCellMaze;
import com.tsunderebug.mazestuff.mazes.RectangularSquareCellMaze;
import com.tsunderebug.mazestuff.utils.Mask;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		RectangularSquareCellMaze m = new MaskedRectangularSquareCellMaze(Mask.invertedFromImage(ImageIO.read(new File("tyler.jpg"))));
		new RecursiveBacktracker().apply(m);
		ImageIO.write(m.toImage(1, 5, 1, false, Color.cyan), "png", new File("test.png"));
		ImageIO.write(m.toImage(1, 5, 1, true, Color.cyan), "png", new File("testc.png"));

	}

}
