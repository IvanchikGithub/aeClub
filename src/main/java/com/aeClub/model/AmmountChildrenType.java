package com.aeClub.model;

public enum AmmountChildrenType {
	NOT_INDICATED ("not indicated"),
	NO_CHILDREN ("no children"),
	ONE ("one"),
	TWO ("two"),
	THREE_OR_MORE ("three or more");
	
	private String name;
	
	private AmmountChildrenType(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
	
	
}
