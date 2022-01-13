package com.aeClub.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
/**
 * GetService versorgt die Information, die mit den Anwendungsdaten verknüpfet sind<br>
 * Zum Beispiel, die Auflistungen der aufzalenden Typen (Katalogen)
 * @author ivan romani
 *
 */
@Service
public interface GetService {
	
	
	/**
	 * Die Methode fuellt das Objekt des Modells die Werten aus den aufzalenden Typen(Katalogen)
	 * @author ivan romani
	 *
	 */
	Model getDataFromCatalogues (Model model);

}
