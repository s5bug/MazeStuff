package com.tsunderebug.mazestuff.cells;

import com.tsunderebug.mazestuff.Cell;
import com.tsunderebug.mazestuff.mazes.polar.PolarMaze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PolarCell implements Cell<PolarCell> {

	protected PolarMaze m;
	protected int r;
	protected int t;
	protected List<PolarCell> connections = new ArrayList<>();

	public PolarCell(PolarMaze m, int r, int t) {
		this.m = m;
		this.r = r;
		this.t = t;
	}

	@Override
	public List<PolarCell> neighbors() {
		if(t == 0) {
			List<PolarCell> l = new ArrayList<>();
			for(int i = 0; i < 6; i++) {
				l.add(m.position(1, i));
			}
			return l;
		} else {
			List<PolarCell> n = new ArrayList<>(Arrays.asList(ccw(), cw(), inwards(), outwards(), secondOutwards()));
			n.removeIf(Objects::isNull);
			return n;
		}
	}

	public PolarCell ccw() {
		return m.position(r, t - 1);
	}

	public PolarCell cw() {
		return m.position(r, t + 1);
	}

	public PolarCell inwards() {
		if(m.mt(r - 1) < m.mt(r)) {
			System.out.println("I am (" + r + ", " + t + "), inwards is (" + (r - 1) + ", " + (t / 2) +")");
			return m.position(r - 1, t / 2);
		} else {
			return m.position(r - 1, t);
		}
	}

	public PolarCell outwards() {
		if(m.mt(r + 1) > m.mt(r)) {
			return m.position(r + 1, t * 2);
		} else {
			return m.position(r + 1, t);
		}
	}

	public PolarCell secondOutwards() {
		if(m.mt(r + 1) > m.mt(r)) {
			return m.position(r + 1, t * 2 + 1);
		} else {
			return null;
		}
	}

	@Override
	public List<PolarCell> connections() {
		return connections;
	}

	@Override
	public void connect(PolarCell c) {
		connections.add(c);
	}

	public int getRadius() {
		return r;
	}

	public int getT() {
		return t;
	}

}
