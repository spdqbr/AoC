package com.spdqbr.aoc.utils;

public class Pair<S, T> {
	public S left;
	public T right;
	
	public Pair(S left, T right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair) {
			Pair other = (Pair)obj;
			boolean leq = (this.left == other.left) || (this.left != null && this.left.equals(other.left));
			boolean req = (this.right == other.right) || (this.right != null && this.right.equals(other.right));
			
			return leq && req;

		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("( ");
		sb.append(left.toString());
		sb.append(", ");
		sb.append(right.toString());
		sb.append(" )");
		return sb.toString();
	}

}
