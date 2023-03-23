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
		return Math.sqrt(Math.pow((this.getIle1().getCanvasX())-(this.getIle2().getCanvasX()),2)+Math.pow((getIle1().getCanvasY())-(getIle2().getCanvasY()),2));
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
	public void affiche(Canvas fond,boolean doublon){
		this.incrementer();
		GraphicsContext gc=fond.getGraphicsContext2D();
		System.out.println("Ilot 1 :");
		/*System.out.println(this.getIle1().getCanvasX());
		System.out.println(this.getIle1().getCanvasY());*/

		System.out.println("Ilot 2 :");
		/*System.out.println(this.getIle2().getCanvasX());
		System.out.println(this.getIle2().getCanvasY());*/


		if (doublon){
			gc.strokeLine(this.getIle1().getCanvasX(),this.getIle1().getCanvasY()-10,this.getIle2().getCanvasX(),this.getIle2().getCanvasY()-10);
			gc.strokeLine(this.getIle1().getCanvasX(),this.getIle1().getCanvasY()+10,this.getIle2().getCanvasX(),this.getIle2().getCanvasY()+10);
		}
		else {
			gc.strokeLine(this.getIle1().getCanvasX(),this.getIle1().getCanvasY(),this.getIle2().getCanvasX(),this.getIle2().getCanvasY());
		}

		int nbTraits = this.getNbTraits();
		System.out.println(nombreTraits);


	}

	public void erase(Canvas fond){
		GraphicsContext gc = fond.getGraphicsContext2D();

		double x1 = Math.min(this.getIle1().getCanvasX(),this.getIle2().getCanvasX());

		double y1 = Math.min(this.getIle1().getCanvasY(),this.getIle2().getCanvasY());

		double x2 = Math.max(this.getIle1().getCanvasX(),this.getIle2().getCanvasX());

		double y2 = Math.max(this.getIle1().getCanvasY(),this.getIle2().getCanvasY());
		System.out.println(y1);
		System.out.println(y2);


		System.out.println();


		System.out.println(this.getIle1().getCanvasX());
		System.out.println(this.getIle2().getCanvasX());


		gc.clearRect(x1,y1-20,x2-x1,y2+20);
	}

	public void setNombreTraits(int nombreTraits) {
		this.nombreTraits = nombreTraits;
	}

	@Override
  public String toString() {
    return "Pont{" +
      "nombreTraits=" + nombreTraits;

  }
}
