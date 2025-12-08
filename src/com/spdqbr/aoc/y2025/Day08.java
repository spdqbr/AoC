package com.spdqbr.aoc.y2025;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spdqbr.aoc.Solution;
import com.spdqbr.aoc.utils.Triple;
import com.spdqbr.aoc.utils.Utils;

public class Day08 extends Solution {
	public static final int day = 8;
	public static final int year = 2025;
	
	public static void main(String[] args) {
//		DEBUG = true;
		(new Day08()).main(year, day, 0);
		(new Day08()).main(year, day, 1);
		(new Day08()).main(year, day, 2);
		(new Day08()).main(year, day, 3);
	}
	
	public static double distance(long[] a, long[] b) {
		return Math.sqrt((a[0]-b[0])*(a[0]-b[0])
				+(a[1]-b[1])*(a[1]-b[1])
				+(a[2]-b[2])*(a[2]-b[2]));
	}
	
	public String part1(String[] input) {
		List<long[]> boxes = new ArrayList<>();
		
		for(String s : input) {
			boxes.add(Utils.stringArraytoLongArray(s.split(",")));
		}
		
		List<Triple<Double, long[], long[]>> distances = new ArrayList<>();
		for(int i = 0; i < boxes.size(); i++) {
			for(int j = i+1; j < boxes.size(); j++) {
				distances.add(new Triple<Double, long[], long[]>(distance(boxes.get(i), boxes.get(j)), boxes.get(i), boxes.get(j)));
			}
		}
		
		Collections.sort(distances, new Comparator<Triple<Double, long[], long[]>>() {
			@Override
			public int compare(Triple<Double, long[], long[]> o1, Triple<Double, long[], long[]> o2) {
				return o1.item0.compareTo(o2.item0);
			}
		});
		
		
		List<Set<long[]>> connections = new ArrayList<>();
		Set<long[]> connection;
		for(long[] box : boxes) {
			connection = new HashSet<long[]>();
			connection.add(box);
			connections.add(connection);
		}
		
		long maxConnections = 10;
		if(input.length > 50) {
			maxConnections = 1000;
		}
		
		long[] a, b;
		Set<long[]> connectionA, connectionB;
		int connectionCount = 0;
		for(int i = 0; i < maxConnections; i++) {
			a = distances.get(i).item1;
			b = distances.get(i).item2;
			
			connectionA = null;
			connectionB = null;
			for(Set<long[]> conn: connections) {
				if(conn.contains(a)) connectionA = conn;
				if(conn.contains(b)) connectionB = conn;
				if(connectionA != null && connectionB != null) break;
			}
			
			if(connectionA != connectionB) {
				connectionA.addAll(connectionB);
				connectionB.clear();
				connections.remove(connectionB);
				connectionCount++;
			}
		}
		
		connections.sort(new Comparator<Set<long[]>>() {
			@Override
			public int compare(Set<long[]> o1, Set<long[]> o2) {
				return Integer.compare(o1.size(), o2.size());
			}
		});
		
		long product = 1;
		System.out.println(connections.size());
		for(int i = 0; i < 3 ; i++) {
			product *= connections.get(connections.size()-1-i).size();
		}
		return ""+product;
	}
	
	public String part2(String[] input) {
		List<long[]> boxes = new ArrayList<>();
		
		for(String s : input) {
			boxes.add(Utils.stringArraytoLongArray(s.split(",")));
		}
		List<Triple<Double, long[], long[]>> distances = new ArrayList<>();
		for(int i = 0; i < boxes.size(); i++) {
			for(int j = i+1; j < boxes.size(); j++) {
				distances.add(new Triple<Double, long[], long[]>(distance(boxes.get(i), boxes.get(j)), boxes.get(i), boxes.get(j)));
			}
		}
		
		Collections.sort(distances, new Comparator<Triple<Double, long[], long[]>>() {
			@Override
			public int compare(Triple<Double, long[], long[]> o1, Triple<Double, long[], long[]> o2) {
				return o1.item0.compareTo(o2.item0);
			}
		});
		
		
		List<Set<long[]>> connections = new ArrayList<>();
		Set<long[]> connection;
		for(long[] box : boxes) {
			connection = new HashSet<long[]>();
			connection.add(box);
			connections.add(connection);
		}
		
		long[] a, b;
		Set<long[]> connectionA, connectionB;
		for(int i = 0; i < distances.size(); i++) {
			a = distances.get(i).item1;
			b = distances.get(i).item2;
			
			connectionA = null;
			connectionB = null;
			for(Set<long[]> conn: connections) {
				if(conn.contains(a)) connectionA = conn;
				if(conn.contains(b)) connectionB = conn;
				if(connectionA != null && connectionB != null) break;
			}
			
			if(connectionA != connectionB) {
				connectionA.addAll(connectionB);
				connectionB.clear();
				connections.remove(connectionB);
				if(connectionA.size() == boxes.size()) {
					System.out.println(Arrays.toString(a));
					System.out.println(Arrays.toString(b));
					System.out.println(a[0] * b[0]);
					break;
				}
			}
		}
		
		return "";
	}
}

