package com.spdqbr.aoc.utils;

public class Coord2D<T extends Number> extends Coord2DVal<T, Integer> implements Comparable<Coord2D<Number>>{
	Coord2DVal<T, Integer> coord;
	
	public Coord2D(T row, T col) {
		super(row, col);
	}
	
	public Coord2D(T row, T col, Integer val) {
		super(row, col, val);
	}

	@Override
	public int compareTo(Coord2D<Number> o) {
		return Integer.compare(this.coord.val, o.coord.val);
	}
}