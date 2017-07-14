package com.tsunderebug.mazestuff.mazes.hex;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.cells.HexagonalCell;
import com.tsunderebug.mazestuff.utils.Dijkstra;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RectangularHexagonalCellMaze implements HexagonalCellMaze {

	private int w;
	private int h;
	private HexagonalCell[][] cells;

	public RectangularHexagonalCellMaze(int w, int h) {
		this.w = w;
		this.h = h;
		cells = new HexagonalCell[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				cells[x][y] = new HexagonalCell(this, x, y);
			}
		}
	}

	@Override
	public HexagonalCell position(int x, int y) {
		if (x >= 0 && y >= 0 && x < w && y < h) {
			return cells[x][y];
		}
		return null;
	}

	@Override
	public Set<HexagonalCell> cells() {
		return new HashSet<>(Arrays.stream(cells).flatMap(Arrays::stream).collect(Collectors.toList()));
	}

	@Override
	public HexagonalCell startCell() {
		return cells[0][0];
	}

	public BufferedImage toImage(int linethickness, int cellsize, int unused, boolean color, Color bright) {
		double a = cellsize / 2;
		double b = (cellsize * Math.sqrt(3) / 2);
		double width = cellsize * 2;
		double height = b * 2;

		BufferedImage c = new BufferedImage((int) ((linethickness + 3 * a) * w + linethickness + a), (int) ((linethickness + height) * h + linethickness + b), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) c.getGraphics();
		g.setBackground(Color.white);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(linethickness));
		Map<Cell, Integer> d = Dijkstra.forMaze(this);
		int m = d.values().stream().max(Integer::compareTo).get();
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				double x1 = (linethickness + 3 * a) * x;
				double x2 = x1 + a;
				double x3 = x1 + width - a;
				double x4 = x1 + width;
				double y1 = (linethickness + height) * y;
				double y2 = y1 + b;
				double y3 = y2 + b;
				HexagonalCell cell = cells[x][y];
				if(cell != null) {
					if(!cell.isHigher()) {
						y1 += b;
						y2 += b;
						y3 += b;
					}
					Polygon p = new Polygon();
					p.addPoint((int) x1 - linethickness, (int) y2);
					p.addPoint((int) x2 - linethickness, (int) y1 - linethickness);
					p.addPoint((int) x3 + linethickness, (int) y1 - linethickness);
					p.addPoint((int) x4 + linethickness, (int) y2);
					p.addPoint((int) x3 + linethickness, (int) y3 + linethickness);
					p.addPoint((int) x2 - linethickness, (int) y3 + linethickness);
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
					g.fill(p);
					g.setColor(Color.black);
					if(cell.north() == null || !cell.connections().contains(cell.north())) {
						g.drawLine((int) x2, (int) y1, (int) x3, (int) y1);
					}
					if(cell.northWest() == null || !cell.connections().contains(cell.northWest())) {
						g.drawLine((int) x1, (int) y2, (int) x2, (int) y1);
					}
					if(cell.southWest() == null || !cell.connections().contains(cell.southWest())) {
						g.drawLine((int) x1, (int) y2, (int) x2, (int) y3);
					}
					if(cell.south() == null || !cell.connections().contains(cell.south())) {
						g.drawLine((int) x2, (int) y3, (int) x3, (int) y3);
					}
					if(cell.southEast() == null || !cell.connections().contains(cell.southEast())) {
						g.drawLine((int) x3, (int) y3, (int) x4, (int) y2);
					}
					if(cell.northEast() == null || !cell.connections().contains(cell.northEast())) {
						g.drawLine((int) x3, (int) y1, (int) x4, (int) y2);
					}
				}
			}
		}
		return c;
	}

}
