package com.spdqbr.aoc.utils;

public class Coord2D<T extends Number> extends Coord2DVal<T, Integer> implements Comparable<Coord2D<Number>>{
	
	public Coord2D(T row, T col) {
		super(row, col);
	}
	
	public Coord2D(T row, T col, Integer val) {
		super(row, col, val);
	}

	Coord2DVal<T, Integer> coord;
	
	@Override
	public int compareTo(Coord2D<Number> o) {
		return Integer.compare(this.coord.val, o.coord.val);
	}
}