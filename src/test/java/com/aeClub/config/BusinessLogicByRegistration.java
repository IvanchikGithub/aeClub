package com.aeClub.config;

import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import com.aeClub.entity.Account;
import com.aeClub.enums.CountryList;
import com.aeClub.enums.DenominationType;
import com.aeClub.form.AccountForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.FindService;
import com.aeClub.util.AccountEmpty;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@TestMethodOrder (OrderAnnotation.class)
public class BusinessLogicByRegistration {
	@Autowired
	private CreateNewUserService createNewUserService;
	@Autowired
	private FindService findService;

	@BeforeAll
	private static void init(@Autowired CreateNewUserService createNewUserService,
			@Autowired FindService findService) {
		findService.findAndDeleteFromEmailPassTable("qwe7@1.1");
		findService.findAndDeleteFromEmailPassTable("qwe8@1.1");
		findService.findAndDeleteFromEmailPassTable("qwe9@1.1");
		findService.findAndDeleteFromAccountTable("BeautifulSun");
	}
	
		public static Stream<Object> dataForCreatingMainInfoUsers () {
		AccountForm accountForm = new AccountForm();
		accountForm.setNameForClub("BeautifulSun");
		accountForm.setGender("woman");
		accountForm.setBirthdateFromForm("1968-12-25");
		accountForm.setCountry(CountryList.TURKEY.getName());
		accountForm.setCity("Stambul");
		accountForm.setDenomination(DenominationType.CHARISMATIC_CHURCH.getName());
		Arguments[] testData = {Arguments.of(accountForm)};
		return Arrays.asList(testData).stream();
	}
	

	@ParameterizedTest
	@CsvSource (value= {"'qwe7@1.1', 'Bgt56yhN'", "'qwe8@1.1', 'Nhy67ujM'"})
	@Order(1)
	public void savingNewEmailAndPassInDataBase(String email, String pass) {
		Assert.isTrue(!findService.isEmailRegistred(email),"email is not deleted");
		createNewUserService.creatingNewPairEmailAndPass(email, pass);
		Assert.isTrue(findService.isEmailRegistred(email),"email is not created");
	}
	

	
	@ParameterizedTest
	@MethodSource("dataForCreatingMainInfoUsers")
	@Order(2)
	public void usersMainInfoIsSavingInDataBank(AccountForm accountForm) {
		int idUser = findService.giveMeIdUserForEmail("qwe7@1.1");
			if (idUser!=0) {
				Account account = findService.getAccountById(idUser);
				Assert.isInstanceOf(AccountEmpty.class, account, "account for \"qwe7@1.1\" not null");
				createNewUserService.createUsersMainInformation(idUser, accountForm, null, null);
				Assert.notNull(findService.getAccountById(idUser), "account is not saved");
			}
	}
	

}
