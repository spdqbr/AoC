package com.spdqbr.aoc;
import static com.spdqbr.aoc.utils.Utils.readFileToArray;

public abstract class Solution {
	public static String[] levels = { "1e", "1", "2e", "2" };
	
	public void main(int year, int day) {
		main(year, day, 4);
	}
	
	public void main(int year, int day, int level) {
		String dayStr = String.format("%02d", day);
		
		String[] example = readFileToArray(String.format("./Input/%s/Day%s.e1.txt", year, dayStr));
		String[] example2 = readFileToArray(String.format("./Input/%s/Day%s.e2.txt", year, dayStr));
		if(example2.length == 0){
			System.out.println("Using example1 for example2");
			example2 = example;
		}
		
		String[] input = readFileToArray(String.format("./Input/%s/Day%s.txt", year, dayStr));

		String[][] inputs = { example, input, example2, input };
		
		for(int i = 0; i < levels.length; i++) {
			execute(i, inputs[i]);
		}
	}

	public void execute(int level, String[] input) {
		long start, end;
		start = System.nanoTime();
		String result = "";
		if( level < 2 ) {
			result = part1(input);
		}else {
			result = part2(input);
		}
		end = System.nanoTime();
		System.out.println(String.format("Part %s : %s", levels[level], result));
		System.out.println(String.format("Time: %s seconds\n", (end-start)/1e9));
	}
	
	public abstract String part1(String[] input);
	
	public abstract String part2(String[] input);
}
