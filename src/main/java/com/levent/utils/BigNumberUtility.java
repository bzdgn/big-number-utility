package com.levent.utils;

public class BigNumberUtility {
	
	// O(N^2)
	public static String mul(String a, String b) {
		// validation
		validateNumber(a);
		validateNumber(b);
		
		// trim leading zeros
		a = trimLeadingZeros(a);
		b = trimLeadingZeros(b);
		
		char[] longNum;
		char[] shortNum;
		boolean isValidatedAndTrimmed = true;
		
		String result = "";
		
		if(a.length() >= b.length()) {
			longNum = a.toCharArray();
			shortNum = b.toCharArray();
		} else {
			longNum = b.toCharArray();
			shortNum = a.toCharArray();
		}
		
		String[] tempSums = new String[shortNum.length];
		
		// O(N^2)
		for(int i = shortNum.length-1, sumIndex = 0; i >= 0; i--) {
			int carry = 0;
			String tempSum = "";
			for(int j = longNum.length-1; j >= 0; j--) {				
				int tempMul = (shortNum[i] - '0') * (longNum[j] - '0') + carry;
				
				if(tempMul > 9) {
					carry = tempMul / 10;
					tempMul %= 10;
				} else {
					carry = 0;
				}
				
				tempSum += (char) (tempMul + '0');
			}
			
			if(carry != 0) {
				tempSum += (char) (carry + '0');
			}
			
			// Add ending zeros same as much as sumIndex
			for(int k = 0; k < sumIndex; k++) {
				tempSum = "0" + tempSum;
			}
			tempSums[sumIndex++] = tempSum;
		}
		
		for(int i = 0; i < tempSums.length; i++) {
			result = sum(result, reverseString(tempSums[i]), isValidatedAndTrimmed);
		}
		
		return result;
	}
	
	// O(N)
	public static String sum(String a, String b) throws IllegalArgumentException {
		boolean isValidatedAndTrimmed = false;
		return sum(a, b, isValidatedAndTrimmed);
	}
	
	private static String sum(String a, String b, boolean isValidatedAndTrimmed) throws IllegalArgumentException {
		
		if(!isValidatedAndTrimmed) {
			// validation
			validateNumber(a);
			validateNumber(b);
			
			// trim leading zeros
			a = trimLeadingZeros(a);
			b = trimLeadingZeros(b);
		}
		
		char[] longNum;
		char[] shortNum;
		
		String result = "";
		
		if(a.length() >= b.length()) {
			longNum = a.toCharArray();
			shortNum = b.toCharArray();
		} else {
			longNum = b.toCharArray();
			shortNum = a.toCharArray();
		}
		
		int i;	// shortNum
		int j;	// longNum
		int carry = 0;
		
		// O(N)
		for(i = shortNum.length-1, j = longNum.length-1; i >= 0 ; i--, j--) {
			int tempSum = shortNum[i] + longNum[j] - '0' - '0' + carry;
			
			if(tempSum > 9) {
				carry = tempSum / 10;
				tempSum %= 10;
			} else {
				carry = 0;
			}
			
			result += (char) (tempSum + '0');
		}
		
		// O(N)
		for(; j >= 0; j--) {
			int tempSum = longNum[j] - '0' + carry;
			
			if(tempSum > 9) {
				carry = tempSum / 10;
				tempSum %= 10;
			} else {
				carry = 0;
			}
			
			result += (char) (tempSum + '0');
		}
		
		if(carry != 0) {
			result += (char) (carry + '0');
		}
		
		return reverseString(result);
	}
	
	private static String reverseString(String input) {
		char[] inputArray = input.toCharArray();
		int len = inputArray.length;
		
		for(int i = 0; 2*i < len; i++) {
			char temp = inputArray[i];
			inputArray[i] = inputArray[len-i-1];
			inputArray[len-i-1] = temp;
		}
		
		return new String(inputArray);
	}
	
	private static String trimLeadingZeros(String number) throws IllegalArgumentException {
		if(number == null || number.length() == 0) {
			return number;
		}
		
		char[] numberArray = number.toCharArray();
		
		int i;
		for(i = 0; numberArray[i] == '0' && i < numberArray.length; i++) {
			;
		}
		
		return number.substring(i);
	}
	
	private static void validateNumber(String number) {
		char[] numberArray = number.toCharArray();
		int len = numberArray.length;
		
		for(int i = 0; i < len; i++) {
			if( (numberArray[i] < '0') || (numberArray[i] > '9') ) {
				throw new IllegalArgumentException("Invalid Character");
			}
		}
	}
	
}