package com.tsunderebug.mazestuff.mazes.polar;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.Maze;
import com.tsunderebug.mazestuff.cells.PolarCell;
import com.tsunderebug.mazestuff.utils.Dijkstra;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PolarMaze implements Maze<PolarCell> {

	private final int r;
	protected PolarCell[][] cells;

	public PolarMaze(int r) {
		this.r = r;
		cells = new PolarCell[r][];
		cells[0] = new PolarCell[] {new PolarCell(this, 0, 0)};
		for(int s = 1; s < r; s++) {
			int mt = mt(s);
			cells[s] = new PolarCell[mt];
			for(int t = 0; t < mt; t++) {
				cells[s][t] = new PolarCell(this, s, t);
			}
		}
	}

	public strictfp int mt(int r) {
		double f = (double) r;
		if(r > 1) {
			f += 1;
		}
		double log2 = Math.log(f) / Math.log(2);
		double back = Math.pow(2, Math.floor(log2));
		return 6 * (int) Math.ceil(back);
	}

	@Override
	public Set<PolarCell> cells() {
		return new HashSet<>(Arrays.stream(cells).flatMap(Arrays::stream).collect(Collectors.toList()));
	}

	@Override
	public PolarCell startCell() {
		return cells[0][0];
	}

	public PolarCell position(int r, int t) {
		if(r >= this.r) {
			return null;
		}
		int mt = mt(r);
		if(r == 0) {
			return cells[0][0];
		} else {
			return cells[r][((t % mt) + mt) % mt];
		}
	}

	public BufferedImage toImage(int linethickness, int cellsize, boolean color, Color bright) {
		// Create image
		BufferedImage img = new BufferedImage(2 * r * (linethickness + cellsize) - cellsize, 2 * r * (linethickness + cellsize) - cellsize, BufferedImage.TYPE_INT_RGB);
		// Get graphics
		Graphics2D g = (Graphics2D) img.getGraphics();

		// Make it white
		g.setColor(Color.white);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());

		// Set thickness
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(linethickness));

		// Get Dijkstra info
		Map<Cell, Integer> d = Dijkstra.forMaze(this);
		int m = d.values().stream().max(Integer::compareTo).get();

		// Draw from outwards
		for(int s = r - 1; s > 0; s--) {
			int outr = (linethickness + cellsize) * (s + 1) - (linethickness + cellsize) / 2;
			int inr = (linethickness + cellsize) * s - (linethickness + cellsize) / 2;
			int outs = (img.getWidth() / 2) - outr;
			int ins = (img.getWidth() / 2) - inr;

			// The maximum cell num for this ring
			int mt = mt(s);
			for(int t = 0; t < mt; t++) {
				// Get the cell
				PolarCell c = position(s, t);
				if(c != null) {
					int leftang = t * 360 / mt;
					int rightang = (t + 1) * 360 / mt;

					System.out.println("t: " + t + "\tmt: " + mt + "\tla: " + leftang + "\tra: " + rightang);

					// Top left
					int x1 = (int) (Math.cos(Math.toRadians(leftang)) * outr) + img.getWidth() / 2;
					int y1 = (int) (-Math.sin(Math.toRadians(leftang)) * outr) + img.getHeight() / 2;

					// Top right
					int x2 = (int) (Math.cos(Math.toRadians(rightang)) * outr) + img.getWidth() / 2;
					int y2 = (int) (-Math.sin(Math.toRadians(rightang)) * outr) + img.getHeight() / 2;

					// Bottom left
					int x3 = (int) (Math.cos(Math.toRadians(leftang)) * inr) + img.getWidth() / 2;
					int y3 = (int) (-Math.sin(Math.toRadians(leftang)) * inr) + img.getHeight() / 2;

					// Bottom right
					int x4 = (int) (Math.cos(Math.toRadians(rightang)) * inr) + img.getWidth() / 2;
					int y4 = (int) (-Math.sin(Math.toRadians(rightang)) * inr) + img.getHeight() / 2;

					// Color
					Color newc = Color.white;
					if (color) {
						try {
							int l = d.get(c);
							int newr = (int) (bright.getRed() * ((double) (m - l) / (double) m));
							int newg = (int) (bright.getGreen() * ((double) (m - l) / (double) m));
							int newb = (int) (bright.getBlue() * ((double) (m - l) / (double) m));
							newc = new Color(newr, newg, newb);
						} catch (NullPointerException e) {
							System.out.println("Cell at " + s + ", " + t + " is unreachable!");
							newc = Color.red;
						}
					}
					g.setColor(newc);
					g.fillArc(outs + linethickness, outs + linethickness, (outr - linethickness) * 2, (outr - linethickness) * 2, leftang, rightang - leftang);
					g.setColor(Color.black);

					if(c.ccw() == null || !c.connections().contains(c.ccw())) {
						g.drawLine(x1, y1, x3, y3);
					}
					if(c.cw() == null || !c.connections().contains(c.cw())) {
						g.drawLine(x2, y2, x4, y4);
					}
					if(c.inwards() == null || !c.connections().contains(c.inwards())) {
						g.drawArc(ins, ins, inr * 2, inr * 2, leftang, rightang - leftang);
					}
					if(mt(s + 1) == mt(s)) {
						if(c.outwards() == null || !c.connections().contains(c.outwards())) {
							g.drawArc(outs, outs, outr * 2, outr * 2, leftang, rightang - leftang);
						}
					} else {
						if(c.outwards() == null || !c.connections().contains(c.outwards())) {
							g.drawArc(outs, outs, outr * 2, outr * 2, leftang, (rightang - leftang) / 2);
						}
						if(c.secondOutwards() == null || !c.connections().contains(c.secondOutwards())) {
							g.drawArc(outs, outs, outr * 2, outr * 2, leftang + (rightang - leftang) / 2, (rightang - leftang) / 2);
						}
					}
				}
			}
		}
		if(color) {
			g.setColor(bright);
			g.fillArc(img.getWidth() / 2 - cellsize / 2, img.getHeight() / 2 - cellsize / 2, cellsize, cellsize, 0, 360);
		}

		return img;
	}

}
