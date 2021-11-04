package com.aeClub.enums;

public enum EducationLevels {
	ELEMENTARY_SCHOOL("elementary school"),
	HIGH_SCHOOL("high school"),
	BACHELORS_DEGREE("bachelor's degree"),
	MASTERS_DEGREE("master's degree");
	
	private String name;
	
	private EducationLevels (String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
}
	
	
