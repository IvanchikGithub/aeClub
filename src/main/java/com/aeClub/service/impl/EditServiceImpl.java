package com.aeClub.service.impl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.aeClub.entity.Account;
import com.aeClub.entity.EmailPass;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.entity.Picture;
import com.aeClub.enums.PicturesType;
import com.aeClub.enums.WallType;
import com.aeClub.form.AccountForm;
import com.aeClub.repository.AccountRepository;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.service.EditService;
import com.aeClub.util.ServiceUtil;

@Service
public class EditServiceImpl implements EditService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EmailPassRepository emailPassRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PictureService pictureService;

	@Override
	public WallType changeActiveWall(String wallType) {
		int newWallType;
		try {
			newWallType = Integer.parseInt(wallType);
			if (newWallType >= 0 && newWallType <= 3) {
				//die Suche einen entsprechenden Wert
				for (WallType value : WallType.values()) {
					if (value.ordinal() == newWallType) {
						return value;
					}
				}
			}
		} catch (NumberFormatException e) {
			//do nothing
		}
		return WallType.EVERYDAY_LIVE_WALL;
	}

	public Model setCheckedInHobbiesAndLanguagesLists(Model model, Account account) {
		@SuppressWarnings(value = { "unchecked" })
		List<Hobby> hobbiesFromCatalog = (List<Hobby>) model.getAttribute("hobbiesFromCatalog");
		List<Hobby> usersHobbies = account.getHobbies();
		if (usersHobbies != null && usersHobbies.size() > 0) {
			List<Hobby> checkedHobbies = setCheckForHobbiesWhichAreUsersHobby(hobbiesFromCatalog,
					usersHobbies);
			model.addAttribute("hobbiesFromCatalog", checkedHobbies);
		}
		@SuppressWarnings(value = { "unchecked" })
		List<Language> languagesFromCatalog = (List<Language>) model
				.getAttribute("languagesFromCatalog");
		List<Language> usersLanguage = account.getLanguages();
		if (usersLanguage != null && usersLanguage.size() > 0) {
			List<Language> checkedLanguages = setCheckForLanguagesWhichAreUsersHobby(
					languagesFromCatalog, usersLanguage);
			model.addAttribute("languagesFromCatalog", checkedLanguages);
		}
		return model;
	}

	@Override
	public void editAccount(Account account, AccountForm accountForm) {
		saveFieldsThatHaveBeenChanged(account, accountForm);
	}

	@Override
	public void editAccountsPictures(Account account, MultipartFile fileWithUsersPhoto,
			MultipartFile[] filesWithUsersExtraPhoto) {

		Optional<String> recievedLinkOnProfilesAvatar = pictureService
				.savePictureInStorage(fileWithUsersPhoto, PicturesType.USERS_AVATAR);
		if (!recievedLinkOnProfilesAvatar.isEmpty()) {
			account.setLinkOnPhotoProfile(recievedLinkOnProfilesAvatar.get() + ".jpg");
		}
		if (filesWithUsersExtraPhoto != null && filesWithUsersExtraPhoto.length != 0) {
			List<Picture> pictures = pictureService
					.handlingFilesWithUsersExtraPhoto(filesWithUsersExtraPhoto);
			if (pictures.size() > 0) {
				pictures.stream().forEach(picture -> account.addPicture(picture));
			}
		}
		accountRepository.save(account);
	}

	@Override
	public void editPass(String password, int idUser) {
		EmailPass emailPass = emailPassRepository.findByIdUser(idUser);
		emailPass.setPassword(passwordEncoder.encode(password));
		emailPassRepository.save(emailPass);
	}

	private List<Hobby> setCheckForHobbiesWhichAreUsersHobby(List<Hobby> hobbiesFromCatalog,
			List<Hobby> usersHobbies) {

		for (Hobby hobby : hobbiesFromCatalog) {
			checkingHobbyIsAsUsersHobby(hobby, usersHobbies);
		}

		// hobbiesFromCatalog.stream().forEach(hobby -> checkingHobbyIsAsUsersHobby(hobby,
		// usersHobbies));
		return hobbiesFromCatalog;
	}

	private void checkingHobbyIsAsUsersHobby(Hobby hobbyFromCatalog, List<Hobby> usersHobbies) {
		for (Hobby theUsersHobby : usersHobbies) {
			if (theUsersHobby.getHobbyType().equals(hobbyFromCatalog.getHobbyType())) {
				hobbyFromCatalog.setChecked(true);
			}
		}
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

	private void saveFieldsThatHaveBeenChanged(Account account, AccountForm form) {
		changingUsersMainInfo(account, form);
		changingUsersExtraInfo(account, form);
		changingListUsersHobbies(account, form);
		changingListUsersLanguages(account, form);

		accountRepository.save(account);
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

	private void changingUsersMainInfo(Account account, AccountForm form) {
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
		if (!ServiceUtil.emptyOrNull(form.getBirthdateFromForm())) {
			String dateInForm = form.getBirthdateFromForm();
			account.setBirthdate(LocalDate.parse(dateInForm));
		}
	}

	private void changingUsersExtraInfo(Account account, AccountForm form) {
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
	}

	private void changingListUsersHobbies(Account account, AccountForm form) {
		// Deleting
		List<Hobby> usersHobbiesInDataBase = account.getHobbies();
		Iterator<Hobby> iterator = usersHobbiesInDataBase.iterator();
		List<String> hobbiesInForm = form.getHobbiesFromForm();

		while (iterator.hasNext()) {
			Hobby hobby = iterator.next();
			boolean hobbyIsPresentInForm = false;
			if (hobbiesInForm != null) {
				for (String hobbyFromForm : hobbiesInForm) {
					if (hobbyFromForm.equals(hobby.getHobbyType())) {
						hobbyIsPresentInForm = true;
					}
				}
				if (!hobbyIsPresentInForm) {
					hobby.setAccount(null);
					iterator.remove();
				}
			} else {
				hobby.setAccount(null);
				iterator.remove();
			}
		}
		// Adding
		if (hobbiesInForm != null) {
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
		}
	}

	private void changingListUsersLanguages(Account account, AccountForm form) {
		// Deleting
		List<Language> usersLanguagesInDataBase = account.getLanguages();
		List<String> languagesInForm = form.getLanguagesFromForm();
		Iterator<Language> iterator2 = usersLanguagesInDataBase.iterator();

		while (iterator2.hasNext()) {
			Language language = iterator2.next();
			boolean languageIsPresentInForm = false;
			if (languagesInForm != null) {
				for (String languageFromForm : languagesInForm) {
					if (languageFromForm.equals(language.getLanguageType())) {
						languageIsPresentInForm = true;
					}
				}

				if (!languageIsPresentInForm) {
					iterator2.remove();
				}
			} else {
				iterator2.remove();
			}
		}
		// Adding
		if (languagesInForm != null) {
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
		}
	}

}