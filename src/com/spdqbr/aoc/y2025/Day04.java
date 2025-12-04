package com.spdqbr.aoc.y2025;
import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.SparseGrid2D;

public class Day04 extends Solution {
	public static final int day = 4;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day04()).main(year, day, 0);
		(new Day04()).main(year, day, 1);
		(new Day04()).main(year, day, 2);
		(new Day04()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		SparseGrid2D<Integer> grid = new SparseGrid2D<>();
		
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				if(input[row].charAt(col) == '@') {
					grid.set(row, col, 1);
				}
			}
		}
		
		int count = 0;
		
		for(Long row : grid.getRowIndexes()) {
			for(Long col : grid.getColIndexes(row)) {
				if(grid.getNeighborValues(row, col, false).size() < 4) {
					count++;
				}
			}
		}
		return ""+count;
	}
	
	public String part2(String[] input) {
		SparseGrid2D<Integer> grid = new SparseGrid2D<>();
		
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				if(input[row].charAt(col) == '@') {
					grid.set(row, col, 1);
				}
			}
		}
		
		int count = 0;
		
		boolean isAnyRemoved = true;
		while(isAnyRemoved) {
			isAnyRemoved = false;
			for(Long row : grid.getRowIndexes()) {
				for(Long col : grid.getColIndexes(row)) {
					if(grid.isSet(row, col)) {
						if(grid.getNeighborValues(row, col, false).size() < 4) {
							count++;
							isAnyRemoved=true;
							grid.remove(row, col);
						}
					}
				}
			}
		}
		
		return ""+count;
	}
}
