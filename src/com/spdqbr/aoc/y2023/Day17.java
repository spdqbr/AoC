package com.spdqbr.aoc.y2023;
import java.util.Arrays;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.Matrix2D;
import com.spdqbr.aoc.utils.Pair;
import com.spdqbr.aoc.utils.SparseGrid2D;

public class Day17 extends Solution {
	public static final int day = 17;
	public static final int year = 2023;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day17()).main(year, day, 0);
//		(new Day17()).main(year, day, 1);
//		(new Day17()).main(year, day, 2);
//		(new Day17()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		int[][] grid = new int[input.length][];
		for(int i = 0; i < input.length; i++) {
			grid[i] = new int[input[i].length()];
			for(int j = 0; j < input.length; j++) {
				grid[i][j] = (int)(input[i].charAt(j) - '0');
			}
		}
		Matrix2D m = new Matrix2D(grid);
		
//		int weight = dijkstra(m, new Coord2D<Integer>(0,0), new Coord2D<Integer>(m.height-1, m.width-1), false);
		
//		System.out.println(weight);
		
		return "";
	}
	
	public char getDirectionFromTo(Coord2D<Integer> from, Coord2D<Integer> to) {
		if(from.row > to.row) return 'N';
		if(from.row < to.row) return 'S';
		if(from.col > to.col) return 'W';
		if(from.col < to.col) return 'E';
		return 'X';
	}
	
//	public int dijkstra(Matrix2D m, Coord2D<Integer>  start, Coord2D<Integer>  end, boolean diagonal) {
//		int[][] pathWeights = new int[m.height][m.width];
//		///Pair(Movement Direction, number of times moved that direction before this);
//		SparseGrid2D<Pair<Character, Integer>> visited = new SparseGrid2D<>();
//		for(int i = 0; i < pathWeights.length; i++) {
//			Arrays.fill(pathWeights[i], Integer.MAX_VALUE);
//		}
//		pathWeights[start.row][start.col] = 0;
//		visited.putInt(start, new Pair<Character, Integer>('X',0));
//		
//		Coord2D<Integer>  active = start;
//		List<Coord2D<Integer> > neighbors;
//		while(visited.getInt(end) == null) {
//			neighbors = m.getNeighbors(active, diagonal);
//			
//			for(Coord2D<Integer>  n : neighbors) {
//				char dir = getDirectionFromTo(active, n);
//				if(visited.get(n.row, n.col) == null) {
//					if(visited.getInt(active).left == dir) {
//						if(visited.getInt(active).right == 2) continue;
//					}
//					int weight = pathWeights[active.row][active.col] + m.get(n); 
//					if(weight < pathWeights[n.row][n.col]) pathWeights[n.row][n.col] = weight; 
//				}
//			}
//			
//			visited[active.row][active.col] = true;
//			
//			int min = Integer.MAX_VALUE;
//			for(int r = 0; r < m.height; r++) {
//				for(int c = 0; c < m.width; c++) {
//					if(!visited[r][c] && pathWeights[r][c] < min) {
//						min = pathWeights[r][c];
//						active = new Coord2D<Integer>(r,c);
//					}
//				}
//			}
//		}
//		
//		return pathWeights[end.row][end.col];
//	}
//	
	public String part2(String[] input) {
		return "";
	}
}
