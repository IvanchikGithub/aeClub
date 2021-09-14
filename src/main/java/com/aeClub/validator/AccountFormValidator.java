package com.aeClub.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aeClub.form.AccountForm;
@Component
public class AccountFormValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == AccountForm.class;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
	
	}
}

