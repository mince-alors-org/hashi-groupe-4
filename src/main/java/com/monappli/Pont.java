package com.monappli;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Cette classe permet de représenter un Pont
 * @author Morgane Pechon
 */
public class Pont implements Serializable{
	/**
	 * Nombre de trait entre les deux îles
	 */
	private int nombreTraits;

	/**
	 * Table contenant les deux îles relier par le pont
	 */
	private ArrayList <Ilot> coords;

	/**
	 * grille principale du niveau en cours
	 */

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
	 * Permet de savoir si un pont est verticale
	 *
	 * @param i1 le premier îlot su pont
	 * @param i2 le sexond îlot du pont
	 * @return true si le pont est verticale (si ils ont le même x)
	 */
	public boolean estVerticale()
	{
		return this.getIle1().estAligneVerticalement(getIle2());
	}

	public boolean estHorizontale()
	{
		return this.getIle1().estAligneHorizontalement(getIle2());
	}

	/**
	 * Permet de savoir si un pont en croise un autre.
	 *
	 * @param p l'autre pont
	 * @return true si les deux ponts se croisent, false sinon
	 */
	public boolean croise(Pont p)
	{
		if (this==p)
			return false;

		if (p.getNbTraits() == 0)
			return false;

		if(p.estVerticale() == this.estVerticale())
		{
			return false;
		}
		else
		{
			if(estHorizontale())
			{
				if(
					(
						//on vérifie si le x de p est entre nos deux x
						(getIle1().getPosX() > p.getIle1().getPosX() && getIle2().getPosX() < p.getIle1().getPosX() ) ||
				   		(getIle1().getPosX() < p.getIle1().getPosX() && getIle2().getPosX() > p.getIle1().getPosX())
					) &&
					(
						//on vérifie si notre y est entre deux y de p
						(getIle1().getPosY() > p.getIle1().getPosY() && getIle1().getPosY() < p.getIle2().getPosY()) ||
						(getIle1().getPosY() < p.getIle1().getPosY() && getIle1().getPosY() > p.getIle2().getPosY())
					)
				   )
				{
					return true ;
				}
			}
			else
			{
				if(
					(
						//on vérifie si notre x est entre deux x de p
						(getIle1().getPosX() > p.getIle1().getPosX() && getIle1().getPosX() < p.getIle2().getPosX() ) ||
				   		(getIle1().getPosX() < p.getIle1().getPosX() && getIle1().getPosX() > p.getIle2().getPosX())
					) &&
					(
						//on vérifie si le y de p est entre nos deux y
						(getIle1().getPosY() > p.getIle1().getPosY() && getIle2().getPosY() < p.getIle1().getPosY()) ||
						(getIle1().getPosY() < p.getIle1().getPosY() && getIle2().getPosY() > p.getIle1().getPosY())
					)
				   )
				{
					return true ;
				}
			}
			return false;
		}
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
	 * permet de trouver le voisin de l'ile entrée en paramètre
	 * @param a Ilot qui recherche son voisin
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
	 * Permet d'afficher le pont entre deux iles
	 * @param doublon true si il existe déjà un trait entre ces deux îles
	 * @param fond est le canvas qui permet l'affichage
	*/
	public void affiche(Canvas fond){


		GraphicsContext gc=fond.getGraphicsContext2D();
		gc.setStroke(Parametre.getCouleur_pont());

		this.erase(fond);
		System.out.println("Ilot 1 :");
		/*System.out.println(this.getIle1().getCanvasX());
		System.out.println(this.getIle1().getCanvasY());*/

		System.out.println("Ilot 2 :");
		/*System.out.println(this.getIle2().getCanvasX());
		System.out.println(this.getIle2().getCanvasY());*/


		if (this.getNbTraits()==2){
			if (this.estHorizontale()){
				gc.strokeLine(this.getIle1().getCanvasX(),this.getIle1().getCanvasY()-10,this.getIle2().getCanvasX(),this.getIle2().getCanvasY()-10);
				gc.strokeLine(this.getIle1().getCanvasX(),this.getIle1().getCanvasY()+10,this.getIle2().getCanvasX(),this.getIle2().getCanvasY()+10);
			}
			else {
				gc.strokeLine(this.getIle1().getCanvasX() -10,this.getIle1().getCanvasY(),this.getIle2().getCanvasX()-10,this.getIle2().getCanvasY());
				gc.strokeLine(this.getIle1().getCanvasX()+10,this.getIle1().getCanvasY(),this.getIle2().getCanvasX()+10,this.getIle2().getCanvasY());
			}

		}
		else if (this.getNbTraits()==1) {

			gc.strokeLine(this.getIle1().getCanvasX(),this.getIle1().getCanvasY(),this.getIle2().getCanvasX(),this.getIle2().getCanvasY());
		}
		System.out.println(this.getNbTraits());


	}


	/**
	 * Permet d'effacer les traits entre les Ilots sur le Canvas
	 * @param fond le canvas à manipuler
	 */
	public void erase(Canvas fond){
		GraphicsContext gc = fond.getGraphicsContext2D();

		double x1 = Math.min(this.getIle1().getCanvasX(),this.getIle2().getCanvasX());

		double y1 = Math.min(this.getIle1().getCanvasY(),this.getIle2().getCanvasY());

		double x2 = Math.max(this.getIle1().getCanvasX(),this.getIle2().getCanvasX());

		double y2 = Math.max(this.getIle1().getCanvasY(),this.getIle2().getCanvasY());



    double width = this.getIle1().getPrefWidth();

    double height = this.getIle1().getPrefHeight();
		System.out.println(y1);
		System.out.println(y2);


		System.out.println();


		System.out.println(this.getIle1().getCanvasX());
		System.out.println(this.getIle2().getCanvasX());


		if (this.estVerticale()){
      gc.setStroke(Color.GREEN);
      //gc.strokeArc(x1-(width/2),y1,width,y2-y1,120,360, ArcType.CHORD);
			gc.clearRect(x1-(width/2)+10,y1,width-20,y2-y1);
      System.out.println("verticale");
		}
		else {
      gc.setStroke(Color.RED);

      //gc.fillArc(x1,y1-(height/2),x2-x1,(y2-y1) + height,120,360, ArcType.CHORD);
			gc.clearRect(x1,y1-(height/2)+10,x2-x1,(y2-y1) + height-20);
      System.out.println("horizontale");
      System.out.println("OGHHHHHHH" +this.getIle1().getPrefWidth());
		}



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
