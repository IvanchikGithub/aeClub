package com.aeClub.form;

import java.util.List;

import com.aeClub.entity.Account;

/**
 * 
 * Die Klasse AccountForm ist eine Form für die Klasse Account.<br>
 * Die Parameter der Klasse sind:<p>
 * private String <i>nameForClub</i>;<br>
 * private String <i>gender</i>;<br>
 * private String <i>birthdateFromForm</i>;<br>
 * private String <i>country</i>;<br>
 * private String <i>denomination</i>;<br>
 * private String <i>city</i>;<p>
 * private Boolean <i>termsAccepted</i>;<br>
 * private String <i>realName</i>;<br>
 * private String <i>realSurname</i>;<br>
 * private String <i>nameChurch</i>;<br>
 * private String <i>amountChildren</i>;<br>
 * private String <i>education</i>;<br>
 * private String <i>aboutMe</i>;<br>
 * private String <i>aboutYou</i>;<p>
 * private List String <i>hobbiesFromForm</i>;<br>
 * private List String <i>languagesFromForm</i>;<br>
 * 
 * @author ivan romani
 *
 * @see Account
 */
public class AccountForm {

	private String nameForClub;
	//man|woman
	private String gender;
	//format : "yyyy-mm-dd"
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
