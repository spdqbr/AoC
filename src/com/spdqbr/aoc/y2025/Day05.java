package com.spdqbr.aoc.y2025;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.BigRange;

public class Day05 extends Solution {
	public static final int day = 5;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day05()).main(year, day, 0);
		(new Day05()).main(year, day, 1);
		(new Day05()).main(year, day, 2);
		(new Day05()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		List<BigRange> ranges = new ArrayList<>();
		String[] val;
		BigInteger n;
		long count = 0;
		for(String s : input) {
			if(s.contains("-")){
				val = s.split("-");
				ranges.add(new BigRange(new BigInteger(val[0]), new BigInteger(val[1])));
			}else if (! s.isBlank()) {
				n = new BigInteger(s);
				for(BigRange r : ranges) {
					if(r.contains(new BigRange(n,n))) { 
						count++;
						break;
					}
				}
			}
		}
		return ""+count;
	}
	
	public static BigInteger max(BigInteger a, BigInteger b) {
		return a.compareTo(b) > 0 ? a : b;
	}
	
	public String part2(String[] input) {
		List<BigRange> ranges = new ArrayList<>();
		String[] val;
		for(String s : input) {
			if(s.contains("-")){
				val = s.split("-");
				ranges.add(new BigRange(new BigInteger(val[0]), new BigInteger(val[1])));
			}else if (! s.isBlank()) {
				break;
			}
		}
		
		Collections.sort(ranges, new Comparator<BigRange>() {
			@Override
			public int compare(BigRange o1, BigRange o2) {
				return o1.left.compareTo(o2.left);
			}
		});
		
		BigInteger maxRight = BigInteger.ZERO;
		BigInteger sum = BigInteger.ZERO;
		
		BigInteger left;
		for(BigRange range : ranges) {
			left = max(range.left, maxRight.add(BigInteger.ONE));
			maxRight = max(range.right, maxRight);
			sum = sum.add(max(BigInteger.ZERO, maxRight.subtract(left).add(BigInteger.ONE)));
		}
		return ""+sum;
	}
}
