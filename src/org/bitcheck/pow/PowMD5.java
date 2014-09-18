package org.bitcheck.pow;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class PowMD5 {

	public static void main(String[] args) {
		
		System.out.println("PowMD5");
		
		Random r = new Random();
		
		byte[] x = new byte[16];
		byte[] y = x;
		
		long time0 = System.currentTimeMillis();
		
		int mode = 35;
		
		//int x = 400;//r.nextInt(500);
		//int y = x;
		
		for (long i = 1; i != Long.MAX_VALUE; ++i) {
			
			x = calc(x, mode);
			

			y = calc(y, mode);
			y = calc(y, mode);

			if (Arrays.equals(x, y)) {

				System.out.println("step = " + i + ", x = " + Arrays.toString(x));
				
				long meetStep = i;
				byte[] meetValue = x;
				
				for (; i != Long.MAX_VALUE; ++i) {

					x = calc(x, mode);

					
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

	
	private static MessageDigest md;
	
	static {
	 try {
		md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	}
	
	public static byte[] calc(byte[] x, int mode) {
		
		if (mode >= 16*8) {
			throw new IllegalArgumentException("invalid mode " + mode);
		}
		
		md.reset();
		byte[] result = md.digest(x);
		
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
