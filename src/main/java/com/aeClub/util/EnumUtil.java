package com.aeClub.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.aeClub.model.GenderType;

public final class EnumUtil {
	public static List<GenderType> getGenderTypes() {
		return Arrays.stream(GenderType.values())
				.collect(Collectors.toList());
	}
}
