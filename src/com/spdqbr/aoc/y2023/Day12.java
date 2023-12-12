package com.spdqbr.aoc.y2023;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Utils;

public class Day12 extends Solution {
	public static final int day = 12;
	public static final int year = 2023;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day12()).main(year, day, 0);
		(new Day12()).main(year, day, 1);
		(new Day12()).main(year, day, 2);
		(new Day12()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		long sum = 0;
		for(String line : input) {
			String springPattern = line.split(" ")[0];
			int[] springGroups = Utils.stringArraytoIntArray(line.split(" ")[1].split(","));
			sum += memoCount(springPattern, 0, springGroups, 0, "");
		}
		return ""+sum;
	}
	
	Map<String, Long> cache = new ConcurrentHashMap<>();
	public long memoCount(String springPattern, int patternStart, int[] springGroups, int groupStart, String tab) {
		String key = springPattern+"|"+patternStart+"|"+Arrays.toString(springGroups)+"|"+groupStart;
		if(!cache.containsKey(key)) {
			if(groupStart >= springGroups.length) {
				for(int i = patternStart; i < springPattern.length(); i++) {
					if(springPattern.charAt(i) == '#') {
						cache.put(key, 0L);
						return 0;
					}
				}
				debug("*********   "+tab);
				cache.put(key, 1L);
				return 1;
			}
			
			int requiredLeftoverSpace = sumFrom(groupStart+1, springGroups) + (springGroups.length - groupStart - 1);
			long count = 0;
			outer:
			for(int i = 0; i < (springPattern.length() -  requiredLeftoverSpace); i++) {
				String candidate = Utils.repeatString(".", i)+Utils.repeatString("#", springGroups[groupStart])+".";
				
				if(patternStart+candidate.length()-1 > springPattern.length()) continue;
				
				for(int j = 0; j < candidate.length(); j++) {
					if(DEBUG) {
						debug(tab+candidate);
						debug(tab+Utils.lPad("^", j+1));
						debug(springPattern);
						debug(Utils.lPad(".",j)+Utils.lPad("^", patternStart+1));
						debug("");
					}
					if( !(j+patternStart == springPattern.length() && j == candidate.length() -1 )
						&&  candidate.charAt(j) != springPattern.charAt(j+patternStart)
						&& springPattern.charAt(j+patternStart) != '?' ) {
						continue outer;
					}
				}
				count += memoCount(springPattern, patternStart+candidate.length(), springGroups, groupStart+1, tab+candidate);
			}
			cache.put(key, count);
		}
		
		return cache.get(key);
	}
	
	public int sumFrom(int start, int[] a) {
		int sum = 0;
		for(int i = start; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
	
	public String part2(String[] input) {
		long sum = 0;
		for(String line : input) {
			String springPattern = line.split(" ")[0];
			int[] springGroups = Utils.stringArraytoIntArray(line.split(" ")[1].split(","));
			sum += memoCount(join(springPattern,5), 0, join(springGroups, 5), 0, "");
		}
		return ""+sum;
	}
	
	public String join(String s, int copies) {
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		for(int i = 1; i < copies; i++) {
			sb.append("?");
			sb.append(s);
		}
		return sb.toString();
	}
	
	public int[] join(int[] a, int copies) {
		int[] out = new int[a.length*copies];
		for(int i = 0; i < copies; i++) {
			System.arraycopy(a, 0, out, a.length*i, a.length);
		}
		return out;
	}
}
