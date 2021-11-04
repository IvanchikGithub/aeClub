package com.aeClub.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Die Klasse Hobby beschreibt die Hobbys von dem Nutzer.
 * Das Objekt der Klasse Hobby enthalted nächste Eigenschaften:<br>
 * <i>String hobbyType</i> - der Name eines Objekts von der aufzahlungen Klasse HobbyType<br>
 * <i>Account account</i><br>
 * <i>boolean isChecked</i><br> 
 * @author ivan romani
 *
 */
@Entity
public class Hobby implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String hobbyType;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private Account account;

	@Transient
	private boolean isChecked;

	public Hobby() {
	}

	public Hobby(String hobbyType) {
		this.hobbyType = hobbyType;
		this.isChecked = false;
	}

	public String getHobbyType() {
		return hobbyType;
	}

	public void setHobbyType(String hobbyType) {
		this.hobbyType = hobbyType;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hobby other = (Hobby) obj;
		return id == other.id;
	}

}
