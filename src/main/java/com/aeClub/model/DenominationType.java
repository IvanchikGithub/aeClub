package com.aeClub.model;

/**
 * Das Anzahltyp besteht aus bekannte protestantische Konfessionen 
 * 
 * @author ivasy
 * @see {@link ReligionInfo}
 */

public enum DenominationType {
	PENTECOSTAL_CHURCH("Pentecostal church"),
	BAPTIST_CHURCH ("Baptist church"),
	CHARISMATIC_CHURCH ("Charismatic church"),
	SEVENTH_DAY_ADVENTIST_CHURCH ("Seventh-day Adventist Church"),
	OTHER_PROTESTANT_CHURCH ("Other protestant church"),
	NOT_PROTESTANT_CHURCH ("Not protestant church"),
	NOT_SPECIFIED ("Not specified");
	
	private String name;
	DenominationType(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
