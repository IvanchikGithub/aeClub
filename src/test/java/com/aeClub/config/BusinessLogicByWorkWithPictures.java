package com.aeClub.config;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import com.aeClub.entity.Account;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.Countries;
import com.aeClub.enums.DenominationTypes;
import com.aeClub.form.AccountForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.FindService;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class BusinessLogicByWorkWithPictures {
	
	@Autowired
	private FindService findService;

	private static final String EMAIL_1 = "qwe5@1.1";

	@BeforeAll
	private void init(@Autowired CreateNewUserService createNewUserService,
			@Autowired FindService findService) {
		createNewUserService.creatingNewPairEmailAndPass(EMAIL_1, "Mjy67uk<");
		createNewUserService.createUsersMainInformation(0, null, null, null);
		int idUser = findService.giveMeIdUserForEmail(EMAIL_1);
		if (idUser != 0 && findService.getAccountById(idUser) == null) {
			AccountForm accountForm = new AccountForm();
			accountForm.setNameForClub("UserWithName1");
			accountForm.setGender("man");
			accountForm.setBirthdateFromForm("1978-06-25");
			accountForm.setCountry(Countries.UKRAINE.name());
			accountForm.setDenomination(DenominationTypes.NOT_SPECIFIED.name());
			accountForm.setCity("Kyiv");
			createNewUserService.createUsersMainInformation(idUser, accountForm, null, null);
		} else {
			throw new IllegalArgumentException("New Account for user was not created");
		}
	}
	
	@ParameterizedTest
	@MethodSource("dataForUserWithHobbiesAndLanguagesLists")
	public void savingListsHobbiesAndLanguagesInDataBank(List<Hobby> hobbies, List<Language> languages) {
		int idUser = findService.giveMeIdUserForEmail(EMAIL_1);
		Account account = findService.getAccountById(idUser);
		Assert.isInstanceOf(Account.class, account, "recieved object account is not Account");
		account.setHobbies(hobbies);
		account.setLanguages(languages);
		
	}
	
	
	
	
}
