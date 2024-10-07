/**
 * 
 */
package com.company.modele.jeu;

/**
 * Repr�sente une case dans le puissance4. Une case peut contenir un jeton
 * 
 */
public class Case {

	private Jeton jeton;
	
	
	/**
	 * Cr�er un jeton qui est de la couleur donn�e
	 * @param jeton
	 */
	public Case(Jeton jeton) {
		this.jeton = jeton;
	}

	/**
	 * @return the jeton
	 */
	public Jeton getJeton() {
		return jeton;
	}

	/**
	 * @param jeton the jeton to set
	 */
	public void setJeton(Jeton jeton) {
		this.jeton = jeton;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(this.jeton != null){
			return this.jeton.toString();
		}else{
			return " ";
		}
	}
	
	
	
}
