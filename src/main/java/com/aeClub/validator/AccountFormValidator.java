package com.aeClub.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
		AccountForm accountForm = (AccountForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameForClub", "NotEmpty.accountForm.requeiredFields");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.accountForm.requeiredFields");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.accountForm.requeiredFields");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denomination", "NotEmpty.accountForm.requeiredFields");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.accountForm.requeiredFields");				
	}
}

