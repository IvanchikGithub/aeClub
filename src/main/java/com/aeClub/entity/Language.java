package com.aeClub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.aeClub.model.LanguageType;

@Entity
public class Language {
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	@Enumerated (value=EnumType.STRING)
	private LanguageType languageType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_profile", nullable=false)
	private Account account;

	public LanguageType getHobbyType() {
		return languageType;
	}

	public void setHobbyType(LanguageType languageType) {
		this.languageType = languageType;
	}



	public LanguageType getLanguageType() {
		return languageType;
	}

	public void setLanguageType(LanguageType languageType) {
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
}
