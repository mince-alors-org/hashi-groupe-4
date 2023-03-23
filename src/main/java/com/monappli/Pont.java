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
	 * grille principale du niveau en cours
	 */
	private Grille grille ;

	/**
	 *
	 * @param ile1 Depart du pont
	 * @param ile2 Arriver du pont
	*/
	Pont(Ilot ile1, Ilot ile2, Grille grille){
		this.nombreTraits=0;
		coords =new ArrayList <Ilot>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPont(this);
		ile2.addPont(this);
		this.grille = grille ;
	}
	Pont(Ilot ile1, Ilot ile2,int nbTraits, Grille grille){
		this.nombreTraits=nbTraits;
		coords =new ArrayList <Ilot>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPontSolution(this);
		ile2.addPontSolution(this);
		ile1.calculValeur(nbTraits);
		ile2.calculValeur(nbTraits);
		this.grille = grille ;
	}
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
	public boolean Croise(Pont p)
	{
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
		for( Pont p : this.grille.listePontExistant())
		{
			if(this.Croise(p)){
				return ;
			}
		}
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
	public void affiche(Canvas fond){
		this.incrementer();
		GraphicsContext gc=fond.getGraphicsContext2D();
		System.out.println("Ilot 1 :");
		System.out.println(this.getIle1().getCanvasX());
		System.out.println(this.getIle1().getCanvasY());

		System.out.println("Ilot 2 :");
		System.out.println(this.getIle2().getCanvasX());
		System.out.println(this.getIle2().getCanvasY());
		gc.strokeLine(this.getIle1().getCanvasX(),this.getIle1().getCanvasY(),this.getIle2().getCanvasX(),this.getIle2().getCanvasY());

	}

  @Override
  public String toString() {
    return "Pont{" +
      "nombreTraits=" + nombreTraits;

  }
}
