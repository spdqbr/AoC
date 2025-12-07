package com.spdqbr.aoc.y2025;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.spdqbr.aoc.Solution;

public class Day07 extends Solution {
	public static final int day = 7;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day07()).main(year, day, 0);
		(new Day07()).main(year, day, 1);
		(new Day07()).main(year, day, 2);
		(new Day07()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		boolean lasers[] = new boolean[input[0].length()];
		lasers[input[0].indexOf('S')] = true;
		int splits = 0;
		
		boolean nextLasers[] = new boolean[lasers.length];
		Arrays.fill(nextLasers, false);
		for(int i = 1; i < input.length; i++) {
			for(int j  = 0; j < input[i].length(); j++) {
				if(lasers[j]) {
					if(input[i].charAt(j) == '^') {
						nextLasers[j-1] = true;
						nextLasers[j+1] = true;
						splits++;
					}else {
						nextLasers[j] = true;
					}
				}
			}
			System.arraycopy(nextLasers, 0, lasers, 0, lasers.length);
			Arrays.fill(nextLasers, false);
		}
		return ""+splits;
	}
	
	Map<String, Long> memo = new HashMap<>();
	
	public long memoCount(String[] input, int beamRow, int beamCol) {
		if(beamRow == 0) memo.clear();
		
		if(beamRow == input.length-1) return 1;
		
		String key = beamRow+","+beamCol;
		if(memo.containsKey(key)) return memo.get(key);

		long sum = 0;
		if(input[beamRow+1].charAt(beamCol) == '^') {
			sum = memoCount(input, beamRow+2, beamCol-1);
			sum += memoCount(input, beamRow+2, beamCol+1);
			
		} else {
			sum = memoCount(input, beamRow+2, beamCol);
		}
		memo.put(key, sum);
		return sum;
	}
	
	public String part2(String[] input) {
		int beamCol = input[0].indexOf('S');
		return ""+memoCount(input, 1, beamCol);
	}
}
