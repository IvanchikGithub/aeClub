package com.aeClub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.aeClub.entity.Account;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.AmmountChildrenType;
import com.aeClub.enums.CountryList;
import com.aeClub.enums.DenominationType;
import com.aeClub.enums.EducationLevel;
import com.aeClub.enums.GenderType;
import com.aeClub.enums.SettingsWallType;
import com.aeClub.enums.WallType;
import com.aeClub.repository.AccountRepository;
import com.aeClub.service.GetService;
import com.aeClub.util.AccountEmpty;
import com.aeClub.util.EnumUtil;

@Service
public class GetServiceImpl implements GetService {

	@Autowired
	AccountRepository accountRepository;
	@Override
	public Account getAccountById(int idUser) {
		Account account = accountRepository.findById(idUser);
		if (account == null) {
			return new AccountEmpty();
		}
		account.setActiveWall(WallType.EVERYDAY_LIVE_WALL);
		account.setActiveSettingsWall(SettingsWallType.MAIN_INFO);
		return account;
	}
	
	public Model getDataFromCatalogues (Model model) {
		List<GenderType> listGender = EnumUtil.getGenderTypes();
		model.addAttribute("listGender", listGender);
		List<CountryList> listCountry = EnumUtil.getCountryList();
		model.addAttribute("listCountry", listCountry);
		List<DenominationType> listDenomination = EnumUtil.getDenominationList();
		model.addAttribute("listDenomination", listDenomination);
		List<EducationLevel> educationLevelList = EnumUtil.getEducationLevelList();
		model.addAttribute("educationLevelList", educationLevelList);
		List<AmmountChildrenType> ammountChildrenTypeList = EnumUtil.getAmmountChildrenList();
		model.addAttribute("ammountChildrenTypeList", ammountChildrenTypeList);
		List<Hobby> hobbies = EnumUtil.getHobbiesList();
		model.addAttribute("hobbies", hobbies);
		List<Language> languages = EnumUtil.getLanguagesList();
		model.addAttribute("languages", languages);
		return model;
	}
	
	

}
