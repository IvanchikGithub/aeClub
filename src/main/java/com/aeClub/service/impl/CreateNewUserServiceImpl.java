package com.aeClub.service.impl;

import java.io.File;
import java.io.IOException;
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
import com.aeClub.form.AccountForm;
import com.aeClub.model.GenderType;
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

		Account account = buildAccountBuilderFromFormsData(idUser, accountForm, fileWithUsersPhoto,
				filesWithUsersExtraPhoto).create();
		accountRepository.save(account);
	}

	private AccountBilder buildAccountBuilderFromFormsData(int idUser, AccountForm accountForm,
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
		Optional<String> recievedLinkOnPhotoProfile = saveUsersPhotoInStorage(fileWithUsersPhoto);
		if (!recievedLinkOnPhotoProfile.isEmpty()) {
			accountBilder.putLinkOnPhotoProfile(recievedLinkOnPhotoProfile.get());
		}
		
		AccountExtraInfo accountExtraInfo= new AccountExtraInfo();
		accountExtraInfo=buildAccountExtraInfoBuilderFromFormsData(accountForm).create(); 
		
		accountBilder.putAccountExtraInfo(accountExtraInfo);
		return accountBilder;
	}

	private AccountExtraInfoBuilder buildAccountExtraInfoBuilderFromFormsData(AccountForm accountForm) {
		AccountExtraInfoBuilder accountExtraInfoBuilder = new AccountExtraInfoBuilder();
		if (!accountForm.getRealName().isBlank()&&accountForm.getRealName()!=null) {
			accountExtraInfoBuilder.putRealName(accountForm.getRealName());
		}
		if (!accountForm.getRealSurname().isBlank()&&accountForm.getRealSurname()!=null) {
			accountExtraInfoBuilder.putRealSurname(accountForm.getRealSurname());
		}
		if (!accountForm.getNameChurch().isBlank()&&accountForm.getNameChurch()!=null) {
			accountExtraInfoBuilder.putNameChurch(accountForm.getNameChurch());
		}
		if (!accountForm.getEducation().isBlank()&&accountForm.getEducation()!=null) {
			accountExtraInfoBuilder.putEducation(accountForm.getEducation());
		}
		if (!accountForm.getAboutMe().isBlank()&&accountForm.getAboutMe()!=null) {
			accountExtraInfoBuilder.putAboutMe(accountForm.getAboutMe());
		}
		if (!accountForm.getAboutYou().isBlank()&&accountForm.getAboutYou()!=null) {
			accountExtraInfoBuilder.putAboutYou(accountForm.getAboutYou());
		}
		if (!accountForm.getAmountChildren().isBlank()&&accountForm.getAmountChildren()!=null) {
			accountExtraInfoBuilder.putAmountChildren(accountForm.getAmountChildren());
		}
		return accountExtraInfoBuilder;
	}
	
	
	private Optional<String> saveUsersPhotoInStorage(MultipartFile fileWithUsersPhoto) {
		if (fileWithUsersPhoto.isEmpty()) {
			return Optional.empty();
		}
		if (!validateExtension(fileWithUsersPhoto)) {
			return Optional.empty();
		}
		String generatedLinkOnPhotoProfile = UUID.randomUUID().toString();
		if (!saveBigPhotoInStorage(fileWithUsersPhoto, generatedLinkOnPhotoProfile)) {
			return Optional.empty();
		}
		if (!saveSmallCopyForPhoto(generatedLinkOnPhotoProfile)) {
			return Optional.empty();
		}
		return Optional.of(generatedLinkOnPhotoProfile);
	}

	private boolean validateExtension(MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		return "png".equals(extension) || "jpeg".equals(extension) || "jpg".equals(extension)
				|| "bmp".equals(extension) || "wbmp".equals(extension);
	}

	private boolean saveBigPhotoInStorage(MultipartFile fileWithUsersPhoto,
			String generatedLinkOnPhotoProfile) {
		try {
			fileWithUsersPhoto.transferTo(
					new File(rootPath + "ProfileMainPhoto\\" + generatedLinkOnPhotoProfile + ".jpg"));
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

	private boolean saveSmallCopyForPhoto(String generatedLinkOnPhotoProfile) {
		try {
			Thumbnails.of(rootPath + "ProfileMainPhoto\\" + generatedLinkOnPhotoProfile + ".jpg")
					.size(110, 110).outputFormat("JPEG").outputQuality(0.90)
					.toFile(rootPath + "ProfileMainPhoto\\" + "small\\" + generatedLinkOnPhotoProfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
