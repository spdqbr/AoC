package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.SparseGrid2D;

public class Day11 extends Solution {
	public static final int day = 11;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day11()).main(year, day, 3);
	}
	
	public static long expansionFactor = 2;
	
	public String part1(String[] input) {
		SparseGrid2D<Integer> universe = new SparseGrid2D<>();
		List<Integer> populatedRows = new ArrayList<>();
		List<Integer> populatedCols = new ArrayList<>();
		int galaxyNumber = 0;
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				if(input[row].charAt(col) == '#') {
					universe.set(row, col, galaxyNumber++);
					if(!populatedRows.contains(row)) populatedRows.add(row);
					if(!populatedCols.contains(col)) populatedCols.add(col);
				}
			}
		}
		
		List<Coord2D<Long>> galaxies = new ArrayList<>();
		int rowOffset = 0;
		long[] bounds = universe.getBounds();
		for(long row = bounds[0]; row <= bounds[1]; row++) {
			int colOffset = 0;
			if(!populatedRows.contains((int)row))
				rowOffset += (expansionFactor -1);
			for(long col = bounds[2]; col <= bounds[3]; col++) {
				if(!populatedCols.contains((int)col))
					colOffset += (expansionFactor -1);
				Integer g = universe.get(row, col);
				if(g != null) {
					galaxies.add(new Coord2D<Long>(row+rowOffset, col+colOffset));
				}
			}
		}
		
		long sum = 0;
		for(int i = 0; i < galaxies.size()-1; i++) {
			for(int j = i+1; j < galaxies.size(); j++) {
				sum += Math.abs(galaxies.get(i).row - galaxies.get(j).row) + Math.abs(galaxies.get(i).col - galaxies.get(j).col);
			}
		}
		
		return ""+sum;
	}
	
	public String part2(String[] input) {
		expansionFactor=1000000;
		return part1(input);
	}
}
