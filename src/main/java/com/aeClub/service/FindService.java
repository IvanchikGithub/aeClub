package com.aeClub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aeClub.entity.EmailPass;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.util.CurrentProfile;


@Service
public class FindService implements UserDetailsService{
	private static final Logger LOGGER = LoggerFactory.getLogger(FindService.class);
	@Autowired
	private EmailPassRepository emailPassRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		EmailPass emailPass = findEmailPass(email);
		if (emailPass != null) {
			return new CurrentProfile(emailPass);
		} else {
			LOGGER.error("Profile not found by " + emailPass);
			throw new UsernameNotFoundException("Profile not found by " + emailPass);
		}
	}
	
	private EmailPass findEmailPass (String email) {
		return emailPassRepository.findByEmail(email);
	}
	
	
	
	
	
//	public long giveMeIdForEmail (String email) {
//		Long userId = findEmailPass(email).getId();
//		 if (userId!=null) {
//			return userId;
//		}
//	}
	
	public boolean isEmailRegistred(String email) {
		if (emailPassRepository.countByEmail(email)>0) {
			return true;
		} else {
			return false;
		}
	}
}
