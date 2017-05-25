package com.levent.utils;

public class FactorialDemo {

	public static void main(String[] args) {
		for(int i = 0; i <= 100; i++) {
			String result = calculateFactorial(i);
			System.out.printf("%4d!: %s\n", i, result);
		}
	}
	
	private static String calculateFactorial(int N) {
		String factorial = "1";
		
		for(int i = 1; i <= N; i++ ) {
			factorial = BigNumberUtility.mul(factorial, "" + i);
		}
		
		return factorial;
	}
	
}
