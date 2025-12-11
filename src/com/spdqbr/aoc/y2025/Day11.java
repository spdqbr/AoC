package com.spdqbr.aoc.y2025;
import java.util.HashMap;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;

public class Day11 extends Solution {
	public static final int day = 11;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day11()).main(year, day, 0);
		(new Day11()).main(year, day, 1);
		(new Day11()).main(year, day, 2);
		(new Day11()).main(year, day, 3);
	}
	
	Map<String, Integer> memo = new HashMap<>();
	public int countPaths(Map<String, String[]> paths, String node, String goal, String tab) {
		String key = node+","+goal;
		if(memo.containsKey(key)) return memo.get(key);
		
		if(node.equals(goal)) return 1;
		
		int count = 0;
		for(String neighbor : paths.get(node)){
			debug(tab + neighbor);
			count += countPaths(paths, neighbor, goal, tab+" ");
		}
		
		memo.put(key, count);
		return count;
	}
	
	public String part1(String[] input) {
		memo.clear();
		Map<String, String[]> paths = new HashMap<>();
		String start = "you";
		
		String[] vals;
		for(String s : input) {
			vals = s.split(": ");
			paths.put(vals[0], vals[1].split(" "));
		}
		
		int count = countPaths(paths, start, "out", "");
		
		return ""+count;
	}
	
	Map<String, Pair<Long, Boolean[]>> memoTrack = new HashMap<>();
	public Long trackPaths(Map<String, String[]> paths, String node, String goal, String tab, boolean seen_fft, boolean seen_dac) {
		String key = node+","+goal+","+seen_fft+","+seen_dac;
		if(memoTrack.containsKey(key)) return memoTrack.get(key).left;
		
		if(node.equals(goal)) {
			if(seen_fft && seen_dac) return 1L;
			return 0L;
		}
		
		long count = 0;
		for(String neighbor : paths.get(node)){
			debug(tab + neighbor+","+seen_fft+","+seen_dac);
			count += trackPaths(paths, neighbor, goal, tab+" ", seen_fft || neighbor.equals("fft"), seen_dac || neighbor.equals("dac"));
		}
		
		memoTrack.put(key, new Pair<Long, Boolean[]>(count, new Boolean[] {seen_fft, seen_dac}));
		return count;
	}
	
	public String part2(String[] input) {
		memoTrack.clear();
		Map<String, String[]> paths = new HashMap<>();
		String start = "svr";
		
		String[] vals;
		for(String s : input) {
			vals = s.split(": ");
			paths.put(vals[0], vals[1].split(" "));
		}
		
		long count = trackPaths(paths, start, "out", "", false, false);
		
		return ""+count;
	}
}
