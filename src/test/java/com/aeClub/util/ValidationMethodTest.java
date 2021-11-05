package com.aeClub.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidationMethodTest {

	@ParameterizedTest (name="run {index}: value {0} expected {1}")
	@CsvSource({"Fkjklj,true","lkjlkj:S,true","sdfsaf,false",",false","1234652,false","@$##%^$&*),false","ASDF,true","iooiertuDioueti,true", "S,true" })
	void testAtLeastOneBigLetter(String value, boolean expected) {
		boolean result = ValidationMethod.atLeastOneBigLetter(value);
		assertThat(result,is(expected));
	}
	
	@ParameterizedTest (name="run {index}: with {0} expected {1}")
	@CsvSource({"Fkjklj,true","a:LIUJHU,true","sdfsaf,true",",false","1234652,false","@$##%^$&*),false","ASDF,false","SDFGE[p,true", "ASDEdEGG,true", "s,true"})
	void testAtLeastOneSmallLetter(String value, boolean expected) {
		boolean result = ValidationMethod.atLeastOneSmallLetter(value);
		assertThat(result,is(expected));
	}

	@ParameterizedTest (name="run {index}: with {0} expected {1}")
	@CsvSource({"1lkjdfjgSDF,true","asdfkjjfdsa;l3,true","12345786,true",",false","AkdsjfASfdjRT,false","@$##%^$&*),false","3,true","sdkjfADSSF4DSFjk,true"})
	void testAtLeastOneDigit(String value, boolean expected) {
		boolean result = ValidationMethod.atLeastOneDigit(value);
		assertThat(result,is(expected));
	}
	
	
	@ParameterizedTest (name="run {index}: with {0} expected {1}")
	@CsvSource({"1lkjdfjgSDF,true", "sfdfdjkыsfajd,false","Efsji3456029348=!@#$%^^&&*()_+,true","ßrtijfgSD3463, false", "dfjehu56$%#fhhй,false"})
	void testIsOnlyEnglishLettersNumbersPunctuationsSpecialSymbols(String value, boolean expected) {
		boolean result = ValidationMethod.isOnlyEnglishLettersNumbersPunctuationsSpecialSymbols(value);
		assertThat(result,is(expected));
	}
	
}
