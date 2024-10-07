/**
 * 
 */
package com.company.modele.joueur;

import com.company.alphabeta.MinMax;
import com.company.modele.jeu.Grille;
import com.company.alphabeta.Heuristique;
import com.company.alphabeta.MinMax;
import com.company.modele.jeu.Grille;
import com.company.modele.jeu.couleur.Couleur;

/**
 * Ce joeur artificiel utilise l'algorithme minmax et l'heuristique stupide pour déterminer qu'elle colonne
 * jouer 
 *
 */
public class JoueurMinMax extends Joueur {

	private MinMax minMax;
	
	public JoueurMinMax(Couleur couleur, Heuristique heuristique) {
		super(couleur);
		this.minMax = new MinMax(heuristique);
	}

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.modele.joueur.Joueur#placerJeton(org.ronan.puissance4.modele.jeu.Grille)
	 */

	public int placerJeton(Grille grille, Joueur joueur, Joueur opposant) {
		return minMax.calculeColonneAJouer(grille, joueur);
	}

}
