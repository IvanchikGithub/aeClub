package com.aeClub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Language {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String languageType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profile", nullable = false)
	private Account account;

	@Transient
	private boolean isChecked;

	public Language() {
	}

	public Language(String languageType) {
		this.languageType = languageType;
		this.isChecked = false;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
