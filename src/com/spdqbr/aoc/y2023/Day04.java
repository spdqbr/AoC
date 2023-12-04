package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spdqbr.aoc.Solution;

public class Day04 extends Solution {
	public static final int day = 4;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day04()).main(year, day, 3);
	}
	
	public int countWinningNumbers(String[] data) {
		String[] winning, card;
		int count = 0;
		winning = data[1].trim().split(" +");
		card = data[2].trim().split(" +");
		
		Arrays.sort(winning);
		
		for(String s : card) {
			if(Arrays.binarySearch(winning, s) >= 0) {
				count++;
			}
		}
		return count;
	}
	
	public String part1(String[] input) {
		String[] data;
		
		long sum = 0;
		int winners;
		for(String line : input) {
			data = line.split("( \\||: )");
			winners = countWinningNumbers(data);
			sum += winners == 0 ? 0 : 1 << (winners-1);
		}
		
		return ""+sum;
	}
	
	public String part2(String[] input) {
		List<long[]> info = new ArrayList<long[]>(input.length);
		
		int value;
		for(int i = 0; i < input.length; i++) {
			value = countWinningNumbers(input[i].split("( \\||: )"));
			info.add(new long[] { 1L, value });
		}
		
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < info.get(i)[1]; j ++) {
				info.get(i+1+j)[0] += info.get(i)[0];
			}
		}
		
		long sum = 0;
		for(long[] val : info) {
			sum += val[0];
		}
		
		return ""+sum;
	}
}
