package com.aeClub.enums;

public enum GenderTypes {
	MAN("man"), WOMAN("woman");

	private String name;

	GenderTypes(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
