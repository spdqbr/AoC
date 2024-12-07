package com.spdqbr.aoc.y2024;
import java.util.Arrays;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.NCounter;
import com.spdqbr.aoc.utils.Utils;

public class Day07_Recursive extends Solution {
	public static final int day = 7;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day07_Recursive()).main(year, day, 0);
		(new Day07_Recursive()).main(year, day, 1);
		(new Day07_Recursive()).main(year, day, 2);
		(new Day07_Recursive()).main(year, day, 3);
	}
	
	public static final char[] operatorsPart1 = { '*', '+' };
	
	public boolean evalPart1(long target, long accumulator, int index, long[] values) {
		if(index == values.length) return accumulator == target;
		
		for(char c : operatorsPart1) {
			switch(c) {
			case '*':
				if(evalPart1(target, accumulator  * values[index], index+1, values)) return true; break;
			case '+':
				if(evalPart1(target, accumulator  + values[index], index+1, values)) return true; break;
			}
		}
		return false;
	}
	
	public String part1(String[] input) {
		long target;
		String[] data;
		long[] values;
		long sum = 0;
		for(int i = 0; i < input.length; i++) {
			data = input[i].split(": ");
			target = Long.parseLong(data[0]);
			data = data[1].split(" +");
			values = Utils.stringArraytoLongArray(data);

			if(evalPart1(target, values[0], 1, values)) {
				sum += target;
			}
		}
		return ""+sum;
	}
	
	public static final char[] operatorsPart2 = { '*', '+', '|' };
	
	public boolean evalPart2(long target, long accumulator, int index, long[] values) {
		if(index == values.length) return accumulator == target;
		
		for(char c : operatorsPart2) {
			switch(c) {
			case '*':
				if(evalPart2(target, accumulator  * values[index], index+1, values)) return true; break;
			case '+':
				if(evalPart2(target, accumulator  + values[index], index+1, values)) return true; break;
			case '|':
				if(evalPart2(target, Long.parseLong(accumulator +""+ values[index]), index+1, values)) return true; break;
			}
		}
		return false;
	}
	
	public String part2(String[] input) {
		long target;
		String[] data;
		long[] values;
		long sum = 0;
		for(int i = 0; i < input.length; i++) {
			data = input[i].split(": ");
			target = Long.parseLong(data[0]);
			data = data[1].split(" +");
			values = Utils.stringArraytoLongArray(data);

			if(evalPart2(target, values[0], 1, values)) {
				sum += target;
			}
		}
		return ""+sum;
	}
}
