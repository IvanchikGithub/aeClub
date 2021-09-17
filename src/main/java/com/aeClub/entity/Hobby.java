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

import com.aeClub.model.HobbyType;

@Entity
public class Hobby {
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	@Enumerated (value=EnumType.STRING)
	private HobbyType hobbyType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_profile", nullable=false)
	private Account account;

	public HobbyType getHobbyType() {
		return hobbyType;
	}

	public void setHobbyType(HobbyType hobbyType) {
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
	
	

}
