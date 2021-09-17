package com.aeClub.util;

import com.aeClub.entity.AccountExtraInfo;

public class AccountExtraInfoBuilder {
	
	private String realName="not indicated";
	private String realSurname="not indicated";
	private String nameChurch="not indicated";
	private String amountChildren="not indicated";
	private String education="not indicated";
	private String aboutMe="not indicated";
	private String aboutYou="not indicated";
	
	public AccountExtraInfo create() {
		return new AccountExtraInfo(this);
	}
	
	
	public AccountExtraInfoBuilder putRealName (String realName) {
		this.realName=realName;
		return this;
	}
	
	public AccountExtraInfoBuilder putRealSurname (String realSurname) {
		this.realSurname=realSurname;
		return this;
	}
	
	public AccountExtraInfoBuilder putNameChurch (String nameChurch) {
		this.nameChurch=nameChurch;
		return this;
	}
	
	public AccountExtraInfoBuilder putAmountChildren (String amountChildren) {
		this.amountChildren=amountChildren;
		return this;
	}
	
	public AccountExtraInfoBuilder putEducation (String education) {
		this.education=education;
		return this;
	}
	
	public AccountExtraInfoBuilder putAboutMe (String aboutMe) {
		this.aboutMe=aboutMe;
		return this;
	}
	
	public AccountExtraInfoBuilder putAboutYou (String aboutYou) {
		this.aboutYou=aboutYou;
		return this;
	}
	
	
	public String getRealName() {
		return realName;
	}
	public String getRealSurname() {
		return realSurname;
	}
	public String getNameChurch() {
		return nameChurch;
	}
	public String getAmountChildren() {
		return amountChildren;
	}
	public String getEducation() {
		return education;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public String getAboutYou() {
		return aboutYou;
	}

}
