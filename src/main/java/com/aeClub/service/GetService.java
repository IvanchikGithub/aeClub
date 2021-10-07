package com.aeClub.service;

import org.springframework.ui.Model;
/**
 * GetService versorgt die Information, die mit den Anwendungsdatene verknüpfet sind<br>
 * Zum Beispiel, die Auflistungen der aufzalenden Typen (Katalogen)
 * @author ivasy
 *
 */
public interface GetService {
	
	
	/**
	 * Die Methode versorgt die Auflistungen der aufzalenden Typen (Katalogen)
	 * @author ivasy
	 *
	 */
	Model getDataFromCatalogues (Model model);

}
