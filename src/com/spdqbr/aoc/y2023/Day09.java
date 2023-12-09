package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.List;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.MathUtils;
import com.spdqbr.aoc.utils.Utils;

public class Day09 extends Solution {
	public static final int day = 9;
	public static final int year = 2023;
	
	public static void main(String[] args) {
		(new Day09()).main(year, day, 3);
	}
	
	public List<Long> getCoefficients(long[] arr){
		List<Long> coefficients = new ArrayList<>();
		long[][] t = new long[2][arr.length];
		System.arraycopy(arr, 0, t[0], 0, arr.length);
		
		int step = 0;
		boolean allZero = false;
		while(!allZero) {
			allZero = true;
			coefficients.add(t[step%2][0]);
			for(int i = 0; i < arr.length-1-step; i++) {
				t[(step+1)%2][i] = t[step%2][i+1] - t[step%2][i];
				if(t[(step+1)%2][i] != 0) allZero = false;
			}
			step++;
		}

		return coefficients;
	}
	
	public String part1(String[] input) {
		long sum = 0;
		for(String line : input) {
			sum += extrapolate(Utils.stringArraytoLongArray(line.split(" ")));
		}
		return ""+sum;
	}
	
	/**
	 * f(n) = sum(d_i * ncr(n,i) from i=0 to number_of_steps_to_zero_difference)
	 * where d_i is the first ith difference
	 */
	public long extrapolate(long[] dataArr) {
		List<Long> coefficients = getCoefficients(dataArr);
		int n = dataArr.length;
		long val = 0;
		for(int i = 0; i < coefficients.size(); i++) {
			val += coefficients.get(i) * MathUtils.ncr(n, i);
		}
		return val;
	}

	
	public String part2(String[] input) {
		long sum = 0;
		for(String line : input) {
			sum += extrapolate(Utils.reverse(Utils.stringArraytoLongArray(line.split(" "))));
		}
		
		return ""+sum;
	}
}
