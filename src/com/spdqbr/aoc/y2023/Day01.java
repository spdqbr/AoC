package com.spdqbr.aoc.y2023;
import com.spdqbr.aoc.Solution;

public class Day01 extends Solution {
	public static final int day = 1;
	
	public static void main(String[] args) {
		(new Day01()).main(day, 3);
	}
	
	public int getNumFromString(String s) {
		String firstDigit = s.replaceAll("^[^\\d]*(\\d).*", "$1");
		String lastDigit = s.replaceAll("^.*(\\d)[^\\d]*", "$1");
		return Integer.parseInt(firstDigit+lastDigit);
	}
	
	public String part1(String[] input) {
		long sum = 0;
		long num = 0;
		for(String line : input) {
			sum += getNumFromString(line);
			sum += num;
		}
		return ""+sum;
	}
	
	public String part2(String[] input) {
		String[] wordnums = "one, two, three, four, five, six, seven, eight, nine".split(", ");
		long sum = 0;
		long num = 0;
		for(String line : input) {
			int min_ind = Integer.MAX_VALUE;
			int min_word = -1;
			int max_ind = Integer.MIN_VALUE;
			int max_word = -1;
			
			int ind;
			for( int i = 0; i < wordnums.length; i++ ) {
				ind = line.indexOf(wordnums[i]);
				if(ind > -1 && ind < min_ind) {
					min_ind = ind;
					min_word = i;
				}
				ind = line.lastIndexOf(wordnums[i]);
				if(ind > -1 && ind > max_ind) {
					max_ind = ind;
					max_word = i;
				}
			}
			if(max_word != -1 && !line.substring(max_ind).matches(".*[0-9]")) {
				line = line.replaceAll("^(.*)"+wordnums[max_word], "$1"+Integer.toString(max_word+1));
				
			}
			
			if(min_word != -1 && !line.substring(0, min_ind).matches(".*[0-9]")) {
				line = line.replaceFirst(wordnums[min_word], Integer.toString(min_word+1));
			}

			num = getNumFromString(line);
			sum += num;
		}
		
		return ""+sum;
	}
}
