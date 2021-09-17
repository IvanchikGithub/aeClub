package com.aeClub.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.aeClub.entity.Hobby;
import com.aeClub.model.AmmountChildrenType;
import com.aeClub.model.CountryList;
import com.aeClub.model.DenominationType;
import com.aeClub.model.EducationLevel;
import com.aeClub.model.GenderType;
import com.aeClub.model.HobbyType;

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
	
}
