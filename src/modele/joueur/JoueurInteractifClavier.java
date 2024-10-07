/**
 * 
 */
package com.company.modele.joueur;

import java.util.Scanner;

import com.company.modele.jeu.Grille;
import com.company.modele.jeu.couleur.Couleur;

/**
 * Un joueur interactif au clavier demande à l'utilisateur de saisir la colonne dans laquelle placer son jeton 
 *
 */
public class JoueurInteractifClavier extends Joueur {

	public JoueurInteractifClavier(Couleur couleur) {
		super(couleur);
	}

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.modele.joueur.Joueur#placerJeton(org.ronan.puissance4.modele.jeu.Grille)
	 */
	@Override
	public int placerJeton(Grille grille,Joueur joueur, Joueur opposant) {
		System.out.print("Ou placer le prochain jeton? Donnez un numéro de colonne entre 1 et " + Grille.LARGEUR_GRILLE + " : ");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();


		return i;
	}

}
