package com.spdqbr.aoc.y2023;
import java.util.Arrays;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.MathUtils;
import com.spdqbr.aoc.utils.Utils;

public class Day06 extends Solution {
	public static final int day = 6;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day06()).main(year, day, 3);
	}
	
	/*
	 *  distance traveled = (time - time_pressed) * time_pressed
	 *  d = (t - p) * p
	 *  d = (tp - p^2)
	 *  p^2 -tp + d = 0
	 */
	
	public long getDist(long pressed, long time) {
		return (time - pressed) * pressed;
	}

	
	public String part1(String[] input) {
		long[] raceTimes = Utils.stringArraytoLongArray(input[0].split(":")[1].trim().split(" +"));
		long[] raceDistances = Utils.stringArraytoLongArray(input[1].split(":")[1].trim().split(" +"));
		
		long prod = 1;
		double[] vals;
		long[] wholeMillis = new long[2];
		for(int i = 0 ; i < raceTimes.length; i++) {
			vals = MathUtils.quadratic(1, -1 * raceTimes[i], raceDistances[i]);
			Arrays.sort(vals);
			wholeMillis[0] = (long)Math.ceil(vals[0]);
			if(vals.length == 1) {
				wholeMillis[1] = wholeMillis[0] + 1;
			}else {
				wholeMillis[1] = (long)Math.floor(vals[1]);
			}
			
			if(getDist(wholeMillis[0], raceTimes[i]) <= raceDistances[i]){
				wholeMillis[0]++;
			}
			if(getDist(wholeMillis[1], raceTimes[i]) <= raceDistances[i]){
				wholeMillis[1]--;
			}
			System.out.println(Arrays.toString(wholeMillis));
			if(wholeMillis[0] <= wholeMillis[1]) {
				prod *= wholeMillis[1] - wholeMillis[0] + 1;
			}
		}
		
		return ""+prod;
	}
	
	public String part2(String[] input) {
		input[0] = input[0].replaceAll(" +", "");
		input[1] = input[1].replaceAll(" +", "");
		return part1(input);
	}
}
