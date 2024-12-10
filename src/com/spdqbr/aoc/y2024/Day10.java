package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.Matrix2D;

public class Day10 extends Solution {
	public static final int day = 10;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day10()).main(year, day, 0);
		(new Day10()).main(year, day, 1);
		(new Day10()).main(year, day, 2);
		(new Day10()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		int[][] inputMap = new int[input.length][input[0].length()];
		List<Coord2D<Integer>> starts = new ArrayList<>();
		List<Coord2D<Integer>> ends = new ArrayList<>();
		for(int i = 0 ; i < input.length; i++) {
			for(int j = 0; j < input[i].length(); j++) {
				char c = input[i].charAt(j);
				int val = c == '.'? 1000 : c - '0';
				inputMap[i][j] = val;
				if(val == 0) starts.add(new Coord2D<Integer>(i, j, val));
				if(val == 9) ends.add(new Coord2D<Integer>(i, j, val));
			}
		}
		
		Matrix2D map = new Matrix2D(inputMap);
		
		long sum = 0;
		for(Coord2D<Integer> start : starts) {
			int score = 0;
			for(Coord2D<Integer> end : ends) {
				if(isConnected(map, start, end) > 0) {
					score++;
				}
			}
			sum += score;
		}
		
		return ""+sum;
	}
	
	public String part2(String[] input) {
		int[][] inputMap = new int[input.length][input[0].length()];
		List<Coord2D<Integer>> starts = new ArrayList<>();
		List<Coord2D<Integer>> ends = new ArrayList<>();
		for(int i = 0 ; i < input.length; i++) {
			for(int j = 0; j < input[i].length(); j++) {
				char c = input[i].charAt(j);
				int val = c == '.'? 1000 : c - '0';
				inputMap[i][j] = val;
				if(val == 0) starts.add(new Coord2D<Integer>(i, j, val));
				if(val == 9) ends.add(new Coord2D<Integer>(i, j, val));
			}
		}
		
		Matrix2D map = new Matrix2D(inputMap);
		
		long sum = 0;
		for(Coord2D<Integer> start : starts) {
			int score = 0;
			for(Coord2D<Integer> end : ends) {
				score += isConnected(map, start, end);
			}
			sum += score;
		}
		
		return ""+sum;
	}
	
	public int isConnected(Matrix2D map, Coord2D<Integer> start, Coord2D<Integer> end) {
		List<Coord2D<Integer>> temp = map.getNeighbors(start, false);
		List<Coord2D<Integer>> neighbors = new ArrayList<>();
		
		for(Coord2D<Integer> n : temp) {
			if(map.get(n) - map.get(start) == 1) {
				if(n.equals(end)) {
					return 1;
				}
				neighbors.add(n);
			}
		}
		
		if(neighbors.size() == 0) {
			return 0;
		}
		
		int count = 0;
		for(Coord2D<Integer> n : neighbors) {
			count += isConnected(map, n, end);
		}
		return count;
	}
}
