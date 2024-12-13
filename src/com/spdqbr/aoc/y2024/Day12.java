package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.Matrix2D;
import com.spdqbr.aoc.utils.Pair;

public class Day12 extends Solution {
	public static final int day = 12;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day12()).main(year, day, 0);
		(new Day12()).main(year, day, 1);
		(new Day12()).main(year, day, 2);
		(new Day12()).main(year, day, 3);
	}
	
	public static List<Coord2D<Integer>> DIRECTIONS = new ArrayList<>();
	static {
		DIRECTIONS.add(new Coord2D<Integer>(-1, 0));
		DIRECTIONS.add(new Coord2D<Integer>(1, 0));
		DIRECTIONS.add(new Coord2D<Integer>(0, -1));
		DIRECTIONS.add(new Coord2D<Integer>(0, 1));
	}
	
	public String part1(String[] input) {
		int[][] plotMatrix = new int[input.length][input[0].length()];
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				plotMatrix[row][col] = input[row].charAt(col);
			}
		}
		Matrix2D plot = new Matrix2D(plotMatrix);
		
		Set<Set<Coord2D<Integer>>> areas = new HashSet<>();
		Set<Coord2D<Integer>> inARegion = new HashSet<>();
		Set<Coord2D<Integer>> region = new HashSet<>();
		
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				if(!inARegion.contains(new Coord2D<Integer>(row,col))) {
					region = findRegion(plot, row, col);
					areas.add(region);
					inARegion.addAll(region);
				}
			}
		}
		long score = 0;
		for(Set<Coord2D<Integer>> r : areas) {
			score += (findPerimeter(plot, r).size() * r.size());
		}
		
		return ""+score;
	}
	
	//perimeter is a combination of an outermost node and a direction from that node
	public static Set<Pair<Coord2D<Integer>,Coord2D<Integer>>> findPerimeter(Matrix2D plot, Set<Coord2D<Integer>> region) {
		Set<Pair<Coord2D<Integer>,Coord2D<Integer>>> perimeter = new HashSet<>();
		for(Coord2D<Integer> x : region) {
			for(Coord2D<Integer> n : plot.getNeighbors(x, false)){
				if(plot.get(n) != plot.get(x)) {
					int dr = n.row - x.row;
					int dc = n.col - x.col;
					perimeter.add(new Pair<Coord2D<Integer>,Coord2D<Integer>>(x, new Coord2D<Integer>(dr,dc)));
				}
			}
			// readable AF, thanks generics!
			if(x.row == 0) perimeter.add(new Pair<Coord2D<Integer>,Coord2D<Integer>>(x, new Coord2D<Integer>(-1,0)));
			if(x.row == plot.height-1) perimeter.add(new Pair<Coord2D<Integer>,Coord2D<Integer>>(x, new Coord2D<Integer>(1,0)));
			if(x.col == 0) perimeter.add(new Pair<Coord2D<Integer>,Coord2D<Integer>>(x, new Coord2D<Integer>(0,-1)));
			if(x.col == plot.width-1) perimeter.add(new Pair<Coord2D<Integer>,Coord2D<Integer>>(x, new Coord2D<Integer>(0,1)));
		}
		return perimeter;
	}
	
	public Set<Coord2D<Integer>> findRegion(Matrix2D plot, Set<Coord2D<Integer>> visited, int row, int col){
		Set<Coord2D<Integer>> out = new HashSet<>();
		
		Coord2D<Integer> current = new Coord2D<Integer>(row, col);
		visited.add(current);
		out.add(current);
		
		boolean changed = true;
		while(changed) {
			changed = false;
			for(Coord2D<Integer> n : plot.getNeighbors(current, false)) {
				if(plot.get(current) == plot.get(n)) {
					if(!visited.contains(n)) {
						changed |= out.addAll(findRegion(plot, visited, n.row, n.col));
					}
				}
			}
		}
		
		return out;
	}
	
	public Set<Coord2D<Integer>> findRegion(Matrix2D plot, int row, int col){
		Set<Coord2D<Integer>> visited = new HashSet<>();
		return findRegion(plot, visited, row, col);
	}
	
	public int countFences(Matrix2D plot, Set<Coord2D<Integer>> region) {

		int count = 0;
		Set<Pair<Coord2D<Integer>,Coord2D<Integer>>> perimeter = findPerimeter(plot, region);
		Set<Pair<Coord2D<Integer>,Coord2D<Integer>>> visited = new HashSet<>();
		
		for(Pair<Coord2D<Integer>,Coord2D<Integer>> p : perimeter) {
			if(visited.contains(p)) continue;
			
			count++;
			Pair<Coord2D<Integer>,Coord2D<Integer>> check = p;
			visited.add(p);
			List<Coord2D<Integer>> queue = new ArrayList<>();
			queue.add(p.left);
			for(int i = 0; i < queue.size(); i++) {
				Coord2D<Integer> current = queue.get(i);
				for(Coord2D<Integer> direction : DIRECTIONS) {
					int newRow = current.row + direction.row;
					int newCol = current.col + direction.col;
					Pair<Coord2D<Integer>,Coord2D<Integer>> newVal = new Pair<Coord2D<Integer>,Coord2D<Integer>>(new Coord2D<Integer>(newRow, newCol), check.right);
					if(perimeter.contains(newVal)) {
						if(!visited.contains(newVal)) {
							queue.add(newVal.left);
							visited.add(newVal);
						}
					}
				}
			}
		}
		
		return  count;
	}
	
	public String part2(String[] input) {
		int[][] plotMatrix = new int[input.length][input[0].length()];
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				plotMatrix[row][col] = input[row].charAt(col);
			}
		}
		Matrix2D plot = new Matrix2D(plotMatrix);
		
		Set<Set<Coord2D<Integer>>> areas = new HashSet<>();
		Set<Coord2D<Integer>> inARegion = new HashSet<>();
		Set<Coord2D<Integer>> region = new HashSet<>();
		
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				if(!inARegion.contains(new Coord2D<Integer>(row,col))) {
					region = findRegion(plot, row, col);
					areas.add(region);
					inARegion.addAll(region);
				}
			}
		}
		long score = 0;
		for(Set<Coord2D<Integer>> r : areas) {
			score += (countFences(plot, r) * r.size());
		}
		
		return ""+score;
	}
}
