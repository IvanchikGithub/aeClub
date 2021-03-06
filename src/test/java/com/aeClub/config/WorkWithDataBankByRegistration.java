package com.aeClub.config;

import static org.springframework.util.Assert.isInstanceOf;
import static org.springframework.util.Assert.isTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.aeClub.entity.Account;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.AmmountChildrenType;
import com.aeClub.enums.Countries;
import com.aeClub.enums.DenominationTypes;
import com.aeClub.enums.EducationLevels;
import com.aeClub.enums.HobbyTypes;
import com.aeClub.enums.LanguageTypes;
import com.aeClub.form.AccountForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.FindService;
import com.aeClub.util.AccountEmpty;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
public class WorkWithDataBankByRegistration {
	@Autowired
	private CreateNewUserService createNewUserService;
	@Autowired
	private FindService findService;

	private static final String EMAIL_1 = "qwe7@1.1";
	private static final String EMAIL_2 = "qwe8@1.1";
	private static final String NAME_USER_1 = "BeautifulSunTestName";
	private static final String NAME_USER_2 = "HugePowerTestName";

	@AfterAll
	private static void deleting(@Autowired FindService findService) {
		findService.findAndDeleteFromAccountTable(NAME_USER_1);
		findService.findAndDeleteFromAccountTable(NAME_USER_2);
		findService.findAndDeleteFromEmailPassTable(EMAIL_1);
		findService.findAndDeleteFromEmailPassTable(EMAIL_2);
	}

	@ParameterizedTest
	@CsvSource(value = { "'qwe7@1.1', 'Bgt56yhN'", "'qwe8@1.1', 'Nhy67ujM'" })
	@Order(1)
	public void savingNewEmailAndPassInDataBase(String email, String pass) {
		isTrue(!findService.isEmailRegistred(email), "email is not deleted");
		createNewUserService.creatingNewPairEmailAndPass(email, pass);
		isTrue(findService.isEmailRegistred(email), "email is not created");
	}

	@ParameterizedTest
	@MethodSource("dataForCreatingUserWithMainInfoOnly")
	@Order(2)
	public void usersMainInfoIsSavedInDataBank(String email, AccountForm accountForm) {
		int idUser = findService.giveMeIdUserForEmail(email);
		if (idUser != 0) {
			Account account = findService.getAccountById(idUser);
			isInstanceOf(AccountEmpty.class, account, "account for " + email + "must not be null");
			createNewUserService.createUsersMainInformation(idUser, accountForm, null, null);
			account = findService.getAccountById(idUser);// diese Methode liefert Account oder
															// AccountEmpty zurueck
			isInstanceOf(Account.class, account, "account is not saved");
			isTrue(account.getNameForClub().equals(NAME_USER_1), "the name for club is not saved");
			isTrue(account.getGender().equals("woman"), "the gender is not saved");
			isTrue(account.getBirthdate().equals(LocalDate.of(1968, 12, 25)),
					"the birthday is not saved");
			isTrue(account.getCountry().equals(Countries.TURKEY.getName()),
					"the country is not saved");
			isTrue(account.getCity().equals("Stambul"), "the city is not saved");
			isTrue(account.getDenomination().equals(DenominationTypes.CHARISMATIC_CHURCH.getName()),
					"the denomination is not saved");

		} else {
			throw new IllegalArgumentException("idUser must not be 0");
		}
	}

	public static Stream<Object> dataForCreatingUserWithMainInfoOnly() {
		AccountForm accountForm = new AccountForm();
		accountForm.setNameForClub(NAME_USER_1);
		accountForm.setGender("woman");
		accountForm.setBirthdateFromForm("1968-12-25");
		accountForm.setCountry(Countries.TURKEY.getName());
		accountForm.setCity("Stambul");
		accountForm.setDenomination(DenominationTypes.CHARISMATIC_CHURCH.getName());

		Arguments[] testData = { Arguments.of(EMAIL_1, accountForm) };

		return Arrays.asList(testData).stream();
	}

