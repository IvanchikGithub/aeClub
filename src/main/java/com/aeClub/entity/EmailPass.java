package com.aeClub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.aeClub.form.CreateEmailPassForm;

/**
 * Die Klasse enthaltet vier Eigenschaften <b>id</b>, <b>email</b>, <b>password</b>,
 * <b>idUser</b>.
 * <p>
 * Die <b>Id</b> wird automatisch von JPA erzeugt und diese Eigenchaft nutzt nur
 * JPA. Wir nutzen <b>idUser</b>, denn diese Attribute mit id des Nutzers verknupft ist. <b>Email</b>
 * und <b>Password</b> bekommen wir uber den CreateEmailPassForm
 * 
 * @author ivan romani
 * @see CreateEmailPassForm
 */

@Entity
public class EmailPass {
	@Id
	@GeneratedValue
	private long id;

	@Column
	private String email;

	@Column
	private String password;

	@Column(name = "id_user")
	private int idUser;

	public EmailPass() {
	}

	public EmailPass(String email, String password) {
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
