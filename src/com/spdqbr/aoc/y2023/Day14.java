package com.spdqbr.aoc.y2023;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spdqbr.aoc.Solution;

public class Day14 extends Solution {
	public static final int day = 14;
	public static final int year = 2023;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day14()).main(year, day, 0);
		(new Day14()).main(year, day, 1);
		(new Day14()).main(year, day, 2);
		(new Day14()).main(year, day, 3);
	}
	
	public String part1(String[] input) {
		char[][] board = new char[input.length][input[0].length()];
		for(int i = 0; i < board.length; i++) {
			board[i] = input[i].trim().toCharArray();
		}
		print(board);
		board = tilt(board, NORTH);
		
		print(board);
		
		long sum = 0;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col] == 'O') {
					sum += (board.length - row);
				}
			}
		}
		return ""+sum;
	}
	
	public void print(char[][] board) {
		if(!DEBUG) return;
		
		for(int i = 0; i < board.length; i++) {
			debug(new String(board[i]));
		}
		debug("");
	}
	
	public static final int[] NORTH = { -1, 0};
	public static final int[] SOUTH = { 1, 0};
	public static final int[] EAST = { 0, 1};
	public static final int[] WEST = { 0, -1};
	
	public int[] add(int row, int col, int[] direction){
		return new int[] { row + direction[0], col + direction[1] };
	}
	
	public char[][] tilt(char[][] board, int[] direction){
		char[][] out = new char[board.length][board[0].length];
		for(int i = 0; i < out.length; i++) {
			Arrays.fill(out[i], '.');
		}
		int[] newPos;
		if(Arrays.equals(direction, NORTH)) {
			for(int col = 0; col < board[0].length; col++) {
				for(int row = 0; row < board.length; row++) {
					newPos = move(out, board, row, col, direction);
					out[newPos[0]][newPos[1]] = board[row][col];
				}
			}
		}else if(Arrays.equals(direction, SOUTH)){
			for(int col = 0; col < board[0].length; col++) {
				for(int row = board.length-1; row >= 0; row--) {
					newPos = move(out, board, row, col, direction);
					out[newPos[0]][newPos[1]] = board[row][col];
				}
			}
		}else if(Arrays.equals(direction, EAST)){
			for(int row = 0; row < board.length; row++) {
				for(int col = board[0].length-1; col >= 0; col--) {
					newPos = move(out, board, row, col, direction);
					out[newPos[0]][newPos[1]] = board[row][col];
				}
			}
		}else if(Arrays.equals(direction, WEST)) {
			for(int row = 0; row < board.length; row++) {
				for(int col = 0; col < board[0].length; col++) {
					newPos = move(out, board, row, col, direction);
					out[newPos[0]][newPos[1]] = board[row][col];
				}
			}
		}
		return out;
	}
	
	public int[] move(char[][] newBoard, char[][] board, int row, int col, int[] direction) {
		int[] out = {row, col};
		if(board[row][col] !=  'O') {
			return out;
		}
		while(out[0] >= 0 && out[0] < board.length
		   && out[1] >= 0 && out[1] < board[out[0]].length
		   && newBoard[out[0]][out[1]] == '.'){
			
		   out[0] += direction[0];
		   out[1] += direction[1];			
		}
		
		
		if( out[0] < 0 || out[1] < 0 
			|| out[0] == board.length || out[1] == board[0].length	
			|| newBoard[out[0]][out[1]] != '.') {
			out[0] -= direction[0];
			out[1] -= direction[1];
		}
		return out;
	}
	
	public String part2(String[] input) {
		char[][] board = new char[input.length][input[0].length()];
		for(int i = 0; i < board.length; i++) {
			board[i] = input[i].trim().toCharArray();
		}
		List<char[][]> history = new ArrayList<>();
		
		int cycles = 1000000000;

		boolean skip = false;
		for(int cycle = 0; cycle < 1000000000; cycle++) {
			board = tilt(board, NORTH);
			board = tilt(board, WEST);
			board = tilt(board, SOUTH);
			board = tilt(board, EAST);
			print(board);
		
			if(!skip) {
				int foundAt = contains(history, board);
				if(foundAt == -1) {
					history.add(board);
				}else {
					System.out.println("Found at i = "+foundAt+" on cycle = "+cycle);
					int repeatLength = cycle - foundAt;
					int mul = ( cycles - foundAt) / repeatLength -1;
					cycle += (repeatLength * mul);
					System.out.println("Skipping to cycle: " +cycle);
					skip = true;
					
					// could just skip to the right board
					// but I find iterating through the last few boards
					// somehow more satisfying (and not appreciably slower)
//					board = history.get(foundAt + cycles - cycle -1);
//					break;
				}
			}
		}
		
		long sum = 0;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col] == 'O') {
					sum += (board.length - row);
				}
			}
		}
		return ""+sum;
	}
	
	public int contains(List<char[][]> history, char[][] board) {
		for(int i = 0; i < history.size(); i++) {
			if(equals(history.get(i), board)) return i;
		}
		return -1;
	}
	
	public boolean equals(char[][] a, char[][] b) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				if(a[i][j] != b[i][j]) return false;
			}
		}
		return true;
	}
}

