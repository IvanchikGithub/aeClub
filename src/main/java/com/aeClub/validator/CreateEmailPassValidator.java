package com.aeClub.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;

import com.aeClub.form.CreateEmailPassForm;
import com.aeClub.service.FindService;
import com.aeClub.util.ValidationMethod;

@Component
public class CreateEmailPassValidator implements Validator {

	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Autowired
	private FindService findService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CreateEmailPassForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		CreateEmailPassForm emailPassForm = (CreateEmailPassForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.emailPassForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", "NotEmpty.emailPassForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "NotEmpty.emailPassForm.password");

		if (!emailPassForm.getPassword1().equals(emailPassForm.getPassword2())) {
			errors.rejectValue("password1", "Equals.emailPassForm.password");
			errors.rejectValue("password2", "Equals.emailPassForm.password");
		}

		if (!this.emailValidator.isValid(emailPassForm.getEmail())) {
			// Invalid email.
			errors.rejectValue("email", "Pattern.emailPassForm.email");
		} else {
			// Check the same email in databank
			if (findService.isEmailRegistred(emailPassForm.getEmail())) {
				errors.rejectValue("email", "Duplicate.emailPassForm.email");
			}
		}
		
		if (emailPassForm.getPassword1().length()<8) {
			errors.rejectValue("password1", "MinLength.emailPassForm.password");
		}
		
		if (!ValidationMethod.isOnlyEnglishLettersNumbersPunctuationsSpecialSymbols(emailPassForm.getPassword1())) {
			errors.rejectValue("password1", "ForbiddenSymbols.emailPassForm.password");
		}
		
		if (!ValidationMethod.atLeastOneDigit(emailPassForm.getPassword1())) {
			errors.rejectValue("password1", "AtLeastOneDigit.emailPassForm.password");
		}
		
		if (!ValidationMethod.atLeastOneSmallLetter(emailPassForm.getPassword1())) {
			errors.rejectValue("password1", "AtLeastSmallLetter.emailPassForm.password");
		}
		
		if (!ValidationMethod.atLeastOneBigLetter(emailPassForm.getPassword1())) {
			errors.rejectValue("password1", "AtLeastOneBigLetter.emailPassForm.password");
		}
	}


}