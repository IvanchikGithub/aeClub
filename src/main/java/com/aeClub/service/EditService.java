package com.aeClub.service;

import org.springframework.ui.Model;

import com.aeClub.entity.Account;
import com.aeClub.enums.WallType;
import com.aeClub.form.AccountForm;

public interface EditService {
	public WallType changeActiveWall(int newWallType);
	public Model setCheckedInHobbiesAndLanguagesLists(Model model, Account account);
	public Account editAccount(AccountForm accountForm, int idUser);
}
