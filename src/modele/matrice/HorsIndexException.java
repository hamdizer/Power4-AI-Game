/**
 * 
 */
package com.company.modele.matrice;

/**
 * Cette exception peut être soulevée si jamais une opération dois se faire en dehors des bornes définies
 *
 */
public class HorsIndexException extends Exception {

	
	/**
	 * Génèré automatiquement par eclipse
	 */
	private static final long serialVersionUID = -4905533898090698708L;

	public HorsIndexException() {
	}

	/**
	 * @param message
	 */
	public HorsIndexException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HorsIndexException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HorsIndexException(String message, Throwable cause) {
		super(message, cause);
	}

}
