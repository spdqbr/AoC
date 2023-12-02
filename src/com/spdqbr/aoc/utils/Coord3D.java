package com.spdqbr.aoc.utils;

import java.util.HashSet;
import java.util.Set;

public class Coord3D {
	public int x;
	public int y;
	public int z;
	
	public Coord3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Coord3D(int[] coords) {
		this(coords[0], coords[1], coords[2]);
	}
	
	@Override
	public String toString() {
		return "( "+x+", "+y+", "+z+" )"; 
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coord3D) {
			Coord3D other = (Coord3D)obj;
			return this.x == other.x && this.y == other.y && this.z == other.z;
		}
		return false;
	}
	
	public Set<Coord3D> squareNeighbors(){
		Set<Coord3D> neighbors = new HashSet<>();
		neighbors.add(new Coord3D(x-1,y,z));
		neighbors.add(new Coord3D(x+1,y,z));
		
		neighbors.add(new Coord3D(x,y-1,z));
		neighbors.add(new Coord3D(x,y+1,z));
		
		neighbors.add(new Coord3D(x,y,z-1));
		neighbors.add(new Coord3D(x,y,z+1));
		return neighbors;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
}
