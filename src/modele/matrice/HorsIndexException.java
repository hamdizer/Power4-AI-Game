/**
 * 
 */
package com.company.modele.matrice;

/**
 * Cette exception peut �tre soulev�e si jamais une op�ration dois se faire en dehors des bornes d�finies
 *
 */
public class HorsIndexException extends Exception {

	
	/**
	 * G�n�r� automatiquement par eclipse
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
