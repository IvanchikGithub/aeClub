package com.aeClub.form;

import java.util.Date;

public class AccountForm {

	private String nameForClub;
	private String Gender;
	private Date birthdate;

	public String getNameForClub() {
		return nameForClub;
	}

	public void setNameForClub(String nameForClub) {
		this.nameForClub = nameForClub;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
