package com.org.data.api.util;

import java.util.Random;
import java.util.UUID;

public class Util {
	
	public static String ipAdderss() {
		Random r = new Random();
		String ipAddress = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
		return ipAddress;
	}
	
	public static long systemTimeInMills() {
		return System.currentTimeMillis();
	}
	
	// Generates a random int with n digits
	public static int randomNum(int n) {
		int m = (int) Math.pow(10, n - 1);
	    return m + new Random().nextInt(9 * m);
	}
	
	public static UUID uuid() {
		return java.util.UUID.randomUUID();
	}
	
	public static String uuidStr() {
		return uuid().toString();
	}	
}
