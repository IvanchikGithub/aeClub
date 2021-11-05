package com.aeClub.enums;

import com.aeClub.Constants;

public enum AmmountChildrenType {
	NOT_INDICATED (Constants.NOT_INDICATED),
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
