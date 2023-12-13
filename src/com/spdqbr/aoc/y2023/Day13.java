package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Utils;

public class Day13 extends Solution {
	public static final int day = 13;
	public static final int year = 2023;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day13()).main(year, day, 0);
		(new Day13()).main(year, day, 1);
		(new Day13()).main(year, day, 2);
		(new Day13()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		List<char[]> grid = new ArrayList<>();
		long rowMirrorCount = 0;
		long colMirrorCount = 0;
		for(int i = 0; i < input.length+1; i++) {
			if(i == input.length || input[i].isBlank()) {
				printGrid(grid);
				rowMirrorCount += findMirrorLine(grid)+1;
				
				grid = transpose(grid);
				colMirrorCount += findMirrorLine(grid)+1;
				
				grid.clear();
				continue;
			}
			
			grid.add(input[i].toCharArray());
		}
		return ""+(colMirrorCount+100*rowMirrorCount);
	}
	
	public int findMirrorLine(List<char[]> grid) {
		for(int i = 0; i < grid.size()-1; i++) {
			if(Utils.arrEquals(grid.get(i), grid.get(i+1))){
				boolean isMirror = true;
				for(int n = 1; isMirror && i-n >= 0 && i+n+1 <grid.size(); n++) {
					if(!Utils.arrEquals(grid.get(i-n), grid.get(i+n+1))) isMirror = false;
				}
				if(isMirror) {
					debug("Mirror after row: "+i);
					return i;
				}
			}
		}
		return -1;
	}
	
	public List<char[]> transpose(List<char[]> grid){
		List<char[]> newGrid = new ArrayList<>();
		for(int col = 0; col < grid.get(0).length; col++) {
			newGrid.add(new char[grid.size()]);
			for(int row = 0; row < grid.size(); row++) {
				newGrid.get(col)[row] = grid.get(row)[col];
			}
		}
		
		return newGrid;
	}
	
	public void printGrid(List<char[]> grid) {
		if(!DEBUG) return;
		for(char[] c : grid) {
			debug(new String(c));
		}
	}
	
	public int findMirrorLineSmudge(List<char[]> grid) {
		for(int i = 0; i < grid.size()-1; i++) {
			long diffSum = 0;
			int arrDiff;
			if((arrDiff =arrDiff(grid.get(i), grid.get(i+1))) <=1 ){
				diffSum = arrDiff;
				for(int n = 1; diffSum <= 1 && i-n >= 0 && i+n+1 <grid.size(); n++) {
					diffSum += arrDiff(grid.get(i-n), grid.get(i+n+1));
				}
				if(diffSum == 1) {
					debug("Mirror after row: "+i);
					return i;
				}
			}
		}
		return -1;
	}
	
	public static int arrDiff(char[] a, char[] b) {
		int diffCount = 0;
		if( (a == null || b == null) && a != b) return Integer.MAX_VALUE;
		if(a.length != b.length) return Integer.MAX_VALUE;
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) diffCount++;
		}
		return diffCount;
	}
	
	public String part2(String[] input) {
		List<char[]> grid = new ArrayList<>();
		long rowMirrorCount = 0;
		long colMirrorCount = 0;
		for(int i = 0; i < input.length+1; i++) {
			if(i == input.length || input[i].isBlank()) {
				printGrid(grid);
				rowMirrorCount += findMirrorLineSmudge(grid)+1;
				
				grid = transpose(grid);
				colMirrorCount += findMirrorLineSmudge(grid)+1;
				
				grid.clear();
				continue;
			}
			
			grid.add(input[i].toCharArray());
		}
		return ""+(colMirrorCount+100*rowMirrorCount);
	}
}
