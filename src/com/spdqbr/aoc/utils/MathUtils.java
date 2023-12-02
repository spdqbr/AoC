package com.spdqbr.aoc.utils;
import java.util.*;
import java.util.regex.*;

public class MathUtils extends Utils{
	
	public static Random rand = new Random();
	public static long[] primes = primesUnder(1000); 
	
	
	public static int random(int min, int max){
		return min+rand.nextInt(max-min);
	}
	
	/**
	 * Returns the sum of all entries in a double[]
	 * @param list the double[] to sum
	 * @return the sum of all entries in list
	 */
	public static double sum(double[] list){
		return Arrays.stream(list).sum();
	}
	
	public static long sum(long[] list){
		return Arrays.stream(list).sum();
	}
	
	/**
	 * Converts an int[] to a double[]
	 * @param list the int[] to convert to a double[]
	 * @return a double[] containing the double equivalents of the entries in list.
	 */
	public static double[] toDoubleArray(int[] list){
		double[] out = new double[list.length];
		for(int i = 0; i< list.length; i++){
			out[i]=(double)list[i];
		}
		return out;
	}
	
	/**
	 * Returns the sum of all entries in an int[]
	 * @param list the int[] to sum
	 * @return the sum of all entries in list
	 */
	public static int sum(int[] list){
		return Arrays.stream(list).sum();
	}

	/**
	 * Returns the arithmetic mean of all entries in a double[]
	 * @param list the double[] whose mean is to be found
	 * @return the mean of all entries in list
	 */
	public static double mean(double[] list){
		return Arrays.stream(list).average().getAsDouble();
	}

	/**
	 * Returns the arithmetic mean of all entries in an int[]
	 * @param list the int[] whose mean is to be found
	 * @return the mean of all entries in list
	 */
	public static double mean(int[] list){
		return Arrays.stream(list).average().getAsDouble();
	}
	
	/**
	 * Returns a Object[] of a given size containing a random collection of entries from the
	 * given Object[]
	 * @param list the source Object[] from which to take a sample
	 * @param size the number of elements to sample from list
	 * @return an Object[] containing <size> random elements from <list>
	 */
	public static Object[] randomSample(Object[] list, int size){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		for(int i = 0; i<list.length; i++){
			arrayList.add(list[i]);
		}
		
		Object[] out = new Object[size];
		int index = 0;
		for(int i = 0; i<size; i++){
			index = rand.nextInt(arrayList.size());
			out[i]=arrayList.remove(index);
		}
		return out;
	}
	
	/**
	 * Returns a int[] of a given size containing a random collection of entries from the
	 * given int[]
	 * @param list the source int[] from which to take a sample
	 * @param size the number of elements to sample from list
	 * @return an int[] containing <size> random elements from <list>
	 */
	public static int[] randomSample(int[] list, int size){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		for(int i = 0; i<list.length; i++){
			arrayList.add(list[i]);
		}
		
		int[] out = new int[size];
		int index = 0;
		for(int i = 0; i<size; i++){
			index = rand.nextInt(arrayList.size());
			out[i]=((Integer)arrayList.remove(index)).intValue();
		}
		return out;
	}
	
	/**
	 * Returns a double[] of a given size containing a random collection of entries from the
	 * given double[]
	 * @param list the source double[] from which to take a sample
	 * @param size the number of elements to sample from list
	 * @return an double[] containing <size> random elements from <list>
	 */
	public static double[] randomSample(double[] list, int size){
		ArrayList<Object> arrayList = new ArrayList<Object>();
		for(int i = 0; i<list.length; i++){
			arrayList.add(list[i]);
		}
		
		double[] out = new double[size];
		int index = 0;
		for(int i = 0; i<size; i++){
			index = rand.nextInt(arrayList.size());
			out[i]=((Double)arrayList.remove(index)).doubleValue();
		}
		return out;
	}
	
