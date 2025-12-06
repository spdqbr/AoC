package com.spdqbr.aoc.y2025;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.spdqbr.aoc.Solution;

public class Day06 extends Solution {
	public static final int day = 6;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day06()).main(year, day, 0);
		(new Day06()).main(year, day, 1);
		(new Day06()).main(year, day, 2);
		(new Day06()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		List<Stack<Long>> stacks = new ArrayList<>();
		
		String[] strings;
		long sum = 0;
		for(String s : input) {
			strings = s.trim().split(" +");
			
			for(int i = 0; i < strings.length; i++) {
				if(stacks.size() == i) {
					stacks.add(i, new Stack<Long>());
				}
				if(strings[0].matches("\\d+")) {
					stacks.get(i).add(Long.parseLong(strings[i]));
				} else {
					Long total = null;
					if(strings[i].equals("*")) {
						total = stacks.get(i).pop();
						while(!stacks.get(i).isEmpty()) {
							total *= stacks.get(i).pop();
						}
					}else {
						total = stacks.get(i).pop();
						while(!stacks.get(i).isEmpty()) {
							total += stacks.get(i).pop();
						}
					}
					debug(total);
					sum += total;
				}
			}
		}
		return ""+sum;
	}
	
	public long collapseBuffers(List<StringBuilder> sbs, char op) {
		long total = 0;
		for(int i = 0; i < sbs.size(); i++) {
			if(i == 0) {
				total = Long.parseLong(sbs.get(i).toString());
			}else {
				if(op == '*' ) {
					total *= Long.parseLong(sbs.get(i).toString());
				}else {
					total += Long.parseLong(sbs.get(i).toString());
				}
			}
		}
		debug(total);
		return total;
	}
	
	public String part2(String[] input) {
		long sum = 0;
		List<StringBuilder> sbs = new ArrayList<>();
		int problemCol = 0;
		char op = ' ';
		char c;
		for(int col = 0; col < input[0].length(); col++) {
			boolean allBlank = true;
			for(int row = 0; row < input.length; row ++) {
				c = input[row].charAt(col);
				if(c != ' ') {
					allBlank = false;
					if(sbs.size() == col - problemCol) {
						sbs.add(new StringBuilder());
					}

					if(c != '*' && c != '+'){
						sbs.get(col-problemCol).append(c);
					}else {
						op = c;
					}
				}
			}
			if(allBlank) {
				problemCol=col+1;
				sum += collapseBuffers(sbs, op);
				sbs.clear();
			}
		}
		sum += collapseBuffers(sbs, op);
		
		return ""+sum;
	}
}
