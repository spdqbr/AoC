package com.spdqbr.aoc.y2024;
import java.util.Arrays;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.NCounter;
import com.spdqbr.aoc.utils.Utils;

public class Day07 extends Solution {
	public static final int day = 7;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day07()).main(year, day, 0);
		(new Day07()).main(year, day, 1);
		(new Day07()).main(year, day, 2);
		(new Day07()).main(year, day, 3);
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
			
			NCounter<Character> counter = new NCounter<Character>(values.length-1, Arrays.asList(new Character[] {'*', '+'})); 			
			for(List<Character> operands : counter) {
				long val = values[0];
				for(int j = 1; j < values.length; j++) {
					switch(operands.get(j-1)) {
					case '*': val *= values[j]; break;
					case '+': val += values[j]; break;
					}
				}
				if(val == target) {
					sum += target;
					break;
				}
			}
		}
		return ""+sum;
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
			
			NCounter<Character> counter = new NCounter<Character>(values.length-1, Arrays.asList(new Character[] {'*', '+', '|'})); 			
			for(List<Character> operands : counter) {
				long val = values[0];
				for(int j = 1; j < values.length; j++) {
					switch(operands.get(j-1)) {
					case '*': val *= values[j]; break;
					case '+': val += values[j]; break;
					case '|': val = Long.parseLong(val+""+values[j]); break;
					}
				}
				if(val == target) {
					sum += target;
					break;
				}
			}
		}
		return ""+sum;
	}
}
