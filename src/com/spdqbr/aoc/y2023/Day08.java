package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.MathUtils;

public class Day08 extends Solution {
	public static final int day = 8;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day08()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		String LR = input[0];
		
		Map<String, String[]> nodes = new HashMap<>();
		
		String[] s, t;
		for(int i = 2; i < input.length; i++) {
			s = input[i].split(" = ");
			t = s[1].replaceAll("[\\(\\) ]", "").split(",");
			nodes.put(s[0], t);
		}
		
		int steps = 0;
		String node = "AAA";
		for(; !node.equals("ZZZ"); steps++) {
			char c = LR.charAt(steps % LR.length());
			if( node.equals("ZZZ")) break;
			node = nodes.get(node)[c == 'L' ? 0 : 1];
		}
		
		return ""+steps;
	}
	
	public long isFinished(long[] zMap) {
		long lcm = 1;
		for(long z : zMap) {
			if(z == -1) return -1;
			lcm = MathUtils.lcm(lcm, z);
		}
		return lcm;
	}
	
	public String part2(String[] input) {
		String LR = input[0];

		Map<String, String[]> nodeMap = new HashMap<>();
		
		String[] s, t;
		for(int i = 2; i < input.length; i++) {
			s = input[i].split(" = ");
			t = s[1].replaceAll("[\\(\\) ]", "").split(",");
			nodeMap.put(s[0], t);
		}
		

		List<String> nodes = new ArrayList<>();
		
		for(String node : nodeMap.keySet()) {
			if(node.endsWith("A")) nodes.add(node);
		}
		
		long[] zMap = new long[nodes.size()];
		Arrays.fill(zMap, -1);
		
		long steps = 0;
		int i = 0;
		String node;
		while( (steps = isFinished(zMap)) == -1) {
			char c = LR.charAt(i % LR.length());
			for(int j = 0 ; j < nodes.size(); j++) {
				if(zMap[j] != -1) continue;
				node = nodes.remove(j);
				nodes.add(j, nodeMap.get(node)[c == 'L' ? 0 : 1]);
				if(nodes.get(j).endsWith("Z")) {
					zMap[j] = (i+1);
				}
			}
			i++;
		}
		
		return ""+steps;
	}
}
