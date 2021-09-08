package com.aeClub.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aeClub.form.EmailPassForm;
@Component
public class EmailPassValidator implements Validator {
	 @Override
	    public boolean supports(Class<?> clazz) {
	        return clazz == EmailPassForm.class;
	    }
	 
	    @Override
	    public void validate(Object target, Errors errors) {
	    	//EmailPassForm emailPassForm = (EmailPassForm)target;
	    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email1", "NotEmpty.emailPassForm.email1");
	    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email2", "NotEmpty.emailPassForm.email2");
	    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", "NotEmpty.emailPassForm.password1");
	    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "NotEmpty.emailPassForm.password2");
	    }
}
