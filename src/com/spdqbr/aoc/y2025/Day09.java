package com.spdqbr.aoc.y2025;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;

public class Day09 extends Solution {
	public static final int day = 9;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day09()).main(year, day, 0);
		(new Day09()).main(year, day, 1);
		(new Day09()).main(year, day, 2);
		(new Day09()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		List<Long[]> grid = new ArrayList<>();
		
		String[] vals;
		for(String s : input) {
			vals = s.split(",");
			grid.add(new Long[] { Long.parseLong(vals[0]), Long.parseLong(vals[1]) });
		}
		
		long maxArea = 0;
		long area;
		Long[] a, b;
		for(int i = 0; i < grid.size(); i++) {
			a = grid.get(i);
			for(int j = i+1; j < grid.size(); j++) {
				b = grid.get(j);
				area = Math.abs(a[0] - b[0]+1) * Math.abs(a[1] - b[1]+1);
				debug(Arrays.toString(a)+" : "+Arrays.toString(b)+" :: "+area);
				if(area > maxArea) {
					maxArea = area;
				}
			}
		}
		
		return ""+maxArea;
	}
	
	public String part2(String[] input) {
		List<int[]> grid = new ArrayList<>();
		
		String[] vals;
		int row, col;
		for(String s : input) {
			vals = s.split(",");
			row = Integer.parseInt(vals[0]);
			col = Integer.parseInt(vals[1]);
			grid.add(new int[] { row, col });
		}
		
		Set<Pair<Integer, Integer>> perimeter = new HashSet<>();
		int[] a, b;
		int min, max;
		for(int i = 0; i < grid.size(); i++) {
			a = grid.get(i);
			b = grid.get((i+1)%grid.size());
			if(a[0] == b[0]) {
				min = Math.min(a[1], b[1]);
				max = Math.max(a[1], b[1]);
				for(int j = min; j <= max; j++){
					perimeter.add(new Pair<Integer, Integer>(a[0], j));
				}
			} else {
				min = Math.min(a[0], b[0]);
				max = Math.max(a[0], b[0]);
				for(int j = min; j <= max; j++){
					perimeter.add(new Pair<Integer, Integer>(j, a[1]));
				}
			}
		}
		
		int minRow, maxRow, minCol, maxCol, area, maxArea = 0;
		Pair<Integer, Integer> check = new Pair<Integer, Integer>(0,0);
		for(int i = 0; i < grid.size(); i++) {
			a = grid.get(i);
			for(int j = i+1; j < grid.size(); j++) {
				b = grid.get(j);
				minRow = Math.min(a[0], b[0]);
				maxRow = Math.max(a[0], b[0]);
				minCol = Math.min(a[1], b[1]);
				maxCol = Math.max(a[1], b[1]);
				
				area = (maxRow - minRow + 1) * (maxCol - minCol+1);
				
				if(area > maxArea) {
					boolean isValid = true;
					
					// this whole block should be replaced with checking intersections rather than walking the whole path
						// traverse the left and right sides of the rectangle, looking for perimeter crossings
						for(int k = minRow+1; isValid && k < maxRow; k++) {
							check.left = k;
							
							check.right = minCol+1;
							if(perimeter.contains(check)) isValid = false;
							
							check.right = maxCol-1;
							if(perimeter.contains(check)) isValid = false;
						}
						
						// traverse the top and bottom sides of the rectangle, looking for perimeter crossings
						for(int k = minCol+1; isValid && k < maxCol; k++) {
							check.right = k;
							
							check.left = minRow+1;
							if(perimeter.contains(check)) isValid = false;
							
							check.left = maxRow-1;
							if(perimeter.contains(check)) isValid = false;
						}
					
					if(isValid) {
						maxArea = area;
					}
				}
			}
		}
		
		return ""+maxArea;
	}
}
