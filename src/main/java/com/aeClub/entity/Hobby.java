package com.aeClub.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Hobby implements Serializable {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String hobbyType;
	
	@ManyToOne
	@JoinColumn(name="id_user", nullable=false)
	private Account account;
	
	@Transient
	private boolean isChecked;
	
	public Hobby () {
	}
	
	public Hobby (String hobbyType) {
		this.hobbyType=hobbyType;
		this.isChecked=false;
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
	
	

}