	@ParameterizedTest
	@MethodSource("dataForCreatingUserWithMainInfoAndExtraInfo")
	@Order(3)
	public void usersMainInfoAndExtraInfoIsSavedInDataBank(String email, AccountForm accountForm) {
		int idUser = findService.giveMeIdUserForEmail(email);
		if (idUser != 0) {
			Account account = findService.getAccountById(idUser);
			isInstanceOf(AccountEmpty.class, account, "account for " + email + "must not be null");
			createNewUserService.createUsersMainInformation(idUser, accountForm, null, null);
			account = findService.getAccountById(idUser);
			isInstanceOf(Account.class, account, "account is not saved");
			isTrue(account.getNameForClub().equals(NAME_USER_2), "the name for club is not saved");
			isTrue(account.getGender().equals("man"), "the gender is not saved");
			isTrue(account.getBirthdate().equals(LocalDate.of(1976, 04, 11)),
					"the birthday is not saved");
			isTrue(account.getCountry().equals(Countries.BRAZIL.getName()),
					"the country is not saved");
			isTrue(account.getCity().equals("Rio"), "the city is not saved");
			isTrue(account.getDenomination().equals(DenominationTypes.NOT_PROTESTANT_CHURCH.getName()),
					"the denomination is not saved");
			isTrue(account.getAccountExtraInfo().getRealName().equals("Piter"),
					"the real name is not saved");
			isTrue(account.getAccountExtraInfo().getRealSurname().equals("Schmidt"),
					"the real name is not saved");
			isTrue(account.getAccountExtraInfo().getAmountChildren()
					.equals(AmmountChildrenType.NO_CHILDREN.getName()), "the real name is not saved");
			isTrue(account.getAccountExtraInfo().getEducation()
					.equals(EducationLevels.BACHELORS_DEGREE.getName()), "the real name is not saved");
			isTrue(account.getAccountExtraInfo().getAboutMe().equals("I'm a good person"),
					"the real name is not saved");
			isTrue(account.getAccountExtraInfo().getAboutYou().equals("I want to meet very good woman"),
					"the real name is not saved");
			List<String> hobbies = getListStringFromListHobby(account.getHobbies());
			List<String> hobbisWithTestData = getListStringHobbyWithTestValues();
			isTrue(hobbies.equals(hobbisWithTestData), "list of hobbies is not correct saved");
			List<String> languages = getListStringFromListLanguage(account.getLanguages());
			List<String> languagesWithTestData = getListStringLanguageWithTestValues();
			isTrue(languages.equals(languagesWithTestData), "list of languages is not correct saved");
		} else {
			throw new IllegalArgumentException("idUser must not be 0");
		}
	}

	public static Stream<Object> dataForCreatingUserWithMainInfoAndExtraInfo() {
		AccountForm accountForm = new AccountForm();
		accountForm.setNameForClub(NAME_USER_2);
		accountForm.setGender("man");
		accountForm.setBirthdateFromForm("1976-04-11");
		accountForm.setCountry(Countries.BRAZIL.getName());
		accountForm.setCity("Rio");
		accountForm.setDenomination(DenominationTypes.NOT_PROTESTANT_CHURCH.getName());
		accountForm.setRealName("Piter");
		accountForm.setRealSurname("Schmidt");
		accountForm.setAmountChildren(AmmountChildrenType.NO_CHILDREN.getName());
		accountForm.setEducation(EducationLevels.BACHELORS_DEGREE.getName());
		accountForm.setAboutMe("I'm a good person");
		accountForm.setAboutYou("I want to meet very good woman");
		List<String> hobbies = new ArrayList<String>();
		hobbies.add(HobbyTypes.COOKING.getName());
		hobbies.add(HobbyTypes.HANDMADE.getName());
		accountForm.setHobbiesFromForm(hobbies);
		List<String> languages = new ArrayList<String>();
		languages.add(LanguageTypes.ENGLISH.getName());
		languages.add(LanguageTypes.GERMAN.getName());
		languages.add(LanguageTypes.RUSSIAN.getName());
		accountForm.setLanguagesFromForm(languages);

		Arguments[] testData = { Arguments.of(EMAIL_2, accountForm) };
		return Arrays.asList(testData).stream();
	}

	private List<String> getListStringFromListHobby(List<Hobby> hobbies) {
		List<String> hobbiesAsString = new ArrayList<String>();
		for (Hobby hobby : hobbies) {
			hobbiesAsString.add(hobby.getHobbyType());
		}
		return hobbiesAsString;
	}

	private List<String> getListStringHobbyWithTestValues() {
		List<String> hobbiesWithTestData = new ArrayList<String>();
		hobbiesWithTestData.add(HobbyTypes.COOKING.getName());
		hobbiesWithTestData.add(HobbyTypes.HANDMADE.getName());
		return hobbiesWithTestData;
	}

	private List<String> getListStringFromListLanguage(List<Language> languages) {
		List<String> languagesAsString = new ArrayList<String>();
		for (Language language : languages) {
			languagesAsString.add(language.getLanguageType());
		}
		return languagesAsString;
	}

	private List<String> getListStringLanguageWithTestValues() {
		List<String> languagesWithTestData = new ArrayList<String>();
		languagesWithTestData.add(LanguageTypes.ENGLISH.getName());
		languagesWithTestData.add(LanguageTypes.GERMAN.getName());
		languagesWithTestData.add(LanguageTypes.RUSSIAN.getName());
		return languagesWithTestData;
	}

}
