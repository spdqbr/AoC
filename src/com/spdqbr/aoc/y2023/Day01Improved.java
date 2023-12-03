package com.spdqbr.aoc.y2023;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spdqbr.aoc.Solution;

public class Day01Improved extends Solution {
	public static final int day = 1;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day01Improved()).main(year, day, 3);
	}
	
	Pattern firstDigitPattern = Pattern.compile("^[^\\d]*(\\d).*");
	Pattern lastDigitPattern = Pattern.compile("^.*(\\d)[^\\d]*");
	
	public int getNumFromString(String s) {
		Matcher m = firstDigitPattern.matcher(s);
		m.find();
		String firstDigit = m.group(1);
		
		m = lastDigitPattern.matcher(s);
		m.find();
		String lastDigit = m.group(1);
		String number = firstDigit+lastDigit;
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			for(int i = 0; i < wordnums.length; i++) {
				number = number.replaceAll(wordnums[i], Integer.toString(i+1));
			}
			return Integer.parseInt(number);
		}
	}
	
	String[] wordnums = "one, two, three, four, five, six, seven, eight, nine".split(", ");
	
	public int getWordNumFromString(String s) {
		String firstDigit = s.replaceAll("^.*?(\\d|one|two|three|four|five|six|seven|eight|nine|zero).*", "$1");
		String lastDigit = s.replaceAll("^.*(\\d|one|two|three|four|five|six|seven|eight|nine|zero).*?$", "$1");
		String number = firstDigit+lastDigit;
		for(int i = 0; i < wordnums.length; i++) {
			number = number.replaceAll(wordnums[i], Integer.toString(i+1));
		}
		return Integer.parseInt(number);
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
		firstDigitPattern = Pattern.compile("^.*?(\\d|one|two|three|four|five|six|seven|eight|nine|zero).*");
		lastDigitPattern = Pattern.compile("^.*(\\d|one|two|three|four|five|six|seven|eight|nine|zero).*?$");
		return part1(input);
	}
}
