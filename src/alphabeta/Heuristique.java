/**
 * 
 */
package com.company.alphabeta;

import com.company.modele.jeu.Grille;
import com.company.modele.joueur.Joueur;

/**
 * Cette heuristique note une grille pour savoir qu'elle son potentiel de gain pour le joueur donn�
 * @author Ronan Quintin
 *
 */
public abstract class Heuristique {

	
	public static final int MAX_NOTE = Integer.MAX_VALUE;
	
	public static final int MIN_NOTE = Integer.MIN_VALUE;
	
	
	/**
	 * Note la grille donn� pour le joueur donn�
	 * @param grille La grille � noter
	 * @param joueur Le joueur dont on cherche � donner savoir si il va gagner
	 * @return une note comprose entre min int et max int
	 */
	public abstract double noteGrille(Grille grille, Joueur joueur);
}
