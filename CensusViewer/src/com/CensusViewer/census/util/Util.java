package com.CensusViewer.census.util;

public class Util {

	public static String getStringSingleQuoted(String str) {
		return "'" + str + "'";
	}
	
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	
	public static int cielNumber(String s) {
		double convertedNumber = 0;
		int finalString =0;
		if(isNumeric(s)) {
			convertedNumber = Math.ceil(Double.parseDouble(s));
			finalString = (int) Math.floor(convertedNumber);
		}
		
		return finalString;
	    //return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public static void main(String args[]) {
		
		String str="    2.000000000000000         ";
		System.out.println("final =" + cielNumber(str.trim()));
	}
}
