package org.bitcheck.pow;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public class PowMD5Rev3 {

	public static void main(String[] args) {
		
		System.out.println("PowMD5Rev3");
		
		Random r = new Random();

		byte[] source = md5("Hello World".getBytes());

		byte[] x = source;
		byte[] y = x;
		
		long time0 = System.currentTimeMillis();
		
		int mode = 33;
		
		//int x = 400;//r.nextInt(500);
		//int y = x;
		
		for (long i = 1; i != Long.MAX_VALUE; ++i) {
			
			x = calc(x, mode, source);
			
			y = calc(y, mode, source);
			y = calc(y, mode, source);

			if (Arrays.equals(x, y)) {

				System.out.println("step = " + i + ", mx = " + Arrays.toString(x));
				
				long meetStep = i;
				byte[] meetValue = x;
				
				for (; i != Long.MAX_VALUE; ++i) {

					x = calc(x, mode, source);

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
	
	public static byte[] md5(byte[] source) {
		md.reset();
		byte[] result = md.digest(source);
		return result;
	}
	
	public static byte[] calc(byte[] x, int mode, byte[] source) {
		
		byte[] result = md5(x);
		
		return mode(result, mode, source);
	}
	
	public static byte[] mode(byte[] x, int mode, byte[] source) {
		
		if (mode >= 16*8) {
			throw new IllegalArgumentException("invalid mode " + mode);
		}
		
		byte[] result = Arrays.copyOf(x, x.length);
		
		int bits = mode % 8;
		int fullBytes = mode / 8;
		int lastZeroIndex = 16 - fullBytes;
		if (bits > 0) {
			lastZeroIndex--;
		}
		
		System.arraycopy(source, 0, result, 0, lastZeroIndex);

		if (bits > 0) {
			
			int b = result[lastZeroIndex];
			int s = source[lastZeroIndex];
			
			switch(bits) {
			case 7:
				b &= 0x7F;
				s &= 0x80;
				break;
			case 6:
				b &= 0x3F;
				s &= 0xC0;
				break;
			case 5:
				b &= 0x1F;
				s &= 0xE0;
				break;
			case 4:
				b &= 0x0F;
				s &= 0xF0;
				break;
			case 3:
				b &= 0x07;
				s &= 0xF8;
				break;
			case 2:
				b &= 0x03;
				s &= 0xFC;
				break;
			case 1:
				b &= 0x01;
				s &= 0xFE;
				break;
			}
			
			result[lastZeroIndex] = (byte) (b | s);
			
		}
		
		return result;
		
	}
	
}
