package com.spdqbr.aoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class NewDay {
	public static void main(String[] args) throws Exception{
		int day = 1;
		int year = 2023;
		
		String dayNum = String.format("%02d", day);
		
		while((new File(String.format("./src/com/spdqbr/aoc/y%s/Day%s.java", year, dayNum))).exists()){
			day++;
			dayNum = String.format("%02d", day);
		}
		System.out.println(day);
		
		for(String s : new String[] { "e1.", "e2.", "" }) {
			File f = new File(String.format("./Input/%s/Day%s.%stxt", year, dayNum, s));
			f.createNewFile();
		}
		
		BufferedReader br = new BufferedReader(new FileReader("./src/com/spdqbr/aoc/Template.java"));
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(String.format("./src/com/spdqbr/aoc/y%s/Day%s.java", year, dayNum)));
		
		String line;
		while((line = br.readLine()) != null) {
			if( line.contains("@@DAYLINE@@")) {
				bw.write(String.format("	public static final int day = %d;",day));
			} else if(line.contains("@@CLASSLINE@@")) {
				bw.write(
					line.replaceAll("Template",String.format("Day%s", dayNum))
					     .replaceAll(" *//@@CLASSLINE@@", ""));
			} else if(line.contains("package")) {
				bw.write(line.replaceAll("com.spdqbr.aoc", String.format("com.spdqbr.aoc.y%s", year)));
			}else {
				bw.write(line);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
