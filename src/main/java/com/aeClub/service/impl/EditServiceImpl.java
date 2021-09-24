package com.aeClub.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.aeClub.entity.Account;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.WallType;
import com.aeClub.form.AccountForm;
import com.aeClub.repository.AccountRepository;
import com.aeClub.service.EditService;
import com.aeClub.util.ServiceUtil;

@Service
public class EditServiceImpl implements EditService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public WallType changeActiveWall(int newWallType) {
		if (newWallType >= 0 && newWallType <= 3) {
			for (WallType wallType : WallType.values()) {
				if (wallType.ordinal() == newWallType) {
					return wallType;
				}
			}
		}
		return WallType.EVERYDAY_LIVE_WALL;
	}

	public Model setCheckedInHobbiesAndLanguagesLists(Model model, Account account) {
		List<Hobby> hobbiesFromCatalog = (List<Hobby>) model.getAttribute("hobbies");
		List<Hobby> usersHobbies = account.getHobbies();
		if (usersHobbies.size() > 0) {
			List<Hobby> checkedHobbies = setCheckForHobbiesWhichAreUsersHobby(hobbiesFromCatalog,
					usersHobbies);
			model.addAttribute("hobbies", checkedHobbies);
		}
		List<Language> languagesFromCatalog = (List<Language>) model.getAttribute("languages");
		List<Language> usersLanguage = account.getLanguages();
		if (usersLanguage.size() > 0) {
			List<Language> checkedLanguages = setCheckForLanguagesWhichAreUsersHobby(
					languagesFromCatalog, usersLanguage);
			model.addAttribute("languages", checkedLanguages);
		}
		return model;
	}

	private List<Hobby> setCheckForHobbiesWhichAreUsersHobby(List<Hobby> hobbiesFromCatalog,
			List<Hobby> usersHobbies) {
		hobbiesFromCatalog.stream().forEach(hobby -> checkingHobbyIsAsUsersHobby(hobby, usersHobbies));
		return hobbiesFromCatalog;
	}

	private Hobby checkingHobbyIsAsUsersHobby(Hobby hobbyFromCatalog, List<Hobby> usersHobbies) {
		for (Hobby theUsersHobby : usersHobbies) {
			if (theUsersHobby.getHobbyType().equals(hobbyFromCatalog.getHobbyType())) {
				hobbyFromCatalog.setChecked(true);
			}
		}
		return hobbyFromCatalog;
	}

	private List<Language> setCheckForLanguagesWhichAreUsersHobby(List<Language> languagesFromCatalog,
			List<Language> usersLanguages) {
		languagesFromCatalog.stream()
				.forEach(language -> checkingHobbyIsAsUsersHobby(language, usersLanguages));
		return languagesFromCatalog;
	}

	private Language checkingHobbyIsAsUsersHobby(Language language, List<Language> usersLanguages) {
		for (Language theUsersLanguage : usersLanguages) {
			if (theUsersLanguage.getLanguageType().equals(language.getLanguageType())) {
				language.setChecked(true);
			}
		}
		return language;
	}

	@Override
	public Account editAccount(AccountForm accountForm, int idUser) {
		Account account = accountRepository.findById(idUser);
		account = saveFieldsThatHaveBeenChanged(account, accountForm);
		return account;
	}

	private Account saveFieldsThatHaveBeenChanged(Account account, AccountForm form) {
		String nameForClubInDataBase = account.getNameForClub();
		String nameForClubInForm = form.getNameForClub();
		if (!fieldsAreEqualOrChangingNotNecessary(nameForClubInDataBase, nameForClubInForm)) {
			account.setNameForClub(nameForClubInForm);
		}
		String countryInDataBase = account.getCountry();
		String countryInForm = form.getCountry();
		if (!fieldsAreEqualOrChangingNotNecessary(countryInDataBase, countryInForm)) {
			account.setCountry(countryInForm);
		}
		String denominationInDataBase = account.getDenomination();
		String denominationInForm = form.getDenomination();
		if (!fieldsAreEqualOrChangingNotNecessary(denominationInDataBase, denominationInForm)) {
			account.setDenomination(denominationInForm);
		}
		String cityInDataBase = account.getCity();
		String cityInForm = form.getCity();
		if (!fieldsAreEqualOrChangingNotNecessary(cityInDataBase, cityInForm)) {
			account.setCity(cityInForm);
		}
		String realNameInDataBase = account.getAccountExtraInfo().getRealName();
		String realNameInForm = form.getRealName();
		if (!fieldsAreEqualOrChangingNotNecessary(realNameInDataBase, realNameInForm)) {
			if (!ServiceUtil.emptyOrNull(realNameInForm)) {
				account.getAccountExtraInfo().setRealName(realNameInForm);
			} else
				account.getAccountExtraInfo().setRealName("not indicated");

		}
		String realSurnameInDataBase = account.getAccountExtraInfo().getRealSurname();
		String realSurnameInForm = form.getRealSurname();
		if (!fieldsAreEqualOrChangingNotNecessary(realSurnameInDataBase, realSurnameInForm)) {
			if (!ServiceUtil.emptyOrNull(realSurnameInForm)) {
				account.getAccountExtraInfo().setRealSurname(realSurnameInForm);
			} else
				account.getAccountExtraInfo().setRealSurname("not indicated");

		}
		String nameChurchInDataBase = account.getAccountExtraInfo().getNameChurch();
		String nameChurchInForm = form.getNameChurch();
		if (!fieldsAreEqualOrChangingNotNecessary(nameChurchInDataBase, nameChurchInForm)) {
			if (!ServiceUtil.emptyOrNull(nameChurchInForm)) {
				account.getAccountExtraInfo().setNameChurch(nameChurchInForm);
			} else
				account.getAccountExtraInfo().setNameChurch("not indicated");
		}
		String amountChildrenInDataBase = account.getAccountExtraInfo().getAmountChildren();
		String amountChildrenInForm = form.getAmountChildren();
		if (!fieldsAreEqualOrChangingNotNecessary(amountChildrenInDataBase, amountChildrenInForm)) {
			account.getAccountExtraInfo().setAmountChildren(amountChildrenInForm);
		}
		String educationInDataBase = account.getAccountExtraInfo().getEducation();
		String educationInForm = form.getEducation();
		if (!fieldsAreEqualOrChangingNotNecessary(educationInDataBase, educationInForm)) {
			account.getAccountExtraInfo().setEducation(educationInForm);
		}
		String aboutMeInDataBase = account.getAccountExtraInfo().getAboutMe();
		String aboutMeInForm = form.getAboutMe();
		if (fieldsAreEqualOrChangingNotNecessary(aboutMeInDataBase, aboutMeInForm)) {
			if (!ServiceUtil.emptyOrNull(aboutMeInForm)) {
				account.getAccountExtraInfo().setAboutMe(aboutMeInForm);
			} else
				account.getAccountExtraInfo().setAboutMe("not indicated");
		}
		String aboutYouInDataBase = account.getAccountExtraInfo().getAboutYou();
		String aboutYouInForm = form.getAboutYou();
		if (!fieldsAreEqualOrChangingNotNecessary(aboutYouInDataBase, aboutYouInForm)) {
			if (!ServiceUtil.emptyOrNull(aboutYouInForm)) {
				account.getAccountExtraInfo().setAboutYou(aboutYouInForm);
			} else
				account.getAccountExtraInfo().setAboutYou("not indicated");
		}

		account = changingListUsersHobbies(account, form);
		account = changingListUsersLanguages(account, form);

		accountRepository.save(account);
		return account;
	}

	private boolean fieldsAreEqualOrChangingNotNecessary(String valueInDataBase, String valueInForm) {
		if (valueInForm == null) {
			return true;
		}
		if (ServiceUtil.emptyOrNull(valueInForm) && valueInDataBase.equals("not indicated")) {
			return true;
		}
		if (!ServiceUtil.emptyOrNull(valueInForm) && valueInDataBase.equals(valueInForm)) {
			return true;
		}
		return false;
	}

	private Account changingListUsersHobbies(Account account, AccountForm form) {
		// Deleting
		List<Hobby> usersHobbiesInDataBase = account.getHobbies();
		Iterator<Hobby> iterator = usersHobbiesInDataBase.iterator();
		List<String> hobbiesInForm = form.getHobbies();

		while (iterator.hasNext()) {
			Hobby hobby = iterator.next();
			boolean hobbyIsPresentInForm = false;
			for (String hobbyFromForm : hobbiesInForm) {
				if (hobbyFromForm.equals(hobby.getHobbyType())) {
					hobbyIsPresentInForm = true;
				}
			}
			if (!hobbyIsPresentInForm) {
				iterator.remove();
			}
		}
		// Adding
		for (String hobbyFromForm : hobbiesInForm) {
			boolean hobbyIsPresentInUsersHobbiesInDataBank = false;
			for (Hobby hobbyInUsersHobbiesInDataBank : usersHobbiesInDataBase) {
				if (hobbyFromForm.equals(hobbyInUsersHobbiesInDataBank.getHobbyType())) {
					hobbyIsPresentInUsersHobbiesInDataBank = true;
				}
			}
			if (!hobbyIsPresentInUsersHobbiesInDataBank) {
				account.addHobby(new Hobby(hobbyFromForm));
			}
		}
		return account;
	}

	private Account changingListUsersLanguages(Account account, AccountForm form) {
		// Deleting
		List<Language> usersLanguagesInDataBase = account.getLanguages();
		List<String> languagesInForm = form.getLanguages();
		Iterator<Language> iterator2 = usersLanguagesInDataBase.iterator();

		while (iterator2.hasNext()) {
			Language language = iterator2.next();
			boolean languageIsPresentInForm = false;
			for (String languageFromForm : languagesInForm) {
				if (languageFromForm.equals(language.getLanguageType())) {
					languageIsPresentInForm = true;
				}
			}
			if (!languageIsPresentInForm) {
				iterator2.remove();
			}
		}
		// Adding
		for (String languageFromForm : languagesInForm) {
			boolean languageIsPresentInUsersLanguagesInDataBank = false;
			for (Language languageInDataBase : usersLanguagesInDataBase) {
				if (languageFromForm.equals(languageInDataBase.getLanguageType())) {
					languageIsPresentInUsersLanguagesInDataBank = true;
				}
			}
			if (!languageIsPresentInUsersLanguagesInDataBank) {
				account.addLanguage(new Language(languageFromForm));
			}
		}
		return account;
	}

}