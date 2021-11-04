package com.aeClub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.AmmountChildrenType;
import com.aeClub.enums.Countries;
import com.aeClub.enums.DenominationTypes;
import com.aeClub.enums.EducationLevels;
import com.aeClub.enums.GenderTypes;
import com.aeClub.service.GetService;
import com.aeClub.util.EnumUtil;

@Service
public class GetServiceImpl implements GetService {
	
	public Model getDataFromCatalogues (Model model) {
		List<GenderTypes> genderTypes = EnumUtil.getGenderTypes();
		model.addAttribute("genderTypes", genderTypes);
		List<Countries> countries = EnumUtil.getCountries();
		model.addAttribute("countries", countries);
		List<DenominationTypes> denominations = EnumUtil.getDenominations();
		model.addAttribute("denominations", denominations);
		List<EducationLevels> educationLevels = EnumUtil.getEducationLevels();
		model.addAttribute("educationLevels", educationLevels);
		List<AmmountChildrenType> ammountChildrenTypes = EnumUtil.getAmmountChildrenTypes();
		model.addAttribute("ammountChildrenTypes", ammountChildrenTypes);
		List<Hobby> hobbies = EnumUtil.getHobbies();
		model.addAttribute("hobbiesFromCatalog", hobbies);
		List<Language> languages = EnumUtil.getLanguages();
		model.addAttribute("languagesFromCatalog", languages);
		return model;
	}
	
	

}
