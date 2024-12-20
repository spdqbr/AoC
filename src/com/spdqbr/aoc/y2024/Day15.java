package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Coord2D;
import com.spdqbr.aoc.utils.Matrix2D;
import com.spdqbr.aoc.utils.Pair;

public class Day15 extends Solution {
	public static final int day = 15;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day15()).main(year, day, 0);
		(new Day15()).main(year, day, 1);
		(new Day15()).main(year, day, 2);
		(new Day15()).main(year, day, 3);
	}

	public static final int EMPTY = 0;
	public static final int WALL = 1;
	public static final int ROBOT = 2;
	public static final int BOX = 3;
	public static final int BOX_LEFT = 4;
	public static final int BOX_RIGHT = 5;
	

	
	public static final Map<Character, Coord2D<Integer>> DIRS = new HashMap<>();
	static {
		DIRS.put('^', new Coord2D<Integer>(-1,0));
		DIRS.put('>', new Coord2D<Integer>(0,1));
		DIRS.put('v', new Coord2D<Integer>(1,0));
		DIRS.put('<', new Coord2D<Integer>(0,-1));
	}
	
	public static String mapToString(Matrix2D map) {
		return map.toString()
				.replaceAll("0, ?", ".")
				.replaceAll("1, ?", "#")
				.replaceAll("2, ?", "@")
				.replaceAll("3, ?", "O")
				.replaceAll("4, ?", "[")
				.replaceAll("5, ?", "]");
	}
	
	public static boolean isValid(Matrix2D map) {
		for(int row = 0; row < map.height; row++) {
			for(int col = 1; col < map.width; col++) {
				if(map.get(row, col) == BOX_RIGHT && map.get(row,col-1) != BOX_LEFT) {
					return false;
				}
			}
		}
		return true;
	}
	
	public String part1(String[] input) {
		int i = 0;
		while(!input[i++].isBlank());
		Matrix2D map = new Matrix2D(new int[i-1][input[0].length()]);
		
		Coord2D<Integer> location = null;
		for(int row = 0; row < i; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				switch(input[row].charAt(col)){
					case '#': map.matrix[row][col] = WALL; break;
					case 'O': map.matrix[row][col] = BOX; break;
					case '.': map.matrix[row][col] = EMPTY; break;
					case '@': 
						map.matrix[row][col] = ROBOT;
						location = new Coord2D<Integer>(row, col);
						break;
				}
			}
		}
		
		StringBuilder commandSet = new StringBuilder();
		for(int row = i; row < input.length; row++) {
			commandSet.append(input[row].strip());
		}
		
		for(char c : commandSet.toString().toCharArray()) {
			location = push(c, location, map);
		}
		
		long sum = 0;
		for(int row = 0; row < map.height; row++) {
			for(int col = 0; col < map.width; col++) {
				if(map.get(row, col) == BOX) {
					sum += (100*row + col);
				}
			}
		}
		
		return ""+sum;
	}
	
	public Coord2D<Integer> push(char direction, Coord2D<Integer> location, Matrix2D map) {
		Coord2D<Integer> newLocation = new Coord2D<Integer>(location.row + DIRS.get(direction).row, location.col + DIRS.get(direction).col);
		if(map.get(newLocation) == WALL) return location;
		
		if(map.get(newLocation) >= BOX) {
			push(direction, newLocation, map);
		}
		
		if(map.get(newLocation) == EMPTY) {
			map.set(newLocation, map.get(location));
			map.set(location, EMPTY);
			return newLocation;
		}
		return location;
	}
	
	public Pair<Boolean, List<Coord2D<Integer>>> bigPush(int depth, char direction, Coord2D<Integer> location, Matrix2D map) {
		List<Coord2D<Integer>> out = new ArrayList<>();
		List<Coord2D<Integer>> move = new ArrayList<>();
		if(direction == '<' || direction == '>') {
			
			out.add(push(direction, location, map));
			return new Pair<Boolean, List<Coord2D<Integer>>>(true, out);
		}
		
		move.add(location);
		
		if(map.get(location) >= BOX) {
			Coord2D<Integer> otherHalf = new Coord2D<>(location.row, location.col+1);
			if(map.get(location) == BOX_RIGHT) {
				otherHalf = new Coord2D<Integer>(location.row, location.col-1);
			}
			move.add(otherHalf);
		}
		
		out.addAll(move);
		for(Coord2D<Integer> m : move) {
			Coord2D<Integer> newLocation = new Coord2D<Integer>(m.row + DIRS.get(direction).row, m.col + DIRS.get(direction).col);
			if(map.get(newLocation) == WALL) {
				out.clear();
				out.add(location);
				return new Pair<Boolean, List<Coord2D<Integer>>>(false, out);
			}else if(map.get(newLocation) >= BOX) {
				Pair<Boolean, List<Coord2D<Integer>>> status = bigPush(depth+1, direction, newLocation, map);
				if(!status.left) {
					out.clear();
					out.add(location);
					return new Pair<Boolean, List<Coord2D<Integer>>>(false, out);
				}
				status.right.removeAll(out);
				out.addAll(status.right);
			}
		}

		if(depth == 0) {
			Coord2D<Integer> update = null;
			
			Collections.sort(out, new Comparator<Coord2D<Integer>>() {

				@Override
				public int compare(Coord2D<Integer> o1, Coord2D<Integer> o2) {
					if(o1.getRow() == o2.getRow()) return 0;
					if(direction == '^') {
						return o1.getRow() < o2.getRow() ? -1 : 1;
					} else {
						return o1.getRow() < o2.getRow() ? 1 : -1;
					}
				}
			});
			
			for(int i = 0; i < out.size(); i++) {
				update = new Coord2D<Integer>(
						out.get(i).row + DIRS.get(direction).row, 
						out.get(i).col + DIRS.get(direction).col);
				Coord2D<Integer> curLoc = out.get(i);
				int  val = map.get(curLoc);
				map.set(update, val);
				map.set(curLoc, EMPTY);
			}
			out.clear();
			out.add(update);
		}
		return new Pair<Boolean, List<Coord2D<Integer>>>(true, out);
	}
	
	public String part2(String[] input) {
		int i = 0;
		while(!input[i++].isBlank());
		Matrix2D map = new Matrix2D(new int[i-1][input[0].length()*2]);
		
		Coord2D<Integer> location = null;
		for(int row = 0; row < i; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				switch(input[row].charAt(col)){
					case '#': 
						map.matrix[row][2*col] = WALL;
						map.matrix[row][2*col+1] = WALL;
						break;
					case '.': 
						map.matrix[row][2*col] = EMPTY; 
						map.matrix[row][2*col+1] = EMPTY;
						break;
					case 'O': 
						map.matrix[row][2*col] = BOX_LEFT;
						map.matrix[row][2*col+1] = BOX_RIGHT;
						break;
					case '@': 
						map.matrix[row][2*col] = ROBOT;
						location = new Coord2D<Integer>(row, 2*col);
						break;
				}
			}
		}
		
		StringBuilder commandSet = new StringBuilder();
		for(int row = i; row < input.length; row++) {
			commandSet.append(input[row].strip());
		}
		
		System.out.println(mapToString(map));
		
		for(char c : commandSet.toString().toCharArray()) {
//			System.out.println(c);
			location = bigPush(0, c, location, map).right.get(0);
//			System.out.println(mapToString(map));
			if(!isValid(map)) {
				System.out.println("INVALID");
				break;
			}
		}
		
		long sum = 0;
		for(int row = 0; row < map.height; row++) {
			for(int col = 0; col < map.width; col++) {
				if(map.get(row, col) == BOX_LEFT) {
					sum += (100*row + col);
				}
			}
		}
		
		return ""+sum;
	}
	
	
}
