package com.spdqbr.aoc.utils;

public class Coord2D implements Comparable<Coord2D>{
	public int row;
	public int col;
	public int val;
	
	public Coord2D(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public Coord2D(int row, int col, int val) {
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
	public int compareTo(Coord2D o) {
		return Integer.compare(val, o.val);
	}
	
	public Coord2D shift(Coord2D vector) {
		return new Coord2D(this.row + vector.row, this.col + vector.col);
	}
}
