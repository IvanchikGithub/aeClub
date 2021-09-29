package com.aeClub.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aeClub.form.ChangePassForm;
import com.aeClub.util.ValidationMethod;
@Component
public class ChangePassValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ChangePassForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangePassForm changePassForm = (ChangePassForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "NotEmpty.emailPassForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", "NotEmpty.emailPassForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "NotEmpty.emailPassForm.password");
		
		if (!changePassForm.getPassword1().equals(changePassForm.getPassword2())) {
			errors.rejectValue("password1", "Equals.emailPassForm.password");
			errors.rejectValue("password2", "Equals.emailPassForm.password");
		}
		
		if (changePassForm.getPassword1().length()<8) {
			errors.rejectValue("password1", "MinLength.emailPassForm.password");
		}
		
		if (!ValidationMethod.isOnlyEnglishLettersNumbersPunctuationsSpecialSymbols(changePassForm.getPassword1())) {
			errors.rejectValue("password1", "ForbiddenSymbols.emailPassForm.password");
		}
		
		if (!ValidationMethod.atLeastOneDigit(changePassForm.getPassword1())) {
			errors.rejectValue("password1", "AtLeastOneDigit.emailPassForm.password");
		}
		
		if (!ValidationMethod.atLeastOneSmallLetter(changePassForm.getPassword1())) {
			errors.rejectValue("password1", "AtLeastSmallLetter.emailPassForm.password");
		}
		
		if (!ValidationMethod.atLeastOneBigLetter(changePassForm.getPassword1())) {
			errors.rejectValue("password1", "AtLeastOneBigLetter.emailPassForm.password");
		}
	}
}
