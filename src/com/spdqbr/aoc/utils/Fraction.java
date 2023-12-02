package com.spdqbr.aoc.utils;
public class Fraction extends Number implements Comparable<Number>{
	public static final long serialVersionUID = 1;
	public static Fraction ONE = new Fraction(1);
	public static Fraction ZERO = new Fraction(0);
	
	private long num;
	private long den;
	
	public Fraction(long n){
		this.num = n;
		this.den = 1;
	}
	
	public Fraction(Fraction frac){
		this.num = frac.getNum();
		this.den = frac.getDen();
	}
	
	public Fraction(long n, long d) throws NumberFormatException{
		if(d == 0)
			throw new NumberFormatException("Zero denominator not allowed");
		this.num = n;
		this.den = d;    		
	}
	
	public Fraction(double number){
		long den = 1;
		while(number % 1 != 0){
			den *= 10;
			number *= 10;
		}
		this.num = (long)number;
		this.den = den;
		this.simplify();
	}
	
	public void simplify(){
		long gcd = MathUtils.gcd(this.num,this.den);
		this.num /= gcd;
		this.den /= gcd;
	}
	
	public long getNum() {
		return num;
	}
	
	public void setNum(long num) {
		this.num = num;
	}
	
	public long getDen() {
		return den;
	}
	
	public void setDen(long den) throws NumberFormatException{
		if(den == 0)
			throw new NumberFormatException("Zero denominator not allowed");
		this.den = den;
	}
	
	public static void commonDenominators(Fraction frac1, Fraction frac2){
		if(frac1.getDen() == frac2.getDen())
			return;
		long frac1Mult = frac2.getDen();
		long frac2Mult = frac1.getDen();
		frac1.setDen(frac1.getDen()*frac1Mult);
		frac1.setNum(frac1.getNum()*frac1Mult);
		frac2.setDen(frac2.getDen()*frac2Mult);
		frac2.setNum(frac2.getNum()*frac2Mult);
	}
	
	public Fraction add(Fraction that){
		Fraction out = new Fraction(this.num,this.den);
		Fraction temp = new Fraction(that.num, that.den);
		Fraction.commonDenominators(out, temp);
		out.num += temp.num;
		out.simplify();
		return out;
	}
	
	public Fraction multiply(Fraction that){
		Fraction out = new Fraction(this.num*that.num,this.den*that.den);
		out.simplify();
		return out;
	}
	
	public Fraction subtract(Fraction that){
		return this.add(that.multiply(new Fraction(-1)));
	}
	
	public Fraction divide(Fraction that){
		return this.multiply(that.inverseOf());
	}
	
	public Fraction inverseOf(){
		return new Fraction(this.den,this.num);
	}
	
	public double doubleValue(){
		return this.num / (double) this.den;
	}
	
	public int intValue(){
		return (int)this.doubleValue();
	}
	
	public float floatValue(){
		return (float)this.doubleValue();
	}
	
	public long longValue(){
		return this.longValue();
	}
	
	public boolean equals(Fraction that){
		Fraction temp1 = new Fraction(this.num,this.den);
		Fraction temp2 = new Fraction(that.num,that.den);
		temp1.simplify();
		temp2.simplify();
		return (temp1.num == temp2.num) && (temp1.den == temp2.den);
	}
	
	public int compareTo(Fraction that){
		Fraction frac1 = new Fraction(this);
		Fraction frac2 = new Fraction(that);
		Fraction.commonDenominators(frac1, frac2);
		return Long.valueOf(frac1.getNum()).compareTo(Long.valueOf(frac2.getNum()));
	}
	
	public int compareTo(Number otherObject){
		if(otherObject instanceof Fraction)
			return compareTo((Fraction)otherObject);
		return Double.valueOf((this.doubleValue())).compareTo(Double.valueOf(((Number)otherObject).doubleValue()));
	}
	
	public Fraction closestWithDenominator(long newDen){
		Fraction temp = new Fraction(1,newDen);
		Fraction in = new Fraction(this);
		Fraction.commonDenominators(in, temp);
		long test = newDen*(in.getNum() / newDen);
		long test2 = newDen*((in.getNum() / newDen)+1);
		if(test - in.getNum() <= test2 - in.getNum())
			temp.setNum(test);
		else
			temp.setNum(test2);
		temp.setNum(temp.getNum()/(temp.getDen()/newDen));
		temp.setDen(temp.getDen()/(temp.getDen()/newDen));
		return temp;
	}
	
	public String toString(){
		return this.num +" / "+this.den;
	}
}