/**
 * 
 */
package com.company.transverse;

import java.util.Date;

/**
 * Ce journaliseur utilise les entr�es sorties classiques de la JVM pour journaliser les messages
 *
 */
public class SimpleJournaliseur implements Journaliseur {
	
	
	private static final String PREFIXE_ERREUR = "[ERREUR]";
	
	private static final String PREFIXE_INFO ="[INFO]";
	

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.transverse.Journaliseur#erreur(java.lang.String)
	 */
	@Override
	public void erreur(String message) {
		System.err.println(this.formatageMessage(PREFIXE_ERREUR, message));
	}

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.transverse.Journaliseur#info(java.lang.String)
	 */
	@Override
	public void info(String message) {
		System.out.println(this.formatageMessage(PREFIXE_INFO, message));
	}

	/**
	 * Formate le message sous la forme de DATE  - PREFIXE MESSAGE
	 * @param prefixe Le prefixe du message (erreur ou info)
	 * @param message Le message � int�grer
	 * @return Le message format� et pr�t � �tre afficher
	 */
	private String formatageMessage(String prefixe, String message){
		
		// Cr�ation du buffer de message
		StringBuffer bufferMessage =  new StringBuffer();
		
		// R�cup�ration de la date
		Date date = new Date(System.currentTimeMillis());
		
		bufferMessage.append(date);
		bufferMessage.append(" - ");
		bufferMessage.append(prefixe);
		bufferMessage.append(" ");
		bufferMessage.append(message);
		
		return bufferMessage.toString();
	}
	
	
}
