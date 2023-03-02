package com.monappli;

import java.util.ArrayList;


/**
 * Cette classe permet de représenter un Pont
 * @author Morgane Pechon
 */
public class Pont {
	/**
	 * Nombre de trait entre les deux îles
	 */
	int nombreTraits;
	/**
	 * Table contenant les deux îles relier par le pont
	 */
	ArrayList <Ilot> coords;
	/**
	 *
	 * @param ile1 Depart du pont
	 * @param ile2 Arriver du pont
	*/
	Pont(Ilot ile1, Ilot ile2){
		this.nombreTraits=0;
		coords =new ArrayList <Ilot>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPont(this);
		ile2.addPont(this);
	}

	/**
	 *@return nombreTraits indique la quantiter de trait afficher 
	 */
	public int getNbTraits(){
		return this.nombreTraits;
	}
	/**
	 * Incrémente le nombre de pont entre les deux îles
	 */
	public void incrementer(){
		if(this.nombreTraits == 2)
			this.nombreTraits=0;
		else
			this.nombreTraits++;
	}
	/**
	 * Renvoit la taille du trait entre les deux île
	 * @return int taille du trai parapore au coordonée des l'îles
	 */
	public int tailleTrait(){
		return (int) Math.sqrt(Math.pow((coords.get(1).getPosX()-coords.get(2).getPosX()),2)+Math.pow((coords.get(1).getPosY()-coords.get(2).getPosY()),2));
	}
	/**
	 * permet de trouver le voisin de l'ile entrée en paramètre
	 * @param a Ilot qui recherche sont voisin
	 * @return Ilot opposer à l'ile entrée en paramètre
	 */
	public Ilot voisin(Ilot a){
		if(coords.get(0)==a){
			return coords.get(1);
		}
		return coords.get(0);
	}
	/**
	 * @return la liste des coordonnées d'un pont
	 * @author
	 */
	public ArrayList<Ilot> getCoords() {
		return coords;
	}

}
