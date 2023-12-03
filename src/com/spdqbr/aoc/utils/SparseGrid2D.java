package com.spdqbr.aoc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseGrid2D<T> {
	private Map<Long, Map<Long, T>> grid = new HashMap<Long, Map<Long, T>>();
	private boolean init = false;
	private long minRow, maxRow, minCol, maxCol;
	
	public SparseGrid2D() {
		
	}
	
	private void minMax(long row, long col) {
		if(!init) {
			init = true;
			minRow = row;
			maxRow = row;
			minCol = col;
			maxCol = col;
		}else {
			if( row > maxRow) maxRow = row;
			if( row < minRow) minRow = row;
			if( col > maxCol) maxCol = col;
			if( col < minCol) minCol = col;
		}
	}
	
	public T get(Coord2D<Long> c) {
		return get(c.row, c.col);
	}
	
	public T get(long row, long col) {
		minMax(row, col);
		if(!grid.containsKey(row)) {
			return null;
		}
		Map<Long, T> r = grid.get(row);
		if(r == null) return null;
		
		if(!r.containsKey(col)) return null;
		
		return r.get(col);
	}
	
	public void set(Coord2D<Long> c, T val) {
		set(c.row, c.col, val);
	}
	
	public void set(long row, long col, T val) {
		set(row, col, val, true);
	}
	
	public boolean set(Coord2D<Long> c, T val, boolean overwrite) {
		return set(c.row, c.col, val, overwrite);
	}
	
	public boolean set(long row, long col, T val, boolean overwrite) {
		minMax(row, col);
		if(!grid.containsKey(row) || grid.get(row) == null) {
			grid.put(row, new HashMap<Long, T>());
		}
		
		Map<Long, T> r = grid.get(row);
		if(!r.containsKey(col) || r.get(col) == null || overwrite) {
			r.put(col, val);
			return true;
		} 
		return false;
	}
	
	private void minMax() {
		minRow = Long.MAX_VALUE;
		minCol = Long.MAX_VALUE;
		maxRow = Long.MIN_VALUE;
		maxCol = Long.MIN_VALUE;
		for(Long row : grid.keySet()) {
			for(Long col : grid.get(row).keySet()) {
				minMax(row, col);
			}
		}
	}
	
	public boolean isSet(Coord2D<Long> c) {
		return isSet(c.row, c.col);
	}
	
	public boolean isSet(long row, long col) {
		return get(row, col) != null;
	}
	
	public Map<Long, T> removeRow(long row) {
		Map<Long, T> out = grid.remove(row);
		minMax();
		return out;
	}
	
	public Map<Long, T> removeCol(long col) {
		Map<Long, T> out = new HashMap<>();
		for(Long rowNum : grid.keySet()) {
			if(grid.get(rowNum).containsKey(col)) {
				out.put(rowNum, grid.get(rowNum).remove(col));
			}
		}
		minMax();
		return out;
	}
	
	public T remove(Coord2D<Long> c) {
		return remove(c.row, c.col);
	}
	
	public T remove(long row, long col) {
		if(isSet(row,col)) {
			T out = grid.get(row).remove(col);
			minMax();
			return out;
		}
		return null;
	}
	
	/**
	 * if includeEmpty == true:
	 * 012
	 * 7x3
	 * 654
	 * 
	 * corners = even
	 * edges = odd
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public List<T> getNeighborValues(long row, long col, boolean includeEmpty) {
		List<T> out = new ArrayList<>();
		List<Coord2D<Long>> n = getNeighborCoords(row, col, includeEmpty); 
		
		for(Coord2D<Long> c : n) {
			out.add(get(c));
		}
		
		return out;
	}
	
	public List<T> getNeighborValues(Coord2D<Long> c, boolean includeEmpty) {
		return getNeighborValues(c.row, c.col, includeEmpty);
	}
	
	public List<T> getNeighborValues(Coord2D<Long> c) {
		return getNeighborValues(c, false);
	}
	
	/**
	 * if includeEmpty == true:
	 * 012
	 * 7x3
	 * 654
	 * 
	 * corners = even
	 * edges = odd
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public List<Coord2D<Long>> getNeighborCoords(long row, long col, boolean includeEmpty){
		List<Coord2D<Long>> temp = new ArrayList<>();
		temp.add(new Coord2D<Long>(row-1,col-1));
		temp.add(new Coord2D<Long>(row-1,col));
		temp.add(new Coord2D<Long>(row-1,col+1));
		temp.add(new Coord2D<Long>(row,col+1));
		temp.add(new Coord2D<Long>(row+1,col+1));
		temp.add(new Coord2D<Long>(row+1,col));
		temp.add(new Coord2D<Long>(row+1,col-1));
		temp.add(new Coord2D<Long>(row,col-1));
		
		if(includeEmpty) return temp;
		
		List<Coord2D<Long>> out = new ArrayList<>();
		for(Coord2D<Long> c : temp) {
			if(isSet(c)) {
				out.add(c);
			}
		}
		
		return out;
	}
	
	public List<Coord2D<Long>> getNeighborCoords(Coord2D<Long> c, boolean includeEmpty){
		return getNeighborCoords(c.row, c.col, includeEmpty);
	}
	
	public List<Coord2D<Long>> getNeighborCoords(long row, long col){
		return getNeighborCoords(row, col, false);
	}
	
	public List<Coord2D<Long>> getNeighborCoords(Coord2D<Long> c){
		return getNeighborCoords(c, false);
	}
	
	
	public Map<Long, Map<Long, T>> getGrid(){
		return grid;
	}
	
	public List<Coord2D<Long>> getSetCoords() {
		List<Coord2D<Long>> coords = new ArrayList<>();
		for(long row : grid.keySet()) {
			for(long col : grid.get(row).keySet()) {
				coords.add(new Coord2D<Long>(row, col));
			}
		}
		return coords;
	}
	
	/**
	 * 
	 * @return { minRow, maxRow, minCol, maxCol }
	 */
	public long[] getBounds() {
		return new long[] {minRow, maxRow, minCol, maxCol};
	}
}
