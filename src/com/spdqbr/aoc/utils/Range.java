package com.spdqbr.aoc.utils;

public class Range {
	public Integer left;
	public Integer right;
	
	public Range(Integer left, Integer right) {
		if(left > right){
			int temp = left;
			left = right;
			right = temp;
		}
		this.left = left;
		this.right = right;
	}
	
	public Range(int left, int right) {
		this(Integer.valueOf(left), Integer.valueOf(right));
	}
	
	public boolean contains(Range other) {
		return this.left <= other.left && this.right >= other.right;
	}
	
	public Range intersect(Range other) {
		Integer left = Math.max(this.left, other.left);
		Integer right = Math.min(this.right, other.right);
		if(left <= right) return new Range(left, right);
		
		return null;
	}

	public Range join(Range other){
		if(this.intersect(other) == null) {
			Range lowerRight = this.right < other.right ? this : other;
			other = lowerRight.equals(other) ? this : other;
			
			if(other.left - lowerRight.right == 1) {
				return new Range(lowerRight.left, other.right);
			}
			return null;
		}

		Integer left = Math.min(this.left, other.left);
		Integer right = Math.max(this.right, other.right);

		return new Range(left, right);
	}

	public int size(){
		return right - left + 1;
	}
	
	public Range gap(Range other) {
		Range lowerRight = this.right < other.right ? this : other;
		other = lowerRight.equals(other) ? this : other;
		
		return new Range(lowerRight.right+1, other.left-1);
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Range){
			Range r = (Range) o;
			return this.left == r.left && this.right == r.right;
		}
		return false;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append(left);
		sb.append(", ");
		sb.append(right);
		sb.append(" ]");
		return sb.toString();
	}
}
