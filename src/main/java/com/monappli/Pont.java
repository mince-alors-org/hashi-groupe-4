package com.monappli;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;


/**
 * Cette classe permet de représenter un Pont
 * @author Morgane Pechon
 */
public class Pont {
	/**
	 * Nombre de trait entre les deux îles
	 */
	private int nombreTraits;
	/**
	 * Table contenant les deux îles relier par le pont
	 */
	private ArrayList <Ilot> coords;
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
	Pont(Ilot ile1, Ilot ile2,int nbTraits){
		this.nombreTraits=nbTraits;
		coords =new ArrayList <Ilot>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPontSolution(this);
		ile2.addPontSolution(this);
		ile1.calculValeur(nbTraits);
		ile2.calculValeur(nbTraits);
	}

	/**
	 *@return nombreTraits indique la quantiter de trait afficher
	 */
	public int getNbTraits(){
		return this.nombreTraits;
	}
	/**
	 * @return La première ile
	 */
	public Ilot getIle1(){
		return this.coords.get(0);
	}
	/**
	 * @return La deuxieme ile
	 */
	public Ilot getIle2(){
		return this.coords.get(1);
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
	public double tailleTrait(){
		return Math.sqrt(Math.pow((this.getIle1().getPosX())-(this.getIle2().getPosX()),2)+Math.pow((getIle1().getPosY())-(getIle2().getPosY()),2));
	}
	/**
	 * permet de trouver le voisin de l'ile entrée en paramètre
	 * @param a Ilot qui recherche sont voisin
	 * @return Ilot opposer à l'ile entrée en paramètre
	 */
	public Ilot voisin(Ilot a){
		if(this.getIle1().equals(a)){
			return this.getIle2();
		}
		return this.getIle1();
	}
	/**
	 * @return la liste des coordonnées d'un pont
	 * @author
	 */
	public ArrayList<Ilot> getCoords() {
		return coords;
	}

	/**
	 * Permet d'afficher le pont entre deux ile
	 * @param fond et le canva qui permet l'affichage
	*/
	public void affiche(Canvas fond){
		GraphicsContext gc=fond.getGraphicsContext2D();
		gc.fillRect(this.getIle1().getPosX(),this.getIle1().getPosY(),this.tailleTrait(),6);

	}

  @Override
  public String toString() {
    return "Pont{" +
      "nombreTraits=" + nombreTraits;

  }
}
