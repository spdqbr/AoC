package com.spdqbr.aoc.utils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
public class BigMathUtils extends MathUtils{
	public static final BigInteger MINUS_ONE = new BigInteger("-1");
	public static final BigInteger ZERO = BigInteger.ZERO;
	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger MAX_LONG = bigInt(Long.MAX_VALUE);
	
	
	public static BigInteger bigInt(long n){
		return BigInteger.valueOf(n);
	}
	
	public static BigDecimal bigDec(long n){
		return BigDecimal.valueOf(n);
	}
	
	public static BigDecimal bigDec(double n){
		return new BigDecimal(n);
	}
	
	public static BigDecimal bigDec(BigInteger n){
		return new BigDecimal(n);
	}
	
	public static long floorLog2(BigInteger n){
		if(n.compareTo(ZERO)<=0)
			return -1;
		long count = 1;
		while(!(n = n.shiftRight(1)).equals(ZERO)){
			count++;
		}
		return count -1;
	}
	
	public static double log(BigInteger a, double base) {
	    int b = a.bitLength() - 1;
	    double c = 0;
	    double d = 1;
	    for (int i = b; i >= 0; --i) {
	        if (a.testBit(i))
	        	c += d;
	        d *= 0.5;
	    }
	    return (Math.log(c) + Math.log(2) * b) / Math.log(base);
	}
	
	public static BigInteger pow(BigInteger a, BigInteger b){
		if(compare(b,Integer.MAX_VALUE)<0)
			return a.pow(b.intValue());
		
		if(b.compareTo(ZERO)<0)
			return ZERO;
		if(b.equals(ZERO))
			return ONE;
		if(b.mod(bigInt(2)).equals(ZERO))
			return pow(a.multiply(a),b.shiftRight(1));
		return a.multiply(pow(a,b.subtract(ONE)));
	}
	
	public static BigDecimal pow(BigDecimal a, BigInteger b){
		return pow(a,b,30);
	}
	
	public static BigDecimal pow(BigDecimal a, BigInteger b, int places){
		if(b.compareTo(ZERO) == 0)
			return bigDec(1);
		if(b.compareTo(ZERO) < 0)
			return bigDec(1).divide(pow(a,b.multiply(MINUS_ONE),places),places, RoundingMode.HALF_UP);
			
		if(b.mod(bigInt(2)).equals(ZERO))
			return pow(a.multiply(a),b.shiftRight(1),places);
		return a.multiply(pow(a,b.subtract(ONE),places));
	}
	
	public static BigInteger gcd(BigInteger a, BigInteger b){
		BigInteger temp;
		while(!b.equals(ZERO)){
			temp = a;
			a = b;
			b = temp.mod(b);
		}
		return a;
	}
	
	public static int compare(BigInteger a, BigDecimal b){
		return bigDec(a).compareTo(b);
	}
	
	public static int compare(BigDecimal a, BigInteger b){
		return a.compareTo(bigDec(b));
	}
	
	public static int compare(long a, BigInteger b){
		return bigInt(a).compareTo(b);
	}
	
	public static int compare(BigInteger a, long b){
		return a.compareTo(bigInt(b));
	}
	
	public static int compare(long a, BigDecimal b){
		return bigDec(a).compareTo(b);
	}
	
	public static int compare(BigDecimal a, long b){
		return a.compareTo(bigDec(b));
	}
	
	public static BigInteger nextPrime(BigInteger n){
		if(compare(n,Long.MAX_VALUE) < 0)
			return bigInt(MathUtils.nextPrime(n.longValue()));
		return MINUS_ONE;
	}
	
	public static BigInteger[] root(BigInteger n, BigInteger k){
		BigInteger low = ZERO;
		BigInteger high = n.add(ONE);
		BigInteger mid = null;
		BigInteger mr  = null;
		while(high.compareTo(low.add(ONE)) > 0){
			mid = (low.add(high)).shiftRight(1);
			mr = pow(mid,k);
			int flag = mr.compareTo(n); 
			if(flag ==0 )
				return new BigInteger[] {mid,ONE};
			if(flag < 0)
				low = mid;
			if(flag > 0)
				high = mid;
		}
		return new BigInteger[] {low, ZERO};
	}

