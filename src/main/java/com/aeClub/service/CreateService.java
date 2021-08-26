package com.aeClub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeClub.entity.EmailPass;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.util.SecurityUtil;

@Service
public class CreateService {
	
	@Autowired
	private EmailPassRepository emailPassRepository;
	
public void createNewUser (String email, String password) {
	EmailPass emailPass = new EmailPass(email, password);
	SecurityUtil.authentificate(emailPass);//Liegen wir User in den Spring Security Context
	emailPassRepository.save(emailPass);
}
}
