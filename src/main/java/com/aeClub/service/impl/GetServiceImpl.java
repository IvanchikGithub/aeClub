package com.aeClub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeClub.entity.Account;
import com.aeClub.model.WallType;
import com.aeClub.repository.AccountRepository;
import com.aeClub.service.GetService;

@Service
public class GetServiceImpl implements GetService {

	@Autowired
	AccountRepository accountRepository;
	@Override
	public Account getAccountById(int idUser) {
		Account account = accountRepository.findById(idUser);
		account.setActiveWall(WallType.EVERYDAY_LIVE_WALL);
		return account;
	}

}
