package com.spdqbr.aoc.utils;

import java.util.Objects;

public class Triple<T, U, V> {
	public T item0;
	public U item1;
	public V item2;
	
	public Triple() {
		
	}
	
	public Triple(T item0, U item1, V item2) {
		super();
		this.item0 = item0;
		this.item1 = item1;
		this.item2 = item2;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("( ");
		sb.append(item0.toString());
		sb.append(", ");
		sb.append(item1.toString());
		sb.append(", ");
		sb.append(item2.toString());
		sb.append(" )");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(item0, item1, item2);
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
		Triple other = (Triple) obj;
		return Objects.equals(item0, other.item0) && Objects.equals(item1, other.item1)
				&& Objects.equals(item2, other.item2);
	}
}
