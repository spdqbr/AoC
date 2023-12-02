package com.spdqbr.aoc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Matrix2D {
	public int height;
	public int width;
	
	public int[][] matrix;
	
	public Matrix2D(int[][] matrix) {
		this.matrix = matrix;
		this.height = matrix.length;
		if(this.height > 0) {
			this.width = matrix[0].length;
		} else {
			this.width = 0;
		}
	}
	
	public Matrix2D(String matrix) {
		String[] lines = matrix.trim().split("\n");
		String[] vals;
		this.matrix = new int[lines.length][];
		for(int i = 0; i < lines.length; i++) {
			vals = lines[i].trim().split(" *, *");
			this.matrix[i] = new int[vals.length];
			for(int j = 0; j < vals.length; j++) {
				this.matrix[i][j] = Integer.parseInt(vals[j]);
			}
		}
		this.height = this.matrix.length;
		if(this.height > 0) {
			this.width = this.matrix[0].length;
		} else {
			this.width = 0;
		}
	}
	
	public Matrix2D(String matrix, boolean unit) {
		this(matrix.replaceAll("([0-9])", "$1,"));
	}
	
	public int get(int row, int col) {
		return matrix[row][col];
	}
	
	public int get(Coord2D coord) {
		return get(coord.row, coord.col);
	}
	
	public List<Coord2D> getNeighbors(Coord2D coord, boolean diagonal) {
		return getNeighbors(coord.row, coord.col, diagonal);
	}
	
	public List<Coord2D> getNeighbors(int row, int col, boolean diagonal) {
		List<Coord2D> list = new ArrayList<Coord2D>();
		for(int y = -1; y <= 1; y++) {
			if(row == 0 && y == -1) continue;
			if(row == height-1 && y == 1) continue;
			for(int x = -1; x <= 1; x++) {
				if(col == 0 && x == -1) continue;
				if(col == width-1 && x == 1) continue;
				if(x == 0 && y == 0) continue;
				if(diagonal || (x == 0 || y == 0)) {
					list.add(new Coord2D(row+y, col+x));	
				}
			}
		}
		return list;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				sb.append(matrix[i][j]);
				sb.append(", ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public int dijkstra(Coord2D start, Coord2D end, boolean diagonal) {
		int[][] pathWeights = new int[height][width];
		boolean[][] visited = new boolean[height][width];
		for(int i = 0; i < pathWeights.length; i++) {
			Arrays.fill(pathWeights[i], Integer.MAX_VALUE);
			Arrays.fill(visited[i], false);
		}
		pathWeights[start.row][start.col] = 0;
		visited[start.row][start.col] = true;
		
		Coord2D active = start;
		List<Coord2D> neighbors;
		while(!visited[end.row][end.col]) {
			neighbors = getNeighbors(active, diagonal);
			for(Coord2D n : neighbors) {
				if(!visited[n.row][n.col]) {
					int weight = pathWeights[active.row][active.col] + get(n); 
					if(weight < pathWeights[n.row][n.col]) pathWeights[n.row][n.col] = weight; 
				}
			}
			visited[active.row][active.col] = true;
			
			int min = Integer.MAX_VALUE;
			for(int r = 0; r < height; r++) {
				for(int c = 0; c < width; c++) {
					if(!visited[r][c] && pathWeights[r][c] < min) {
						min = pathWeights[r][c];
						active = new Coord2D(r,c);
					}
				}
			}
		}
		
		return pathWeights[end.row][end.col];
	}
	
	public int dijkstraPQ(Coord2D start, Coord2D end, boolean diagonal) {
		boolean[][] visited = new boolean[height][width];
		Coord2D[][] cObjs = new Coord2D[height][width];
		PriorityQueue<Coord2D> pq = new PriorityQueue<>();
		
		for(int i = 0; i < cObjs.length; i++) {
			Arrays.fill(visited[i], false);
			for(int j = 0; j < cObjs[i].length; j++) {
				cObjs[i][j] = new Coord2D(i,j,Integer.MAX_VALUE);
				pq.add(cObjs[i][j]);
			}
		}
		
		visited[start.row][start.col] = true;
		cObjs[start.row][start.col].val = 0;
		modify(cObjs[start.row][start.col], pq);
		
		Coord2D active = cObjs[start.row][start.col];
		List<Coord2D> neighbors;
		while(cObjs[end.row][end.col].val == Integer.MAX_VALUE) {
			neighbors = getNeighbors(active, diagonal);
			for(Coord2D n : neighbors) {
				if(!visited[n.row][n.col]) {
					int weight = cObjs[active.row][active.col].val + get(n); 
					if(weight < cObjs[n.row][n.col].val) {
						cObjs[n.row][n.col].val = weight;
						modify(cObjs[n.row][n.col],pq);
					}
				}
			}
			visited[active.row][active.col] = true;
			
			active = pq.poll();
		}
		
		return cObjs[end.row][end.col].val;
	}
	
	public void modify(Coord2D c, PriorityQueue<Coord2D> pq) {
		pq.remove(c);
		pq.add(c);
	}
}
