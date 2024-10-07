/**
 * 
 */
package com.company.modele.jeu;

import com.company.modele.jeu.couleur.Couleur;
import com.company.modele.jeu.couleur.Jaune;
import com.company.modele.jeu.couleur.Rouge;
import com.company.modele.matrice.HorsIndexException;
import com.company.modele.matrice.Matrice;
import com.company.transverse.Journaliseur;
import com.company.transverse.UsineJournaliseur;

/**
 * Une grille contient des case structur�es en lignes et colonnes.
 *
 */
public class Grille implements Cloneable{

	private static Journaliseur journaliseur = UsineJournaliseur.CreerJournaliseur();

	public static final int LARGEUR_GRILLE = 7;

	public static final int LONGUEUR_GRILLE = 6;

	protected Matrice<Case> matriceDeCases;



	/**
	 * Initialise la grille � vide
	 */
	public Grille() {
		this.matriceDeCases = new Matrice<Case>(LARGEUR_GRILLE, LONGUEUR_GRILLE);
		for(int i = 1; i<=LARGEUR_GRILLE; i++){
			for(int j = 1 ; j <= LONGUEUR_GRILLE; j++){
				Case currentCase = new Case(null);
				try {
					this.matriceDeCases.affecteValeur(i, j, currentCase);
				} catch (HorsIndexException e) {
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * Cr�er et retourne une copie de cette grille
	 */
	public Grille copie()  {
		Grille copieDeLaGrille = new Grille();
		for(int i = 1; i <= LARGEUR_GRILLE; i++){
			for(int j = 1; j<= LONGUEUR_GRILLE; j++){
				try {
					Jeton jeton = this.matriceDeCases.recupereValeur(i, j).getJeton();
					if(jeton != null){
						Couleur couleur = jeton.getCouleur();
						Jeton copieDuJeton = new Jeton(couleur);
						Case copieDeLaCase = copieDeLaGrille.matriceDeCases.recupereValeur(i, j);
						copieDeLaCase.setJeton(copieDuJeton);
					}

				} catch (HorsIndexException e) {
					e.printStackTrace();
				}

			}
		}
		return copieDeLaGrille;
	}


	/**
	 * Ins�re un jeton dans la colonne dont l'index est donn�e en param�tre. Cet index doit �tre compris entre 1 et {@link #LARGEUR_GRILLE}.
	 * Il ne faut pas que cette colonne soit d�j� pleine.
	 * @param i L'index de la colonne dans laquelle ins�rer le jeton donn�e
	 * @throws ColonnePleineException Cette exception est soulev�e si l'on tente d'ins�rer un Jeton dans une colonne vide
	 * @throws HorsIndexException Si jamais la colonne demand� n'existe pas
	 * @jeton Le jeton � ins�rer
	 */
	public void insereJeton(int i, Jeton jeton) throws ColonnePleineException, HorsIndexException{
		if(estColonnePleine(i)){
			String message = "La colonne dans laquelle on tente d'ins�rer le Jeton " + jeton + " est pleine.";
			journaliseur.erreur(message);
			throw new ColonnePleineException(message);
		}
		int j = this.plusHautJeton(i) + 1;
		Case currentCase = this.matriceDeCases.recupereValeur(i, j);
		currentCase.setJeton(jeton);

	}

	/**
	 * Retourne vrai si la colonne dont l'index est donn�e en param�tre.
	 * @param i L'index de la colonne � tester
	 * @return vrai si la colonne est pleine, autrement faux.
	 * @throws HorsIndexException Si l'index donn� ne correspond � aucune colonne
	 */
	public boolean estColonnePleine(int i) throws HorsIndexException{
		return (this.plusHautJeton(i) == LONGUEUR_GRILLE);
	}

	/**
	 * R�cup�re le jeton dans la ligne et la colonne demand�e
	 * @param i index de colonne
	 * @param j index de ligne
	 * @return Le jeton demand�
	 * @throws HorsIndexException
	 * @see Matrice#recupereValeur(int, int)
	 */
	public Jeton recupereJeton(int i, int j) throws HorsIndexException {
		return matriceDeCases.recupereValeur(i, j).getJeton();
	}

	/**
	 * Retourne l'index du plus haut jeton de la colonne donn�e.
	 * @param i Colonne dont on souhaite connaitre la hauteur du plus haut jeton
	 * @return L'index du plus haut jeton dans la colonne donn�e
	 * @throws HorsIndexException Si l'index donn� ne correspond � aucune colonne
	 */
	private int plusHautJeton(int i) throws HorsIndexException{
		int indexPlusHaut = 0;
		for(int j = LONGUEUR_GRILLE; j >= 1 && indexPlusHaut==0; j--){
			if(this.matriceDeCases.recupereValeur(i, j).getJeton() != null){
				indexPlusHaut = j;
			}
		}
		return indexPlusHaut;
	}

	/**
	 * Retourne vrai si il existe un alignement de "tailleAlignement" de jeton de la couleur "couleur". L'alignement doit se faire 
	 * horizontalement, verticalement ou en diagonnale. La recherche d'alignement se fait sur toute la grille.
	 */
	public boolean chercheAlignementDeJeton(int tailleAlignement, Couleur couleur){
		
		boolean alignementTrouve = false;
		// On fait une recherche horizontale sur toute les cases possibles
		for(int j = 1; j <= LONGUEUR_GRILLE && !alignementTrouve; j++){
			for (int i = 1; i <= LARGEUR_GRILLE && !alignementTrouve; i++){
				alignementTrouve = this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j,0, 1) ||
						this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 1, 1) ||
						this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 1, 0) ||
						this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 1, -1) ||
						this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 0, -1) ||
						this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, -1, -1) ||
						this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, -1, 0) || 
						this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j,-1, 1);

			}
		}
		
		return alignementTrouve;
	}

	/**
	 * Retourne le nombre d'alignement qui existe de "tailleAlignement" de jeton de la couleur "couleur". L'alignement doit se faire 
	 * horizontalement, verticalement ou en diagonnale. La recherche d'alignement se fait sur toute la grille.
	 */
	public int compteAlignementDeJeton(int tailleAlignement, Couleur couleur){
		int nbAlignement =0;
		// On fait une recherche horizontale sur toute les cases possibles
		for(int j = 1; j <= LONGUEUR_GRILLE; j++){
			for (int i = 1; i <= LARGEUR_GRILLE; i++){
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j,0, 1) ){ nbAlignement++;;}
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 1, 1) ){ nbAlignement++;;}
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 1, 0) ){ nbAlignement++;;}
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 1, -1) ){ nbAlignement++;;}
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, 0, -1) ){ nbAlignement++;;}
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, -1, -1) ){ nbAlignement++;;}
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j, -1, 0) ){ nbAlignement++;;} 
				if(this.chercheAlignementDeJeton(tailleAlignement, couleur, i, j,-1, 1) ){ nbAlignement++;;}

			}
		}

		return nbAlignement;
	}

	/**
	 * Retourne vrai si il existe un alignement de "tailleAlignement" de jeton de la couleur "couleur". La recherche se fait � partir de l'index de colonne "i" et de ligne "j"
	 * , et vas dans le sens des d�clinaisons horizontales et verticales fournies en param�tre. Par exemple si l'on donne (0,0) alors la recherche se fera sur la m�me case, alors
	 * que si l'on donne (1,1) la recherche se fera en diagonnale "nord-est", ou encore elle se fera en direction ouest si l'on donne (-1,0)
	 * @param tailleAlignement Taille de l'alignement recherch�
	 * @param couleur Couleur de l'alignement recherch�
	 * @param i colonne du d�but de la recherche
	 * @param j ligne du d�but de la recherche
	 * @param declinaisonVerticale direction de recherche verticale
	 * @param declinaisonHorizontale direction de recherche horizontale
	 * @return vrai si un alignement est trouv�.
	 */
	public boolean chercheAlignementDeJeton(int tailleAlignement, Couleur couleur, int i , int j, int declinaisonHorizontale, int declinaisonVerticale){
		boolean alignementPreserve = true;
		while(tailleAlignement != 0 && alignementPreserve){
			// On cherche l'alignement de la taille demand�
			Jeton jeton = null;
			try {
				// On r�cup�re le jeton correspondant � i j pour v�rifier sa couleur
				jeton = this.recupereJeton(i, j);
			} catch (HorsIndexException e) {
				// L'alignement n'existe pas puisqu'on est hors grille
				alignementPreserve = false;
			}
			// On teste la couleur du jeton, si il n'est pas de la bonne couleur alors l'alignement n'est pas pr�serv� et on quite la boucle
			if(jeton==null || !jeton.getCouleur().equals(couleur)){
				alignementPreserve = false;
			}
			// On cherche sur la prochaine et on r�duit le nombre de cases � chercher et donc l'alignement
			i+=declinaisonHorizontale;
			j+=declinaisonVerticale;
			tailleAlignement--;
		}
		return alignementPreserve;
	}


	/**
	 * Retourne vrai si la grille est pleine
	 */
	public boolean estGrillePleine() {
		for (int i=1; i <= LARGEUR_GRILLE; i++){
			try {
				if(!estColonnePleine(i)){
					return false;
				}
			} catch (HorsIndexException e) {
				e.printStackTrace();
			}
		}
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.matriceDeCases.toString();
	}

	public static void main(String[] args) {

		Grille grille = new Grille();

		for(int i = 1; i <= LARGEUR_GRILLE; i++){
			for(int j = 1 ; j <= LONGUEUR_GRILLE; j++){
				Jeton jeton;
				int random = (int)(Math.random() * 100);
				if( ( random  % 2) == 0){
					jeton = new Jeton(new Rouge());
				}else{
					jeton = new Jeton(new Jaune());
				}
				try {
					grille.insereJeton( i , jeton);
				} catch (ColonnePleineException e) {
					e.printStackTrace();
				} catch (HorsIndexException e) {
					e.printStackTrace();
				}
				
			}
		}
		System.out.println(grille);

		System.out.println(grille.copie());

	}




}
