/**
 * 
 */
package com.company.modele.joueur;

import com.company.modele.jeu.Grille;
import com.company.modele.jeu.couleur.Couleur;

/**
 * Cette classe abstraite d�finie l'ensemble des op�rations que faire un joueur. Afin d'�viter d'�ventuelles tricheries un joueur ne 
 *  peut pas ins�rer directement un jeton dans la grille mais demande � la partie de le faire � sa place. A chaque joueur est donn� une couleur.
 *
 */
public abstract class Joueur {
	
	
	/**
	 * La couleur du joueur
	 */
	private Couleur couleur;
	
	/**
	 * Le nom du joeur
	 */
	private String nom;
	
	/**
	 * Initialise le joueur avec la couleur donn�e
	 * @param couleur La couleur donn�e
	 */
	public Joueur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Retourne la couleur du joueur
	 * @return
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	
	/**
	 * @return the nomJoeur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nomJoeur to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Le joueur analyse la grille immutable et choisis dans qu'elle colonne placer son prochain jeton.
	 * @param grilleLa grille de jeu courrante
	 * @return Le num�ro de la colonne dans lequel ins�rer le prochain jeton
	 */
	public abstract int placerJeton(Grille grille, Joueur joueur, Joueur opposant) ;
}
