package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Utils;

public class Day02 extends Solution {
	public static final int day = 2;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day02()).main(year, day, 0);
		(new Day02()).main(year, day, 1);
		(new Day02()).main(year, day, 2);
		(new Day02()).main(year, day, 3);
	}
	
	public static int signum(int i) {
		if(i == 0) return 0;
		if(i < 0) return -1;
		return 1;
	}
	
	public static boolean isSafe(List<Integer> list) {
		int signum = signum(list.get(1)-list.get(0));
		int diff;
		for(int i = 1; i < list.size(); i++) {
			diff = list.get(i) - list.get(i-1);
			if(signum(diff) != signum) return false;
			diff = Math.abs(diff);
			if(diff > 3 || diff < 1) return false;
		}
		return true;
	}
	
	public String part1(String[] input) {
		int count = 0;
		for(String line : input) {
			if(isSafe(Utils.stringArraytoIntegerList(line.split("\\s+")))) {
				count ++;
			}
		}
		return ""+count;
	}
	
	public static boolean safeDelete(List<Integer> list) {
		List<Integer> test = new ArrayList<>(list.size());
		for(int i = 0; i < list.size(); i++) {
			test.clear();
			for(int j = 0; j < list.size(); j++) {
				if(i != j) {
					test.add(list.get(j));
				}
			}
			if(isSafe(test)) return true;
		}
		return false;
	}
	
	public String part2(String[] input) {
		int count = 0;
		for(String line : input) {
			if(safeDelete(Utils.stringArraytoIntegerList(line.split("\\s+")))) {
				count ++;
			}
		}
		return ""+count;
	}
}
