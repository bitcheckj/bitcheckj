package org.bitcheck.pow;

import java.util.Random;

public class Pow {

	public static void main(String[] args) {
		
		System.out.println("Pow");
		
		Random r = new Random();
		
		int mode = 500;
		
		int source = 400;
		
		int x = source;//r.nextInt(500);
		int y = x;
		
		for (int i = 0; i != 1000; ++i) {
			
			x = calc(x, mode);
			
			System.out.println(x);

			y = calc(y, mode);
			y = calc(y, mode);

			if (x == y) {

				System.out.println("step = " + (i+1) + ", x = " + x);
				
				int checkPoint = x;
				
				y = source;
				for (int j = 0; j != 1000; ++j) {

					x = calc(x, mode);
					y = calc(y, mode);

					System.out.println(x);
					
					if (x == checkPoint) {
						
						int N = j + 1;
						System.out.println("y = " + y + ", N = " + N + ", checkPoint=" + checkPoint);

						x = y;
						y = source;
						for (int k = 0; k <= 1000; ++k) {
							
							x = calc(x, mode);
							y = calc(y, mode);

							if (y == x) {
								
								System.out.println("match x = " + x + ", y = " + y);
								
								int R = k + 1;
								System.out.println("N = " + N + ", R = " + R + ", i = " + i + ", k = " + k + ", j = " + j);
								
								int prev = -1;
								x = source;
								for (int p = 0; p != R; ++p) {
									prev = x;
									x = calc(x, mode);
								}
								
								System.out.println("enter loop " + x);
								System.out.println("prev " + prev);
								
								for (int p = 0; p != N; ++p) {
									prev = x;
									x = calc(x, mode);
								}

								System.out.println("next loop " + x);
								System.out.println("prev " + prev);

								break;
								
							}
							
						}
						
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
