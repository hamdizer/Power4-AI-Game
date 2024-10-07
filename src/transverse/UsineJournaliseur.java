/**
 * 
 */
package com.company.transverse;

/**
 * Fabrique un journaliseur selon une configuration pr�d�finie
 *
 */
public class UsineJournaliseur {
	
	public static Journaliseur CreerJournaliseur(){
	
		Journaliseur journaliseur = new SimpleJournaliseur();
		
		return journaliseur;
	}

}
