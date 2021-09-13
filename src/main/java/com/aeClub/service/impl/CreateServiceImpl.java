package com.aeClub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aeClub.entity.EmailPass;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.service.CreateService;
import com.aeClub.util.SecurityUtil;

@Service
public class CreateServiceImpl implements CreateService {

	@Autowired
	private EmailPassRepository emailPassRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void createNewUser(String email, String password) {
		EmailPass emailPass = new EmailPass(email, passwordEncoder.encode(password));
		emailPassRepository.save(emailPass);
		SecurityUtil.authentificate(emailPass);
	}
	
	
	
	
}
