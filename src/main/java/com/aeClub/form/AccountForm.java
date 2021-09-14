package com.aeClub.form;

import java.util.Date;

public class AccountForm {

	private String nameForClub;
	private String gender;
	private Date birthdate;
	private String country;
	private String denomination;
	private String city;
	private Boolean termsAccepted;
	

	public Boolean getTermsAccepted() {
		return termsAccepted;
	}

	public void setTermsAccepted(Boolean termsAccepted) {
		this.termsAccepted = termsAccepted;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getNameForClub() {
		return nameForClub;
	}

	public void setNameForClub(String nameForClub) {
		this.nameForClub = nameForClub;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
