package uk.com.tradecompany.utils;

public class StringUtils {
	
	public static boolean isNullOrBlank(String s) {
	  return (s==null || s.trim().equals(""));
	}
	
	public static String uperCase(String s) {
		return isNullOrBlank(s) ? s : s.toUpperCase();
	}
	
	public static String removeBlankSpaces(String s) {
		return isNullOrBlank(s) ? s : s.replaceAll("\\s+","");
	}
	
	public static String upperCaseWithNoBlankSpaces(String s) {
		if(!isNullOrBlank(s)) {
			s = removeBlankSpaces(s);
			s = uperCase(s);
		}
		return s;
	}
}
