/**
 * 
 */
package com.company.transverse;

/**
 * Cette interface d�finise les op�rations de base que doivent fournir les journaliseur
 *
 */
public interface Journaliseur {

	
	
	/**
	 * Journalise le message d'erreur donn�
	 */
	public void erreur(String message);
	
	/**
	 * Journalise le message d'information donn�
	 * @param message
	 */
	public void info(String message);
}