	/**
	 * Returns an int[] containing the elements from a certain range of a given int[]
	 * @param list The parent int[]
	 * @param start The start index for the sub array (inclusive)
	 * @param end The end index for the sub array (exclusive)
	 * @return an int[] containing elements from list[start] to list[end-1]
	 */
	public static int[] subArray(int[] list, int start, int end){
		if(start < 0 || end > list.length || end < start)
			return null;
		int[] out = new int[end-start];
		for(int i = 0; i<end-start; i++){
			out[i] = list[i+start];
		}
		return out;
	}
	
	/**
	 * Returns a double[] containing the elements from a certain range of a given double[]
	 * @param list The parent double[]
	 * @param start The start index for the sub array (inclusive)
	 * @param end The end index for the sub array (exclusive)
	 * @return an double[] containing elements from list[start] to list[end-1]
	 */
	public static double[] subArray(double[] list, int start, int end){
		if(start < 0 || end > list.length || end < start)
			return null;
		double[] out = new double[end-start];
		for(int i = 0; i<end-start; i++){
			out[i] = list[i+start];
		}
		return out;
	}
	
	/**
	 * Returns an Object[] containing the elements from a certain range of a given Object[]
	 * @param list The parent Object[]
	 * @param start The start index for the sub array (inclusive)
	 * @param end The end index for the sub array (exclusive)
	 * @return an Object[] containing elements from list[start] to list[end-1]
	 */
	public static Object[] subArray(Object[] list, int start, int end){
		if(start < 0 || end > list.length || end < start)
			return null;
		Object[] out = new Object[end-start];
		for(int i = 0; i<end-start; i++){
			out[i] = list[i+start];
		}
		return out;
	}
	
	/**
	 * Returns a ArrayList<String> containing all possible permutations of a given string.
	 * @param prefix 
	 * @param suffix
	 * @param out a reference to the output ArrayList
	 * @return a ArrayList<String> containing all possible permutations of a given string
	 */
	private static HashSet<String> permutations(String prefix, String suffix, HashSet<String> out){
		if(suffix.length() == 1){
			//System.out.println(prefix+suffix);
			out.add(prefix+suffix);
			return out;
		}
		for(int i = 0; i< suffix.length(); i++){
			String newPrefix = prefix+suffix.substring(i,i+1);
			String newSuffix = suffix.substring(0,i)+suffix.substring(i+1,suffix.length());
			out = permutations(newPrefix,newSuffix, out);
		}
		return out;
	}
	
	/**
	 * Returns a String[] containing all possible permutations of a given String.
	 * @param perm The string to permute
	 * @return a String[] containing all possible permutations of perm
	 */
	public static String[] permutations(String perm){
		HashSet<String> out = permutations("",perm, new HashSet<String>());
		String[] ret = new String[out.size()];
		int index = 0;
		for(String s : out){
			ret[index] = s;
			index++;
		}
		return ret;
	}
	
	/**
	 * Returns the greatest common divisor of two integers
	 * @param a the first integer
	 * @param b the second integer
	 * @return the gcd of a and b
	 */
	public static long gcd(long a, long b){
		return b==0?a:gcd(b,a%b);
	}
	

	/**
	 * Returns the least common multiple of two integers
	 * @param a the first integer
	 * @param b the second integer
	 * @return the lcm of a and b
	 */
	public static long lcm(long a, long b){
		return a*(b/gcd(a,b));
	}
	
	
	/**
	 * Returns all possible combinations of all possible lengths of the elements of list
	 * @param list the input list
	 * @return all possible combinations of elements of list
	 */
	public static ArrayList<Object> combinations(Object[] list){
		ArrayList<Object> out = new ArrayList<Object>();
		for(int i = 0; i <= list.length; i++){
			ArrayList<ArrayList<Object>> temp = combinations(list, i);
			for(int j = 0; j<temp.size(); j++){
				out.add(temp.get(j));
			}
		}
		return out;
	}
	
