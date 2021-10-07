package com.aeClub.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aeClub.form.AccountForm;

/**
 * Die Klasse AccountFormValidator ist geerbte Klasse von Validator. Bei Validation
 * uberprufen wir ob die Parameter, die der Hauptinformation gehört: <i> nameForClub</i>, <i> gender</i>,<i> country</i>, <i> denomination</i>,<i> city</i>
 * gultig sind.
 * <p>
 * Das Folgendes lost das Fehler aus: <br>
 * ein Parametr ist leer <br>
 * 
 * @author ivasy
 *
 * @see Validator
 */
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

