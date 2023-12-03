package com.spdqbr.aoc.y2023;
import java.util.HashMap;
import java.util.Map;

import com.spdqbr.aoc.Solution;

public class Day02 extends Solution {
	public static final int day = 2;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day02()).main(year, day, 0);
	}
	
	public String part1(String[] input) {
		Map<String, Integer> bag = new HashMap<>();
		bag.put("red",12);
		bag.put("green", 13);
		bag.put("blue",14);
		
		String[] rounds;
		String game;
		boolean validRound;
		int sum = 0;
		for(int i = 0; i < input.length; i++) {
			game = input[i];
			rounds = game.split(":")[1].split("; ");
			int valid = 0;
			for(String round : rounds) {
				validRound = true;
				for(String hand : round.split(", ")){
					String[] val = hand.strip().split(" ");
					if(Integer.parseInt(val[0]) > bag.get(val[1])) {
						validRound = false;
						break;
					}
				}
				if(validRound) valid++;
			}
			if(valid == rounds.length) {
				sum += i + 1;
//				System.out.println("Game "+(i+1)+" is valid.");
			}
		}
		
		return ""+sum;
	}
	
	public String part2(String[] input) {
		Map<String, Integer> bag = new HashMap<>();
		
		String[] rounds;
		String game;
		int sum = 0;
		for(int i = 0; i < input.length; i++) {
			game = input[i];
			rounds = game.split(":")[1].split("; ");
			bag.put("red",0);
			bag.put("green", 0);
			bag.put("blue",0);
			for(String round : rounds) {
				for(String hand : round.split(", ")){
					String[] val = hand.strip().split(" ");
					if(Integer.parseInt(val[0]) > bag.get(val[1])) {
						bag.put(val[1], Integer.parseInt(val[0]));
					}
				}
			}
			sum += bag.get("red") * bag.get("blue") * bag.get("green");
		}
		
		return ""+sum;
	}
}
