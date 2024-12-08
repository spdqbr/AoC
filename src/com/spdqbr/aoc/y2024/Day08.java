package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;

public class Day08 extends Solution {
	public static final int day = 8;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day08()).main(year, day, 0);
		(new Day08()).main(year, day, 1);
		(new Day08()).main(year, day, 2);
		(new Day08()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		Map<Character, List<Pair<Integer, Integer>>> map = new HashMap<>();
		
		Character c;
		for(int row = 0; row < input.length; row++) {
			for( int col = 0; col < input[row].length(); col++) {
				c = input[row].charAt(col);
				if(c == '.') continue;
				if(!map.containsKey(c)){
					map.put(c, new ArrayList<Pair<Integer, Integer>>());
				}
				map.get(c).add(new Pair<Integer, Integer>(row, col));
			}
		}
		
		Set<Pair<Integer, Integer>> antinodes = new HashSet<>();
		List<Pair<Integer, Integer>> list;
		Pair<Integer, Integer> antinode;
		int dx, dy;
		for(char antenna: map.keySet()) {
			list = map.get(antenna);
			for(int i = 0; i < list.size()-1; i++) {
				for(int j = i+1; j < list.size(); j++) {
					dx = list.get(j).left - list.get(i).left;
					dy = list.get(j).right - list.get(i).right;
					antinode = new Pair<Integer, Integer>(list.get(i).left-dx, list.get(i).right-dy);
					if(! antinode.equals(list.get(j))){
						if(antinode.left >= 0 && antinode.left < input.length) {
							if(antinode.right >= 0 && antinode.right < input[0].length()) {
								antinodes.add(antinode);
							}
						}
					}
					antinode = new Pair<Integer, Integer>(list.get(i).left+dx, list.get(i).right+dy);
					if(! antinode.equals(list.get(j))){
						if(antinode.left >= 0 && antinode.left < input.length) {
							if(antinode.right >= 0 && antinode.right < input[0].length()) {
								antinodes.add(antinode);
							}
						}
					}
					antinode = new Pair<Integer, Integer>(list.get(j).left-dx, list.get(j).right-dy);
					if(! antinode.equals(list.get(i))){
						if(antinode.left >= 0 && antinode.left < input.length) {
							if(antinode.right >= 0 && antinode.right < input[0].length()) {
								antinodes.add(antinode);
							}
						}
					}
					antinode = new Pair<Integer, Integer>(list.get(j).left+dx, list.get(j).right+dy);
					if(! antinode.equals(list.get(i))){
						if(antinode.left >= 0 && antinode.left < input.length) {
							if(antinode.right >= 0 && antinode.right < input[0].length()) {
								antinodes.add(antinode);
							}
						}
					}
				}
			}
		}
		
		return ""+antinodes.size();
	}
	
	public String part2(String[] input) {
		Map<Character, List<Pair<Integer, Integer>>> map = new HashMap<>();
		
		Character c;
		for(int row = 0; row < input.length; row++) {
			for( int col = 0; col < input[row].length(); col++) {
				c = input[row].charAt(col);
				if(c == '.') continue;
				if(!map.containsKey(c)){
					map.put(c, new ArrayList<Pair<Integer, Integer>>());
				}
				map.get(c).add(new Pair<Integer, Integer>(row, col));
			}
		}
		
		Set<Pair<Integer, Integer>> antinodes = new HashSet<>();
		List<Pair<Integer, Integer>> list;
		Pair<Integer, Integer> antinode;
		int dx, dy;
		for(char antenna: map.keySet()) {
			list = map.get(antenna);
			for(int i = 0; i < list.size()-1; i++) {
				for(int j = i+1; j < list.size(); j++) {
					dx = list.get(j).left - list.get(i).left;
					dy = list.get(j).right - list.get(i).right;
					antinode = new Pair<Integer, Integer>(list.get(i).left, list.get(i).right);
					while(antinode.left >= 0 && antinode.left < input.length && antinode.right >= 0 && antinode.right < input[0].length()) {
						antinodes.add(antinode);
						antinode = new Pair<Integer, Integer>(antinode.left-dx, antinode.right-dy);
					}
					antinode = new Pair<Integer, Integer>(list.get(i).left, list.get(i).right);
					while(antinode.left >= 0 && antinode.left < input.length && antinode.right >= 0 && antinode.right < input[0].length()) {
						antinodes.add(antinode);
						antinode = new Pair<Integer, Integer>(antinode.left+dx, antinode.right+dy);
					}
				}
			}
		}
		
		return ""+antinodes.size();
	}
}