	public static BigInteger sqrt(BigInteger n){
		return root(n, bigInt(2))[0];
	}
	
	public static BigDecimal sqrt(BigDecimal n, int scale){
		BigDecimal two = BigDecimal.valueOf(2);
		BigDecimal guess = n.divide(two,scale,RoundingMode.HALF_UP);
		BigDecimal lastGuess = BigDecimal.ZERO;
		while(!guess.equals(lastGuess)){
			lastGuess = guess;
			guess = guess.add(n.divide(guess,scale,RoundingMode.HALF_UP)).divide(two,scale,RoundingMode.HALF_UP);
		}
		return guess;
	}

	public static boolean isPrime(BigInteger n){
		boolean doLoop = false;
		BigInteger j = ONE;
		BigInteger d = n.shiftRight(1);
		BigInteger a = bigInt(2);
		
		BigInteger bound = (bigDec(0.75).multiply(bigDec(Math.log(log(n,Math.E)))).multiply(bigDec(log(n,Math.E)))).toBigInteger().add(ONE);

		while((d.and(ONE)).equals(ZERO)){
			d = d.shiftRight(1);
			j = j.add(ONE);
		}
		while(a.compareTo(bound)< 0){
			a = nextPrime(a);
			BigInteger p = a.modPow(d, n);
			if(p.equals(ONE) || p.equals(n.subtract(ONE)))
				continue;
			
			for(BigInteger x = ZERO; x.compareTo(j)<0; x = x.add(ONE)){
				p = p.multiply(p).mod(n);
				if(p.equals(ONE))
					return false;
				else if(p.equals(n.subtract(ONE))){
					doLoop = true;
					break;
				}
			}
			
			if(doLoop){
				doLoop = false;
				continue;
			}
			
			return false;
		}
		return true;
	}
	
	public static BigInteger factorial(long n){
		BigInteger prod = BigInteger.ONE;
		for(long i = 2; i <=n; i++)
			prod = prod.multiply(BigInteger.valueOf(i));
		return prod;
	}
	
    public static BigInteger bigNcr(int n, int r){
    	r = r > n/2? n-r: r;
    	if(n <= 0 || r < 0)
    		return BigInteger.ZERO;
    	if(r == 0)
    		return BigInteger.ONE;
    	if(r == 1)
    		return BigInteger.valueOf(n);
    	BigInteger[] pascalRow = new BigInteger[n/2+1];
    	Arrays.fill(pascalRow,BigInteger.ZERO);
    	pascalRow[0] = BigInteger.ONE;
    	for(int i = 0; i < n; i++){
    		for(int j = pascalRow.length-1; j > 0; j--)
    			pascalRow[j] = pascalRow[j].add(pascalRow[j-1]);
    	}
    	return pascalRow[r];
    }
    
    public static long sumDigits(BigInteger n){
    	long sum = 0;
    	while(!n.equals(BigInteger.ZERO)){
    		sum += n.mod(BigInteger.TEN).longValue();
    		n = n.divide(BigInteger.TEN);
    	}
    	return sum;
    }
    
    public static BigInteger repUnit(int digits){
    	return BigInteger.TEN.pow(digits).subtract(BigInteger.ONE).divide(BigInteger.valueOf(9));
    }
    
    public static BigInteger incrementRepUnit(BigInteger repUnit){
    	return repUnit.multiply(BigInteger.TEN).add(BigInteger.ONE);
    }
    
    public static BigInteger min(BigInteger a, BigInteger b) {
    	if(a == null && b == null) return null;
    	if(a == null) return b;
    	if(b == null) return a;
    	return a.compareTo(b) < 0 ? a : b;
    }
    
    public static BigInteger max(BigInteger a, BigInteger b) {
    	if(a == null && b == null) return null;
    	if(a == null) return b;
    	if(b == null) return a;
    	return a.compareTo(b) > 0 ? a : b;
    }
}