	/**
	 * Returns all possible combinations of all possible lengths of the characters of string
	 * @param string the input string
	 * @return all possible combinations of characters of string
	 */
	public static String[] combinations(String string){
		ArrayList<Object> al = new ArrayList<Object>();
		for(int i = 0; i<=string.length(); i++){
			String[] temp = combinations(string,i);
			for(int j = 0; j<temp.length; j++){
				al.add(temp[j]);
			}
		}
		String[] out = new String[al.size()];
		for(int i = 0; i<out.length; i++){
			out[i] = (String)al.get(i);
		}
		return out;
	}
	
	/**
	 * returns an ArrayList of length ncr(list.size(),choose)
	 * itself containing ArrayLists of length choose.  Each sub ArrayList
	 * contains a possible combination of choose elements from list.  All possible
	 * combinations of length n are given.
	 * @param list
	 * @param choose
	 * @return All possible combinations of length choose from list
	 */
	public static ArrayList<ArrayList<Object>> combinations(Object[] list, int choose){
		return combinations(list,choose,null,null);
	}
	
	/**
	 * See combinations(Object[] list, int choose)
	 * 
	 * @param list The list from which to choose
	 * @param choose The number of elements to choose from the list
	 * @param prefix prefix carried for recursion
	 * @param out prefix carried for recursion
	 * @return
	 */
	public static ArrayList<ArrayList<Object>> combinations(Object[] list, int choose, ArrayList<Object> prefix, ArrayList<ArrayList<Object>> out){
		if(prefix == null)
			prefix = new ArrayList<Object>();
		if(out == null)
			out = new ArrayList<ArrayList<Object>>();
		if(choose == 0){
			out.add(new ArrayList<Object>(prefix));
			return out;
		}
		for(int i = 0; i<list.length; i++){
			prefix.add(list[i]);
			combinations(subArray(list,i+1,list.length),choose-1,prefix,out);
			prefix.remove(prefix.size()-1);
		}
		return out;
	}
	
	
	/**
	 * Gives a string list containing all possible combinations of choose letters from the given string
	 * @param string the source list of characters
	 * @param choose the number of characters to choose
	 * @return all possible combinations of choose characters from string
	 */
	public static String[] combinations(String string, int choose){
		String[] array = new String[string.length()];
		for(int i = 0; i<string.length(); i++){
			array[i] = string.substring(i,i+1);
		}
		ArrayList<ArrayList<Object>> al = combinations(array, choose);
		ArrayList<Object> out = new ArrayList<Object>();
		ArrayList<Object> temp;
		for(int i = 0; i < al.size(); i++){
			temp = (ArrayList<Object>)al.get(i);
			StringBuffer buffer = new StringBuffer();
			for(int j = 0; j < temp.size(); j++){
				buffer.append(temp.get(j));
			}
			out.add(buffer.toString());
		}
		String[] outS = new String[out.size()];
		for(int i = 0; i < outS.length; i++){
			outS[i] = (String)out.get(i);
		}
		return outS;
	}

	
	/**
	 * Convert an int from base 10 to an arbitrary base
	 * @param dec  The base 10 representation of a number
	 * @param baseNum The base to which dec should be converted
	 * @return representation of dec_10 in dec_baseNum
	 */
	public static String toBase(int dec, int baseNum){
		if(dec == 0)
			return "0";
		StringBuffer sb = new StringBuffer();
		int base = 1;
		int pow = 0;
		while(base <= dec){
			base *= baseNum;
			pow ++;
		}
		int mult = 0;
		while(pow != 0){
			pow --;
			base /= baseNum;
			mult = dec / base;
			dec %= base;
			if(mult > 9)
				sb.append((char)('A'+(mult-10)));
			else
				sb.append(mult);
		}
		return sb.toString();
	}
	
	/**
	 * Convert an int from decimal to binary, padding
	 * on the left with 0's to reach the given number of bits.
	 * If the number requires more than the given number of bits,
	 * it is trimmed to size()
	 * 
	 * @param dec dec integer to convert
	 * @param bits the number of bits the string should be
	 * @return the n-bit binary representation of the given decimal integer
	 */
	public static String toBin(int dec, int bits){
	   dec %= (int)Math.pow(2,bits);
	   String out = Integer.toBinaryString(dec);
	   return lPad(out,bits,'0');
	}
	
