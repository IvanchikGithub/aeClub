package com.aeClub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.aeClub.entity.Account;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.SettingsWallType;
import com.aeClub.enums.WallType;
import com.aeClub.service.EditService;

@Service
public class EditServiceImpl implements EditService {

	@Override
	public WallType changeActiveWall(int newWallType) {
		if (newWallType >= 0 && newWallType <= 3) {
			for (WallType wallType : WallType.values()) {
				if (wallType.ordinal() == newWallType) {
					return wallType;
				}
			}
		}
		return WallType.EVERYDAY_LIVE_WALL;
	}

	public Model setCheckedInHobbiesAndLanguagesLists(Model model, Account account) {
		List<Hobby> hobbiesFromCatalog = (List<Hobby>) model.getAttribute("hobbies");
		List<Hobby> usersHobbies = account.getHobbies();
		if (usersHobbies.size() > 0) {
			List<Hobby> checkedHobbies = setCheckForHobbiesWhichAreUsersHobby(hobbiesFromCatalog,
					usersHobbies);
			model.addAttribute("hobbies", checkedHobbies);
		}
		List<Language> languagesFromCatalog = (List<Language>) model.getAttribute("languages");
		List<Language> usersLanguage = account.getLanguages();
		if (usersLanguage.size() > 0) {
			List<Language> checkedLanguages = setCheckForLanguagesWhichAreUsersHobby(
					languagesFromCatalog, usersLanguage);
			model.addAttribute("languages", checkedLanguages);
		}
		return model;
	}

	private List<Hobby> setCheckForHobbiesWhichAreUsersHobby(List<Hobby> hobbiesFromCatalog,
			List<Hobby> usersHobbies) {
		hobbiesFromCatalog.stream().forEach(hobby -> checkingHobbyIsAsUsersHobby(hobby, usersHobbies));
		return hobbiesFromCatalog;
	}

	private Hobby checkingHobbyIsAsUsersHobby(Hobby hobbyFromCatalog, List<Hobby> usersHobbies) {
		for (Hobby theUsersHobby : usersHobbies) {
			if (theUsersHobby.getHobbyType().equals(hobbyFromCatalog.getHobbyType())) {
				hobbyFromCatalog.setChecked(true);
			}
		}
		return hobbyFromCatalog;
	}

	private List<Language> setCheckForLanguagesWhichAreUsersHobby(List<Language> languagesFromCatalog,
			List<Language> usersLanguages) {
		languagesFromCatalog.stream()
				.forEach(language -> checkingHobbyIsAsUsersHobby(language, usersLanguages));
		return languagesFromCatalog;
	}

	private Language checkingHobbyIsAsUsersHobby(Language language, List<Language> usersLanguages) {
		for (Language theUsersLanguage : usersLanguages) {
			if (theUsersLanguage.getLanguageType().equals(language.getLanguageType())) {
				language.setChecked(true);
			}
		}
		return language;
	}
	
	
	

}
