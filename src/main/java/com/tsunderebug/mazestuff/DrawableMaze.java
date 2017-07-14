package com.tsunderebug.mazestuff;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface DrawableMaze<C extends Cell<C>> extends Maze<C> {

	BufferedImage toImage(int linethickness, int cellsize, int padding, boolean color, Color bright);
	default BufferedImage toImage(int linethickness, int cellsize, boolean color, Color bright) {
		return toImage(linethickness, cellsize, 0, color, bright);
	}

}
