package com.aeClub.enums;

/**
 * Das Anzahltyp besteht aus bekannte protestantische Konfessionen 
 * 
 * @author ivan romani
 * @see {@link ReligionInfo}
 */

public enum DenominationTypes {
	PENTECOSTAL_CHURCH("Pentecostal church"),
	BAPTIST_CHURCH ("Baptist church"),
	CHARISMATIC_CHURCH ("Charismatic church"),
	SEVENTH_DAY_ADVENTIST_CHURCH ("Seventh-day Adventist Church"),
	OTHER_PROTESTANT_CHURCH ("Other protestant church"),
	NOT_PROTESTANT_CHURCH ("Not protestant church"),
	NOT_SPECIFIED ("Not specified");
	
	private String name;
	DenominationTypes(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
