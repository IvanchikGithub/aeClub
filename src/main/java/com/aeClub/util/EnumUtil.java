package com.aeClub.util;

import java.util.List;
import java.util.stream.Collectors;

import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.AmmountChildrenType;
import com.aeClub.enums.Countries;
import com.aeClub.enums.DenominationTypes;
import com.aeClub.enums.EducationLevels;
import com.aeClub.enums.GenderTypes;
import com.aeClub.enums.HobbyTypes;
import com.aeClub.enums.LanguageTypes;

public final class EnumUtil {
	public static List<GenderTypes> getGenderTypes() {
		return List.of(GenderTypes.values());
		
	}
	
	public static List<Countries> getCountries() {
		return List.of(Countries.values());
	}
	
	public static List<DenominationTypes> getDenominations() {
		return List.of(DenominationTypes.values());
	}
	
	public static List<EducationLevels> getEducationLevels() {
		return List.of(EducationLevels.values());
	}
	
	public static List<AmmountChildrenType> getAmmountChildrenTypes() {
		return List.of(AmmountChildrenType.values());
	}
	
	public static List<Hobby> getHobbies() {
		List<HobbyTypes> hobbyTypes = List.of(HobbyTypes.values());
		return hobbyTypes.stream().map(hobbyTyp->new Hobby(hobbyTyp.name())).collect(Collectors.toList());
	}
	
	public static List<Language> getLanguages() {
		List<LanguageTypes> languagesTypes = List.of(LanguageTypes.values());
		return languagesTypes.stream().map(languageTyp->new Language(languageTyp.name())).collect(Collectors.toList());
	}
	
	
}
