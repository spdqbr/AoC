package com.spdqbr.aoc.y2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;
import com.spdqbr.aoc.utils.Triple;

public class Day06 extends Solution {
	public static final int day = 6;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day06()).main(year, day, 0);
		(new Day06()).main(year, day, 1);
		(new Day06()).main(year, day, 2);
		(new Day06()).main(year, day, 3);
	}
	
	public static List<Pair<Integer,Integer>> directions = new ArrayList<>();
	static {
			directions.add(new Pair<Integer,Integer>(-1,0)); //up
			directions.add(new Pair<Integer,Integer>(0,1)); //right
			directions.add(new Pair<Integer,Integer>(1,0)); //down
			directions.add(new Pair<Integer,Integer>(0,-1)); //left
	};
	
	public Triple<char[][], Pair<Integer, Integer>, Integer> loadMap(String[] input){
		Triple<char[][], Pair<Integer, Integer>, Integer> out = new Triple<char[][], Pair<Integer, Integer>, Integer>();
		char[][] map = new char[input.length][input[0].length()];
		out.item0 = map;
		
		char c;
		int index;
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				c = input[row].charAt(col);
				if((index = "^>V<".indexOf(c)) >= 0){
					out.item1 = new Pair<Integer, Integer>(row, col);
					out.item2 = index;
					c = '.';
				}
				map[row][col] = c;
			}
		}
		
		return out;
	}
	
	public Pair<Boolean, Set<Pair<Integer, Integer>>> findVisited(Triple<char[][], Pair<Integer, Integer>, Integer> initialState){
		Set<Pair<Pair<Integer, Integer>, Integer>> visitedWithDirection = new HashSet<>();
		boolean loop = true;
		
		Pair<Pair<Integer, Integer>, Integer> currentLocDir = new Pair<>(
				new Pair<Integer, Integer>(
						initialState.item1.left, 
						initialState.item1.right), 
				initialState.item2);
		Pair<Pair<Integer, Integer>, Integer> newLocDir;
		char c;
		while( visitedWithDirection.add(currentLocDir)) {
			newLocDir = new Pair<Pair<Integer, Integer>, Integer>(
					new Pair<Integer, Integer>(
							currentLocDir.left.left + directions.get(currentLocDir.right).left, 
							currentLocDir.left.right + directions.get(currentLocDir.right).right), 
					currentLocDir.right);
			try {
				c = initialState.item0[newLocDir.left.left][newLocDir.left.right];
				if( c != '.') {
					newLocDir.right = newLocDir.right + 1;
					newLocDir.right = newLocDir.right % directions.size();
					newLocDir.left = currentLocDir.left;
				}
			}catch(IndexOutOfBoundsException ob){
				loop = false;
				break;
			}
			currentLocDir = newLocDir;
		}
		
		Set<Pair<Integer, Integer>> unique = new HashSet<>();
		for( Pair<Pair<Integer, Integer>, Integer> visited : visitedWithDirection) {
			unique.add(visited.left);
		}
		return new Pair<Boolean, Set<Pair<Integer, Integer>>>(loop, unique);
	}
	
	@Override
	public String part1(String[] input) {
		Triple<char[][], Pair<Integer, Integer>, Integer> data = loadMap(input);
		int count = findVisited(data).right.size();
		return ""+count;
	}
	
	@Override
	public String part2(String[] input) {
		Triple<char[][], Pair<Integer, Integer>, Integer> data = loadMap(input);
		Set<Pair<Integer, Integer>> visited = findVisited(data).right;
		
		int loopCount = 0;
		for(Pair<Integer, Integer> test : visited) {
			data.item0[test.left][test.right] = '#';
			if( findVisited(data).left ) {
				loopCount++;
			}
			data.item0[test.left][test.right] = '.';
		}

		return ""+loopCount;
	}

}
