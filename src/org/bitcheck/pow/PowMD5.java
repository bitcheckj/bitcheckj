package org.bitcheck.pow;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class PowMD5 {

	public static void main(String[] args) {
		
		System.out.println("Pow");
		
		Random r = new Random();
		
		byte[] x = new byte[16];
		byte[] y = x;
		
		long time0 = System.currentTimeMillis();
		
		int mode = 500;
		
		//int x = 400;//r.nextInt(500);
		//int y = x;
		
		for (long i = 1; i != Long.MAX_VALUE; ++i) {
			
			x = calc(x);
			

			y = calc(y);
			y = calc(y);

			if (Arrays.equals(x, y)) {

				System.out.println("step = " + i + ", x = " + x);
				
				long meetStep = i;
				byte[] meetValue = x;
				
				for (; i != Long.MAX_VALUE; ++i) {

					x = calc(x);

					
					if (Arrays.equals(x, meetValue)) {
						long N = i-meetStep;
						long R = i % N;
						
						System.out.println("N = " + N + ", R = " + R + ", i = " + i);
						break;
					}
				}
				

			  break;
			}
			
		}
		
		long time1 = System.currentTimeMillis();
		System.out.println("timeMls = " + (time1 - time0));
		
	}
	
	private static int a = 345634446;
	private static int b = 124151255;
	
	private static MessageDigest md;
	
	static {
	 try {
		md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	}
	
	public static byte[] calc(byte[] x) {
		
		md.reset();
		byte[] result = md.digest(x);
		
		Arrays.fill(result, 0, 9, (byte)0);
		
		return result;
	}
	
}
