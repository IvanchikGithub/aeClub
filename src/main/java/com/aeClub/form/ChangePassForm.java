package com.aeClub.form;

import com.aeClub.entity.EmailPass;

/**
 * 
 * Die Klasse ChangePassForm ist eine Form für die Änderung das Kennwort des Nutzers<br>
 * Die Parameter der Klasse sind:
 * <p>
 * private String <i>oldPassword</i>;<br>
 * private String <i>password1</i>;<br>
 * private String <i>password2</i>;
 * <p>
 * Wir brauchen zwei Kennworter in der Form, weil es in der Form auf der Seite zwei Felder
 * fur die Kennworter gibt. Die Nutzer muss sein Kennwort zweite Mal eingeben, um sie sich
 * überzeugen, dass das Kennwort richtig(ohne falsche Buchstaben) aufgeschrieben ist.
 * 
 * @author ivan romani
 *
 * @see EmailPass
 */
public class ChangePassForm {
	private String oldPassword;
	private String password1;
	private String password2;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}