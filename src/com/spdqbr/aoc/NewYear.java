package com.spdqbr.aoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class NewYear {
	public static void main(String[] args) throws Exception {
		int year = 2025;
		int days = 12;

		File srcPath = new File(String.format("./src/com/spdqbr/aoc/y%s/", year));
		if (!srcPath.isDirectory()) {
			srcPath.mkdirs();
		}

		File inPath = new File(String.format("./Input/%s/", year));
		if (!inPath.isDirectory()) {
			inPath.mkdirs();
		}

		for (int day = 1; day <= days; day++) {

			String dayNum = String.format("%02d", day);

			while ((new File(String.format("./src/com/spdqbr/aoc/y%s/Day%s.java", year, dayNum))).exists()) {
				day++;
				dayNum = String.format("%02d", day);
			}
			System.out.println(day);

			for (String s : new String[] { "e1.", "e2.", "" }) {
				File f = new File(String.format("./Input/%s/Day%s.%stxt", year, dayNum, s));
				f.createNewFile();
			}

			BufferedReader br = new BufferedReader(new FileReader("./src/com/spdqbr/aoc/Template.java"));
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(String.format("./src/com/spdqbr/aoc/y%s/Day%s.java", year, dayNum)));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("@@YEARLINE@@")) {
					bw.write(line.replaceAll("-1", Integer.toString(year)).replaceAll(" *//@@YEARLINE@@", ""));
				} else if (line.contains("@@DAYLINE@@")) {
					bw.write(line.replaceAll("-1", Integer.toString(day)).replaceAll(" *//@@DAYLINE@@", ""));
				} else if (line.contains("@@CLASSLINE@@")) {
					bw.write(line.replaceAll("Template", String.format("Day%s", dayNum)).replaceAll(" *//@@CLASSLINE@@",
							""));
				} else if (line.contains("package")) {
					bw.write(line.replaceAll("com.spdqbr.aoc", String.format("com.spdqbr.aoc.y%s", year)));
				} else {
					bw.write(line);
				}
				bw.write("\n");
			}
			bw.flush();
			bw.close();
			br.close();
		}
	}
}
