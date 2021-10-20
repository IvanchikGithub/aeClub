package com.aeClub.service;

import org.springframework.stereotype.Service;

import com.aeClub.entity.Account;
import com.aeClub.util.AccountEmpty;

/**
 * FindService bekommt verschidene Information aus dem Datenbank
 * 
 * @author ivasy
 *
 */
@Service
public interface FindService {
	/**
	 * Die Methode versucht ein Account über <i>id</i> aus der Datenbank zu empfangen. Falls die Profil nicht
	 * gefunden ist, die Methode liefert das Objekt der Klasse AccountEmpty.
	 * 
	 * @param id
	 * @return
	 * @see AccountEmpty
	 */
	Account getAccountById(int id);

	public boolean isEmailRegistred(String email);

	public boolean isPasswordCorrect(String password, int idUser);
	
	public void findAndDeleteFromEmailPassTable (String email);
	
	public void findAndDeleteFromAccountTable(String nameForClub);
	
	public int giveMeIdUserForEmail (String email);
	
	
}
