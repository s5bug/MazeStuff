package com.tsunderebug.mazestuff.mazes.tri;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.cells.TriangularCell;
import com.tsunderebug.mazestuff.utils.Dijkstra;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TriangularTriangularCellMaze implements TriangularCellMaze {

	private final int l;
	private final TriangularCell[][] cells;

	public TriangularTriangularCellMaze(int l) {
		this.l = l;
		cells = new TriangularCell[l][];
		for(int r = 0; r < l; r++) {
			cells[r] = new TriangularCell[r * 2 + 1];
			for(int c = 0; c < r * 2 + 1; c++) {
				cells[r][c] = new TriangularCell(this, c + l - r - 1, r);
			}
		}
	}

	@Override
	public TriangularCell position(int x, int y) {
		try {
			return cells[y][x + y - l + 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public Set<TriangularCell> cells() {
		return new HashSet<>(Arrays.stream(cells).flatMap(Arrays::stream).collect(Collectors.toList()));
	}

	@Override
	public TriangularCell startCell() {
		return cells[0][0];
	}

	public BufferedImage toImage(int linethickness, int cellsize, boolean color, Color bright) {
		int h = (int) (Math.sqrt(3) / 2 * cellsize);

		BufferedImage c = new BufferedImage(l * (cellsize + linethickness) + linethickness, l * (h + linethickness) + linethickness, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) c.getGraphics();
		g.setBackground(Color.white);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(linethickness));
		Map<Cell, Integer> d = Dijkstra.forMaze(this);
		int m = d.values().stream().max(Integer::compareTo).get();
		for(int r = 0; r < l; r++) {
			for(int col = 0; col < 2 * r + 1; col++) {
				int x = (col / 2 + (l - r) / 2);
				int x1 = x * (cellsize + linethickness);
				int x2 = x * (cellsize + linethickness) + cellsize / 2;
				int x3 = (x + 1) * (cellsize + linethickness);
				int y1 = r * (h + linethickness);
				int y2 = (r + 1) * (h + linethickness);
				if(r % 2 == 1) {
					x1 -= cellsize / 2;
					x2 -= cellsize / 2;
					x3 -= cellsize / 2;
				}
				TriangularCell cell = cells[r][col];
				if(cell != null) {
					Polygon p = new Polygon();
					if(cell.isUpwards()) {
						p.addPoint(x1 - linethickness, y2);
						p.addPoint(x2, y1);
						p.addPoint(x3, y2);
					} else {
						x1 += cellsize / 2;
						x2 += cellsize / 2;
						x3 += cellsize / 2;
						p.addPoint(x1, y1);
						p.addPoint(x2, y2);
						p.addPoint(x3, y1);
					}
					Color newc = Color.white;
					if (color) {
						try {
							int l = d.get(cell);
							int newr = (int) (bright.getRed() * ((double) (m - l) / (double) m));
							int newg = (int) (bright.getGreen() * ((double) (m - l) / (double) m));
							int newb = (int) (bright.getBlue() * ((double) (m - l) / (double) m));
							newc = new Color(newr, newg, newb);
						} catch (NullPointerException e) {
							System.out.println("Cell at " + x + ", " + r + " is unreachable!");
							newc = Color.red;
						}
					}
					g.setColor(newc);
					g.fill(p);
					g.setColor(Color.black);
					if (cell.isUpwards()) {
						if (cell.south() == null || !cell.connections().contains(cell.south())) {
							g.drawLine(x1, y2, x3, y2);
						}
						if (cell.northWest() == null || !cell.connections().contains(cell.northWest())) {
							g.drawLine(x1, y2, x2, y1);
						}
						if (cell.northEast() == null || !cell.connections().contains(cell.northEast())) {
							g.drawLine(x2, y1, x3, y2);
						}
					} else {
						if (cell.north() == null || !cell.connections().contains(cell.north())) {
							g.drawLine(x1, y1, x3, y1);
						}
						if (cell.southWest() == null || !cell.connections().contains(cell.southWest())) {
							g.drawLine(x1, y1, x2, y2);
						}
						if (cell.southEast() == null || !cell.connections().contains(cell.southEast())) {
							g.drawLine(x2, y2, x3, y1);
						}
					}
				}
			}
		}
		return c;
	}

}
