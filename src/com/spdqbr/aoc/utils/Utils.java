package com.spdqbr.aoc.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Utils {

	public static String repeatString(String in, int repeats) {
		if (in.equals("") || repeats < 1)
			return "";
		if (repeats % 2 == 0)
			return repeatString(in + in, repeats >> 1);
		return in + repeatString(in, repeats - 1);
	}

	/**
	 * Pads a string on the left with a given character until it is equal to or
	 * longer than the given length. If it is already longer than the given
	 * length, no action is performed.
	 * 
	 * @param In
	 *            the string to pad
	 * @param length
	 *            The total desired ending length of the string.
	 * @param pad
	 *            The character to be used as a pad
	 * @return A string of at least the given length.
	 */
	public static String lPad(String inStr, int len, char padChar) {
		if (inStr.length() >= len)
			return inStr.substring(0, len);
		return repeatString(padChar + "", len - inStr.length()) + inStr;
	}

	/**
	 * Pads a string on the left with ' ' until it is equal to or longer than
	 * the given length. If it is already longer than the given length, no
	 * action is performed.
	 * 
	 * @param in
	 * @param length
	 * @return A string of at least the given length.
	 */
	public static String lPad(String in, int length) {
		return lPad(in, length, ' ');
	}

	/**
	 * Pads a string on the right with a given character until it is equal to or
	 * longer than the given length. If it is already longer than the given
	 * length, no action is performed.
	 * 
	 * @param In
	 *            the string to pad
	 * @param length
	 *            The total desired ending length of the string.
	 * @param pad
	 *            The character to be used as a pad
	 * @return A string of at least the given length.
	 */
	public static String rPad(String inStr, int len, char padChar) {
		if (inStr.length() >= len)
			return inStr.substring(0, len);
		return inStr + repeatString(padChar + "", len - inStr.length());
	}

	/**
	 * Pads a string on the right with ' ' until it is equal to or longer than
	 * the given length. If it is already longer than the given length, no
	 * action is performed.
	 * 
	 * @param in
	 * @param length
	 * @return A string of at least the given length.
	 */
	public static String rPad(String in, int length) {
		return rPad(in, length, ' ');
	}

	/**
	 * Pads a string with a given character until it is equal to or longer than
	 * the given length. If it is already longer than the given length, no
	 * action is performed. If an odd number of padding chars are required to
	 * meet the given length, the extra character is added to the right
	 * 
	 * @param In
	 *            the string to pad
	 * @param length
	 *            The total desired ending length of the string.
	 * @param pad
	 *            The character to be used as a pad
	 * @return A string of at least the given length.
	 */
	public static String cPad(String inStr, int len, char padChar) {
		return rPad(lPad(inStr, (len + inStr.length()) >> 1, padChar), len,
				padChar);
	}

	/**
	 * Pads a string with ' ' until it is equal to or longer than the given
	 * length. If it is already longer than the given length, no action is
	 * performed. If an odd number of padding chars are required to meet the
	 * given length, the extra character is added to the right
	 * 
	 * @param In
	 *            the string to pad
	 * @param length
	 *            The total desired ending length of the string.
	 * @param pad
	 *            The character to be used as a pad
	 * @return A string of at least the given length.
	 */
	public static String cPad(String in, int length) {
		return cPad(in, length, ' ');
	}

	/**
	 * Wrapper for System.currentTimeMillis() (I got tired of typing it)
	 * 
	 * @return The current system time in milliseconds
	 */
	public static long now() {
		return System.currentTimeMillis();
	}

	/**
	 * Converts a long into a String of the format: X years X days X hours X
	 * minutes X.xxx seconds
	 * 
	 * It will only start from the most significant time. Example: Input 61123
	 * Output 1 minute 1.123 seconds
	 * 
	 * @param millis
	 *            The length of time in milliseconds
	 * @return The human readable time string
	 */
	public static String longToTime(long millis) {
		long check = 1000L * 60 * 60 * 24 * 365L;
		long[] times = { 365, 24, 60, 60, 1000, 1 };
		long[] time = { 0, 0, 0, 0, 0, 0 };
		String[] labels = { "years", "days", "hours", "minutes" };

		boolean startCounting = false;

		StringBuffer out = new StringBuffer();

		for (int i = 0; i < times.length; i++) {
			time[i] = millis / check;
			millis %= check;
			check /= times[i];
			if (time[i] != 0 || startCounting || i > 3) {
				startCounting = true;
				switch (i) {
				case 4:
					out.append(time[i] + ".");
					break;
				case 5:
					out.append(time[i] + " seconds");
					break;
				default:
					out.append(time[i] + " " + labels[i] + " ");
				}
			}
		}
		return out.toString();
	}

	public static String toString(int[][] list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.length; i++)
			sb.append(Arrays.toString(list[i])
					+ (i == list.length - 1 ? "" : "\r\n"));
		return sb.toString();
	}

	public static String toString(long[][] list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.length; i++)
			sb.append(Arrays.toString(list[i])
					+ (i == list.length - 1 ? "" : "\r\n"));
		return sb.toString();
	}

	public static String toString(double[][] list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.length; i++)
			sb.append(Arrays.toString(list[i])
					+ (i == list.length - 1 ? "" : "\r\n"));
		return sb.toString();
	}

	public static String toString(Object[][] list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.length; i++)
			sb.append(Arrays.toString(list[i])
					+ (i == list.length - 1 ? "" : "\r\n"));
		return sb.toString();
	}

	/**
	 * Returns a String containing the contents of the given file
	 * 
	 * @param aFile
	 *            The File to read
	 * @return A String contaiing the contents of the file
	 */
	public static String readFile(File aFile) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader(aFile));
			String separator = System.getProperty("line.separator");
			String nextLine = in.readLine();
			while (nextLine != null) {
				sb.append(nextLine);
				sb.append(separator);
				nextLine = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString();
	}

	public static String readFile(String fileLocation) {
		return readFile(new File(fileLocation));
	}
	
	public static String[] readFileToArray(File aFile) {
		ArrayList<String> list = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(aFile));
			String line;
			while((line = in.readLine()) != null) {
				list.add(line);
			}
			in.close();
		}catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
		String[] arr = new String[list.size()];
		arr = list.toArray(arr);
		return arr;
	}
	
	public static String[] readFileToArray(String fileLocation) {
		return readFileToArray(new File(fileLocation));
	}
	
	public static int[] readFileToInts(File file) {
		return Arrays.stream(readFile(file).trim().split("[,\n]"))
			.mapToInt(x -> Integer.parseInt(x))
			.toArray();
	}
	
	public static int[] readFileToInts(String fileLocation) {
		return readFileToInts(new File(fileLocation));
	}
	
	public static int[][] readFileToMatrix(String fileLocation){
		return readFileToMatrix(new File(fileLocation));
	}
	
	public static int[][] readFileToMatrix(File file){
		String[] input = readFileToArray(file);
		int[][] out = new int[input.length][];
		for(int row = 0; row < input.length; row++) {
			out[row] = new int[input[row].length()];
			for(int col = 0; col < input[row].length(); col++) {
				out[row][col] = Integer.parseInt(input[row].substring(col,col+1));
			}
		}
		return out;
	}
	
	public static int[][] readFileToMatrix(String fileLocation, String split){
		return readFileToMatrix(new File(fileLocation), split);
	}
	
	public static int[][] readFileToMatrix(File file, String split){
		String[] input = readFileToArray(file);
		int[][] out = new int[input.length][];
		String[] temp;
		for(int row = 0; row < input.length; row++) {
			temp = input[row].split(split);
			out[row] = new int[temp.length];
			for(int col = 0; col < input[row].length(); col++) {
				out[row][col] = Integer.parseInt(temp[col]);
			}
		}
		return out;
	}
	
	public static int max(int[] list) {
		return Arrays.stream(list).reduce((x,y) -> x > y ? x : y).getAsInt();
	}
	
	public static long max(long[] list) {
		return Arrays.stream(list).reduce((x,y) -> x > y ? x : y).getAsLong();
	}
	
	/**
	 * Writes the contents of a given String to a given File.
	 * 
	 * THIS WILL OVERWRITE AN EXISTING FILE
	 * 
	 * @param aFile
	 *            The File to write
	 * @param contents
	 *            The contents to write to file
	 * @return false if any errors occurred, true otherwise.
	 */
	public static boolean writeFile(File aFile, String contents) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(aFile));
			out.write(contents);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Writes the given string to a file at the given location. This will
	 * OVERWRITE and existing contents.
	 * 
	 * @param fileLocation
	 *            A string representing the location of the file
	 * @param contents
	 *            A the contents to be written to the file
	 * @return true if the file is written successfully, false otherwise
	 */
	public static boolean writeFile(String fileLocation, String contents) {
		return writeFile(new File(fileLocation), contents);
	}

	/**
	 * Appends a given String to the a file if it exists. If it does not Exist,
	 * create the file then write the contents.
	 * 
	 * @param aFile
	 *            The file to append to
	 * @param contents
	 *            What to append
	 * @return false if any errors occurred, true otherwise.
	 */
	public static boolean appendToFile(File aFile, String contents) {
		try {
			if (!aFile.exists())
				aFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(aFile, true));
			out.write(contents);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Appends the given string to the file in the given location.
	 * 
	 * @param fileLocation
	 *            string representing the location of the file
	 * @param contents
	 *            the contents to append to the file
	 * @return true if the file was successfully written, false otherwise
	 */
	public static boolean appendToFile(String fileLocation, String contents) {
		return appendToFile(new File(fileLocation), contents);
	}


	/**
	 * Given a File array, zips all the files and places them into a given Zip
	 * outputStream. Recursively zips directorys
	 * 
	 * @param inFiles
	 * @param zip
	 * @return
	 */
	public static boolean zipFiles(File[] inFiles, ZipOutputStream zip,
			String entryPrefix, String outFileName) {
		boolean returnValue = true;
		try {
			FileInputStream fis;
			byte[] data;
			for (int i = 0; i < inFiles.length && returnValue; i++) {
				if (inFiles[i].isDirectory()) {
					returnValue = zipFiles(
							inFiles[i].listFiles(),
							zip,
							entryPrefix + inFiles[i].getName() + File.separator,
							outFileName);
				} else {
					if (inFiles[i].toString().equals(outFileName))
						continue;
					fis = new FileInputStream(inFiles[i]);
					data = new byte[fis.available()];
					fis.read(data, 0, fis.available());
					fis.close();
					ZipEntry entry = new ZipEntry(entryPrefix
							+ inFiles[i].getName());
					zip.putNextEntry(entry);
					zip.write(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return returnValue;
	}

	/**
	 * Given a File array, zips all the files and places them into a given Zip
	 * file. Recursively zips directories
	 * 
	 * @param inFiles
	 *            the File[] containing the files to be zipped
	 * @param outFile
	 *            The output zip file
	 * @return false if any errors occurred, true otherwise.
	 */
	public static boolean zipFiles(File[] inFiles, File outFile) {
		boolean returnValue = true;
		try {
			ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(
					outFile));
			returnValue = zipFiles(inFiles, zip, "", outFile.toString());
			zip.flush();
			zip.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return returnValue;
	}

	/**
	 * Zips a file into the given zip file. If inFile is a directory, all files
	 * inside the directory are zipped.
	 * 
	 * @param inFile
	 *            The file to zip
	 * @param outFile
	 *            the output zip file
	 * @return false if any errors occurred, true otherwise.
	 */
	public static boolean zipFile(File inFile, File outFile) {
		return zipFiles(inFile.isDirectory() ? inFile.listFiles()
				: new File[] { inFile }, outFile);
	}

	/**
	 * Zips a file into a file called FileName.zip If inFile is a directory, all
	 * files inside the directory are zipped. and the zip file is created inside
	 * the directory with the name <name of directory>.zip Example: Zips
	 * MyFile.txt to MyFile.txt.zip Zips MyFile.properties to
	 * MyFile.properties.zip Zips contents of c:\myDirectory to
	 * c:\myDirectory\myDirectory.zip
	 * 
	 * @param inOutFile
	 *            the file to be zipped
	 * @return false if any errors occurred, true otherwise.
	 */
	public static boolean zipFile(File inOutFile) {
		return zipFile(inOutFile, new File(inOutFile.toString()
				+ (inOutFile.isDirectory() ? File.separator
						+ inOutFile.getName() : "") + ".zip"));
	}

	/**
	 * Applies the rot13 cipher to a text string. Only changes the characters
	 * [A-Za-z], all others are simply carried through the text.
	 * 
	 * @param string
	 *            A plaintext or ciphertext string to be ciphered.
	 * @return the rot13'd text
	 */
	public static String rot13(String string) {
		StringBuffer sb = new StringBuffer();
		char ch;
		for (int i = 0; i < string.length(); i++) {
			ch = string.charAt(i);
			if (ch >= 'A' && ch <= 'Z')
				sb.append(ch < 'N' ? (char) (ch + 13) : (char) (ch - 13));
			else if (ch >= 'a' && ch <= 'z')
				sb.append(ch < 'n' ? (char) (ch + 13) : (char) (ch - 13));
			else
				sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * Applies the equivalent to the rot13 cipher to a text string. But changes
	 * all characters
	 * 
	 * @param string
	 *            A plaintext or ciphertext string to be ciphered.
	 * @return the rot128'd text
	 */
	public static String rot128(String string) {
		StringBuffer sb = new StringBuffer();
		char ch;
		for (int i = 0; i < string.length(); i++) {
			ch = string.charAt(i);
			sb.append(ch < (char) 128 ? (char) (ch + 128) : (char) (ch - 128));
		}
		return sb.toString();
	}

	/**
	 * Given a key and a string of text, the method enciphers the given text by
	 * repeating the key until its length matches the cipher text length. Then
	 * the list of characters in the text string and the list of characters of
	 * the repeated key string are xored and returned as a list of ints.
	 * 
	 * For example key = key = {107,101,121} repeated key = keykey =
	 * {107,101,121,107,101,121} text = secret = {115,101, 99,114,101,116} rkey
	 * xor text= ????? = { 24, 0, 26, 25, 0, 13}
	 * 
	 * Since xor is symmetric, if this method can be used to decipher text which
	 * has been enciphered with this method if the key is known
	 * 
	 * @param key
	 *            the Cipher key to use
	 * @param text
	 *            the text to encipher / decipher
	 * @return the enciphered/deciphered int array
	 */
	public static int[] xorCrypt(char[] key, String text) {
		int[] out = new int[text.length()];
		for (int i = 0; i < text.length(); i++) {
			out[i] = (text.charAt(i) ^ key[i % key.length]);
		}
		return out;
	}

	public static String xorCryptToString(String key, String text) {
		return intArrayToString(xorCrypt(key.toCharArray(), text));
	}

	public static String xorCryptToString(char[] key, String text) {
		return intArrayToString(xorCrypt(key, text));
	}

	/**
	 * Given a key and a int array, the method deciphers the given int array by
	 * repeating the key until its length matches the cipher array length. Then
	 * the cipher array and the list of int values of the repeated key string
	 * are xored and returned as a String.
	 * 
	 * For example key = key = {107,101,121} repeated key = keykey =
	 * {107,101,121,107,101,121} cipher = ????? = { 24, 0, 26, 25, 0, 13} rkey
	 * xor text= secret = {115,101, 99,114,101,116}
	 * 
	 * This can also be used to encipher an int array with a given key
	 * 
	 * @param key
	 *            the key to use for enciphering or deciphering
	 * @param cipher
	 *            the int array to encipher or decipher
	 * @return the enciphered or deciphered int array as a string
	 */
	public static String xorCryptToString(String key, int[] cipher) {
		return intArrayToString(xorCrypt(key, cipher));
	}

	public static int[] xorCrypt(String key, int[] cipher) {
		return xorCrypt(key.toCharArray(), intArrayToString(cipher));
	}
	
	public static int[] stringArraytoIntArray(String[] a) {
		int[] out = new int[a.length];
		for(int i = 0 ; i < a.length; i++) {
			out[i] = Integer.parseInt(a[i]);
		}
		return out;
	}
	
	public static long[] stringArraytoLongArray(String[] a) {
		long[] out = new long[a.length];
		for(int i = 0 ; i < a.length; i++) {
			out[i] = Long.parseLong(a[i]);
		}
		return out;
	}
	
	public static BigInteger[] stringArraytoBigIntegerArray(String[] a) {
		BigInteger[] out = new BigInteger[a.length];
		for(int i = 0 ; i < a.length; i++) {
			out[i] = new BigInteger(a[i]);
		}
		return out;
	}

	public static int[] charSeqToIntArray(CharSequence seq) {
		int[] out = new int[seq.length()];
		for (int i = 0; i < seq.length(); i++) {
			out[i] = (int) seq.charAt(i);
		}
		return out;
	}

	public static String intArrayToString(int[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append((char) array[i]);
		}
		return sb.toString();
	}
	
	public static String matrixToString(int[][] matrix) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				sb.append(matrix[i][j]);
				sb.append(", ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String collapseWhiteSpace(String in) {
		return in.replaceAll("\\s+", " ");
	}

	public static boolean isPalindrome(String s) {
		for (int i = 0; i <= s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i))
				return false;
		}
		return true;
	}

	public static boolean isAnagram(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		if (c1.length != c2.length)
			return false;
		Arrays.sort(c1);
		Arrays.sort(c2);
		for (int i = 0; i < c1.length; i++)
			if (c1[i] != c2[i])
				return false;
		return true;
	}

	public static String reverse(String s) {
		StringBuffer out = new StringBuffer();
		for (int i = s.length() - 1; i >= 0; i--)
			out.append(s.charAt(i));
		return out.toString();
	}
	
	
	public static long[] reverse(long[] arr) {
		long[] rev = new long[arr.length];
		for(int i =0; i < rev.length; i++) {
			rev[i] = arr[arr.length-1-i];
		}
		return rev;
	}
	
	public static int[] reverse(int[] arr) {
		int[] rev = new int[arr.length];
		for(int i =0; i < rev.length; i++) {
			rev[i] = arr[arr.length-1-i];
		}
		return rev;
	}
	
	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
}
