package com.aeClub.service;

import org.springframework.web.multipart.MultipartFile;

import com.aeClub.form.AccountForm;

public interface CreateService {
	
	public void createNewPairEmailAndPassForNewUser (String email, String password);
	public void createUsersMainInformation (int idUser, AccountForm accountForm, MultipartFile fileUsersPhoto);
	
}
