package com.spdqbr.aoc.y2023;
import static com.spdqbr.aoc.utils.Utils.isDigit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.Pair;
import com.spdqbr.aoc.utils.SparseGrid2D;

public class Day03 extends Solution {
	public static final int day = 3;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day03()).main(year, day, 3);
	}
	
	public SparseGrid2D<Pair<Long,Long>> populateGrid(String[] input){
		SparseGrid2D<Pair<Long,Long>> grid = new SparseGrid2D<>();
		char c;
		long id = 0;
		Pair<Long, Long> p;
		Pair<Long, Long> part = new Pair<>(-1L, -1L);
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++){
				c = input[row].charAt(col);
				if(isDigit(c)){
					long collector = 0;
					int i = 0;
					while(col < input[row].length() && isDigit(input[row].charAt(col))){
						collector *= 10;
						collector += (input[row].charAt(col) - '0');
						i++;
						col++;
					}
					p = new Pair<Long, Long>(id, collector);
					id++;
					while( i > 0) {
						grid.set(row, col-i, p);
						i--;
					}
					if( col < input[row].length() && input[row].charAt(col) != '.') {
						grid.set(row, col, part);
					}
				}else if( c != '.') {
					grid.set(row, col, part);
				}
			}
		}
		return grid;
	}
	
	
	public SparseGrid2D<Pair<Long,Long>> populateGearGrid(String[] input){
		SparseGrid2D<Pair<Long,Long>> grid = new SparseGrid2D<>();
		char c;
		long id = 0;
		Pair<Long, Long> p;
		Pair<Long, Long> part = new Pair<>(-1L, -1L);
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++){
				c = input[row].charAt(col);
				if(isDigit(c)){
					long collector = 0;
					int i = 0;
					while(col < input[row].length() && isDigit(input[row].charAt(col))){
						collector *= 10;
						collector += (input[row].charAt(col) - '0');
						i++;
						col++;
					}
					p = new Pair<Long, Long>(id, collector);
					id++;
					while( i > 0) {
						grid.set(row, col-i, p);
						i--;
					}
					if( col < input[row].length() && input[row].charAt(col) == '*') {
						grid.set(row, col, part);
					}
				}else if( c == '*') {
					grid.set(row, col, part);
				}
			}
		}
		return grid;
	}
	
	public static void printGrid(SparseGrid2D<Pair<Long,Long>> grid) {
		long[] b = grid.getBounds();
		for(long row = b[0]; row < b[1]; row++) {
			for(long col = b[2]; col < b[3]; col++) {
				if(grid.get(row, col) == null) System.out.print(".");
				else if(grid.get(row, col).left == -1) System.out.print('#');
				else System.out.print(grid.get(row, col).left);
			}
			System.out.println();
		}
	}
	
	public String part1(String[] input) {
		SparseGrid2D<Pair<Long,Long>> grid = populateGrid(input);
		List<Coord2D<Long>> coords = grid.getSetCoords();
		Set<Coord2D<Long>> neighbors = new HashSet<>();
		Long sum = 0L;
		for(Coord2D<Long> c : coords) {
			if(grid.get(c).left == -1) neighbors.addAll(grid.getNeighborCoords(c));
		}
		
		Set<Long> added = new HashSet<>();
		for(Coord2D<Long> c : neighbors) {
			if(grid.get(c) != null && grid.get(c).left != -1 && !added.contains(grid.get(c).left)) {
				sum += grid.get(c).right;
				added.add(grid.get(c).left);
			}
		}
		
		return ""+sum;
	}
	
	public String part2(String[] input) {
		SparseGrid2D<Pair<Long,Long>> grid = populateGearGrid(input);
		List<Coord2D<Long>> coords = grid.getSetCoords();
		List<Coord2D<Long>> neighbors;
		Set<Pair<Long,Long>> temp = new HashSet<>();
		
		System.out.println(Arrays.toString(grid.getBounds()));
		Long sum = 0L;
		for(Coord2D<Long> c : coords) {
			if(grid.get(c).left == -1) {
				neighbors = grid.getNeighborCoords(c);
				temp.clear();
				for(Coord2D<Long> n : neighbors) {
					if(grid.get(n).left != -1) {
						temp.add(grid.get(n));
					}
				}
				if(temp.size() == 2) {
					long prod = 1;
					for(Pair<Long, Long> val : temp) {
						prod *= val.right;
					}
					sum += prod;
				}
			}
		}
		
		return ""+sum;
	}
}
