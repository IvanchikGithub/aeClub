package com.aeClub.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Die Klasse Picture beschreibt einen Bilder von dem Nutzer. Es ist nicht unbedingt ein
 * Hauptfoto, es kann irgendien Bilder vom Nutzer. Das Objekt der Klasse Language
 * enthalted nächste Eigenschaften:<br>
 * <i>String linkPicture</i> - der Link, der gibt an, wo der Bilder im Speicherplatz ist.<br>
 * <i>Account account</i><br>
 * 
 * @author ivan romani
 *
 */
@Entity
public class Picture {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String linkPicture;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private Account account;

	public Picture() {
	}

	public Picture(String linkPicture) {
		this.linkPicture = linkPicture;
	}

	public String getLinkPicture() {
		return linkPicture;
	}

	public void setLinkPicture(String linkPicture) {
		this.linkPicture = linkPicture;
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
		Picture other = (Picture) obj;
		return id == other.id;
	}

	
}
