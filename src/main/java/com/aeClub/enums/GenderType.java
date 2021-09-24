package com.aeClub.enums;

public enum GenderType {
	MAN("man"), WOMAN("woman");

	private String name;

	GenderType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}