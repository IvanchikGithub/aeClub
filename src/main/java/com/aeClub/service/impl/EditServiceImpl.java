package com.aeClub.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.aeClub.Constants;
import com.aeClub.entity.Account;
import com.aeClub.entity.EmailPass;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.entity.Picture;
import com.aeClub.enums.PicturesTypes;
import com.aeClub.enums.WallTypes;
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
	public WallTypes changeActiveWall(String wallType) {
		int newWallType;
		try {
			newWallType = Integer.parseInt(wallType);
			if (newWallType >= 0 && newWallType <= 3) {
				return WallTypes.values()[newWallType];
			}
		} catch (NumberFormatException e) {
			// do nothing
		}
		return WallTypes.EVERYDAY_LIVE_WALL;
	}

	public Model setCheckedInHobbiesAndLanguagesLists(Model model, Account account) {
		@SuppressWarnings(value = { "unchecked" })
		List<Hobby> hobbiesFromCatalog = (List<Hobby>) model.getAttribute("hobbiesFromCatalog");
		List<Hobby> usersHobbies = account.getHobbies();
		if (usersHobbies != null && usersHobbies.size() > 0) {
			hobbiesFromCatalog.stream()
					.forEach(hobby -> checkingInCatalogHobbiesWhichAreUsersHobby(hobby, usersHobbies));
			model.addAttribute("hobbiesFromCatalog", hobbiesFromCatalog);
		}
		@SuppressWarnings(value = { "unchecked" })
		List<Language> languagesFromCatalog = (List<Language>) model
				.getAttribute("languagesFromCatalog");
		List<Language> usersLanguages = account.getLanguages();
		if (usersLanguages != null && usersLanguages.size() > 0) {
			languagesFromCatalog.stream()
					.forEach(language -> checkingInCatalogLanguagesWhichAreUsersLanguages(language,
							usersLanguages));
			model.addAttribute("languagesFromCatalog", languagesFromCatalog);
		}
		return model;
	}

	private void checkingInCatalogHobbiesWhichAreUsersHobby(Hobby hobbyFromCatalog,
			List<Hobby> usersHobbies) {
		for (Hobby theUsersHobby : usersHobbies) {
			if (theUsersHobby.getHobbyType().equals(hobbyFromCatalog.getHobbyType())) {
				hobbyFromCatalog.setChecked(true);
			}
		}
	}

	private void checkingInCatalogLanguagesWhichAreUsersLanguages(Language language,
			List<Language> usersLanguages) {
		for (Language theUsersLanguage : usersLanguages) {
			if (theUsersLanguage.getLanguageType().equals(language.getLanguageType())) {
				language.setChecked(true);
			}
		}
	}

	@Override
	public void editAccount(Account account, AccountForm accountForm) {
		saveFieldsThatHaveBeenChanged(account, accountForm);
	}

	@Override
	public void editAccountsPictures(Account account, MultipartFile fileWithUsersPhoto,
			MultipartFile[] filesWithUsersExtraPhoto) {

		Optional<String> recievedLinkOnProfilesAvatar = pictureService
				.savePictureInStorage(fileWithUsersPhoto, PicturesTypes.USERS_AVATAR);
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

	private void saveFieldsThatHaveBeenChanged(Account account, AccountForm form) {
		changingUsersMainInfo(account, form);
		changingUsersExtraInfo(account, form);
		changingListUsersHobbies(account, form);
		changingListUsersLanguages(account, form);

		accountRepository.save(account);
	}

	private boolean needToRewriteValueInDataBase(String valueInDataBase, String valueInForm) {
		if (valueInForm == null) {
			return false;
		}
		if (ServiceUtil.emptyOrNull(valueInForm) && valueInDataBase.equals(Constants.NOT_INDICATED)) {
			return false;
		}
		if (!ServiceUtil.emptyOrNull(valueInForm) && valueInDataBase.equals(valueInForm)) {
			return false;
		}
		return true;
	}

	private void changingUsersMainInfo(Account account, AccountForm form) {
		String nameForClubInDataBase = account.getNameForClub();
		String nameForClubInForm = form.getNameForClub();
		if (needToRewriteValueInDataBase(nameForClubInDataBase, nameForClubInForm)) {
			account.setNameForClub(nameForClubInForm);
		}
		String countryInDataBase = account.getCountry();
		String countryInForm = form.getCountry();
		if (needToRewriteValueInDataBase(countryInDataBase, countryInForm)) {
			account.setCountry(countryInForm);
		}
		String denominationInDataBase = account.getDenomination();
		String denominationInForm = form.getDenomination();
		if (needToRewriteValueInDataBase(denominationInDataBase, denominationInForm)) {
			account.setDenomination(denominationInForm);
		}
		String cityInDataBase = account.getCity();
		String cityInForm = form.getCity();
		if (needToRewriteValueInDataBase(cityInDataBase, cityInForm)) {
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
		if (needToRewriteValueInDataBase(realNameInDataBase, realNameInForm)) {
			account.getAccountExtraInfo().setRealName(getValueOrNotIndicated(realNameInForm));
		}
		String realSurnameInDataBase = account.getAccountExtraInfo().getRealSurname();
		String realSurnameInForm = form.getRealSurname();
		if (needToRewriteValueInDataBase(realSurnameInDataBase, realSurnameInForm)) {
			account.getAccountExtraInfo().setRealSurname(getValueOrNotIndicated(realSurnameInForm));
		}
		String nameChurchInDataBase = account.getAccountExtraInfo().getNameChurch();
		String nameChurchInForm = form.getNameChurch();
		if (needToRewriteValueInDataBase(nameChurchInDataBase, nameChurchInForm)) {
			account.getAccountExtraInfo().setNameChurch(getValueOrNotIndicated(nameChurchInForm));
		}
		String amountChildrenInDataBase = account.getAccountExtraInfo().getAmountChildren();
		String amountChildrenInForm = form.getAmountChildren();
		if (needToRewriteValueInDataBase(amountChildrenInDataBase, amountChildrenInForm)) {
			account.getAccountExtraInfo().setAmountChildren(amountChildrenInForm);
		}
		String educationInDataBase = account.getAccountExtraInfo().getEducation();
		String educationInForm = form.getEducation();
		if (needToRewriteValueInDataBase(educationInDataBase, educationInForm)) {
			account.getAccountExtraInfo().setEducation(educationInForm);
		}
		String aboutMeInDataBase = account.getAccountExtraInfo().getAboutMe();
		String aboutMeInForm = form.getAboutMe();
		if (needToRewriteValueInDataBase(aboutMeInDataBase, aboutMeInForm)) {
			account.getAccountExtraInfo().setAboutMe(getValueOrNotIndicated(aboutMeInForm));
		}
		String aboutYouInDataBase = account.getAccountExtraInfo().getAboutYou();
		String aboutYouInForm = form.getAboutYou();
		if (needToRewriteValueInDataBase(aboutYouInDataBase, aboutYouInForm)) {
			account.getAccountExtraInfo().setAboutYou(getValueOrNotIndicated(aboutYouInForm));
		}
	}

	private String getValueOrNotIndicated(String value) {
		if (!ServiceUtil.emptyOrNull(value)) {
			return value;
		} else {
			return Constants.NOT_INDICATED;
		}
	}

	private void changingListUsersHobbies(Account account, AccountForm form) {
		// Deleting: Ich lösche alle Hobbys von Nutzer in der Datenbank, die nicht in der Form
		// anwesend sind
		List<Hobby> usersHobbiesInDataBase = account.getHobbies();
		Stream<Hobby> streamHobbyInDataBase = usersHobbiesInDataBase.stream();
		List<String> hobbiesInForm = form.getHobbiesFromForm();

		Consumer<Hobby> preparingHobbyForDeleting = hobby -> hobby.setAccount(null);

		if (hobbiesInForm == null) {
			usersHobbiesInDataBase.removeAll(
					streamHobbyInDataBase.peek(preparingHobbyForDeleting).collect(Collectors.toList()));
			return;
		}

		Predicate<Hobby> isHobbyInForm = hobby -> hobbiesInForm.contains(hobby.getHobbyType());

		List<Hobby> hobbiesForDeleting = streamHobbyInDataBase.filter(isHobbyInForm.negate())
				.peek(preparingHobbyForDeleting).collect(Collectors.toList());
		usersHobbiesInDataBase.removeAll(hobbiesForDeleting);

		// Adding: Ich ergänze alle Hobbys, die in der Form anwesend sind, aber in der Datenbank
		// noch nicht gespeichert
		List<String> usersHobbiesInDataBaseAsString = usersHobbiesInDataBase.stream()
				.map(hobby -> hobby.getHobbyType()).collect(Collectors.toList());
		Stream<String> streamHobbiesInForm = hobbiesInForm.stream();
		Predicate<String> isHobbyFromFormInDatabase = hobby -> usersHobbiesInDataBaseAsString
				.contains(hobby);
		Consumer<String> addingHobbyWhichNotPresentInDatabank = hobby -> account
				.addHobby(new Hobby(hobby));

		streamHobbiesInForm.filter(isHobbyFromFormInDatabase.negate())
				.forEach(addingHobbyWhichNotPresentInDatabank);
	}

	private void changingListUsersLanguages(Account account, AccountForm form) {
		// Deleting
		List<Language> usersLanguagesInDataBase = account.getLanguages();
		Stream<Language> streamLanguagesInDataBase = usersLanguagesInDataBase.stream();
		List<String> languagesInForm = form.getLanguagesFromForm();

		Predicate<Language> isLanguageInForm = language -> languagesInForm
				.contains(language.getLanguageType());
		Consumer<Language> preparingLanguageForDeleting = language -> language.setAccount(null);

		if (languagesInForm == null) {
			usersLanguagesInDataBase.removeAll(streamLanguagesInDataBase
					.peek(preparingLanguageForDeleting).collect(Collectors.toList()));
			return;
		}

		List<Language> languagesForDeleting = streamLanguagesInDataBase.filter(isLanguageInForm.negate())
				.peek(preparingLanguageForDeleting).collect(Collectors.toList());
		usersLanguagesInDataBase.removeAll(languagesForDeleting);

		// Adding

		List<String> usersLanguagesInDataBaseAsString = usersLanguagesInDataBase.stream()
				.map(language -> language.getLanguageType()).collect(Collectors.toList());
		Stream<String> streamLanguagesInForm = languagesInForm.stream();
		Predicate<String> isLanguageFromFormInDatabase = language -> usersLanguagesInDataBaseAsString
				.contains(language);
		Consumer<String> addingLanguagesWhichNotPresentInDatabank = language -> account
				.addLanguage(new Language(language));

		streamLanguagesInForm.filter(isLanguageFromFormInDatabase.negate())
				.forEach(addingLanguagesWhichNotPresentInDatabank);

	}

}