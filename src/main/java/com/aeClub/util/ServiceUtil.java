package com.aeClub.util;

public class ServiceUtil {

	public static boolean emptyOrNull(String value) {
		return (value.isBlank() || value.isEmpty() || value == null);
	}
}
