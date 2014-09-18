package org.bitcheck.pow;

import java.util.Random;

public class Pow {

	public static void main(String[] args) {
		
		System.out.println("Pow");
		
		Random r = new Random();
		
		int mode = 500;
		
		int x = 400;//r.nextInt(500);
		int y = x;
		
		for (int i = 1; i != 1000; ++i) {
			
			x = calc(x, mode);
			
			System.out.println(x);

			y = calc(y, mode);
			y = calc(y, mode);

			if (x == y) {

				System.out.println("step = " + i + ", x = " + x);
				
				int meetStep = i;
				int meetValue = x;
				
				for (; i != 1000; ++i) {

					x = calc(x, mode);

					System.out.println(x);
					
					if (x == meetValue) {
						int N = i-meetStep;
						int R = i % N;
						
						System.out.println("N = " + N + ", R = " + R + "i = " + i);
						break;
					}
				}
				

			  break;
			}
			
		}
		
	}
	
	private static int a = 345634446;
	private static int b = 124151255;
	
	public static int calc(int x, int mode) {
		return ( a * x + b ) % mode;
	}
	
}
