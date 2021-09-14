package com.aeClub.util;

import java.util.Date;

import com.aeClub.entity.Account;
import com.aeClub.model.GenderType;

public class AccountBilder {
	private int idUser;
	private String nameForClub;
	private String gender;
	private Date birthdate;
	private String country;
	private String city;
	private String denomination;

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
	
	public AccountBilder setManGender () {
		this.gender = GenderType.MAN.getName();
		return this;
	}
	
	public AccountBilder setWomanGender () {
		this.gender = GenderType.WOMAN.getName();
		return this;
	}

	public AccountBilder putBirthday(Date birthday) {
		this.birthdate = birthday;
		return this;
	}
	
	public AccountBilder putCountry (String country) {
		this.country = country;
		return this;
	}
	
	public AccountBilder putCity (String city) {
		this.city = city;
		return this;
	}
	
	public AccountBilder putDenomination (String denomination) {
		this.denomination = denomination;
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

	public Date getBirthdate() {
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

}
