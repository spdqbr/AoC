package com.spdqbr.aoc.y2025;
import java.util.HashMap;
import java.util.Map;

import com.spdqbr.aoc.Solution;

public class Day03 extends Solution {
	public static final int day = 3;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day03()).main(year, day, 0);
		(new Day03()).main(year, day, 1);
		(new Day03()).main(year, day, 2);
		(new Day03()).main(year, day, 3);
	}
	
	public String part1_orig(String[] input) {
		long sum = 0;
		for(String s : input) {
			int max = 0;
			
			int bestA = 0;
			int a, val;
			for(int i = 0; i < s.length(); i++) {
				a = (int)(s.charAt(i) - '0');
				if(a < bestA) {
					continue;
				}
				for(int j = i+1; j < s.length(); j++) {
					val = a*10+(int)(s.charAt(j) - '0');
					if(val > max) {
						max = val;
						bestA = a;
					}
				}
			}
			debug(max);
			sum += max;
		}
		
		return ""+sum;
	}
	
	public Map<String, Long> memo = new HashMap<>();
	
	public long findMax(String input, int minIndex, int batteries) {
		String key = input+"."+minIndex+"."+batteries;
		if(memo.containsKey(key)) {
			return memo.get(key);
		}
		if(batteries == 1) {
			int max = -1;
			int val;
			for(int i = minIndex; i < input.length(); i++) {
				val = input.charAt(i) - '0';
				if(val > max) max = val; 
			}
			debug(max);
			return max;
		}
		
		long max = -1;
		long charMax = -1;
		long val;
		long subMax = -1;
		for(int i = minIndex; i <= input.length()-batteries; i ++) {
			val = input.charAt(i) - '0';
			if( val < charMax) {
				continue;
			}
			charMax = val;
			val *= (long)Math.pow(10, batteries-1);
			debug(val);
			if(subMax == -1) {
				subMax = findMax(input, i+1, batteries-1);
			}
			val += findMax(input, i+1, batteries -1);
			debug(val);
			
			if(val > max) {
				max = val;
			}
		}
		memo.put(key, max);
		return max;
	}
	
	public String part1(String[] input) {
		long sum = 0;
		int batteries = 2;
		long max;
		int i = 0;
		for(String s : input) {
			max = findMax(s, 0, batteries);
			debug((i++)+" :: "+max);
			sum += max;
		}
		
		return ""+sum;
	}
	
	public String part2(String[] input) {
		long sum = 0;
		int batteries = 12;
		long max;
		int i = 0;
		for(String s : input) {
			max = findMax(s, 0, batteries);
			debug((i++)+" :: "+max);
			sum += max;
		}
		
		return ""+sum;
	}
}
