package com.spdqbr.aoc.utils;

public class Coord2D<T extends Number> implements Comparable<Coord2D<Number>>{
	public T row;
	public T col;
	public int val;
	
	public Coord2D(T row, T col) {
		this.row = row;
		this.col = col;
	}
	
	public Coord2D(T row, T col, int val) {
		this(row, col);
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "( "+row+", "+col+" )"; 
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coord2D) {
			Coord2D other = (Coord2D)obj;
			return this.row == other.row && this.col == other.col;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public int compareTo(Coord2D<Number> o) {
		return Integer.compare(this.val, o.val);
	}
}