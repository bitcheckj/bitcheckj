package org.bitcheck.pow;

import java.util.Random;

public class Pow {

	public static void main(String[] args) {
		
		System.out.println("Pow");
		
		Random r = new Random();
		
		int mode = 500;
		
		int x = 400;//r.nextInt(500);
		
		for (int i = 0; i != 1000; ++i) {
			
			x = calc(x, mode);
			
			System.out.println(x);
			
		}
		
	}
	
	private static int a = 345634446;
	private static int b = 124151255;
	
	public static int calc(int x, int mode) {
		return ( a * x + b ) % mode;
	}
	
}
