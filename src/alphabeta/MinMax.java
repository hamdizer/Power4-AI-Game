/**
 * 
 */
package com.company.alphabeta;

import com.company.modele.jeu.ColonnePleineException;
import com.company.modele.jeu.Grille;
import com.company.modele.jeu.Jeton;
import com.company.modele.joueur.Joueur;
import com.company.modele.matrice.HorsIndexException;
import com.company.modele.partie.Partie;

/**
 * Cet algoritme de recherche permet grace � une heuristique de d�terminer ou joueur le prochain coup. Il s'agit d'un algorithme 
 * min-max classique
 *
 */
public class MinMax {


	private static final int PROFONDEUR_EXPLORATION_MIN_MAX = 3;
	private Heuristique heuristique;

	/**
	 * @param heuristique
	 */
	public MinMax(Heuristique heuristique) {
		this.heuristique = heuristique;
	}

	/**
	 * Utilise son heurtistique et l'algorithme min max pour d�terminer dans quel colonne jouer le prochain jeton. 
	 * @param grille la grille de jeu
	 * @param joueur le joueur dont c'est le tour
	 * @return L'index de la colonne ou joueur
	 */
	public int calculeColonneAJouer(Grille grille, Joueur joueur){
		int colonneAJouer = 1;
		double valeurDeJeu = Heuristique.MIN_NOTE;
		for(int i=1; i <= Grille.LARGEUR_GRILLE; i++){
			try {
				if(!grille.estColonnePleine(i)){
					Grille copieDeLaGrille = grille.copie();
					Jeton jeton = new Jeton(joueur.getCouleur());
					copieDeLaGrille.insereJeton(i, jeton);
					double valeurDeJeuCourante = minmax(copieDeLaGrille, joueur, PROFONDEUR_EXPLORATION_MIN_MAX);
					if (valeurDeJeuCourante >= valeurDeJeu){
						valeurDeJeu = valeurDeJeuCourante;
						colonneAJouer = i;
					}
				}
			} catch (HorsIndexException e) {
				e.printStackTrace();
			}  catch (ColonnePleineException e) {
				e.printStackTrace();
			}
		}
		return colonneAJouer;
	}


	/**
	 * Renvoie la valeur de jeu pour le joueur donn� en utilisant l'algorithme min max sur la profondeur donn�e. 
	 * Minmax calcule recursivement la valeur de jeu en simulant tous les coups possible du joueur, puis de l'oposant. Plus simplement il teste tout le coup possible du joeur,
	 * puis regarde tout les coups possibles de l'adversaire suite � ce coup et ainsi de suite. Cet ensemble de possibilit�s forme un arbre des possibles. Chaque noeud de cet arbre 
	 * est une grille sur lequel s'applique l'algorithme.  Il minise alors la valeur de jeu de l'opposant et maximise celle du joueur. Lorsque la profondeur 0 est atteinte alors
	 * l'heuristique est appel�e pour calculer la valeur de jeu, les valeurs sont ensuite remont� dans la pile d'appels puis minimis�es, maximis�es selon que le noeud soit un noeud opposant (minimisation)
	 * ou un noeud joueur (maximisation)
	 * @param grille La grille de jeu
	 * @param joueur Le joeur
	 * @param profondeur La profondeur maximale d'exploration des possibles
	 * @return
	 */
	private double minmax(Grille grille, Joueur joueur,  int profondeur){
		return this.min(grille, joueur, profondeur);
	}

	/**
	 * Applique la partie min, de minmax
	 */
	private double min(Grille grille, Joueur joueur, int profondeur){
		if(profondeur != 0){
			double valeurDeJeu = Heuristique.MAX_NOTE;
			for(int i=1; i <= Grille.LARGEUR_GRILLE; i++){
				try {
					if(!grille.estColonnePleine(i)){
						Grille copieDeLaGrille = grille.copie();
						Jeton jeton = new Jeton(Partie.getPartie().joueurSuivant(joueur).getCouleur());
						copieDeLaGrille.insereJeton(i, jeton);
						valeurDeJeu = Math.min(valeurDeJeu, this.max(copieDeLaGrille, joueur, profondeur-1));
					}
				} catch (HorsIndexException e) {
					e.printStackTrace();
				}catch (ColonnePleineException e) {
					e.printStackTrace();
				} 
			}
			return valeurDeJeu;
		}else{
			return this.heuristique.noteGrille(grille, joueur);
		}
	}

	/**
	 * Applique la partie max de minmax
	 */
	private double max(Grille grille, Joueur joueur, int profondeur){
		if(profondeur != 0){
			double valeurDeJeu = Heuristique.MIN_NOTE;
			for(int i=1; i <= Grille.LARGEUR_GRILLE; i++){
				try{
					if(!grille.estColonnePleine(i)){
						Grille copieDeLaGrille = grille.copie();
						Jeton jeton = new Jeton(joueur.getCouleur());
						copieDeLaGrille.insereJeton(i, jeton);
						valeurDeJeu = Math.max(valeurDeJeu, this.min(copieDeLaGrille, joueur, profondeur-1));
					}
				} catch (HorsIndexException e) {
					e.printStackTrace();
				}catch (ColonnePleineException e) {
					e.printStackTrace();
				} 
			}
			return valeurDeJeu;
		}else{
			return this.heuristique.noteGrille(grille, joueur);
		}
	}



}
