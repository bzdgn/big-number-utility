package com.levent.utils;

public class ThousandFactorial {
	
	private final static int N = 1000;
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		String result = calculateFactorial(N);
		long t2 = System.currentTimeMillis();
		
		long time = t2-t1;
		
		System.out.println("Calculation Time in millis: " + time);
		System.out.printf("%5d!: %s\n", N, result);
	}
	
	private static String calculateFactorial(int N) {
		String factorial = "1";
		
		for(int i = 1; i <= N; i++ ) {
			factorial = BigNumberUtility.mul(factorial, "" + i);
		}
		
		return factorial;
	}

}
