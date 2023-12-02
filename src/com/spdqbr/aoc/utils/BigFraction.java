package com.spdqbr.aoc.utils;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class BigFraction {
	public static BigFraction ONE = new BigFraction(1);
	public static BigFraction ZERO = new BigFraction(0);
	
	private BigInteger num;
	private BigInteger den;
	
	public BigFraction(Fraction f){
		this(f.getNum(),f.getDen());
	}
	
	public BigFraction(long n){
		this(BigInteger.valueOf(n));
	}
	
	public BigFraction(BigInteger n){
		this(n,BigInteger.ONE);
	}
	
	public BigFraction(long n, long d){
		this(BigInteger.valueOf(n),BigInteger.valueOf(d));
	}
	
	public BigFraction(BigInteger n, BigInteger d) throws NumberFormatException{
		if(d.equals(BigInteger.ZERO))
			throw new NumberFormatException("Zero denominator not allowed");
		this.num = n;
		this.den = d;    		
	}
	
	public BigFraction(double number){
		BigInteger den = BigInteger.ONE;
		while(number % 1 != 0){
			den = den.multiply(BigInteger.TEN);
			number *= 10;
		}
		this.num = BigInteger.valueOf((long)number);
		this.den = den;
		this.simplify();
	}
	
	public void simplify(){
		BigInteger gcd = BigMathUtils.gcd(this.num,this.den);
		this.num = this.num.divide(gcd);
		this.den = this.den.divide(gcd);
	}
	
	public BigInteger getNum() {
		return num;
	}
	
	public void setNum(long num){
		this.setNum(BigInteger.valueOf(num));
	}
	
	public void setNum(BigInteger num) {
		this.num = num;
	}
	
	public BigInteger getDen() {
		return den;
	}
	
	public void setDen(long den){
		this.setDen(BigInteger.valueOf(den));
	}
	
	public void setDen(BigInteger den) throws NumberFormatException{
		if(den.equals(BigInteger.ZERO))
			throw new NumberFormatException("Zero denominator not allowed");
		this.den = den;
	}
	
	public void commonDenominatorWith(BigFraction that){
		this.num = this.num.multiply(that.den);
		this.den = this.den.multiply(that.den);
	}
	
	public BigFraction add(BigFraction that){
		BigFraction out = new BigFraction(this.num,this.den);
		out.commonDenominatorWith(that);
		BigFraction temp = new BigFraction(that.num, that.den);
		temp.commonDenominatorWith(out);
		out.num = out.num.add(temp.num);
		out.simplify();
		return out;
	}
	
	public BigFraction add(long that){
		return this.add(new BigFraction(that));
	}
	
	public BigFraction multiply(BigFraction that){
		BigFraction out = new BigFraction(this.num.multiply(that.num),this.den.multiply(that.den));
		out.simplify();
		return out;
	}
	
	public BigFraction subtract(BigFraction that){
		return this.add(that.multiply(new BigFraction(-1)));
	}
	
	public BigFraction divide(BigFraction that){
		return this.multiply(that.inverseOf());
	}
	
	public BigFraction inverseOf(){
		return new BigFraction(this.den,this.num);
	}
	
	public double toDouble(){
		return this.toBigDecimal(20).doubleValue();
	}
	
	public BigDecimal toBigDecimal(int places){
		return new BigDecimal(this.num).divide(new BigDecimal(this.den), places, RoundingMode.HALF_UP);
	}
	
	public boolean equals(BigFraction that){
		BigFraction temp1 = new BigFraction(this.num,this.den);
		BigFraction temp2 = new BigFraction(that.num,that.den);
		temp1.simplify();
		temp2.simplify();
		return (temp1.num == temp2.num) && (temp1.den == temp2.den);
	}
	
	public String toString(){
		return this.num +" / "+this.den;
	}
}
