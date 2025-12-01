package com.spdqbr.aoc.y2025;

import static com.spdqbr.aoc.utils.Utils.*;

import com.spdqbr.aoc.Solution;

public class Day01 extends Solution {
	public static final int day = 1;
	public static final int year = 2025;

	public static void main(String[] args) {
//		DEBUG = true;
		(new Day01()).main(year, day, 0);
		(new Day01()).main(year, day, 1);
		(new Day01()).main(year, day, 2);
		(new Day01()).main(year, day, 3);
	}

	public int parse(String s) {
		int val = Integer.parseInt(s.substring(1));
		return s.charAt(0) == 'L' ? -1 * val : val;
	}

	public String part1(String[] input) {
		int dial = 50;

		int val;
		int count = 0;
		for (String s : input) {
			val = parse(s);
			dial += val;
			if (dial < 0)
				dial += 100;
			dial %= 100;

			if (dial == 0)
				count++;
		}

		return "" + count;
	}

	public String part2(String[] input) {
		int dial = 50;

		int val;
		int count = 0;
		for (String s : input) {
			val = parse(s);

			if (dial == 0 && val < 0)
				dial += 100;
			dial += val;

			while (dial < 0) {
				dial += 100;
				count++;
			}

			while (dial >= 100) {
				dial -= 100;
				if (dial != 0)
					count++;
			}

			if (dial == 0)
				count++;
		}
		return "" + count;
	}
}
