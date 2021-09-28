package com.aeClub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmailPass {
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String email;
	
	@Column
	private String password;

	@Column (name = "id_user")
	private int idUser;
	
	
	public EmailPass() {
	}

	public EmailPass(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
