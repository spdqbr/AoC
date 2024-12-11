package com.spdqbr.aoc.y2024;
import java.util.HashMap;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;
import com.spdqbr.aoc.utils.Utils;

public class Day11 extends Solution {
	public static final int day = 11;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day11()).main(year, day, 0);
		(new Day11()).main(year, day, 1);
		(new Day11()).main(year, day, 2);
		(new Day11()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		long[] data = Utils.stringArraytoLongArray(input[0].split(" "));

		long steps = 25;
		long count = 0;
		long stones;
		for(long datum : data) {
			stones = step(new Pair<Long, Long>(datum, steps));
			count += stones;
		}
		
		return ""+count;
	}
	
	public static Map<Pair<Long, Long>,Long> memo = new HashMap<>();
	
	public long step(Pair<Long, Long> stoneSteps) {
		if(stoneSteps.left < 0) throw new RuntimeException("Overflow");
		if(memo.containsKey(stoneSteps)) {
			return memo.get(stoneSteps);
		}
		
		String s = Long.toString(stoneSteps.left);
		long val = -1;
		if(stoneSteps.right == 0) {
			val = 1;
		} else if(stoneSteps.left == 0) {
			val = step(new Pair<Long, Long>(1L, stoneSteps.right-1));
		} else if(s.length() % 2 == 0){
			long a = Long.parseLong(s.substring(0, s.length()/2));
			long b = Long.parseLong(s.substring(s.length()/2));
			val = step(new Pair<Long, Long>(a, stoneSteps.right-1)) + step(new Pair<Long, Long>(b, stoneSteps.right-1));
		} else {
			val = step(new Pair<Long, Long>(stoneSteps.left*2024, stoneSteps.right-1)); 
		}
		
		memo.put(stoneSteps, val);
		return val;
	}
	
	public String part2(String[] input) {
		long[] data = Utils.stringArraytoLongArray(input[0].split(" "));

		long steps = 75;
		long count = 0;
		long stones;
		for(long datum : data) {
			stones = step(new Pair<Long, Long>(datum, steps));
			count += stones;
		}
		
		return ""+count;
	}
}
