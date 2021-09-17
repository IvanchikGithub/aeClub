package com.aeClub.model;

public enum LanguageType {
	ALBANIAN("albanian"), ARMENIAN("armenian"), BELARUSIAN("belarusian"), BOSNIAN("bosnian"),
	BULGARIAN("bulgarian"), CATALAN("catalan"), CRIMEAN_TATAR("crimean tatar"), CROATIAN("croatian"),
	CZECH("czech"), DANISH("danish"), DUTCH("dutch"), ENGLISH("english"), ESTONIAN("estonian"),
	FINNISH("finnish"), FRENCH("french"), GEORGIAN("georgian"), GERMAN("german"), GREEK("greek"),
	HEBREW("hebrew"), HUNGARIAN("hungarian"), ITALIAN("italian"), KAZAKH("kazakh"), KYRGYZ("kyrgyz"),
	LATVIAN("latvian"), LITHUANIAN("lithuanian"), MACEDONIAN("macedonian"), MOLDAVIAN("moldavian"),
	NORWEGIAN("norwegian"), POLISH("polish"), PORTUGUESE("portuguese"), ROMANIAN("romanian"),
	RUSSIAN("russian"), SERBIAN("serbian"), SLOVAK("slovak"), SLOVENE("slovene"), SWEDISH("swedish"),
	TAJIK("tajik"), TATAR("tatar"), TURKISH("turkish"), TURKMEN("turkmen"), UKRAINIAN("ukrainian"),
	UZBEK("uzbek"), SPANISH("spanish");

	private String name;

	private LanguageType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