    public static long modPow(long a, long b, long c){
    	a %= c;
    	if(b == 0)
    		return 1;
    	if(b == 1)
    		return a;
    	if(b % 2 == 0)
    		return modPow(a*a, b/2, c);
    	return (a*modPow(a,b-1,c))%c;
    }

	public static boolean isPrime(long n){
		if(n < 2)
			return false;
		if(Arrays.binarySearch(primes, n) >=0 )
			return true;
		long i;
		for(i = 0; i < primes.length && primes[(int)i] <= Math.sqrt(n); i++)
			if(n % primes[(int)i] == 0)
				return false;
		if( i < primes.length)
			return true;
		
		for(i = primes[primes.length-1]; i <= Math.sqrt(n); i+=2)
			if(n%i==0)
				return false;
		return true;		
	}


	/**
	 * Returns a long[] containing all primes under a the given upper bound.
	 * @param n the upper bound for p (exclusive)
	 * @return an long[] containing all primes, p, satisfying 0 <= p < n
	 */
	public static long[] primesUnder(int n){
    	boolean[] sieve = new boolean[n];
    	Arrays.fill(sieve,true);
    	sieve[0] = false;
    	sieve[1] = false;
    	int composites = 2;
    	for(int i = 2; i < sieve.length; i++)
    		if(sieve[i])
    			for(long j = i; i*j < sieve.length; j++)
    				if(sieve[(int)j*i]){
    					sieve[(int)j*i] = false;
    					composites++;
    				}

    	long[] out = new long[n - composites];
    	int sieveIndex = 0;
		for(int i = 0; i < out.length; i++ ){
			while(!sieve[sieveIndex])
				sieveIndex ++;
			out[i] = sieveIndex;
			sieveIndex++;
		}
		return out;
	}
	
	/**
	 * Returns the first n prime numbers
	 * @param n the number of primes to return
	 * @return the first n primes
	 */
	public static int[] primes(int n){
		if(n <= 0)
			return new int[0];
		int[] out = new int[n];
		out[0] = 2;
		int test = 3;
		for(int i = 1; i < out.length; i++){
			for(int j = 0; j < i && out[j] < Math.ceil(Math.sqrt(test))+1; j++)
				if(test % out[j] == 0){
					j = -1;
					test += 2;
				}
			out[i] = test;
			test += 2;
		}
		return out;
	}
	
     /**
      * The recursive Binary Search function for ArrayLists's
      * @param array The array to search
      * @param value The value to find
      * @param min The lowest number to search
      * @param max The highest number to search
      * @return
      */
     public static int binarySearch(ArrayList<Object> array, int value, int min, int max){
    	 int low = min;
    	 int high = max;
    	 int lastLocation = -1;
    	 int thisLocation = (high-low)>>1;
    	 while(lastLocation != thisLocation && ((Integer)array.get(thisLocation)).intValue() != value){
    		 lastLocation = thisLocation;
    		 if(((Integer)array.get(thisLocation)).intValue() == Integer.MAX_VALUE || ((Integer)array.get(thisLocation)).intValue()>value)
    			 high = thisLocation;
    		 else
    			 low = thisLocation;
    		 thisLocation = low + (high-low)>>1;
    	 }
    	 return ((Integer)array.get(thisLocation)).intValue()>value?thisLocation-1:thisLocation;
     }
	 
