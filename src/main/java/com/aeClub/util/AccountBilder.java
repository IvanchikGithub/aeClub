package com.aeClub.util;

import java.time.LocalDate;

import com.aeClub.entity.Account;
import com.aeClub.entity.AccountExtraInfo;
import com.aeClub.enums.GenderType;

/**
 * Die Klasse AccountBilder ist ein Erbauer für die Klasse Account, die laut das
 * Entwurfmuster Erbauer das Objekt der Klasse Account erstellen kann.
 * 
 * @author ivasy
 * @see Account
 */
public class AccountBilder {

	private int idUser;
	private String nameForClub;
	private String gender;
	private LocalDate birthdate;
	private String country;
	private String city;
	private String denomination;
	private String linkOnPhotoProfile;
	private AccountExtraInfo accountExtraInfo;

	public Account create() {
		return new Account(this);
	}

	public AccountBilder putIdUser(int idUser) {
		this.idUser = idUser;
		return this;
	}

	public AccountBilder putNameForClub(String nameForClub) {
		this.nameForClub = nameForClub;
		return this;
	}

	public AccountBilder putManGender() {
		this.gender = GenderType.MAN.getName();
		return this;
	}

	public AccountBilder putWomanGender() {
		this.gender = GenderType.WOMAN.getName();
		return this;
	}

	public AccountBilder putBirthday(LocalDate birthday) {
		this.birthdate = birthday;
		return this;
	}

	public AccountBilder putCountry(String country) {
		this.country = country;
		return this;
	}

	public AccountBilder putCity(String city) {
		this.city = city;
		return this;
	}

	public AccountBilder putDenomination(String denomination) {
		this.denomination = denomination;
		return this;
	}

	public AccountBilder putTemplateLinkOnPhotoProfileForMan() {
		this.linkOnPhotoProfile = "photoman.jpg";
		return this;
	}

	public AccountBilder putTemplateLinkOnPhotoProfileForWoman() {
		this.linkOnPhotoProfile = "photowoman.jpg";
		return this;
	}

	public AccountBilder putLinkOnProfilesAvatar(String linkOnPhotoProfile) {
		this.linkOnPhotoProfile = linkOnPhotoProfile;
		return this;
	}

	public AccountBilder putAccountExtraInfo(AccountExtraInfo accountExtraInfo) {
		this.accountExtraInfo = accountExtraInfo;
		return this;
	}

	public int getIdUser() {
		return idUser;
	}

	public String getNameForClub() {
		return nameForClub;
	}

	public String getGender() {
		return gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getDenomination() {
		return denomination;
	}

	public String getLinkOnPhotoProfile() {
		return linkOnPhotoProfile;
	}

	public AccountExtraInfo getAccountExtraInfo() {
		return accountExtraInfo;
	}

}
