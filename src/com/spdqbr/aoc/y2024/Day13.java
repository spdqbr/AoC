package com.spdqbr.aoc.y2024;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.MathUtils;
import com.spdqbr.aoc.utils.Triple;

public class Day13 extends Solution {
	public static final int day = 13;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day13()).main(year, day, 0);
		(new Day13()).main(year, day, 1);
		(new Day13()).main(year, day, 2);
		(new Day13()).main(year, day, 3);
	}
	
	Pattern p = Pattern.compile("\\+(\\d+)");
	Pattern pp = Pattern.compile("=(\\d+)");
	
	/** Use Cramer's Rule for solving system of linear equations in matrix form
	 *
	 * 	[ a[0], a[1] ] * [ presses ] = [ prize[0] ]
	 *  [ b[0], b[1] ] * [ presses ] = [ prize[1] ]
	 */
	public long findMin(long[]  a, long[]  b, long[] prize) {
		long determinate = a[0]*b[1] - a[1]*b[0];
		if(determinate == 0) return -1;
		long aDet = prize[0]*b[1] - prize[1]*b[0];
		long bDet = prize[1]*a[0] - prize[0]*a[1];
		if(aDet % determinate == 0 && bDet % determinate == 0) {
            long aCount = aDet / determinate;
            long bCount = bDet / determinate;
            if(aCount >= 0 && bCount >= 0) {
            	return aCount*3 + bCount;
            }
		}
		return -1;
	}
	
	public String part1(String[] input) {
		long sum = 0;
		Matcher m;
		long[] a = new long[2];
		long[] b = new long[2];
		long[] prize = new long[2];
		for(int i = 0; i < input.length; i++) {
			m = p.matcher(input[i++]);
			m.find();
			a[0] = Integer.parseInt(m.group(1));
			m.find();
			a[1] = Integer.parseInt(m.group(1));
			
			m = p.matcher(input[i++]);
			m.find();
			b[0] = Integer.parseInt(m.group(1));
			m.find();
			b[1] = Integer.parseInt(m.group(1));
			
			m = pp.matcher(input[i++]);
			m.find();
			prize[0] = Integer.parseInt(m.group(1));
			m.find();
			prize[1] = Integer.parseInt(m.group(1));
			
			long min = findMin(a, b, prize);
			if(min > 0) {
				sum += min;
			}
		}
		
		return ""+sum;
	}
	

	
	public String part2(String[] input) {
		long sum = 0;
		Matcher m;
		long[] a = new long[2];
		long[] b = new long[2];
		long[] prize = new long[2];
		for(int i = 0; i < input.length; i++) {
			m = p.matcher(input[i++]);
			m.find();
			a[0] = Integer.parseInt(m.group(1));
			m.find();
			a[1] = Integer.parseInt(m.group(1));
			
			m = p.matcher(input[i++]);
			m.find();
			b[0] = Integer.parseInt(m.group(1));
			m.find();
			b[1] = Integer.parseInt(m.group(1));
			
			m = pp.matcher(input[i++]);
			m.find();
			prize[0] = 10000000000000L + Long.parseLong(m.group(1));
			m.find();
			prize[1] = 10000000000000L + Long.parseLong(m.group(1));
			
			long min = findMin(a, b, prize);
			if(min > 0) {
				sum += min;
			}
		}
		
		return ""+sum;
	}
}
