package com.aeClub.enums;

public enum HobbyTypes {
	BAKING("baking"), BLOGGING("blogging"), COLLECTING_THINGS("collecting things"), COOKING("cooking"),
	CREATING_THINGS("creating things"), HANDMADE("handmade"), CYCLING("cycling"), DANCING("dancing"),
	EMBROIDERING("embroidering"), FISHING("fishing"), GARDENING("gardening"), HIKING("hiking"),
	KNITTING("knitting"), LEARNING_NEW_LANGUAGES("learning new languages"),
	LISTENING_TO_MUSIC("listening to music"), PAINTING("painting"), PHOTOGRAPHY("photography"),
	PLAYING_BOARD_GAMES("playing board games"), PLAYING_COMPUTER_GAMES("playing computer games"),
	PLAYING_MUSICAL_INSTRUMENTS("playing musical instruments"), READING_BOOKS("reading books"),
	ROLLER_SKATING("roller-skating"), SHOPPING("shopping"), SINGING("singing"), SKATING("skating"),
	SKIING("skiing"), SKY_JUMPING("sky-jumping"), SPORT("sport"), SURFING("surfing"),
	TRAVELLING("travelling"), WALKING("walking"), WRITING_STORIES("writing stories");

	private String name;

	HobbyTypes(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
