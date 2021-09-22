package com.aeClub.service;

import org.springframework.ui.Model;

import com.aeClub.entity.Account;

public interface GetService {
	Account getAccountById (int id);
	Model getDataFromCatalogues (Model model);

}
