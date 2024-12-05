package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spdqbr.aoc.Solution;

public class Day01 extends Solution {
	public static final int day = 1;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day01()).main(year, day, 0);
		(new Day01()).main(year, day, 1);
		(new Day01()).main(year, day, 2);
		(new Day01()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		
		String[] data;
		for(String line : input){
			data = line.split("\\s+");
			left.add(Integer.parseInt(data[0]));
			right.add(Integer.parseInt(data[1]));
		}		
		
		Collections.sort(left);
		Collections.sort(right);
		
		long distance = 0;
		for(int i = 0; i < left.size(); i++) {
			distance += Math.abs(left.get(i) - right.get(i));
		}
		return ""+distance;
	}
	
	public String part2(String[] input) {
		List<Integer> left = new ArrayList<>();
		Map<Integer, Integer> right = new HashMap<>();
		
		String[] data;
		int r;
		for(String line : input){
			data = line.split("\\s+");
			left.add(Integer.parseInt(data[0]));
			r = Integer.parseInt(data[1]);
			if(!right.containsKey(r)) {
				right.put(r, 0);
			}
			right.put(r, right.get(r)+1);
		}	
		
		long sum = 0;
		for(int i = 0; i < left.size(); i++) {
			if(right.containsKey(left.get(i))){
				sum += left.get(i) * right.get(left.get(i));
			}
		}
		
		return ""+sum;
	}
}
