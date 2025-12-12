package com.spdqbr.aoc.y2025;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;
import com.spdqbr.aoc.utils.Utils;

public class Day12 extends Solution {
	public static final int day = 12;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day12()).main(year, day, 0);
		(new Day12()).main(year, day, 1);
		(new Day12()).main(year, day, 2);
		(new Day12()).main(year, day, 3);
	}
	
	private static class Shape{
		boolean[][] shape = new boolean[3][3];
		int countSet = 0;
		
		public Shape(String[] layout) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j <3; j++) {
					shape[i][j] = layout[i].charAt(j) == '#';
					if(shape[i][j]) countSet++;
				}
			}
		}
		
		public int countSet() {
			return countSet;
		}
		
		public void rotateClock() {
			boolean[][] newShape = new boolean[3][3];
			newShape[0][0] = shape[2][0];
			newShape[2][0] = shape[2][2];
			newShape[2][2] = shape[0][2];
			newShape[0][2] = shape[0][0];
			
			newShape[0][1] = shape[1][0];
			newShape[1][0] = shape[2][1];
			newShape[2][1] = shape[1][2];
			newShape[1][2] = shape[0][0];
			shape = newShape;
		}
		
		public void flipX() {
			boolean[][] newShape = new boolean[3][3];
			newShape[0][0] = shape[0][2];
			newShape[0][2] = shape[0][0];
			newShape[2][0] = shape[2][2];
			newShape[2][2] = shape[2][0];
			
			newShape[1][0] = shape[1][2];
			newShape[1][2] = shape[1][0];
			shape = newShape;
		}
		
		public void flipY() {
			boolean[][] newShape = new boolean[3][3];
			newShape[0][0] = shape[2][0];
			newShape[2][0] = shape[0][0];
			newShape[0][2] = shape[2][2];
			newShape[2][2] = shape[0][2];
			
			newShape[0][1] = shape[2][1];
			newShape[2][1] = shape[0][1];
			shape = newShape;
		}
		
		public boolean[][] apply(boolean[][] grid, int row, int col, int rotation, boolean flipx, boolean flipy){
			boolean[][] out = new boolean[grid.length][grid[0].length];
			for(int i = 0; i < grid.length; i++) {
				System.arraycopy(grid[i], 0, out[i], 0, grid[i].length);
			}
			
			for(int rot = 0; rot < rotation; rot++) {
				rotateClock();
			}
			
			if(flipx) flipX();
			if(flipy) flipY();
			
			boolean fits = true;
			outer:
			for(int r = 0; row < 3; row++) {
				for(int c = 0; col < 3; col++) {
					if(!this.shape[r][c]) continue;
					if(!out[row+r][col+c]) {
						out[row+r][col+c] = true;
					}else {
						fits = false;
						break outer;
					}
				}
			}
			if(flipy) flipY();
			if(flipx) flipX();
			for(int i = 4-rotation; i < 4; i++) {
				rotateClock();
			}
			
			return fits? out : null;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(boolean[] a : shape) {
				for(boolean b : a) {
					sb.append(b?".":"#");
				}
				sb.append("\n");
			}
			return sb.toString();
		}
	}
	
	public String toString(boolean[][] grid) {
		StringBuilder sb = new StringBuilder();
		for(boolean[] a : grid) {
			for(boolean b : a) {
				sb.append(b?".":"#");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public int countFree(boolean[][] grid) {
		int count = 0;
		for(boolean[] r : grid) {
			for(boolean c : r) {
				if(!c) count++;
			}
		}
		return count;
	}
	
	public boolean fit(boolean[][] grid, Map<Integer, Shape> shapeMap, int[] presents) {
		boolean allZero = true;
		for(int present: presents) {
			if(present != 0) {
				allZero = false;
				break;
			}
		}
		if(allZero) return true;
		
		boolean[][] updated;
		int[] pCopy = new int[presents.length];
		System.arraycopy(presents, 0, pCopy, 0, presents.length);
		Shape s;
		for(int i = 0; i < presents.length; i++) {
			if(presents[i] == 0) continue;
			
			s = shapeMap.get(i);
			if(s.countSet() * presents[i] > countFree(grid)) return false;
			
			
			for(int startRow = 0; startRow < grid.length-3; startRow++) {
				for(int startCol = 0; startCol < grid[0].length-3; startCol++) {
					for(int rot = 0; rot < 4; rot++) {
						for(int flip = 0; flip < 4; flip++) {
							updated = s.apply(grid, startRow, startCol, rot, (flip & 1) == 1, ((flip>>1)&1) == 1);
							if(updated != null) {
								pCopy[i]--;
								return fit(updated, shapeMap, pCopy);
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public String part1(String[] input) {
		Map<Integer, Shape> shapeMap = new HashMap<>();
		List<Pair<int[], int[]>> targets = new ArrayList<>();
		
		String[] s = new String[3];
		int[] size, presents;
		int shapeCount = 0;
		for(int i = 0; i < input.length; i++) {
			debug(input[i]);
			if(input[i].matches("^\\d+:")){
				for(int j = 0; j < 3 ; j++) {
					s[j] = input[i+j+1];
				}
				shapeMap.put(shapeCount++, new Shape(s));
				i += 4;
			}else if(input[i].matches("^\\d+x\\d+:.*")) {
				s = input[i].split(":");
				size = Utils.stringArraytoIntArray(s[0].split("x"));
				presents = Utils.stringArraytoIntArray(s[1].trim().split(" +"));
				targets.add(new Pair<int[], int[]>(size, presents));
			}
		}
		
		int count = 0;
		boolean[][] grid;
		for(Pair<int[], int[]> target: targets) {
			size = target.left;
			presents = target.right;
			
			int totalSet = 0;
			for(int i = 0; i < presents.length; i++) {
				totalSet += (presents[i] * shapeMap.get(i).countSet);
			}
			
			if(totalSet > size[0] * size[1]) continue;
			
			if(fit(new boolean[size[0]][size[1]], shapeMap, presents)) count++;
		}
		
		return ""+count;
	}
	
	public String part2(String[] input) {
		return "Happy AoC!";
	}
}
