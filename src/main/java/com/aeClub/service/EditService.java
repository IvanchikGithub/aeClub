package com.aeClub.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.aeClub.entity.Account;
import com.aeClub.enums.WallTypes;
import com.aeClub.form.AccountForm;

/**
 * EditService ist verantwortlich für die Befehle, wenn wir etwas verändern möchten.<br>
 * Es geht um die Veränderungen ein Objekt, einen Bestandteil von einem Objekt wobei die
 * massive Änderungen die Gruppe der Objekte
 * 
 * @author ivan romani
 *
 */
@Service
public interface EditService {
	
	
	/**
	 * Diese Methode besorgt ein richtiges Objekt von dem Typ <i>WallType</i><br>
	 * Dieses Objekt entsprechend die richtige Wand, die dargestellt werden muss. 
	 * 
	 * @param wallType 0, 1, 2, 3 aber als eine Zeichenkette
	 * @return
	 */
	public WallTypes changeActiveWall(String wallType);

	/**
	 * Diese Methode läuft die Auflistungen Hobbys und Sprachen aus den Katalogen durch, falls
	 * ein Hobby(eine Sprache) in der Auflistung den Hobbys(den Sprachen) des Nutzer
	 * angewesend ist, dann wird die Eigenschaft des Hobbys(der Sprache) <i>isChecked</i> in
	 * <b>true</b> gesetzt.<br>
	 * Die Eigenschaft isChecked ist bei der Front genutzt. Die erhaltene Änderungen wird
	 * gerade in das Objekt des Models gespeichert.
	 * 
	 * @param model
	 * @param account
	 * @return
	 */
	public Model setCheckedInHobbiesAndLanguagesLists(Model model, Account account);

	/**
	 * Die Methode <i>editAccount</i> speichert die Änderungen des Accounts in die Datenbank
	 * 
	 * @param account
	 * @param accountForm
	 */
	public void editAccount(Account account, AccountForm accountForm);

	/**
	 * Die Methode <i>editAccountsPictures</i> speichert die neue Builder des Nutzers in die
	 * Datenbank
	 * 
	 * @param account
	 * @param fileWithUsersPhoto
	 * @param filesWithUsersExtraPhoto
	 */
	public void editAccountsPictures(Account account, MultipartFile fileWithUsersPhoto,
			MultipartFile[] filesWithUsersExtraPhoto);

	/**
	 * Die Methode <i>editPass</i> speichert die neues Kennwort des Nutzers in die Datenbank
	 * 
	 * @param password
	 * @param idUser
	 */
	public void editPass(String password, int idUser);
}
