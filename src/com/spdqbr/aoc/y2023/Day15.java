package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;

public class Day15 extends Solution {
	public static final int day = 15;
	public static final int year = 2023;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day15()).main(year, day, 0);
		(new Day15()).main(year, day, 1);
		(new Day15()).main(year, day, 2);
		(new Day15()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		String[] init = input[0].split(",");
		long sum = 0;
		for(String s : init) {
			sum += hash(s);
		}
		return ""+sum;
	}
	
	Map<String, Integer> labelMap = new HashMap<>();
	public int hash(String s) {
		if(!labelMap.containsKey(s)){
			int current = 0;
			for(char c : s.toCharArray()) {
				current += c;
				current *= 17;
				current %= 256;
			}
			labelMap.put(s, current);
		}
		return labelMap.get(s);
	}
	
	public void remove(List<Pair<String, Integer>> box, String label) {
		int found = -1;
		for(int i = 0; i < box.size(); i++) {
			if(box.get(i).left.equals(label)) {
				found = i;
				break;
			}
		}
		if(found > -1)
			box.remove(found);
	}
	
	public void upsert(List<Pair<String, Integer>> box, Pair<String, Integer> lens) {
		int found = -1;
		for(int i = 0; i < box.size(); i++) {
			if(box.get(i).left.equals(lens.left)) {
				found = i;
				break;
			}
		}
		if(found > -1) {
			box.remove(found);
			box.add(found, lens);
		}else {
			box.add(lens);
		}
	}
	
	public String part2(String[] input) {
		String[] init = input[0].split(",");
		List<List<Pair<String, Integer>>> boxes = new ArrayList<List<Pair<String, Integer>>>();
		for(int i = 0; i < 256; i++) boxes.add(new ArrayList<Pair<String, Integer>>());
		String[] thing;
		for(String s : init) {
			thing = s.split("[-=]");
			int hash = hash(thing[0]);
			if(thing.length == 1) {
				remove(boxes.get(hash), thing[0]);
			} else {
				upsert(boxes.get(hash), new Pair<String, Integer>(thing[0], Integer.parseInt(thing[1])));
			}
			debug(boxes);
		}
		
		long sum = 0;		
		for(int i = 0; i < boxes.size(); i++) {
			for(int j = 0; j < boxes.get(i).size(); j++) {
				debug("box "+i+" slot "+j+" FL "+ boxes.get(i).get(j).right +" = "+ ((i+1) * j * boxes.get(i).get(j).right));
				sum += (i+1) * (j+1) * boxes.get(i).get(j).right;
			}
		}
		return ""+sum;
	}
}
