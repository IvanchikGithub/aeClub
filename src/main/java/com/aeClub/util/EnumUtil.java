package com.aeClub.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.AmmountChildrenType;
import com.aeClub.enums.CountryList;
import com.aeClub.enums.DenominationType;
import com.aeClub.enums.EducationLevel;
import com.aeClub.enums.GenderType;
import com.aeClub.enums.HobbyType;
import com.aeClub.enums.LanguageType;

public final class EnumUtil {
	public static List<GenderType> getGenderTypes() {
		return Arrays.stream(GenderType.values())
				.collect(Collectors.toList());
	}
	
	public static List<CountryList> getCountryList() {
		return Arrays.stream(CountryList.values())
				.collect(Collectors.toList());
	}
	
	public static List<DenominationType> getDenominationList() {
		return Arrays.stream(DenominationType.values())
				.collect(Collectors.toList());
	}
	
	public static List<EducationLevel> getEducationLevelList() {
		return Arrays.stream(EducationLevel.values())
				.collect(Collectors.toList());
	}
	
	public static List<AmmountChildrenType> getAmmountChildrenList() {
		return Arrays.stream(AmmountChildrenType.values())
				.collect(Collectors.toList());
	}
	
	public static List<Hobby> getHobbiesList() {
		List<HobbyType> hobbyTypes = Arrays.stream(HobbyType.values())
				.collect(Collectors.toList());
		return hobbyTypes.stream().map(hobbyTyp->new Hobby(hobbyTyp.name())).collect(Collectors.toList());
	}
	
	public static List<Language> getLanguagesList() {
		List<LanguageType> languagesTypes = Arrays.stream(LanguageType.values())
				.collect(Collectors.toList());
		return languagesTypes.stream().map(languageTyp->new Language(languageTyp.name())).collect(Collectors.toList());
	}
	
	
}