	 /**
	  * Takes a string of numbers and mathematical operators and attempts
	  * to evaluate it using the standard PEMDAS order of operations
	  * @param expression the String to evaluate
	  * @return The valuation of the string.
	  * @throws NumberFormatException
	  */
     public static double evalString(String expression) throws NumberFormatException{
         try{
        	 expression = expression.replaceAll("\\s+", "").replaceAll("(\\d)\\-", "$1~");
             System.out.println("Now evaluating: "+expression);
             Pattern pat = Pattern.compile("\\([^\\(]+?\\)");
             Matcher mat = pat.matcher(expression);
             if(mat.find()){
                 String parens = mat.group();
                 return evalString(expression.replaceFirst("\\([^\\(]+?\\)", evalString(parens.substring(1,parens.length()-1))+""));
             }
             pat = Pattern.compile("\\-?\\d*\\.?\\d+\\x5e\\-?\\d*\\.?\\d+");
             mat = pat.matcher(expression);
             
             while(mat.find()){
                 String exp = mat.group();
                 int index = exp.indexOf("^");
                 expression = expression.replaceFirst("\\-?\\d*\\.?\\d+\\x5e\\-?\\d*\\.?\\d+", Math.pow(Double.parseDouble(exp.substring(0,index)),Double.parseDouble(exp.substring(index+1,exp.length())))+"");
             }
             
             pat = Pattern.compile("\\-?\\d*\\.?\\d+\\%\\-?\\d*\\.?\\d+");
             mat = pat.matcher(expression);
             while(mat.find()){
                 String exp = mat.group();
                 int index = exp.indexOf("%");
                 expression = expression.replaceFirst("\\-?\\d*\\.?\\d+\\%\\-?\\d*\\.?\\d+", (Double.parseDouble(exp.substring(0,index))%Double.parseDouble(exp.substring(index+1,exp.length())))+"");
             }
             
             String[] subs = expression.split("\\+");
             if(subs.length > 1 && subs[0] != null && !subs[0].equals("")){
                 double sum = evalString(subs[0]);
                 for(int i = 1; i < subs.length; i++){
                     sum += evalString(subs[i]);
                 }
                 return sum;
             }
             subs = expression.split("~");
             if(subs.length > 1 && subs[0] != null && !subs[0].equals("")){
            	 double diff = evalString(subs[0]);
                 for(int i = 1; i < subs.length; i++){
                     diff -= evalString(subs[i]);
                 }
                 return diff;
             }
             subs = expression.split("\\*");
             if(subs.length > 1){
                 double prod = evalString(subs[0]);
                 for(int i = 1; i < subs.length; i++){
                     prod *= evalString(subs[i]);
                 }
                 return prod;
             }
             subs = expression.split("/");
             if(subs.length > 1){
                 double quot = evalString(subs[0]);
                 for(int i = 1; i < subs.length; i++){
                     quot /= evalString(subs[i]);
                 }
                 return quot;
             }

             return Double.parseDouble(expression);
         }catch(NumberFormatException e){
             throw new NumberFormatException();
         }
     }
	 
     /**
      * Returns the set of all possible subsets of list
      * @param list the input list
      * @return all possible subsets of list
      */
	 public static int[][] powerSet(int[] list){
	     int[][] out = new int[(int)Math.pow(2,list.length)][];
	     String include;
	     int includeCount;
	     for(int i = 0; i<Math.pow(2,list.length); i++){
	         include = toBin(i,list.length);
	         includeCount = 0;
	         for(int j = 0; j < list.length; j++){
	             if(include.charAt(j) == '1')
	                 includeCount ++;
	         }
	         out[i] = new int[includeCount];
	         for(int j = 0; j < list.length; j++){
	             if(include.charAt(j) == '1'){
	                 out[i][out[i].length-includeCount] = list[j];
	                 includeCount--;
	             }
	         }
	     }
	     return out;
	 }
	 
	 public static long nextPrime(long n){
		 	double LOG_2 = Math.log(2);
			if(n < primes[primes.length-1]){
				int index = Arrays.binarySearch(primes, n);
				return index < 0?primes[-1*index-1]:primes[index+1];
			}
			n += 1;

			n += (n & 1) ^ 1;
			long inc = n % 3;
			n += ((4 - inc) >> 1) & 2;
			inc = 6 - ((inc + ((2 - inc) & 2)) << 1);
			boolean should_break = false;

			while(true){
				for(int i = 0; i < primes.length; i++){
					if( n % primes[i] == 0){
						should_break = true;
						break;
					}
				}

				if(should_break){
					should_break = false;
					n += inc;
					inc = 6 - inc;
					continue;
				}

				long p = 1;
				for(int i =(int)(Math.log(n) / LOG_2); i > 0; i--){
					p <<= (n >> i) & 1;
					p = (p * p) % n;
				}
				
				if(p == 1)
					return isPrime(n)?n:nextPrime(n);
			
				n += inc;
				inc = 6 - inc;
			}
		}
	 
