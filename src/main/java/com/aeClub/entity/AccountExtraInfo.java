package com.aeClub.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.aeClub.util.AccountExtraInfoBuilder;

/**
 * Die Klasse AccountExtraInfo beinhaltet die zusätztliche Eigenschaften für die Klasse
 * Account:<br>
 * <i>String realName</i><br>
 * <i>String realSurname</i><br>
 * <i>String nameChurch</i><br>
 * <i>String amountChildren</i><br>
 * <i>String education</i><br>
 * <i>String aboutMe</i><br>
 * <i>String aboutYou</i><br>
 * 
 * @author ivan romani
 *
 */

@Embeddable
public class AccountExtraInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 64)
	private String realName;
	@Column(nullable = false, length = 64)
	private String realSurname;
	@Column(nullable = false)
	private String nameChurch;
	@Column(nullable = false, length = 32)
	private String amountChildren;
	@Column(nullable = false, length = 64)
	private String education;
	@Column(nullable = false)
	private String aboutMe;
	@Column(nullable = false)
	private String aboutYou;

	public AccountExtraInfo() {
	}

	public AccountExtraInfo(AccountExtraInfoBuilder accountExtraInfoBuilder) {
		this.realName = accountExtraInfoBuilder.getRealName();
		this.realSurname = accountExtraInfoBuilder.getRealSurname();
		this.nameChurch = accountExtraInfoBuilder.getNameChurch();
		this.amountChildren = accountExtraInfoBuilder.getAmountChildren();
		this.education = accountExtraInfoBuilder.getEducation();
		this.aboutMe = accountExtraInfoBuilder.getAboutMe();
		this.aboutYou = accountExtraInfoBuilder.getAboutYou();
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

}
