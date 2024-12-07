package com.spdqbr.aoc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NCounter<T> implements Iterable<List<T>> {
	private int nBits;
	private List<T> items;
	
	public NCounter(int nBits, List<T> items){
		this.nBits = nBits;
		this.items = new ArrayList<>();
		this.items.addAll(items);
	}

	@Override
	public Iterator<List<T>> iterator() {
		int base = items.size();
		
		long limit = (long)Math.pow(base, nBits);
		
		Iterator<List<T>> it = new Iterator<List<T>>() {
			int current = 0;

			@Override
			public boolean hasNext() {
				return current < limit;
			}

			@Override
			public List<T> next() {
				List<T> out = new ArrayList<>();
				int currentLocal = current;
				for(int i = 0; i < nBits; i++) {
					out.add(items.get(currentLocal % items.size()));
					currentLocal /= items.size();
				}
				current++;
				return out;
			}
			
		};
		
		return it;
	}
	
	public static void main(String[] args) {
		NCounter<Character> c = new NCounter<Character>(5, Arrays.asList(new Character[] { 'a', 'b', 'c'}));
		
		for(List<Character> list : c) {
			System.out.println(list);
		}
	}
}