	 public static ArrayList<Long> factor(long n){
		 ArrayList<Long> factors = new ArrayList<Long>();
		 if(isPrime(n)){
			 factors.add(n);
			 return factors;
		 }
		 long[] primes = primesUnder((int)Math.ceil(Math.sqrt(n)+1));
		 long nCopy = n;
		 for(int i = 0; i < primes.length && nCopy != 1; i++){
			 while(nCopy % primes[i] == 0){
				 factors.add((long)primes[i]);
				 nCopy /= primes[i];
			 }
		 }
		 if(nCopy != 1){
			 //System.out.println("Not quite done yet, adding factors of "+nCopy);
			 factors.addAll(factor(nCopy));
		 }
		 return factors;
	 }
	 
	 
	 public static long[][] factorExp(long n){
		 ArrayList<Long> factors = factor(n);
		 int uniqueCount = 1;
		 Object last = factors.get(0);
		 for(int i = 0; i < factors.size(); i++){
			 Object current = factors.get(i);
			 if(!last.equals(current)){
				 uniqueCount++;
				 last = current;
			 }
		 }
		 long[][] out = new long[2][uniqueCount];

		 out[0][0] = factors.get(0); 
		 out[1][0] = 0;
		 int i = 0;
		 for(int j = 0; j < factors.size(); j++){
			 long tempInt = factors.get(j);
			 if(tempInt != out[0][i]){
				 i++;
				 out[0][i] = tempInt;
			 }
			 out[1][i]++;
		 }
		 return out;		 
	 }
	 
	 public static String factorString(long n){
		 long[][] factorExp = factorExp(n);
		 StringBuffer sb = new StringBuffer();
		 for(int i = 0; i < factorExp[0].length; i++){
			 sb.append(factorExp[0][i]+"^"+factorExp[1][i]+"+");
		 }
		 return sb.substring(0,sb.length()-1);
	 }
	 
	 public static double[] quadratic(double a, double b, double c){
    	if(b*b < 4*a*c)
    		return new double[0];
    	if(b*b == 4*a*c)
    		return new double[] { (-1*b + Math.sqrt(b*b-4*a*c))/(2*a) };
    	return new double[] { (-1*b + Math.sqrt(b*b-4*a*c))/(2*a) , (-1*b - Math.sqrt(b*b-4*a*c))/(2*a)}; 
    }
	 
	    
    public static boolean isPentagonal(long n){
    	double[] solutions = MathUtils.quadratic(1.5, -0.5, -1*n);
    	if(solutions.length == 0)
    		return false;
    	for(int i = 0; i < solutions.length; i++)
    		if( solutions[i] >= 0 && solutions[i] % 1 == 0)
    			return true;
    	return false;
    }
    
    public static boolean isTriangle(long n){
    	double[] solutions = MathUtils.quadratic(0.5, 0.5, -1*n);
    	if(solutions.length == 0)
    		return false;
    	for(int i = 0; i < solutions.length; i++)
    		if( solutions[i] >= 0 && solutions[i] % 1 == 0)
    			return true;
    	return false;
    }
    
