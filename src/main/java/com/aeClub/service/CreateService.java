package com.aeClub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aeClub.entity.EmailPass;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.util.SecurityUtil;

@Service
public class CreateService {
	
	@Autowired
	private EmailPassRepository emailPassRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
public void createNewUser (String email, String password) {
	EmailPass emailPass = new EmailPass(email, passwordEncoder.encode(password));
	emailPassRepository.save(emailPass);
	SecurityUtil.authentificate(emailPass);
}
}
