package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.List;

import com.spdqbr.aoc.Solution;

public class Day04 extends Solution {
	public static final int day = 4;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day04()).main(year, day, 0);
		(new Day04()).main(year, day, 1);
		(new Day04()).main(year, day, 2);
		(new Day04()).main(year, day, 3);
	}
	
	public static String TARGET = "XMAS";
	
	public String part1(String[] input) {
		int count = 0;
		for(int row = 0; row < input.length; row++) {
			for(int col = 0; col < input[row].length(); col++) {
				if(input[row].charAt(col) == TARGET.charAt(0)){
					List<String> strings = new ArrayList<>();
					for(int dr = -1; dr <= 1; dr++) {
						if(row + dr*TARGET.length() +1 < 0 || row +dr*TARGET.length() > input.length) continue;
						for(int dc = -1; dc <= 1; dc++) {
							if(col + dc*TARGET.length() +1 < 0 || col +dc*TARGET.length() > input[row].length()) continue;
							if(dr !=0 || dc != 0) {
								StringBuilder sb = new StringBuilder();
								for(int i = 0; i < TARGET.length(); i++) {
									sb.append(input[row+dr*i].charAt(col+dc*i));
								}
								sb.append(","+dr+","+dc);
								strings.add(sb.toString());
							}
						}
					}
					int subCount = 0;
					for(int i = 0; i < strings.size(); i++) {
						if(strings.get(i).startsWith(TARGET)){
							subCount++;
						}
					}
					count += subCount;
					debug(row+", "+col+" :: "+subCount+" :: "+count);
					debug(strings);
				}
			}
		}
		return ""+count;
	}
	
	public String part2(String[] input) {
		int count = 0;
		String s1, s2;
		for(int row = 1; row < input.length-1; row++) {
			for(int col = 1; col < input[row].length()-1; col++) {
				if(input[row].charAt(col) == 'A'){
					StringBuilder sb = new StringBuilder();
					StringBuilder sb2 = new StringBuilder();
					for(int dr = -1, dy = -1; dr <= 1; dr++, dy++) {
						sb.append(input[row+dr].charAt(col+dy));
						sb2.append(input[row+dr].charAt(col-dy));
					}
					s1 = sb.toString();
					s2 = sb2.toString();
					if( (s1.equals("MAS") || s1.equals("SAM")) && (s2.equals("MAS") || s2.equals("SAM"))) {
						count++;
					}
				}
			}
		}
		return ""+count;
	}
}
