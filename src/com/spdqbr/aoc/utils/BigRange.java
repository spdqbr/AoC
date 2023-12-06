package com.spdqbr.aoc.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BigRange {
	public BigInteger left;
	public BigInteger right;
	
	public BigRange(BigRange range) {
		this(range.left, range.right);
	}
	
	public BigRange(int left, int right) {
		this(BigInteger.valueOf(left), BigInteger.valueOf(right));
	}
	
	public BigRange(BigInteger left, BigInteger right) {
		if(left.compareTo(right) > 0){
			BigInteger temp = left;
			left = right;
			right = temp;
		}
		this.left = left;
		this.right = right;
	}
	
	public static BigRange createBigRange(int left, int right) {
		return createBigRange(BigInteger.valueOf(left), BigInteger.valueOf(right));
	}
	
	public static BigRange createBigRange(BigInteger left, BigInteger right) {
		if(left.compareTo(right) > 0){
			return null;
		}
		return new BigRange(left, right);
	}
	
	public boolean contains(BigRange other) {
		return this.left.compareTo(other.left) <= 0 && this.right.compareTo(other.right) >= 0;
	}
	
	public BigRange intersect(BigRange other) {
		BigInteger left = BigMathUtils.max(this.left, other.left);
		BigInteger right = BigMathUtils.min(this.right, other.right);
		if(left.compareTo(right) <= 0) return new BigRange(left, right);
		
		return null;
	}

	public BigRange join(BigRange other){
		if(this.intersect(other) == null) {
			BigRange lowerRight = this.right.compareTo(other.right) < 0 ? this : other;
			other = lowerRight.equals(other) ? this : other;
			
			if(other.left.subtract(lowerRight.right).compareTo(BigInteger.ONE) == 0) {
				return new BigRange(lowerRight.left, other.right);
			}
			return null;
		}

		BigInteger left = BigMathUtils.min(this.left, other.left);
		BigInteger right = BigMathUtils.max(this.right, other.right);

		return new BigRange(left, right);
	}
	
	public BigInteger size(){
		return right.subtract(left).add(BigInteger.ONE);
	}
	
	public BigRange gap(BigRange other) {
		BigRange lowerRight = this.right.compareTo(other.right) < 0 ? this : other;
		other = lowerRight.equals(other) ? this : other;
		
		return new BigRange(lowerRight.right.add(BigInteger.ONE), other.left.subtract(BigInteger.ONE));
	}
	
	public List<BigRange> split(BigInteger splitPoint){
		List<BigRange> out = new ArrayList<BigRange>();
		if(splitPoint.compareTo(this.left) <= 0) out.add(new BigRange(this.left, this.right));
		else if(splitPoint.compareTo(this.right ) >= 0) out.add(new BigRange(this.left, this.right));
		else {
			out.add(new BigRange(this.left, splitPoint));
			out.add(new BigRange(splitPoint, this.right));
		}
		return out;
	}
	
	public BigRange add(BigInteger delta) {
		return new BigRange(this.left.add(delta), this.right.add(delta));
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof BigRange){
			BigRange r = (BigRange) o;
			return this.left.equals(r.left) && this.right.equals(r.right);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
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
