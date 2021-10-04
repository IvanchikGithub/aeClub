package com.aeClub.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.aeClub.entity.Account;
import com.aeClub.enums.WallType;
import com.aeClub.form.AccountForm;

public interface EditService {
	public WallType changeActiveWall(int newWallType);

	public Model setCheckedInHobbiesAndLanguagesLists(Model model, Account account);

	public void editAccount(Account account, AccountForm accountForm);

	public void editAccountsPictures(Account account, MultipartFile fileWithUsersPhoto,
			MultipartFile[] filesWithUsersExtraPhoto);
	
	public void editPass (String password, int idUser);
}
