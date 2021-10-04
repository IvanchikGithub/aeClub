package com.aeClub.form;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AccountForm {

	private String nameForClub;
	private String gender;
	private String birthdateFromForm;
	private String country;
	private String denomination;
	private String city;

	private Boolean termsAccepted;
	private String realName;
	private String realSurname;
	private String nameChurch;
	private String amountChildren;
	private String education;
	private String aboutMe;
	private String aboutYou;

	private List<String> hobbiesFromForm;
	private List<String> languagesFromForm;

	public List<String> getLanguagesFromForm() {
		return languagesFromForm;
	}

	public void setLanguagesFromForm(List<String> languagesFromForm) {
		this.languagesFromForm = languagesFromForm;
	}

	public List<String> getHobbiesFromForm() {
		return hobbiesFromForm;
	}

	public void setHobbiesFromForm(List<String> hobbiesFromForm) {
		this.hobbiesFromForm = hobbiesFromForm;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealSurname() {
		return realSurname;
	}

	public void setRealSurname(String realSurname) {
		this.realSurname = realSurname;
	}

	public String getNameChurch() {
		return nameChurch;
	}

	public void setNameChurch(String nameChurch) {
		this.nameChurch = nameChurch;
	}

	public String getAmountChildren() {
		return amountChildren;
	}

	public void setAmountChildren(String amountChildren) {
		this.amountChildren = amountChildren;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAboutYou() {
		return aboutYou;
	}

	public void setAboutYou(String aboutYou) {
		this.aboutYou = aboutYou;
	}

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

	public String getBirthdateFromForm() {
		return birthdateFromForm;
	}

	public void setBirthdateFromForm(String birthdateFromForm) {
		this.birthdateFromForm = birthdateFromForm;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
