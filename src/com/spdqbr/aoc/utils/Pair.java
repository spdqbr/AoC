package com.spdqbr.aoc.utils;

import java.util.Objects;

public class Pair<S, T> {
	public S left;
	public T right;
	
	public Pair(S left, T right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		return Objects.equals(left, other.left) && Objects.equals(right, other.right);
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
