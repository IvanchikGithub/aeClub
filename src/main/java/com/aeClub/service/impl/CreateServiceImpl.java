package com.aeClub.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aeClub.entity.Account;
import com.aeClub.entity.EmailPass;
import com.aeClub.form.AccountForm;
import com.aeClub.model.GenderType;
import com.aeClub.repository.AccountRepository;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.service.CreateService;
import com.aeClub.util.AccountBilder;
import com.aeClub.util.SecurityUtil;

@Service
public class CreateServiceImpl implements CreateService {

	@Autowired
	private EmailPassRepository emailPassRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void createNewPaarEmailPassAndIdUser(String email, String password) {
		EmailPass emailPass = new EmailPass(email, passwordEncoder.encode(password));
		emailPass.setIdUser(getIdForNewUser());
		emailPassRepository.save(emailPass);
		SecurityUtil.authentificate(emailPass);
	}

	private int getIdForNewUser() {
		Random rand = new Random();
		int idUser;
		do {
			idUser = 1000 + rand.nextInt(99000);
		} while (emailPassRepository.countByIdUser(idUser) != 0);
		return idUser;
	}

	public void createUsersMainInformation(int idUser, AccountForm accountForm,
			MultipartFile fileUsersPhoto) {
		AccountBilder accountBilder = new AccountBilder();
		accountBilder.putIdUser(idUser)
		.putNameForClub(accountForm.getNameForClub())
		.putBirthday(accountForm.getBirthdate())
		.putCountry(accountForm.getCountry())
		.putCity(accountForm.getCity())
		.putDenomination(accountForm.getDenomination());
		if (accountForm.getGender().equals(GenderType.MAN.getName())) {
			accountBilder.setManGender();
		} else {
			accountBilder.setWomanGender();
		}
		Account account = accountBilder.create();
		accountRepository.save(account);
	}

}
