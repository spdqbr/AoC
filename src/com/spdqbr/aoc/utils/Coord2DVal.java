package com.spdqbr.aoc.utils;

import java.util.Objects;

public class Coord2DVal<T extends Number, U>{


	public T row;
	public T col;
	public U val;
	public boolean deepEquals = false;
	
	public Coord2DVal(T row, T col) {
		this.row = row;
		this.col = col;
	}
	
	public Coord2DVal(T row, T col, U val) {
		this(row, col);
		this.val = val;
	}
	
	public T getRow() {
		return row;
	}
	
	public void setRow(T row) {
		this.row = row;
	}
	
	public T getCol() {
		return col;
	}
	
	public void setCol(T col) {
		this.col = col;
	}
	
	public U getVal() {
		return val;
	}
	
	public void setVal(U val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		if(deepEquals) {
			return "( "+row+", "+col+" : "+val+" )";
		}
		return "( "+row+", "+col+" )"; 
	}
	
	@Override
	public int hashCode() {
		if(deepEquals) return Objects.hash(col, row, val);
		return Objects.hash(col, row);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord2DVal other = (Coord2DVal) obj;
		if(deepEquals) {
			return Objects.equals(col, other.col) && Objects.equals(row, other.row) && Objects.equals(val, other.val);
		}
		return Objects.equals(col, other.col) && Objects.equals(row, other.row);
	}
}
