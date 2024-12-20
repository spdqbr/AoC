package com.spdqbr.aoc.y2024;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.MathUtils;

public class Day14 extends Solution {
	public static final int day = 14;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day14()).main(year, day, 0);
		(new Day14()).main(year, day, 1);
//		(new Day14()).main(year, day, 2);
		(new Day14()).main(year, day, 3);
	}
	
	public static int ROWS;
	public static int COLS;
	
	private static class Robot {
		public int id, x, y, xVel, yVel;
		
		public Robot(int[] vals) {
			this(vals[0], vals[1], vals[2], vals[3], vals[4]);
		}
		
		public Robot(int id, int x, int y, int xVel, int yVel) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.xVel = xVel;
			this.yVel = yVel;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Robot other = (Robot) obj;
			return id == other.id;
		}

		@Override
		public String toString() {
			return "Robot [id=" + id + ", x=" + x + ", y=" + y + ", xVel=" + xVel + ", yVel=" + yVel + "]";
		}
	}
	
	public static Pattern numberPattern = Pattern.compile("[=,](-?[0-9]+)");
	
	public static void tick(Set<Robot> robots, int count) {
		for(Robot r : robots) {
			r.x += r.xVel * count;
			r.y += r.yVel * count;
			
			r.x = MathUtils.mod(r.x, COLS);
			r.y = MathUtils.mod(r.y, ROWS);
		}
	}
	
	public static void printRobots(Set<Robot> robots) {
		int[][] grid = new int[ROWS][COLS];
		for(Robot r : robots) {
			grid[r.y][r.x]++;
		}
		StringBuilder sb = new StringBuilder();
		for(int[] row : grid) {
			for(int col : row) {
				
				sb.append(col == 0?'.':(char)('0'+col));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static int[] quadrantCount(Set<Robot> robots) {
		int[] quadrantCount = new int[] { 0,0,0,0 };
		int xMiddle = COLS/2;
		int yMiddle = ROWS/2;
		for(Robot r : robots) {
			if(r.x < xMiddle) {
				if(r.y < yMiddle) {
					quadrantCount[0]++;
				}else if(r.y > yMiddle) {
					quadrantCount[1]++;
				}
			}else if(r.x > xMiddle) {
				if(r.y < yMiddle) {
					quadrantCount[2]++;
				}else if(r.y > yMiddle) {
					quadrantCount[3]++;
				}
			}
		}
		return quadrantCount;
	}
	
	public String part1(String[] input) {
		if(input.length < 20) {
			ROWS = 7;
			COLS = 11;
		} else {
			ROWS = 103;
			COLS = 101;
		}

		Set<Robot> robots = new HashSet<>();
		
		int[] vals = new int[5];
		Matcher m;
		for(int i = 0; i < input.length; i++) {
			m = numberPattern.matcher(input[i]);
			vals[0] = i;
			int j = 1;
			while(m.find()) {
				vals[j++] = Integer.parseInt(m.group(1));
			}
			
			robots.add(new Robot(vals));
		}
		
		tick(robots, 100);
		int[] qc = quadrantCount(robots);
		System.out.println(Arrays.toString(qc));
		
		return ""+Arrays.stream(qc).reduce(1, (x,y) -> x*y);
	}
	
	public static boolean allSingle(Set<Robot> robots) {
		int[][] grid = new int[ROWS][COLS];
		for(Robot r : robots) {
			grid[r.y][r.x]++;
			if(grid[r.y][r.x] > 1) return false;
		}
		return true;
	}
	
	public String part2(String[] input) {
		if(input.length < 20) {
			ROWS = 7;
			COLS = 11;
		} else {
			ROWS = 103;
			COLS = 101;
		}

		Set<Robot> robots = new HashSet<>();
		
		int[] vals = new int[5];
		Matcher m;
		for(int i = 0; i < input.length; i++) {
			m = numberPattern.matcher(input[i]);
			vals[0] = i;
			int j = 1;
			while(m.find()) {
				vals[j++] = Integer.parseInt(m.group(1));
			}
			
			robots.add(new Robot(vals));
		}
		
		int i = 0;
		// Assume the tree appears when no robots overlap?
		// It's a guess that turns out to be a good one
		for(; !allSingle(robots); i++) {
			tick(robots, 1);
		}
		
		printRobots(robots);
		return ""+i;
	}
}
