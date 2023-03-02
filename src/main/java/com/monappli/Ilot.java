package com.monappli;


import java.util.ArrayList;

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
	 * @param posX position de l'ilot courant dans l'axe des abscisses (X)
	 * @param posY position de l'ilot courant dans l'axe des ordonnées (Y)
	 * @param valeur nombre de ponts supportés par this
	*/
	public Ilot(int posX, int posY, int valeur) {
		this.posX = posX;
		this.posY = posY;
		this.valeur = valeur;
		ponts = new ArrayList<Pont>();
		this.pontsSolution = new ArrayList<Pont>() ;
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
	 * Ajout d'un pont connecté au nœud courant
	 * @param pont : le pont à ajouter
	 */
	public void addPont(Pont pont){
		ponts.add(pont);
	}


	public void addPontSolution(Pont pont){
		pontsSolution.add(pont);
	}

	/**
	 * Suppression d'un pont connecté au nœud courant
	 * @param pont : le pont à supprimer
	 */
	public void deletePont(Pont pont){
		ponts.remove(pont);
	}


	/**
	 * Indique si l'ilot peut être connecté à un pont
	 * @return true si l'ilot peut faire partie d'un pont
	 */
	public boolean valide(){
		return ponts.size() < valeur;
	}

	/**
	 * Indique le nombre de ponts activer
	 * @return Le nombre de ponts activer
	 */
	public int nbPont(){
		int somme =0;
		for(Pont p :this.ponts){
			somme+=p.nombreTraits;
		}
		return somme;
	}

	/**
	 * Indique si le nombre de ponts est dépassé ou non
	 * @return vrai si le nombre de ponts est trop grand faux sinon
	 */
	public boolean pontDepasse(){
		return this.nbPont() > this.valeur;
	}
	/**
	 * Indique si le nombre de ponts est valide
	 * @return vrai si le nombre de ponts est égale à la valeur false sinon
	 */
	public boolean pontValide(){
		return this.nbPont() == this.valeur;
	}
	/**
	 * Indique les voisins de l'ilot
	 * @return ArrayList<Ilot> : retourne la liste des voisins de l'ile
	 * @author Morgane
	 */
	public ArrayList<Ilot> listeVoisin(){
		ArrayList<Ilot> voisin =new ArrayList<Ilot>();
		for(Pont p : this.ponts){
			voisin.add(p.voisin(this));
		}
		return voisin;
	}
	public ArrayList<Ilot> listeVoisinRelier(){
		ArrayList<Ilot> voisin = new ArrayList<Ilot>();
		for(Pont p : this.ponts){
			if(p.getNbTraits()!=0){
				voisin.add(p.voisin(this));
			}
		}
		return voisin;
	}

	/**
	 * Pour vérifier si deux iles sont identiques
	 * @param ilot : ile à comparer
	 * @return vrai si les iles sont identiques
	 * @author Morgane
	 */
	public int compareTo(Ilot ilot) {
		if (this.posX==ilot.posX && this.posY==ilot.posY)
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