    public static long ncr(int n, int r){
    	if(n > 66){
    		java.math.BigInteger bigncr = BigMathUtils.bigNcr(n, r);
    		if(bigncr.compareTo(java.math.BigInteger.valueOf(Long.MAX_VALUE)) > 0)
    			return -1;
    		return bigncr.longValue();
    	}
    	r = r > n/2? n-r: r;
    	if(n <= 0 || r < 0)
    		return 0;
    	if(r == 0)
    		return 1;
    	if(r == 1)
    		return n;
    	long[] pascalRow = new long[n/2+1];
    	pascalRow[0] = 1;
    	for(int i = 0; i < n; i++){
    		for(int j = pascalRow.length-1; j > 0; j--)
    			pascalRow[j] += pascalRow[j-1];
    	}
    	return pascalRow[r];
    }
    
    public static long totient(long n){
    	long[][] factors = MathUtils.factorExp(n);
    	long phi = 1;
    	for(int i = 0; i < factors[0].length; i++)
    		phi *= (long)((factors[0][i]-1)*Math.pow(factors[0][i], factors[1][i]-1));
    	return phi;
    }
    
    public static int[] continuedFractionSqrt(int s, int depth){
    	int[] out = new int[depth];
    	int m = 0;
    	int d = 1;
    	int a0 = (int)Math.floor(Math.sqrt(s));
    	int a = a0;
    	for(int i = 0; i < out.length; i++){
    		m = d*a-m;
    		d = (s - m*m)/d;
    		a = (int)Math.floor((a0+m)/(double)d);
    		out[i] = a;
    	}
    	return out;
    }
    
    public static double evalPostfix(String postfix){
		ArrayList<Double> stack = new ArrayList<Double>();
		String[] values = postfix.split(",");
		for(int i = 0; i < values.length; i++){
			if(values[i].equals("+")){
				double b = stack.remove(stack.size()-1);
				double a = stack.remove(stack.size()-1);
				stack.add(a+b);
			}else if(values[i].equals("-")){
				double b = stack.remove(stack.size()-1);
				double a = stack.remove(stack.size()-1);
				stack.add(a-b);
			}else if(values[i].equals("*")){
				double b = stack.remove(stack.size()-1);
				double a = stack.remove(stack.size()-1);
				stack.add(a*b);
			}else if(values[i].equals("/")){
				double b = stack.remove(stack.size()-1);
				double a = stack.remove(stack.size()-1);
				stack.add(a/b);
			}else{
				stack.add(Double.valueOf(values[i]));
			}
		}
		if(stack.size() == 1)
			return stack.get(0);
		throw new NumberFormatException("Invalid stack");
	}
    
    public static long[] divisors(long n){
    	if(n == 0)
    		return new long[0];
    	if(n == 1)
    		return new long[] {1};
		long[][] factorExp = factorExp(n);
		long[] exponents = new long[factorExp[1].length];
		Arrays.fill(exponents, 0);
		ArrayList<Long> divisors = new ArrayList<Long>();
		while(true){
			long thisDivisor = 1;
			for(int i = 0; i < exponents.length; i++)
				thisDivisor *= (long)Math.pow(factorExp[0][i], exponents[i]);
			divisors.add(thisDivisor);
			int i = 0;
			exponents[i] ++;
			while(exponents[i] > factorExp[1][i]){
				exponents[i] = 0;
				i++;
				if(i >= exponents.length){
					long[] out = new long[divisors.size()];
					for(int j = 0; j < out.length; j++)
						out[j] = divisors.get(j);
					return out;
				}
				exponents[i]++;
			}
		}
	}
    
    public static int sumDigits(long n){
    	int sum = 0;
    	while(n != 0){
    		sum += n%10;
    		n /= 10;
    	}
    	return sum;
    }
    
	public static long rad(int n) {
		long[] facs = com.spdqbr.aoc.utils.MathUtils.factorExp(n)[0];
		long prod = 1;
		for (int i = 0; i < facs.length; i++)
			prod *= facs[i];
		return prod;
	}
	
	public static int digitalSum(int n){
		int sum = 0;
		while(n != 0){
			sum += n%10;
			n /= 10;
		}
		return sum;
	}
	
	public static int distance(int a, int b) {
		return Math.abs(a-b);
	}
	
	public static long distance(long a, long b) {
		return Math.abs(a-b);
	}
}
