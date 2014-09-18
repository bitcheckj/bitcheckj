package org.bitcheck.pow;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class PowMD5Rev2 {

	public static void main(String[] args) {
		
		System.out.println("PowMD5Rev2");
		
		Random r = new Random();
		
		byte[] x = md5("Hello World".getBytes());
		byte[] y = x;
		
		long time0 = System.currentTimeMillis();
		
		int mode = 25;
		
		//int x = 400;//r.nextInt(500);
		//int y = x;
		
		for (long i = 1; i != Long.MAX_VALUE; ++i) {
			
			x = md5(x);
			
			y = md5(y);
			y = md5(y);

			byte[] mx = mode(x, mode);
			byte[] my = mode(y, mode);
			
			if (Arrays.equals(mx, my)) {

				System.out.println("step = " + i + ", mx = " + Arrays.toString(mx));
				
				long meetStep = i;
				byte[] meetValue = mx;
				
				for (; i != Long.MAX_VALUE; ++i) {

					x = md5(x);
					
					mx = mode(x, mode);

					if (Arrays.equals(mx, meetValue)) {
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

	
	private static MessageDigest md;
	
	static {
	 try {
		md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	}
	
	public static byte[] md5(byte[] source) {
		md.reset();
		byte[] result = md.digest(source);
		return result;
	}
	
	public static byte[] calc(byte[] x, int mode) {
		
		byte[] result = md5(x);
		
		return mode(result, mode);
	}
	
	public static byte[] mode(byte[] source, int mode) {
		
		if (mode >= 16*8) {
			throw new IllegalArgumentException("invalid mode " + mode);
		}
		
		byte[] result = Arrays.copyOf(source, source.length);
		
		int bits = mode % 8;
		int fullBytes = mode / 8;
		int lastZeroIndex = 16 - fullBytes;
		if (bits > 0) {
			lastZeroIndex--;
		}
		
		Arrays.fill(result, 0, lastZeroIndex, (byte)0);

		if (bits > 0) {
			
			int b = result[lastZeroIndex];
			
			switch(bits) {
			case 7:
				b &= 0x7F;
				break;
			case 6:
				b &= 0x3F;
				break;
			case 5:
				b &= 0x1F;
				break;
			case 4:
				b &= 0x0F;
				break;
			case 3:
				b &= 0x07;
				break;
			case 2:
				b &= 0x03;
				break;
			case 1:
				b &= 0x01;
				break;
			}
			
			result[lastZeroIndex] = (byte) b;
			
		}
		
		return result;
		
	}
	
}
