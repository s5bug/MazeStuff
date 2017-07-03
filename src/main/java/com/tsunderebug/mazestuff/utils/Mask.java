package com.tsunderebug.mazestuff.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mask {

	private Mask() {}

	public static boolean[][] fromImage(BufferedImage i) {
		boolean[][] p = new boolean[i.getWidth()][i.getHeight()];
		for(int x = 0; x < i.getWidth(); x++) {
			for(int y = 0; y < i.getHeight(); y++) {
				Color c = new Color(i.getRGB(x, y));
				int avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				p[x][y] = avg > 127;
			}
		}
		return p;
	}

	public static boolean[][] invertedFromImage(BufferedImage i) {
		boolean[][] p = new boolean[i.getWidth()][i.getHeight()];
		for(int x = 0; x < i.getWidth(); x++) {
			for(int y = 0; y < i.getHeight(); y++) {
				Color c = new Color(i.getRGB(x, y));
				int avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				p[x][y] = avg < 128;
			}
		}
		return p;
	}

}
