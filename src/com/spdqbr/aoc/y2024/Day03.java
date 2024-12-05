package com.spdqbr.aoc.y2024;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spdqbr.aoc.Solution;

public class Day03 extends Solution {
	public static final int day = 3;
	public static final int year = 2024;
	
	
	public static Pattern MUL_PATTERN = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
	public static Pattern DO_PATTERN = Pattern.compile("(^|do\\(\\))(.*?)($|don't\\(\\))");
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day03()).main(year, day, 0);
		(new Day03()).main(year, day, 1);
		(new Day03()).main(year, day, 2);
		(new Day03()).main(year, day, 3);
	}
	
	public BigInteger parse(String s) {
		BigInteger sum = BigInteger.ZERO;
		Matcher m = MUL_PATTERN.matcher(s);
		
		while(m.find()) {
			sum = sum.add((new BigInteger(m.group(1))).multiply(new BigInteger(m.group(2)))); 
		}
		
		return sum;
	}
	
	public String part1(String[] input) {
		BigInteger sum = BigInteger.ZERO;
		for(int i = 0; i < input.length; i++) {
			sum = sum.add(parse(input[i])); 
		}
		return ""+sum;
	}
	
	
	public String part2(String[] input) {
		BigInteger sum = BigInteger.ZERO;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length; i++) {
			sb.append(input[i]);
		}
		
		String in = sb.toString();
		
			Matcher m = DO_PATTERN.matcher(in);
			
			while(m.find()) {
				sum = sum.add(parse(m.group(2)));
			}
		
		return ""+sum;
	}
}
