package com.aeClub.util;

public  class ValidationMethod {
	public static boolean atLeastOneDigit(String validatedValue) {
		if (validatedValue == null) {
			return false;
		}
		String validationTemplate = "0123456789";
		for (int i = 0; i < validatedValue.length(); i++) {
			char ch = validatedValue.charAt(i);
			if (validationTemplate.indexOf(ch) != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean atLeastOneSmallLetter(String validatedValue) {
		if (validatedValue == null) {
			return false;
		}
		String validationTemplate = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < validatedValue.length(); i++) {
			char ch = validatedValue.charAt(i);
			if (validationTemplate.indexOf(ch) != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean atLeastOneBigLetter(String validatedValue) {
		if (validatedValue == null) {
			return false;
		}
		String validationTemplate = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < validatedValue.length(); i++) {
			char ch = validatedValue.charAt(i);
			if (validationTemplate.indexOf(ch) != -1) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public static boolean isOnlyEnglishLettersNumbersPunctuationsSpecialSymbols (String validatedValue) {
		final String SPETCH_SYMBOLS = "~#$%^&*-+=_\\|/@`!'\";:><,.?{}";
		final String PUNCTUATIONS = ".,?!-:()'\"[]{}; \t\n";
		final String NUMBERS = "0123456789";
		final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		StringBuilder validationTemplate = new StringBuilder(LETTERS);
		validationTemplate.append(NUMBERS);
		validationTemplate.append(PUNCTUATIONS);
		validationTemplate.append(SPETCH_SYMBOLS);
		
		if(validatedValue == null) {
			return false;
		}
		
		for(int i=0;i<validatedValue.length();i++){
			char ch = validatedValue.charAt(i);
			if(validationTemplate.toString().indexOf(ch) == -1) {
				return false;
			}
		}
		return true;
		
	}

}
