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
			int mt = 6 * ((s + 1) / 2);
			cells[s] = new PolarCell[mt];
			for(int t = 0; t < mt; t++) {
				cells[s][t] = new PolarCell(this, s, t);
			}
		}
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
		int mt = 6 * ((r + 1) / 2);
		if(r == 0) {
			return cells[0][0];
		} else {
			while (t < 0) {
				t += mt;
			}
			return cells[r][t % mt];
		}
	}

	public BufferedImage toImage(int linethickness, int cellsize, boolean color, Color bright) {
		BufferedImage b = new BufferedImage(r * (cellsize + linethickness) * 2, r * (cellsize + linethickness) * 2, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) b.getGraphics();
		g.setBackground(Color.white);
		g.fillRect(0, 0, b.getWidth(), b.getHeight());
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(linethickness));
		Map<Cell, Integer> d = Dijkstra.forMaze(this);
		int m = d.values().stream().max(Integer::compareTo).get();
		for(int s = r - 1; s > 0; s--) {
			int mt = 6 * ((r + 1) / 2);
			for(int t = 0; t < mt; t++) {
				PolarCell p = position(s, t);
				if(p != null) {
					int c = (r - (s + 1)) * (cellsize + linethickness);
					int l = 2 * (s + 1) * (cellsize + linethickness);
					Color newc = Color.white;
					if (color) {
						try {
							int pl = d.get(p);
							int newr = (int) (bright.getRed() * ((double) (m - pl) / (double) m));
							int newg = (int) (bright.getGreen() * ((double) (m - pl) / (double) m));
							int newb = (int) (bright.getBlue() * ((double) (m - pl) / (double) m));
							newc = new Color(newr, newg, newb);
						} catch (NullPointerException e) {
							System.out.println("Cell at " + s + ", " + t + " is unreachable!");
							newc = Color.red;
						}
					}
					g.setColor(newc);
					g.fillArc(c, c, l, l, t * 360 / mt, (t + 1) * 360 / mt);
					g.setColor(Color.black);
					if(p.ccw() == null || !p.connections().contains(p.ccw())) {
						g.drawLine((int) (Math.cos(Math.toRadians(t * 360.0d / mt)) * s * (cellsize + linethickness) + b.getWidth() / 2), (int) (Math.sin(Math.toRadians(t * 360.0d / mt)) * s * (cellsize + linethickness) + b.getHeight() / 2), (int) (Math.cos(Math.toRadians(t * 360.0d / mt)) * (s + 1) * (cellsize + linethickness) + b.getWidth() / 2), (int) (Math.sin(Math.toRadians(t * 360.0d / mt)) * (s + 1) * (cellsize + linethickness) + b.getHeight() / 2));
					}
					if(p.cw() == null || !p.connections().contains(p.ccw())) {
						g.drawLine((int) (Math.cos(Math.toRadians((t + 1) * 360.0d / mt)) * s * (cellsize + linethickness) + b.getWidth() / 2), (int) (Math.sin(Math.toRadians((t + 1) * 360.0d / mt)) * s * (cellsize + linethickness) + b.getHeight() / 2), (int) (Math.cos(Math.toRadians((t + 1) * 360.0d / mt)) * (s + 1) * (cellsize + linethickness) + b.getWidth() / 2), (int) (Math.sin(Math.toRadians((t + 1) * 360.0d / mt)) * (s + 1) * (cellsize + linethickness) + b.getHeight() / 2));
					}
				}
			}
		}
		PolarCell cc = position(0, 0);
		Color newc = Color.white;
		if (color) {
			try {
				int pl = d.get(cc);
				int newr = (int) (bright.getRed() * ((double) (m - pl) / (double) m));
				int newg = (int) (bright.getGreen() * ((double) (m - pl) / (double) m));
				int newb = (int) (bright.getBlue() * ((double) (m - pl) / (double) m));
				newc = new Color(newr, newg, newb);
			} catch (NullPointerException e) {
				System.out.println("Cell at 0, 0 is unreachable!");
				newc = Color.red;
			}
		}
		g.setColor(newc);
		g.fillArc((r - 1) * (cellsize + linethickness), (r - 1) * (cellsize + linethickness), 2 * (cellsize + linethickness), 2 * (cellsize + linethickness), 0, 360);
		g.setColor(Color.black);
		g.drawArc(0, 0, b.getWidth(), b.getHeight(), 0, 360);
		return b;
	}

}
