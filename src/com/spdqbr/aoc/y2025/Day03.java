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
	
	public long findMax(String input, int minIndex, int batteries) {
		if(batteries == 0) {
			return 0;
		}
		int indexOfNextBiggest = -1;
		for(int i = 9; indexOfNextBiggest == -1 && i > 0; i--) {
			for(int j = minIndex; j <= input.length()-batteries; j++) {
				if(input.charAt(j)-'0' == i) {
					indexOfNextBiggest = j;
					break;
				}
			}
		}
		
		long value = (long)((input.charAt(indexOfNextBiggest)-'0')*Math.pow(10, batteries-1));
		return value + findMax(input, indexOfNextBiggest+1, batteries-1);
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
