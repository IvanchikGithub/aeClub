package com.aeClub.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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
import com.aeClub.form.AccountForm;
import com.aeClub.model.GenderType;
import com.aeClub.model.PicturesType;
import com.aeClub.repository.AccountRepository;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.util.AccountBilder;
import com.aeClub.util.AccountExtraInfoBuilder;
import com.aeClub.util.SecurityUtil;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class CreateNewUserServiceImpl implements CreateNewUserService {

	@Autowired
	private EmailPassRepository emailPassRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

		if (filesWithUsersExtraPhoto.length != 0) {
			List<Picture> pictures = handlingFilesWithUsersExtraPhoto(filesWithUsersExtraPhoto);
			if (pictures.size() > 0) {
				pictures.stream().forEach(picture -> account.addPicture(picture));
			}
		}

		if (accountForm.getHobbies().size() > 0) {
			accountForm.getHobbies().stream().forEach(hobby -> account.addHobby(new Hobby(hobby)));
		}
		
		if (accountForm.getLanguages().size() > 0) {
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
		Optional<String> recievedLinkOnProfilesAvatar = savePictureInStorage(fileWithUsersPhoto,
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
		if (notEmptyAndNotNull(realName)) {
			accountExtraInfoBuilder.putRealName(realName);
		}
		String realSurname = accountForm.getRealSurname();
		if (notEmptyAndNotNull(realSurname)) {
			accountExtraInfoBuilder.putRealSurname(realSurname);
		}
		String nameChurch = accountForm.getNameChurch();
		if (notEmptyAndNotNull(nameChurch)) {
			accountExtraInfoBuilder.putNameChurch(nameChurch);
		}
		String education = accountForm.getEducation();
		if (notEmptyAndNotNull(education)) {
			accountExtraInfoBuilder.putEducation(education);
		}
		String aboutMe = accountForm.getAboutMe();
		if (notEmptyAndNotNull(aboutMe)) {
			accountExtraInfoBuilder.putAboutMe(aboutMe);
		}
		String aboutYou = accountForm.getAboutYou();
		if (notEmptyAndNotNull(aboutYou)) {
			accountExtraInfoBuilder.putAboutYou(aboutYou);
		}
		String amountChildren = accountForm.getAmountChildren();
		if (notEmptyAndNotNull(amountChildren)) {
			accountExtraInfoBuilder.putAmountChildren(amountChildren);
		}
		return accountExtraInfoBuilder;
	}

	private boolean notEmptyAndNotNull(String value) {
		return !(value.isBlank() || value.isEmpty() || value == null);
	}

	private List<Picture> handlingFilesWithUsersExtraPhoto(MultipartFile[] filesWithUsersExtraPhoto) {
		List<Picture> pictures = new ArrayList<Picture>();
		for (int i = 0; i < filesWithUsersExtraPhoto.length; i++) {
			Optional<String> recievedLinkOnPictureInAlbum = savePictureInStorage(
					filesWithUsersExtraPhoto[i], PicturesType.PHOTO_IN_ALBUM);
			if (!recievedLinkOnPictureInAlbum.isEmpty()) {
				Picture picture = new Picture(recievedLinkOnPictureInAlbum.get()+".jpg");
				pictures.add(picture);
			}
		}
		return pictures;
	}

	private Optional<String> savePictureInStorage(MultipartFile fileWithUsersPhoto,
			PicturesType pictureType) {
		if (fileWithUsersPhoto.isEmpty()) {
			return Optional.empty();
		}
		if (!validateExtension(fileWithUsersPhoto)) {
			return Optional.empty();
		}
		String generatedLinkOnPhotoProfile = UUID.randomUUID().toString();
		if (!savePictureFullSizeInStorage(fileWithUsersPhoto, generatedLinkOnPhotoProfile,
				pictureType)) {
			return Optional.empty();
		}
		if (!savePictureSmallSizeInStorage(generatedLinkOnPhotoProfile, pictureType)) {
			return Optional.empty();
		}
		return Optional.of(generatedLinkOnPhotoProfile);
	}

	private boolean validateExtension(MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		return "png".equals(extension) || "jpeg".equals(extension) || "jpg".equals(extension)
				|| "bmp".equals(extension) || "wbmp".equals(extension);
	}

	private boolean savePictureFullSizeInStorage(MultipartFile fileWithUsersPhoto,
			String generatedLinkOnPhotoProfile, PicturesType picturesType) {
		try {
			fileWithUsersPhoto.transferTo(new File(
					rootPath + picturesType.getDirectory() + generatedLinkOnPhotoProfile + ".jpg"));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean savePictureSmallSizeInStorage(String generatedLinkOnPhotoProfile,
			PicturesType picturesType) {
		try {
			Thumbnails.of(rootPath + picturesType.getDirectory() + generatedLinkOnPhotoProfile + ".jpg")
					.size(110, 110).outputFormat("jpg").outputQuality(0.90).toFile(rootPath
							+ picturesType.getDirectory() + "small\\" + generatedLinkOnPhotoProfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getRootPath() {
		return rootPath;
	}

}
