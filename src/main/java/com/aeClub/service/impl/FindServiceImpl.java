package com.aeClub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aeClub.entity.Account;
import com.aeClub.entity.EmailPass;
import com.aeClub.enums.WallType;
import com.aeClub.repository.AccountRepository;
import com.aeClub.repository.EmailPassRepository;
import com.aeClub.service.FindService;
import com.aeClub.util.AccountEmpty;
import com.aeClub.util.CurrentProfile;

@Service
public class FindServiceImpl implements UserDetailsService, FindService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FindServiceImpl.class);
	@Autowired
	private EmailPassRepository emailPassRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	AccountRepository accountRepository;

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

	private EmailPass findEmailPass(String email) {
		return emailPassRepository.findByEmail(email);
	}

	@Override
	public Account getAccountById(int idUser) {
		Account account = accountRepository.findByIdUser(idUser);
		if (account == null) {
			return new AccountEmpty();
		}
		account.setActiveWall(WallType.EVERYDAY_LIVE_WALL);
		return account;
	}

	@Override
	public int giveMeIdUserForEmail(String email) {
		EmailPass emailPass = findEmailPass(email);
		int userId = 0;
		if (emailPass != null) {
			userId = emailPass.getIdUser();
		}
		return userId;
	}

	@Override
	public boolean isEmailRegistred(String email) {
		if (emailPassRepository.countByEmail(email) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isPasswordCorrect(String password, int idUser) {
		EmailPass emailPass = emailPassRepository.findByIdUser(idUser);
		if (emailPass != null && passwordEncoder.matches(password, emailPass.getPassword())) {

			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void findAndDeleteFromEmailPassTable(String email) {
		EmailPass emailPass = findEmailPass(email);
		if (emailPass != null) {
			emailPassRepository.removeByEmail(email);
		}
	}
	
	@Override
	@Transactional
	public void findAndDeleteFromAccountTable(String nameForClub) {
		accountRepository.removeByNameForClub(nameForClub);
	}
	
	

}
