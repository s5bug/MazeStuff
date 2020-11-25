package com.tsunderebug.mazestuff.mazes;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.cells.SquareCell;
import com.tsunderebug.mazestuff.utils.Dijkstra;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RectangularSquareCellMaze implements SquareCellMaze {

	protected final int h;
	protected final int w;
	protected SquareCell[][] cells;

	public RectangularSquareCellMaze(int w, int h) {
		this.w = w;
		this.h = h;
		cells = new SquareCell[w][h];
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				cells[x][y] = new SquareCell(this, x, y);
			}
		}
	}

	@Override
	public SquareCell position(int x, int y) {
		if(x >= 0 && y >= 0 && x < w && y < h) {
			return cells[x][y];
		}
		return null;
	}

	@Override
	public Set<SquareCell> cells() {
		return new HashSet<>(Arrays.stream(cells).flatMap(Arrays::stream).collect(Collectors.toList()));
	}

	@Override
	public SquareCell startCell() {
		return cells[0][0];
	}

	public BufferedImage toImage(int linethickness, int cellsize, int padding, boolean color, Color bright) {
		BufferedImage c = new BufferedImage((linethickness + cellsize) * w + linethickness, (linethickness + cellsize) * h + linethickness, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) c.getGraphics();
		g.setBackground(Color.white);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(linethickness));
		Map<SquareCell, Integer> d = Dijkstra.forMaze(this);
		int m = d.values().stream().max(Integer::compareTo).get();
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				SquareCell cell = cells[x][y];
				if(cell != null) {
					int x1 = (linethickness + cellsize) * x;
					int x2 = (linethickness + cellsize) * x + padding;
					int x3 = (linethickness + cellsize) * (x + 1) - padding;
					int x4 = (linethickness + cellsize) * (x + 1);
					int y1 = (linethickness + cellsize) * y;
					int y2 = (linethickness + cellsize) * y + padding;
					int y3 = (linethickness + cellsize) * (y + 1) - padding;
					int y4 = (linethickness + cellsize) * (y + 1);
					Color newc = Color.white;
					if (color) {
						try {
							int l = d.get(cell);
							int newr = (int) (bright.getRed() * ((double) (m - l) / (double) m));
							int newg = (int) (bright.getGreen() * ((double) (m - l) / (double) m));
							int newb = (int) (bright.getBlue() * ((double) (m - l) / (double) m));
							newc = new Color(newr, newg, newb);
						} catch (NullPointerException e) {
							System.out.println("Cell at " + x + ", " + y + " is unreachable!");
							newc = Color.red;
						}
					}
					g.setColor(newc);
					g.fillRect(x2, y2, x3 - x2, y3 - y2);
					g.setColor(Color.black);
					if (cell.north() == null || !cell.connections().contains(cell.north())) {
						g.drawLine(x2, y2, x3, y2);
					} else {
						g.setColor(newc);
						g.fillRect(x2, y1, x3 - x2, y2 - y1);
						g.setColor(Color.black);
						g.drawLine(x2, y1, x2, y2);
						g.drawLine(x3, y1, x3, y2);
					}
					if (cell.south() == null || !cell.connections().contains(cell.south())) {
						g.drawLine(x2, y3, x3, y3);
					} else {
						g.setColor(newc);
						g.fillRect(x2, y3, x3 - x2, y4 - y3);
						g.setColor(Color.black);
						g.drawLine(x2, y3, x2, y4);
						g.drawLine(x3, y3, x3, y4);
					}
					if (cell.east() == null || !cell.connections().contains(cell.east())) {
						g.drawLine(x3, y2, x3, y3);
					} else {
						g.setColor(newc);
						g.fillRect(x3, y2, x4 - x3, y3 - y2);
						g.setColor(Color.black);
						g.drawLine(x3, y2, x4, y2);
						g.drawLine(x3, y3, x4, y3);
					}
					if (cell.west() == null || !cell.connections().contains(cell.west())) {
						g.drawLine(x2, y2, x2, y3);
					} else {
						g.setColor(newc);
						g.fillRect(x1, y2, x2 - x1, y3 - y2);
						g.setColor(Color.black);
						g.drawLine(x1, y2, x2, y2);
						g.drawLine(x1, y3, x2, y3);
					}
				}
			}
		}
		return c;
	}

}
