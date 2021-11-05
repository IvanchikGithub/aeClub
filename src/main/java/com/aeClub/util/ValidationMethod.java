package com.aeClub.util;

public  class ValidationMethod {
	public static boolean atLeastOneDigit(String validatedValue) {
		if (validatedValue == null) {
			return false;
		}
		return validatedValue.matches(".*\\d.*");
	}
	
	public static boolean atLeastOneSmallLetter(String validatedValue) {
		if (validatedValue == null) {
			return false;
		}
		return validatedValue.matches(".*[a-z].*");
	}
	
	public static boolean atLeastOneBigLetter(String validatedValue) {
		if (validatedValue == null) {
			return false;
		}
		return validatedValue.matches(".*[A-Z].*");
	}
	
	
	
	
	public static boolean isOnlyEnglishLettersNumbersPunctuationsSpecialSymbols (String validatedValue) {
		if(validatedValue == null) {
			return false;
		}
		
		return validatedValue.matches("[0-9a-zA-Z~#$%^&*-+=_\\\\|/@`!'\\\";:><,.?{}!()\\[\\]]+");
	}

}
