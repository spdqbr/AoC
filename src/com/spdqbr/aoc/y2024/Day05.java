package com.spdqbr.aoc.y2024;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.spdqbr.aoc.Solution;

public class Day05 extends Solution {
	public static final int day = 5;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day05()).main(year, day, 0);
		(new Day05()).main(year, day, 1);
		(new Day05()).main(year, day, 2);
		(new Day05()).main(year, day, 3);
	}
	
	public boolean check(String[] data, Map<String, Set<String>> before) {
		for(int x = 0; x < data.length; x++) {
			for(int y = x+1; y < data.length; y++) {
				if( before.containsKey(data[y])) {
					if(before.get(data[y]).contains(data[x])) return false;
				}
			}
		}
		return true;
	}
	
	public String part1(String[] input) {
		Map<String, Set<String>> before = new HashMap<>();
		int i = 0;
		String[] data;
		for(i = 0; ! input[i].isBlank(); i++) {
			data = input[i].split("\\|");
			if(!before.containsKey(data[0])) before.put(data[0], new HashSet<String>());
			before.get(data[0]).add(data[1]);
		}
		
		debug(before);
		i++;
		long sum = 0;
		for(; i < input.length; i++) {
			data = input[i].split(",");
			if(check(data, before)) {
				debug(input[i]+" :: "+data[data.length/2]);
				sum += Long.parseLong(data[data.length/2]);
			}
		}
		return ""+sum;
	}
	
	public String part2(String[] input) {
		final Map<String, Set<String>> before = new HashMap<>();
		int i = 0;
		String[] data;
		for(i = 0; ! input[i].isBlank(); i++) {
			data = input[i].split("\\|");
			if(!before.containsKey(data[0])) before.put(data[0], new HashSet<String>());
			before.get(data[0]).add(data[1]);
		}
		
		debug(before);
		i++;
		long sum = 0;
		for(; i < input.length; i++) {
			data = input[i].split(",");
			if(!check(data, before)) {
				Arrays.sort(data, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						if(before.containsKey(o1)){
							if(before.get(o1).contains(o2)) return -1;
						}
						if(before.containsKey(o2)){
							if(before.get(o2).contains(o1)) return 1;
						}
						return 0;
					}
				});
				sum += Long.parseLong(data[data.length/2]);
			}
		}
		return ""+sum;
	}
}
