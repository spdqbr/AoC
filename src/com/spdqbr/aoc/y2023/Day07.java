package com.spdqbr.aoc.y2023;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;

public class Day07 extends Solution {
	public static final int day = 7;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day07()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		Map<String, Long> scores = new HashMap<>();
		List<String> games = new ArrayList<>();
		String[] val;
		for(String i : input) {
			val = i.split(" +");
			scores.put(val[0], Long.parseLong(val[1]));
			games.add(val[0]);
		}
		
		games.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return compareHands(o1, o2);
			}
			
		});
		
		long sum = 0;
		for(int i = 0; i < games.size(); i++) {
			sum += ( (i+1) * scores.get(games.get(i)));
		}
		
		return ""+sum;
	}
	
	public String part2(String[] input) {
		Map<String, BigInteger> scores = new HashMap<>();
		List<Pair<String, String>> games = new ArrayList<>();
		String[] val;
		String best;
		
		for(String i : input) {
			val = i.split(" +");
			scores.put(val[0], new BigInteger(val[1]));
			best = getBestHand(val[0]);
			games.add(new Pair<String, String>(val[0], best));
		}
		
		games.sort(new Comparator<Pair<String,String>>() {
			@Override
			public int compare(Pair<String,String> o1, Pair<String,String> o2) {
				return compareHands(o1.right, o1.left, o2.right, o2.left, true);
			}
			
		});
		
		BigInteger sum = BigInteger.ZERO;
		for(int i = 0; i < games.size(); i++) {
			sum = sum.add(scores.get(games.get(i).left).multiply(BigInteger.valueOf(i+1)));
		}
		
		return ""+sum;
	}
	
	public int compareHands(String camelHand, String otherCamelHand) {
		return compareHands(camelHand, camelHand, otherCamelHand, otherCamelHand, false);
	}
	
	public int compareHands(String camelHand, String orig, String otherCamelHand, String oorig, boolean jokers) {
		Map<Character, Integer> c1 = countChars(camelHand);
		Map<Character, Integer> c2 = countChars(otherCamelHand);
		
		int c1Max = mapMax(c1);
		int c2Max = mapMax(c2);
		if(c1Max != c2Max) {
			return Integer.compare(c1Max, c2Max);
		}
		
		int cmp = compareCards(camelHand, orig, otherCamelHand, oorig, jokers);
		if(c1Max == 4) {
			return cmp;
		}else if(c1Max == 3) {
			if(countValues(c1, 2) == 1) {
				if(countValues(c2, 2) == 1) {
					return cmp;
				}
				return 1;
			}else if(countValues(c2, 2) == 1) {
				return -1;
			}else {
				return cmp;
			}
		}else if (c1Max == 2) {
			if(countValues(c1, 2) == 2) {
				if(countValues(c2, 2) == 2) {
					return cmp;	
				}else {
					return 1;
				}
			} else if(countValues(c2, 2) == 2) {
				return -1;
			} else {
				return cmp;
			}
		}else {
			return cmp;
		}
	}
	
	public String getBestHand(String s){
		List<String> jokerHands = new ArrayList<>();
		Map<Character, Integer> m = countChars(s);
		if(! m.containsKey('J')) {
			return s;
		}
		
		for(int c = 2; c < 14; c++) {
			jokerHands.add(s.replaceFirst("J", ""+intToCard(c)));
		}
		
		String best = getBestHand(jokerHands.get(0));
		String otherBest;
		for(int i = 1; i < jokerHands.size(); i++) {
			otherBest = getBestHand(jokerHands.get(i));
			if(compareHands(best, s, otherBest, s, true) < 0) {
				best = otherBest;
			}
		}
		
		return best;
	}
	
	
	public int countValues(Map<Character, Integer> m, int value) {
		int out = 0;
		for(Character c : m.keySet()) {
			if(m.get(c) == value) out++;
		}
		return out;
	}
	
	public int compareCards(String camelHand, String otherCamelHand, boolean jokers) {
		return compareCards(camelHand, camelHand, otherCamelHand, otherCamelHand, jokers);
	}
	
	public int compareCards(String camelHand, String orig, String otherCamelHand, String oorig, boolean jokers) {
		for(int i = 0; i < camelHand.length(); i++) {
			if( orig.charAt(i) != oorig.charAt(i)) {
				return Integer.compare(getCardValue(orig.charAt(i), jokers), getCardValue(oorig.charAt(i), jokers));
			}
		}
		return 0;
	}
	
	public char intToCard(int i) {
		if(i <= 9) return (char)(i+'0');
		switch(i) {
		case 10: return 'T';
		case 12: return 'Q';
		case 13: return 'K';
		}
		return 'A';
	}

	public int getCardValue(char c, boolean jokers) {
		if(c <= '9') return c - '0';
		switch(c) {
		case 'T': return 10;
		case 'J': return jokers? 1 : 11;
		case 'Q': return 12;
		case 'K': return 13;
		}
		return 14;
	}
	
	public int mapMax(Map<Character, Integer> m) {
		int max = Integer.MIN_VALUE;
		for(Character c : m.keySet()) {
			max = m.get(c) > max ? m.get(c) : max;
		}
		return max;
	}
	
	public Map<Character, Integer> countChars(String s){
		Map<Character, Integer> out = new HashMap<>();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(!out.containsKey(c)){
				out.put(c, 0);
			}
			out.put(c, out.get(c)+1);
		}
		return out;
	}
}
