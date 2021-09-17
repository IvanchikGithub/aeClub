package com.aeClub.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aeClub.util.AccountBilder;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "id_user", nullable = false)
	private int idUser;

	@Column(name = "name_for_club", nullable = false, length = 64)
	private String nameForClub;
	@Column(name = "gender", nullable = false, length = 5)
	private String gender;
	@Column(name = "birthdate", nullable = false)
	private Date birthdate;
	@Column(name = "country", nullable = false, length = 64)
	private String country;
	@Column(name = "city", nullable = false, length = 64)
	private String city;
	@Column(name = "denomination", nullable = false, length = 64)
	private String denomination;
	@Column(name = "linkOnPhotoProfile", nullable = false, length = 64)
	private String linkOnPhotoProfile;
	@Embedded
	private AccountExtraInfo accountExtraInfo;

	public Account() {
	}

	public Account(AccountBilder accountBuilder) {
		this.idUser = accountBuilder.getIdUser();
		this.nameForClub = accountBuilder.getNameForClub();
		this.gender = accountBuilder.getGender();
		this.birthdate = accountBuilder.getBirthdate();
		this.country = accountBuilder.getCountry();
		this.city = accountBuilder.getCity();
		this.denomination = accountBuilder.getDenomination();
		this.linkOnPhotoProfile = accountBuilder.getLinkOnPhotoProfile();
		this.accountExtraInfo = accountBuilder.getAccountExtraInfo();
	}
	
	public String getLinkOnPhotoProfile() {
		return linkOnPhotoProfile;
	}

	public void setLinkOnPhotoProfile(String linkOnPhotoProfile) {
		this.linkOnPhotoProfile = linkOnPhotoProfile;
	}

	public AccountExtraInfo getAccountExtraInfo() {
		return accountExtraInfo;
	}

	public void setAccountExtraInfo(AccountExtraInfo accountExtraInfo) {
		this.accountExtraInfo = accountExtraInfo;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

//
//	/**
//	 * 
//	 */
//
//	@Column(name = "photo_link", nullable = false, length = 64)
//	private String photoLink;
//

}
