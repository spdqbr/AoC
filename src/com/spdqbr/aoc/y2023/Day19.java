package com.spdqbr.aoc.y2023;
import java.util.HashMap;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Utils;

public class Day19 extends Solution {
	public static final int day = 19;
	public static final int year = 2023;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day19()).main(year, day, 0);
		(new Day19()).main(year, day, 1);
//		(new Day19()).main(year, day, 2);
//		(new Day19()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		Map<String, String[]> rules = new HashMap<>();
		String[] rule;
		int i = 0;
		for(;i <input.length; i++) {
			if(input[i].isEmpty()) break;
			
			rule = input[i].split("[}{,]");
			rules.put(rule[0], rule);			
		}
		i++;
		int[] part;
		String pipeline;
		long sum = 0;
		for(; i < input.length; i++) {
			part = Utils.stringArraytoIntArray(input[i].replaceAll("[}{xmas=]", "").split(","));
			pipeline = "in";
			while( !pipeline.equals("R") && !pipeline.endsWith("A") ) {
				pipeline =  applyRule(part, rules.get(pipeline));
//				System.out.println(pipeline);
			}
			if(pipeline.equals("A")) {
				sum += sumArray(part);
			}
		}
		
		
		return ""+sum;
	}
	
	public long sumArray(int[] a) {
		long sum = 0;
		for(int i : a) {
			sum += i;
		}
		return sum;
	}
	
	
	public String applyRule(int[] part, String[] rule) {
		String[] rulePart;
		for(int i=1; i < rule.length; i++) {
			rulePart = rule[i].split("[<>:]");
			if(rulePart.length == 1) return rulePart[0];
			int test = Integer.parseInt(rulePart[1]);
			if(rule[i].contains("<")){
				if(getVal(part, rulePart[0]) < test) return rulePart[2];
			}else if(rule[i].contains(">")){
				if(getVal(part, rulePart[0]) > test) return rulePart[2];
			}
		}
		return null;
	}
	
	public int getVal(int[] part, String c) {
		switch(c) {
		case "x": return part[0];
		case "m": return part[1];
		case "a": return part[2];
		default: return part[3];
		}
	}
	
	public String part2(String[] input) {
		Map<String, String[]> rules = new HashMap<>();
		String[] rule;
		int i = 0;
		for(;i <input.length; i++) {
			if(input[i].isEmpty()) break;
			
			rule = input[i].split("[}{,]");
			rules.put(rule[0], rule);			
		}
		
		return "";
	}
}
