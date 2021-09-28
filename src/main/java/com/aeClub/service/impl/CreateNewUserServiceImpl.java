package com.aeClub.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aeClub.entity.Account;
import com.aeClub.entity.AccountExtraInfo;
import com.aeClub.entity.EmailPass;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.entity.Picture;
import com.aeClub.enums.GenderType;
import com.aeClub.enums.PicturesType;
import com.aeClub.form.AccountForm;
import com.aeClub.repository.AccountRepository;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.util.AccountBilder;
import com.aeClub.util.AccountExtraInfoBuilder;
import com.aeClub.util.SecurityUtil;
import com.aeClub.util.ServiceUtil;

@Service
public class CreateNewUserServiceImpl implements CreateNewUserService {

	@Autowired
	private EmailPassRepository emailPassRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PictureService pictureService;

	@Value("${spring.servlet.multipart.max-file-size}")
	long MAXIMUM_FILE_SIZE_ALLOWED;

	@Value("${rootPath}")
	String rootPath;

	public void createNewPairEmailAndPass(String email, String password) {
		EmailPass emailPass = new EmailPass(email, passwordEncoder.encode(password));
		emailPass.setIdUser(genereateIdForNewUser());
		emailPassRepository.save(emailPass);
		SecurityUtil.authentificate(emailPass);
	}

	private int genereateIdForNewUser() {
		Random rand = new Random();
		int idUser;
		do {
			idUser = 1000 + rand.nextInt(99000);
		} while (emailPassRepository.countByIdUser(idUser) != 0);
		return idUser;
	}

	public void createUsersMainInformation(int idUser, AccountForm accountForm,
			MultipartFile fileWithUsersPhoto, MultipartFile[] filesWithUsersExtraPhoto) {

		Account account = createAccountBuilderFromFormsData(idUser, accountForm, fileWithUsersPhoto,
				filesWithUsersExtraPhoto).create();

		if (filesWithUsersExtraPhoto!=null && filesWithUsersExtraPhoto.length != 0) {
			List<Picture> pictures = pictureService.handlingFilesWithUsersExtraPhoto(filesWithUsersExtraPhoto);
			if (pictures.size() > 0) {
				pictures.stream().forEach(picture -> account.addPicture(picture));
			}
		}

		if (accountForm.getHobbies()!=null && accountForm.getHobbies().size() > 0) {
			accountForm.getHobbies().stream().forEach(hobby -> account.addHobby(new Hobby(hobby)));
		}
		
		if (accountForm.getLanguages()!=null && accountForm.getLanguages().size() > 0) {
			accountForm.getLanguages().stream().forEach(language -> account.addLanguage(new Language(language)));
		}

		accountRepository.save(account);
	}

	private AccountBilder createAccountBuilderFromFormsData(int idUser, AccountForm accountForm,
			MultipartFile fileWithUsersPhoto, MultipartFile[] filesWithUsersExtraPhoto) {
		AccountBilder accountBilder = new AccountBilder();
		accountBilder.putIdUser(idUser).putNameForClub(accountForm.getNameForClub())
				.putBirthday(accountForm.getBirthdate()).putCountry(accountForm.getCountry())
				.putCity(accountForm.getCity()).putDenomination(accountForm.getDenomination());
		if (accountForm.getGender().equals(GenderType.MAN.getName())) {
			accountBilder.putManGender();
			accountBilder.putTemplateLinkOnPhotoProfileForMan();
		} else {
			accountBilder.putWomanGender();
			accountBilder.putTemplateLinkOnPhotoProfileForWoman();
		}
		Optional<String> recievedLinkOnProfilesAvatar = pictureService.savePictureInStorage(fileWithUsersPhoto,
				PicturesType.USERS_AVATAR);
		if (!recievedLinkOnProfilesAvatar.isEmpty()) {
			accountBilder.putLinkOnProfilesAvatar(recievedLinkOnProfilesAvatar.get()+".jpg");
		}

		AccountExtraInfo accountExtraInfo = new AccountExtraInfo();
		accountExtraInfo = buildAccountExtraInfoBuilderFromFormsData(accountForm).create();

		accountBilder.putAccountExtraInfo(accountExtraInfo);
		return accountBilder;
	}

	private AccountExtraInfoBuilder buildAccountExtraInfoBuilderFromFormsData(AccountForm accountForm) {
		AccountExtraInfoBuilder accountExtraInfoBuilder = new AccountExtraInfoBuilder();
		String realName=accountForm.getRealName();
		if (!ServiceUtil.emptyOrNull(realName)) {
			accountExtraInfoBuilder.putRealName(realName);
		}
		String realSurname = accountForm.getRealSurname();
		if (!ServiceUtil.emptyOrNull(realSurname)) {
			accountExtraInfoBuilder.putRealSurname(realSurname);
		}
		String nameChurch = accountForm.getNameChurch();
		if (!ServiceUtil.emptyOrNull(nameChurch)) {
			accountExtraInfoBuilder.putNameChurch(nameChurch);
		}
		String education = accountForm.getEducation();
		if (!ServiceUtil.emptyOrNull(education)) {
			accountExtraInfoBuilder.putEducation(education);
		}
		String aboutMe = accountForm.getAboutMe();
		if (!ServiceUtil.emptyOrNull(aboutMe)) {
			accountExtraInfoBuilder.putAboutMe(aboutMe);
		}
		String aboutYou = accountForm.getAboutYou();
		if (!ServiceUtil.emptyOrNull(aboutYou)) {
			accountExtraInfoBuilder.putAboutYou(aboutYou);
		}
		String amountChildren = accountForm.getAmountChildren();
		if (!ServiceUtil.emptyOrNull(amountChildren)) {
			accountExtraInfoBuilder.putAmountChildren(amountChildren);
		}
		return accountExtraInfoBuilder;
	}




}
