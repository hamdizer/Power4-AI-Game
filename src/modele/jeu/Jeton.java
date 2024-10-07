/**
 * 
 */
package com.company.modele.jeu;

import com.company.modele.jeu.couleur.Couleur;

/**
 * Un jeton est lié à une couleur. Le but du jeu est d'alligner 4 jetons de la même couleur pour gagner.
 *
 */
public class Jeton {

	private Couleur couleur;
	
	 public Jeton(Couleur couleur) {
		 this.couleur = couleur;
	 }

	/**
	 * @return the couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(couleur!=null){
			return this.couleur.toString();
		}else{
			return super.toString();
		}
		
	}

	 
	 
}
