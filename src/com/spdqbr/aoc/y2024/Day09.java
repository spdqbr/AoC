package com.spdqbr.aoc.y2024;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Pair;

public class Day09 extends Solution {
	public static final int day = 9;
	public static final int year = 2024;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day09()).main(year, day, 0);
		(new Day09()).main(year, day, 1);
		(new Day09()).main(year, day, 2);
		(new Day09()).main(year, day, 3);
	}
	
	public static int geLastDataIndex(List<Integer> drive) {
		for(int i = drive.size()-1; i > 0; i--) {
			if(drive.get(i) != -1) {
				return i;
			}
		}
		return -1;
	}
	
	public String part1(String[] input) {
		List<Integer> drive = new ArrayList<>();
		int id = 0;
		for(int i = 0; i < input[0].length(); i++) {
			for( int j = 0; j < input[0].charAt(i) - '0'; j++) {
				 drive.add(id);
			}
			i++;
			if( i < input[0].length()) {
				for( int j = 0; j < input[0].charAt(i) - '0'; j++) {
					 drive.add(-1);
				}
			}
			id++;
		}
		
		List<Integer> compacted = new ArrayList<>();
		int val;
		while(drive.size() > 0) {
			val = drive.remove(0);
			if(val >= 0) {
				compacted.add(val);
			}else {
				int source = geLastDataIndex(drive);
				if(source < 0) break;
				compacted.add(drive.remove(source));
			}
		}
		
		long checksum = 0;
		for(int i =0 ;i < compacted.size(); i++) {
			checksum += i * compacted.get(i);
		}
		return ""+checksum;
	}
	
	public void print(Map<Integer, Pair<Integer, Integer>> fileLocSizes, int size) {
		char[] out = new char[size];
		Arrays.fill(out, '.');
		
		for(int id : fileLocSizes.keySet()) {
			for(int i = 0; i < fileLocSizes.get(id).right; i++) {
				out[i+fileLocSizes.get(id).left] = (char)(id + '0');
			}
		}
		
		System.out.println(new String(out));
	}
	
	public String part2(String[] input) {
		Map<Integer, Pair<Integer, Integer>> fileLocSizes = new HashMap<>();
		List<Pair<Integer, Integer>> freeSpace = new ArrayList<>();
		int id = 0;
		int location = 0;
		for(int i = 0; i < input[0].length(); i++) {
			fileLocSizes.put(id, new Pair<Integer, Integer>(location, input[0].charAt(i) - '0'));
			location += fileLocSizes.get(id).right;
			
			i++;
			if( i < input[0].length()) {
				freeSpace.add(new Pair<Integer, Integer>(location, input[0].charAt(i) - '0'));
			}
			location += freeSpace.get(freeSpace.size()-1).right;
			id++;
		}
		
//		int size = fileLocSizes.get(id-1).left + fileLocSizes.get(id-1).right;
		
		for(id--; id >=0; id--) {
			Pair<Integer, Integer> targetFile = fileLocSizes.get(id);
			Pair<Integer, Integer> free;
			for(int i = 0; i < freeSpace.size(); i++) {
				free = freeSpace.get(i);
				if(free.left > targetFile.left) break;
				if(free.right >= targetFile.right) {
					debug("Moving "+id+" ("+targetFile+") to "+free);
					freeSpace.add(new Pair<Integer, Integer>(targetFile.left, targetFile.right)); // free the file's space
					targetFile.left = free.left; // move the file
					free.left += targetFile.right; // move the start of the free space
					free.right -= targetFile.right; // subtract the used space
//					print(fileLocSizes, size);
					break;
				}
			}
		}
		
		long checksum = 0;
		Pair<Integer, Integer> file;
		for(int ident : fileLocSizes.keySet()) {
			file = fileLocSizes.get(ident);
			for(int i = 0; i < file.right; i++) {
				checksum += ( ident * (file.left+i));
			}
		}
		return ""+checksum;
	}
}
