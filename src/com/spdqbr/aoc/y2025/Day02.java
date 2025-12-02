package com.spdqbr.aoc.y2025;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spdqbr.aoc.Solution;

public class Day02 extends Solution {
	public static final int day = 2;
	public static final int year = 2025;
	
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day02()).main(year, day, 0);
		(new Day02()).main(year, day, 1);
		(new Day02()).main(year, day, 2);
		(new Day02()).main(year, day, 3);
	}
	
	public boolean isValid(long n, Pattern p) {
		String nstr = Long.toString(n);
		Matcher m = p.matcher(nstr);
		return ! m.find();
	}
	
	public String part1(String[] input) {
		Pattern p = Pattern.compile("^(.+)\\1$");
		String[] ranges = input[0].split(",");
		long sum = 0;
		for(String range :  ranges) {
			long lower = Long.parseLong(range.split("-")[0]);
			long upper = Long.parseLong(range.split("-")[1]);
			
			for(long i = lower; i <= upper; i++) {
				if( ! isValid(i, p)) {
					sum += i;
				}
			}
		}
		return ""+sum;
	}
	
	public String part2(String[] input) {
		Pattern p = Pattern.compile("^(.+)\\1+$");
		String[] ranges = input[0].split(",");
		long sum = 0;
		for(String range :  ranges) {
			long lower = Long.parseLong(range.split("-")[0]);
			long upper = Long.parseLong(range.split("-")[1]);
			
			for(long i = lower; i <= upper; i++) {
				if( ! isValid(i, p)) {
					sum += i;
				}
			}
		}
		return ""+sum;
	}
}
