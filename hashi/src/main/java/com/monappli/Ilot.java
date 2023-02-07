package main.java.com.monappli;

import java.util.ArrayList;

import java.util.Collections;

/**
 * Cette classe permet de représenter un Ilot
 * @author nmention
 */
public class Ilot implements Comparable<Ilot>{
	/**
	 * position de this dans l'axe des abscisses (X)
	 */
	private int posX;
	/**
	 * position de this dans l'axe des ordonnées (Y)
	 */
	private int posY;
	/**
	 * nombre de ponts supportés par this
	 */
	private int valeur;

	/**
	 * ponts reliés à this
	 */
	private ArrayList<Pont> ponts;

	private ArrayList<Pont> pontsSolution;

	/**
	 *
	 * @param posX position du l'ilot courant dans l'axe des abscisses (X)
	 * @param posY position de l'ilot courant dans l'axe des ordonnées (Y)
	 * @param valeur nombre de ponts supportés par this
	*/
	public Ilot(int posX, int posY, int valeur) {
		this.posX = posX;
		this.posY = posY;
		this.valeur = valeur;
		ponts = new ArrayList<>();
		this.pontsSolution = new ArrayList<>() ;
	}
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}


	public ArrayList<Pont> getPonts() {
		return ponts;
	}

	public void setPonts(ArrayList<Pont> ponts) {
		this.ponts = ponts;
	}

	/**
	 * Ajout d'un pont connecté au noeud courant
	 * @param pont : le pont à ajouter
	 */
	public void addPont(Pont pont){
		ponts.add(pont);
	}


	public void addPontSolution(Pont pont){
		pontsSolution.add(pont);
	}

	/**
	 * Suppression d'un pont connecté au noeud courant
	 * @param pont : le pont à supprimer
	 */
	public void deletePont(Pont pont){
		ponts.remove(pont);
	}


	/**
	 * Indique si l'ilot peut être connecté à un pont
	 * @return true si l'ilot peut faire parti d'un pont
	 */
	public boolean valide(){
		return ponts.size() < valeur;
	}

	/**
	 * Indique le nombre de pont activer
	 * @return Le nombre de pont activer
	 */
	public int nbPont(){
		int somme =0;
		for(Pont p :this.pont){
			somme+=p.nombreTraits;
		}
		return somme;
	}

	/**
	 * Indique si le nombre de pont est dépasser ou non
	 * @return vrai si le nombre de pont est trop grand faux si non
	 */
	public boolean pontDepasse(){
		return this.nbPont()>this.valeur? true: false;
	}
	/**
	 * Indique si le nombre de pont est valide
	 * @return vrai si le nombre de pont est égale à la valeur faux si non
	 */
	public boolean pontValide(){
		return this.nbPont()==this.valeur? true: false;
	}
	/**
	 * Indique les voisin de l'ilot
	 * @return ArrayList<Ilot> : retourne la liste des voisin de l'ile
	 * @author Morgane
	 */
	public ArrayList<Ilot> listeVoisin(){
		ArrayList<Ilot> voisin =new ArrayList<>();
		for(Pont p : this.pont){
			voisin.add(p.voisin(this));
		}
		return voisin;
	}

	/**
	 * Pour vérifier si deux ile sont identique
	 * @param Ilot : ile à comparer
	 * @return vrai si les ile sont identique
	 * @author Morgane
	 */
	public int compareTo(Ilot o) {
		if (this.posX==o.posX && this.posY==o.posY)
			return 1;
		return 0;
	}


	@Override
	public String toString() {
		return "Ilot{" +
			"posX=" + posX +
			", posY=" + posY +
			", valeur=" + valeur +
			", listePontsSolution=" + ponts +
			'}';
	}


}
