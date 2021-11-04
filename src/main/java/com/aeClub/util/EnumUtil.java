package com.aeClub.util;

import java.util.Arrays;
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
		return Arrays.stream(GenderTypes.values())
				.collect(Collectors.toList());
	}
	
	public static List<Countries> getCountries() {
		return Arrays.stream(Countries.values())
				.collect(Collectors.toList());
	}
	
	public static List<DenominationTypes> getDenominations() {
		return Arrays.stream(DenominationTypes.values())
				.collect(Collectors.toList());
	}
	
	public static List<EducationLevels> getEducationLevels() {
		return Arrays.stream(EducationLevels.values())
				.collect(Collectors.toList());
	}
	
	public static List<AmmountChildrenType> getAmmountChildrenTypes() {
		return Arrays.stream(AmmountChildrenType.values())
				.collect(Collectors.toList());
	}
	
	public static List<Hobby> getHobbies() {
		List<HobbyTypes> hobbyTypes = Arrays.stream(HobbyTypes.values())
				.collect(Collectors.toList());
		return hobbyTypes.stream().map(hobbyTyp->new Hobby(hobbyTyp.name())).collect(Collectors.toList());
	}
	
	public static List<Language> getLanguages() {
		List<LanguageTypes> languagesTypes = Arrays.stream(LanguageTypes.values())
				.collect(Collectors.toList());
		return languagesTypes.stream().map(languageTyp->new Language(languageTyp.name())).collect(Collectors.toList());
	}
	
	
}
