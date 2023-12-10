package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.Pair;
import com.spdqbr.aoc.utils.SparseGrid2D;

public class Day10 extends Solution {
	public static final int day = 10;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day10()).main(year, day, 3);
	}
	
	public List<Coord2D<Long>> connections(long row, long col, char val){
		List<Coord2D<Long>> connections = new ArrayList<>();
		
		switch(val) {
		case '|':
			connections.add(new Coord2D<>(row-1, col));
			connections.add(new Coord2D<>(row+1, col));
			break;
		case '-':
			connections.add(new Coord2D<>(row, col-1));
			connections.add(new Coord2D<>(row, col+1));
			break;
		case 'L':
			connections.add(new Coord2D<>(row-1, col));
			connections.add(new Coord2D<>(row, col+1));
			break;
		case 'J':
			connections.add(new Coord2D<>(row-1, col));
			connections.add(new Coord2D<>(row, col-1));
			break;
		case '7':
			connections.add(new Coord2D<>(row, col-1));
			connections.add(new Coord2D<>(row+1, col));
			break;
		case 'F':
			connections.add(new Coord2D<>(row, col+1));
			connections.add(new Coord2D<>(row+1, col));
			break;
		}
		return connections;
	}
	
	public List<Coord2D<Long>> findPath(Coord2D<Long> start, SparseGrid2D<List<Coord2D<Long>>> grid){
		List<Coord2D<Long>> path = new ArrayList<>();
		List<Coord2D<Long>> neighbors;
		Coord2D<Long> currentNode = start;
		Coord2D<Long> prevNode = null;
		Coord2D<Long> nextNode = null;
		while(!path.contains(start)) {
			neighbors = grid.get(currentNode);
			nextNode = neighbors.get(0).equals(prevNode) ? grid.get(currentNode).get(1) : grid.get(currentNode).get(0);
			path.add(nextNode);
			prevNode = currentNode;
			currentNode = nextNode;
		}
		
		return path;
	}
	
	public Pair<Coord2D<Long>, SparseGrid2D<List<Coord2D<Long>>>> populateGrid(String[] input){
		SparseGrid2D<List<Coord2D<Long>>> grid = new SparseGrid2D<>();
		Coord2D<Long> start = null;
		char[] r;
		for(int row = 0; row < input.length; row++) {
			r = input[row].toCharArray();
			for(int col = 0; col < r.length; col++) {
				if( r[col] == 'S') {
					start = new Coord2D<>((long)row, (long)col);
					grid.set(row, col, new ArrayList<Coord2D<Long>>());
					continue;
				}
				if(r[col] != '.') {
					grid.set(row, col, connections(row, col, r[col]));
				}
			}
		}
		
		for(Coord2D<Long> n : grid.getNeighborCoords(start)) {
			if(grid.get(n).contains(start)) {
				grid.get(start).add(n);
			}
		}
		
		return new Pair<Coord2D<Long>, SparseGrid2D<List<Coord2D<Long>>>>(start, grid);
	}
	
	public String part1(String[] input) {
		Pair<Coord2D<Long>, SparseGrid2D<List<Coord2D<Long>>>> data = populateGrid(input);
		
		List<Coord2D<Long>> path = findPath(data.left, data.right);

		return ""+(path.size()/2);
	}
	
	public String part2(String[] input) {
		Pair<Coord2D<Long>, SparseGrid2D<List<Coord2D<Long>>>> data = populateGrid(input);
		
		List<Coord2D<Long>> path = findPath(data.left, data.right);
		
		char startChar = '.';
		Coord2D<Long> next, prev, current;
		current = data.left;
		next = path.get(0);
		prev = path.get(path.size()-2);
		
		List<Coord2D<Long>> neighbors;
		for(char c : "-|F7JL".toCharArray()) {
			neighbors = connections(current.row, current.col, c);
			if(neighbors.contains(next) && neighbors.contains(prev)) {
				startChar = c;
				break;
			}
		}

		input[current.row.intValue()] = input[current.row.intValue()].replace('S', startChar);
		
		SparseGrid2D<Integer> pathGrid = new SparseGrid2D<>();
		
		//-1 up
		//1 down
		//0 straight
		for(int i = 0; i < path.size(); i++) {
			current = path.get(i);
			next = path.get((i+1) % path.size());
			next = path.get((i+1) % path.size());
			prev = path.get((i+path.size()-1) % path.size());
			switch(input[current.row.intValue()].charAt(current.col.intValue())){
				case '|':
				case 'F':
				case '7':
					if(next.row < current.row || prev.row > current.row ) {
						pathGrid.set(path.get(i), -1);
					}else if(next.row > current.row || prev.row < current.row) {
						pathGrid.set(current, 1);
					}
					break;
				default:
					pathGrid.set(path.get(i), 0);
			}
		}
		
		int inside = 0;
		long innerCount = 0;
		long[] bounds = pathGrid.getBounds();
		for(long row = bounds[0]; row <= bounds[1]; row++) {
			inside = 0;
			for(long col = bounds[2]; col <= bounds[3]; col++) {
				if(pathGrid.get(row, col) == null) {
					if(inside % 2 == 1 || inside % 2 == -1) {
						innerCount++;
						System.out.print('I');
					}else {
						System.out.print('.');
					}
				} else {
					switch(pathGrid.get(row, col)) {
					case 0: System.out.print('-'); break;
					case 1: System.out.print('v'); break;
					case -1: System.out.print('^'); break;
					}
					inside += pathGrid.get(row, col);
				}
			}
			System.out.println();
		}
		
		return ""+innerCount;
	}
}
