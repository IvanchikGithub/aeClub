package com.aeClub.service;

import java.time.LocalDate;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.aeClub.entity.Account;
import com.aeClub.entity.EmailPass;
import com.aeClub.form.AccountForm;

/**
 * CreateNewUserService beinthaltet die Methode, die mit den Erstellungen <i> die Paar
 * Email und Password </i> und <i> die Hauptinformation des Nutzersprofil </i> verknüpfen
 * sind.
 * 
 * @author ivasy
 *
 */
public interface CreateNewUserService {
	/**
	 * Jeder Nutzer hat sein Paar, das aus Email and Password besteht. Diese Information ist
	 * in der Tabele EmailPass gespeichert.
	 * 
	 * Einem Nutzer in unserer Anwendung gehört:<br>
	 * ein Objekt <i>EmailPass</i><br>
	 * ein Objekt <i>Account</i><br>
	 * 
	 * @param email
	 * @param password
	 * @see EmailPass
	 * @see Account
	 **/
	public void creatingNewPairEmailAndPass(String email, String password);

	/**
	 * Der Nutzer ist über die Klasse Account beschreibt.<br>
	 * Account hat die Daten, die die Hauptinformation nennt: <i>nameForClub</i>,
	 * <i>gender</i>, <i>birthdate</i>, <i>country</i>, <i>city</i>, <i>denomination</i>,
	 * <i>linkOnPhotoProfile</i><br>
	 * Diese Methode versorgt das Schpeichern der Hauptinformation in die Datenbank
	 * 
	 * @param idUser
	 * @param accountForm
	 * @param fileUsersPhoto
	 * @param filesWithUsersExtraPhoto
	 */
	public void createUsersMainInformation(int idUser, AccountForm accountForm,
			MultipartFile fileUsersPhoto, MultipartFile[] filesWithUsersExtraPhoto);

}
