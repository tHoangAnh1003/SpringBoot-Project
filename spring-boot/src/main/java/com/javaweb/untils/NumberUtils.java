package com.javaweb.untils;

public class NumberUtils {
	public static boolean checkNumber(String data) {
		try {
			Long number = Long.parseLong(data);
		} catch (Exception e) { 
			return false;
		}
		return true;
	}
}
