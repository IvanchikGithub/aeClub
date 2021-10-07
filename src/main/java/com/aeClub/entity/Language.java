package com.aeClub.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Die Klasse Language beschreibt die Sprache von dem Nutzer.
 * Das Objekt der Klasse Language enthalted nächste Eigenschaften:<br>
 * <i>String LanguageType</i> - der Name eines Objekts von der aufzahlungen Klasse
 * LanguageType<br>
 * <i>Account account</i><br>
 * <i>boolean isChecked</i><br> 
 * @author ivasy
 *
 */
@Entity
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String languageType;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
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
