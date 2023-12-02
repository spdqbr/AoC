package com.spdqbr.aoc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseGrid3D<T> {
	private Map<Long, Map<Long, Map<Long, T>>> grid = new HashMap<Long, Map<Long, Map<Long, T>>>();
	private boolean init = false;
	private long minX, maxX, minY, maxY, minZ, maxZ;
	
	public SparseGrid3D() {
		
	}
	
	private void minMax(long x, long y, long z) {
		if(!init) {
			init = true;
			minX = x;
			maxX = x;
			minY = y;
			maxY = y;
			minZ = z;
			maxZ = z;
		}else {
			if( x > maxX) maxX = x;
			if( x < minX) minX = x;
			if( y > maxY) maxY = y;
			if( y < minY) minY = y;
			if( z > maxZ) maxZ = z;
			if( z < minZ) minZ = z;
		}
	}
	
	public T get(long x, long y, long z) {
		minMax(x, y, z);
		if(!grid.containsKey(x)) {
			return null;
		}
		Map<Long, Map<Long, T>> r = grid.get(x);
		if(r == null) return null;
		
		Map<Long, T> c = r.get(y);
		if(c == null) return null;
		
		if(!c.containsKey(z)) return null;
		
		return c.get(z);
	}
	
	public void set(long x, long y, long z, T val) {
		set(x, y, z, val, true);
	}
	
	public boolean set(long x, long y, long z, T val, boolean overwrite) {
		minMax(x, y, z);
		if(!grid.containsKey(x) || grid.get(x) == null) {
			grid.put(x, new HashMap<Long, Map<Long, T>>());
		}
		
		Map<Long, Map<Long, T>> r = grid.get(x);
		if(!r.containsKey(y) || r.get(y) == null) {
			r.put(y, new HashMap<Long, T>());
			return true;
		}
		
		Map<Long, T> c = r.get(y);
		if(!c.containsKey(y) || c.get(y) == null || overwrite) {
			c.put(z, val);
			return true;
		} 
		return false;
	}
	
	private void minMax() {
		minX = Long.MAX_VALUE;
		minY = Long.MAX_VALUE;
		minZ = Long.MAX_VALUE;
		maxX = Long.MIN_VALUE;
		maxY = Long.MIN_VALUE;
		maxZ = Long.MIN_VALUE;
		for(Long row : grid.keySet()) {
			for(Long col : grid.get(row).keySet()) {
				for(Long z : grid.get(row).get(col).keySet())
					minMax(row, col, z);
			}
		}
	}
	
	public boolean isSet(long x, long y, long z) {
		return get(x, y, z) == null;
	}
	
	/**
	 * 012
	 * 7x3
	 * 654
	 * 
	 * corners = even
	 * edges = odd
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
//	public List<T> getNeighbors(long x, long y, long z) {
//		List<T> out = new ArrayList<>();
//		
//		out.add(get(x-1,y-1, z));
//		out.add(get(x-1,y, z));
//		out.add(get(x-1,y+1, z));
//		out.add(get(x,y+1, z));
//		out.add(get(x+1,y+1, z));
//		out.add(get(x+1,y, z));
//		out.add(get(x+1,y-1, z));
//		out.add(get(x,y-1, z));
//		
//		
//		
//		return out;
//	}
	
	/**
	 * 
	 * @return { minRow, maxRow, minCol, maxCol }
	 */
	public long[] getBounds() {
		return new long[] {minX, maxX, minY, maxY, minZ, maxZ};
	}
}
