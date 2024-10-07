package com.company.alphabeta.heuristiques;

import com.company.alphabeta.Heuristique;
import com.company.modele.jeu.Grille;
import com.company.modele.jeu.Jeton;
import com.company.modele.jeu.couleur.Couleur;
import com.company.modele.joueur.Joueur;
import com.company.modele.matrice.HorsIndexException;
import com.company.modele.partie.Partie;



/**
 * Cette heuristique compte le nombre d'alignement possible pour un joueur donn�.
 *
 *
 */
public class HeuristiqueAlignementPossible extends Heuristique{

	@Override
	public double noteGrille(Grille grille,  Joueur joueur) {



		if(grille.chercheAlignementDeJeton(Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, Partie.getPartie().joueurSuivant(joueur).getCouleur())){
			return Heuristique.MIN_NOTE;
		}

		if(grille.chercheAlignementDeJeton(Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, joueur.getCouleur())){
			return Heuristique.MAX_NOTE;
		}
		
		Couleur couleur = joueur.getCouleur();
		double resultat = 0;
		
		for(int i = 1; i <= Grille.LARGEUR_GRILLE; i++){
			for(int j = 1; j <= Grille.LONGUEUR_GRILLE; j++){
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j,0, 1) ;
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j, 1, 1) ;
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j, 1, 0) ;
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j, 1, -1) ;
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j, 0, -1) ;
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j, -1, -1) ;
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j, -1, 0) ; 
				resultat +=this.chercheAlignementDeJeton(grille, Partie.TAILLE_ALIGNEMENT_POUR_VICTOIRE, couleur, i, j,-1, 1) ;
				
			}
		}

		return resultat;
	}

	
	
	private double chercheAlignementDeJeton(Grille grille, int tailleAlignement, Couleur couleur, int i , int j, int declinaisonHorizontale, int declinaisonVerticale){
		double resultat = 1;
		while(tailleAlignement != 0 && resultat != 0){
			// On cherche l'alignement de la taille demand�
			Jeton jeton = null;
			try {
				// On r�cup�re le jeton correspondant � i j pour v�rifier sa couleur
				jeton = grille.recupereJeton(i, j);
			} catch (HorsIndexException e) {
				// L'alignement n'existe pas puisqu'on est hors grille
				resultat = 0;
			}
			// On teste la couleur du jeton
			if(jeton==null) {
				resultat *= 0.5;
			}else if( jeton.getCouleur().equals(couleur)){
				resultat *= 1.0;
			}else{
				resultat *= 0;
			}
			// On cherche sur la prochaine et on r�duit le nombre de cases � chercher et donc l'alignement
			i+=declinaisonHorizontale;
			j+=declinaisonVerticale;
			tailleAlignement--;
		}
		return resultat;
	}

}
