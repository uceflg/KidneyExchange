package edu.cmu.cs.dickerson.kpd.helper;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class IOUtil {

	private IOUtil() {}

	public static final boolean DEBUG = true;
	public static void dPrint(Object o) { 
		if(DEBUG) { System.err.print(o.toString()); }
	}
	public static void dPrintln(Object o) { 
		if(DEBUG) { System.err.println(o.toString()); }
	}
	public static void dPrint(Object o1, Object o2) { 
		dPrint("[" + o1.toString() + "]: " + o2.toString());
	}
	public static void dPrintln(Object o1, Object o2) { 
		dPrintln("[" + o1.toString() + "]: " + o2.toString());
	}

	/**
	 * Closes a data stream, ignoring any IOExceptions thrown
	 * @param c some closeable stream (CSVWriter/Readers in this project)
	 * @return true if everything closed, false if IOException thrown
	 */
	public static boolean closeIgnoreExceptions(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Makes an educated guess at converting a string to a boolean.
	 * @param s String that looks like a Boolean, like "Y" or "fAlse"
	 * @return true if we think s converts to true, false if we think it converts
	 * to false, and IllegalArgumentException if we can't figure it out
	 */
	public static boolean stringToBool(String raw) {
		
		String s = raw.trim().toUpperCase();
		if(s.equals("Y") || s.equals("YES") || s.equals("T") || s.equals("TRUE")) {
			return true;
		} else if(s.equals("N") || s.equals("NO") || s.equals("F") || s.equals("FALSE")) {
			return false;
		} else {
			throw new IllegalArgumentException("Could not convert " + s + " to a boolean value.");
		}
	}
	
	
	/**
	 * Takes a String[] and returns a dictionary of "string" -> integer index in array
	 * @param headers String[]
	 * @return 
	 */
	public static Map<String, Integer> stringArrToHeaders(String[] headers) {
		if(null == headers || headers.length < 1) {
			return new HashMap<String, Integer>();
		}
		
		Map<String, Integer> headerMap = new HashMap<String, Integer>();
		for(int idx=0; idx<headers.length; ++idx) {
			headerMap.put(headers[idx].toUpperCase().trim(), idx);
		}
		return headerMap;
	}
	
	public static Set<String> splitOnWhitespace(String s) {
		Set<String> split = new HashSet<String>();
		split.addAll(Arrays.asList( s.split("\\s+") ));
		return split;
	}
}
