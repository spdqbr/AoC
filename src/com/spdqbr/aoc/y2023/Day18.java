package com.spdqbr.aoc.y2023;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.SparseGrid2D;

public class Day18 extends Solution {
	public static final int day = 18;
	public static final int year = 2023;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day18()).main(year, day, 0);
//		(new Day18()).main(year, day, 1);
//		(new Day18()).main(year, day, 2);
//		(new Day18()).main(year, day, 3);
	}
	
	public static Coord2D<Integer> RIGHT = new Coord2D<Integer>(0, 1);
	public static Coord2D<Integer> LEFT = new Coord2D<Integer>(0, -1);
	public static Coord2D<Integer> UP = new Coord2D<Integer>(-1, 0);
	public static Coord2D<Integer> DOWN = new Coord2D<Integer>(1, 0);
	
	public String part1(String[] input) {
		SparseGrid2D<String> grid = new SparseGrid2D<>();
		String[] instruction;
		Coord2D<Integer> vector, current;
		current = new Coord2D<Integer>(0,0);
		for(String line : input) {
			instruction = line.split(" ");
			switch(instruction[0].charAt(0)) {
			case 'R': vector = RIGHT; break;
			case 'L': vector = LEFT; break;
			case 'U': vector = UP; break;
			case 'D': vector = DOWN; break;
			default: vector = new Coord2D<Integer>(0,0);
			}
			
			for(int i = 0; i < Integer.parseInt(instruction[1]); i++) {
				grid.putInt(current, instruction[2]);
				current = add(current, vector);
			}
		}
		
//		grid.printGrid();
		
		long[] bounds = grid.getBounds();
		long startRow = bounds[0]+1;
		long startCol = 0;
		for(long col = bounds[2]; col <= bounds[3]; col++) {
			if(grid.get(startRow, col) != null){
				startCol = col+1;
				break;
			}
		}
		
		floodFill(grid, startRow, startCol);
		
		int count = 0;
		for(long row = bounds[0]; row <= bounds[1]; row++) {
			for(long col = bounds[2]; col <= bounds[3]; col++) {
				if(grid.get(row, col) != null) count++;
			}
		}
		
//		System.out.println();
//		grid.printGrid();
		return ""+count;
	}
	
	public void floodFill(SparseGrid2D<String> grid, long startRow, long startCol) {
		List<Coord2D<Long>> n = grid.getNeighborCoords(startRow, startCol, true);
		Set<Coord2D<Long>> nn = new HashSet<>();
		for(Coord2D<Long> c : n) {
			if(grid.get(c) == null) {
				nn.add(c);
			}
		}
		Coord2D<Long> current;
		while(! nn.isEmpty()) {
			current = nn.iterator().next();
			nn.remove(current);
			grid.set(current, "");
			n = grid.getNeighborCoords(current, true);
			for(Coord2D<Long> c : n) {
				if(grid.get(c) == null) {
					nn.add(c);
				}
			}
		}
	}
	
	public Coord2D<Integer> add(Coord2D<Integer> a, Coord2D<Integer> b){
		return new Coord2D<Integer>(a.row+b.row, a.col+b.col);
	}
	
	public String part2(String[] input) {
		SparseGrid2D<String> grid = new SparseGrid2D<>();
		String instruction;
		Coord2D<Integer> vector, current;
		current = new Coord2D<Integer>(0,0);
		for(String line : input) {
			instruction = line.split(" ")[2];
			switch(instruction.charAt(instruction.length()-2)) {
			case '0': vector = RIGHT; break;
			case '2': vector = LEFT; break;
			case '3': vector = UP; break;
			case '1': vector = DOWN; break;
			default: vector = new Coord2D<Integer>(0,0);
			}
			
			for(int i = 0; i < Integer.parseInt(instruction.substring(2,instruction.length()-3),16); i++) {
				grid.putInt(current, "#");
				current = add(current, vector);
			}
		}
		
//		grid.printGrid();
		
		long[] bounds = grid.getBounds();
		long startRow = bounds[0]+1;
		long startCol = 0;
		for(long col = bounds[2]; col <= bounds[3]; col++) {
			if(grid.get(startRow, col) != null){
				startCol = col+1;
				break;
			}
		}
		
		floodFill(grid, startRow, startCol);
		
		int count = 0;
		for(long row = bounds[0]; row <= bounds[1]; row++) {
			for(long col = bounds[2]; col <= bounds[3]; col++) {
				if(grid.get(row, col) != null) count++;
			}
		}
		
		System.out.println();
		grid.printGrid();
		return ""+count;
	}
}
