package com.aeClub.service;

import org.springframework.web.multipart.MultipartFile;

import com.aeClub.form.AccountForm;

public interface CreateNewUserService {
	
	public void createNewPairEmailAndPass (String email, String password);
	public void createUsersMainInformation (int idUser, AccountForm accountForm, MultipartFile fileUsersPhoto, MultipartFile[] filesWithUsersExtraPhoto);
	public String getRootPath();
	
}
