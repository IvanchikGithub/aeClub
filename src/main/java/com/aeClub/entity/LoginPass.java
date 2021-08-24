package com.aeClub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LoginPass {
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
}
