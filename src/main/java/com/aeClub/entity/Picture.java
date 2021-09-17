package com.aeClub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Picture {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String linkPicture;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_profile", nullable=false)
	private Account account;

	public Picture() {
	}
	
	public Picture (String linkPicture) {
		this.linkPicture=linkPicture;
	}
	
	public String getLinkPhoto() {
		return linkPicture;
	}

	public void setLinkPhoto(String linkPicture) {
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
	
	
	
}
