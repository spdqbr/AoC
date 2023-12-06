package com.spdqbr.aoc.y2023;
import static com.spdqbr.aoc.utils.Utils.stringArraytoBigIntegerArray;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.BigMathUtils;
import com.spdqbr.aoc.utils.BigRange;

public class Day05 extends Solution {
	public static final int day = 5;
	public static final int year = 2023;
	
	public BigInteger convert(BigInteger destStart, BigInteger sourceStart, BigInteger range, BigInteger value) {
		if(value.compareTo(sourceStart) < 0 || value.compareTo(sourceStart.add(range).subtract(BigInteger.ONE)) > 0 ) return value;
		
		return value.subtract(sourceStart).add(destStart);
	}
	
	public static void main(String[] args) {
		(new Day05()).main(year, day, 3);
	}
	
	public List<List<BigInteger[]>> parseRules(String[] input){
		List<List<BigInteger[]>> rules = new ArrayList<>();
		List<BigInteger[]> currentRule = new ArrayList<BigInteger[]>();;
		
		for(int i = 1; i < input.length; i++) {
			if(input[i].trim().isEmpty()) {
				if(currentRule.size() != 0) {
					rules.add(currentRule);
					currentRule = new ArrayList<BigInteger[]>();
				}
				i+=2;
			}
			if(i >= input.length) {
				rules.add(currentRule);
				break;
			}
			
			currentRule.add(stringArraytoBigIntegerArray(input[i].split(" ")));
		}
		
		rules.add(currentRule);
		
		return rules;
	}
	
	public String part1(String[] input) {
		Set<BigInteger> seeds = new HashSet<>();
		seeds.addAll(Arrays.asList(stringArraytoBigIntegerArray(input[0].split(": ")[1].split(" "))));
		
		List<List<BigInteger[]>> rules = parseRules(input);
		
		Set<BigInteger> newSeeds = new HashSet<>();
		Set<BigInteger> oldSeeds = new HashSet<>();
		BigInteger newSeed;
		for(List<BigInteger[]> ruleSet : rules) {
			newSeeds.clear();
			for(BigInteger[] rule : ruleSet) {
				oldSeeds.clear();
				oldSeeds.addAll(seeds);
				for(BigInteger seed : oldSeeds) {
					newSeed = convert(rule[0], rule[1], rule[2], seed);
					if(! newSeed.equals(seed)) {
						seeds.remove(seed);
						newSeeds.add(newSeed);
					}
				}
			}
			seeds.addAll(newSeeds);
		}
		
		BigInteger min = null;
		for(BigInteger seed : seeds) {
			min = BigMathUtils.min(min, seed);
		}
		
		return min.toString();
	}
	
	public BigRange dataToRange(BigInteger start, BigInteger length) {
		return new BigRange(start, start.add(length).subtract(BigInteger.ONE));
	}
	
	public Set<BigRange> splitRange(BigRange range, BigRange intersection){
		Set<BigRange> out = new HashSet<>();
		
		if(range.left.compareTo(intersection.left) < 0) 
			out.add(new BigRange(range.left, intersection.left.subtract(BigInteger.ONE)));
		if(range.right.compareTo(intersection.right) > 0) 
			out.add(new BigRange(intersection.right.add(BigInteger.ONE), range.right));
		
		return out;
	}
	
	public String part2(String[] input) {
		BigInteger[] seedData = stringArraytoBigIntegerArray(input[0].split(": ")[1].split(" "));
		Set<BigRange> seedRanges = new HashSet<BigRange>();
		for( int i = 0 ; i < seedData.length; i+=2) {
			seedRanges.add(dataToRange(seedData[i], seedData[i+1]));
		}
		
		List<List<BigInteger[]>> rules = parseRules(input);
		
		Set<BigRange> newSeedRanges = new HashSet<>();
		Set<BigRange> originalRanges = new HashSet<>();
		BigRange newRange, intersection;
		for(List<BigInteger[]> ruleSet : rules) {
			newSeedRanges.clear();
			for(BigInteger[] rule : ruleSet) {
				originalRanges.clear();
				originalRanges.addAll(seedRanges);
				for(BigRange range : originalRanges) {
					intersection = range.intersect(dataToRange(rule[1],rule[2]));
					if(intersection != null) {
						seedRanges.remove(range);
						seedRanges.addAll(splitRange(range, intersection));
						newRange = intersection.add(rule[0].subtract(rule[1]));
						newSeedRanges.add(newRange);
					}
				}
			}
			seedRanges.addAll(newSeedRanges);
		}
		BigInteger min = null;
		for(BigRange range : seedRanges) {
			min = BigMathUtils.min(min, range.left);
		}
		
		return min.toString();
	}
}