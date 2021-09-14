package com.aeClub.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.aeClub.model.CountryList;
import com.aeClub.model.DenominationType;
import com.aeClub.model.GenderType;

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
}
