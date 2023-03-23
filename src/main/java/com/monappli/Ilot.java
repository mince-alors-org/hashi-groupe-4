package com.monappli;



//import java.beans.EventHandler;
import java.util.ArrayList;

import com.monappli.hashiScene.MainPanel;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


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


	private double canvasX;

	private double canvasY;
	/**
	 * nombre de ponts supportés par this
	 */
	private int valeur;



	/**
	 * ponts reliés à this
	 */
	private ArrayList<Pont> ponts;

	private ArrayList<Pont> pontsSolution;
	private Canvas fond;
	private Btn btn;
	private static Ilot ile=null;
	/**
	 *
	 * @param posX position de l'ilot courant dans l'axe des abscisses (X)
	 * @param posY position de l'ilot courant dans l'axe des ordonnées (Y)
	 * @param valeur nombre de ponts supportés par this
	*/
	public Ilot(int posX, int posY, int valeur) {
		this.posX = posX;
		this.posY = posY;
		canvasX = 0;
		canvasY = 0;
		this.valeur = valeur;
		ponts = new ArrayList<Pont>();
		this.pontsSolution = new ArrayList<Pont>() ;
	}

	public Ilot(int posX, int posY,Canvas fond){
		this.valeur=0;
		canvasX = 0;
		canvasY = 0;
		this.btn=new Btn(this);
		this.fond=fond;
		this.posX = posX;
		this.posY = posY;
		ponts = new ArrayList<>();
		pontsSolution = new ArrayList<>();
	}
	public void creaBtn(){
		this.btn=new Btn(this);
	}

	private class Btn extends Button{
		private Ilot bout;
		/**
		* Pour l'affichage
		*/
		private boolean active;
		Btn(Ilot bout){
			super();
			this.bout=bout;
			this.setActive(false);
		}
		/**
		* Permet l'affichage grphique d'une île
	 	* @author Ambre
		* @param longueur
		* @param largeur
		*/
		public void setStyleParam(int longueur, int largeur){
			this.setStyle(			 "-fx-background-radius: 200px;"+
									 "\n-fx-background-insets: 0 0 0 0;"+
									 "\n-fx-background-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_ilot())+";"+
									 "\n-fx-font:"+ (int)(1.0*24/Math.pow((largeur>longueur ? largeur : longueur) / (largeur>longueur ? longueur : largeur  ),1.0/4)) +" px;"
									 );
		}

		/**
		* Permet de gérer l'activiter d'une île
		* @author Ambre,Morgane,Noé
		* @param active
		*/
		public void setActive(boolean act){
			this.active=act;
			if(act){
					this.setBorder(new Border(new BorderStroke(
											Color.GREEN, 
											BorderStrokeStyle.SOLID, 
											new CornerRadii(200), 
											new BorderWidths(4)
								)));
				if(ile==null){
					ile=this.bout;
				}
				else{
					Pont p=liaisonP(ile);
					if(p!=null && !ile.equals(this.bout)){
						if (p.getNbTraits() == 1){
							p.erase(fond);
							p.affiche(fond,true);

						}
						else {
							p.affiche(fond,false);
						}
						this.setBorder(new Border(new BorderStroke(
							Color.BLACK,
							BorderStrokeStyle.SOLID,
							new CornerRadii(200),
							new BorderWidths(4)
						)));
						ile.getBtn().setBorder(new Border(new BorderStroke(
							Color.BLACK,
							BorderStrokeStyle.SOLID,
							new CornerRadii(200),
							new BorderWidths(4)
						)));
						ile=null;
					}
					else if(ile.equals(this.bout)){
						ile=null;
						this.active=false;
					}
					else{
						System.out.println(p);
					}
				}
			}
			else{
				this.setBorder(new Border(new BorderStroke(
											Color.BLACK, 
											BorderStrokeStyle.SOLID, 
											new CornerRadii(200), 
											new BorderWidths(4)
								)));

			}
		}
		public boolean getActive(){
			return active;
		}
	}





	public int getPosX() {
		return posX;
	}
	public Btn getBtn(){
		return this.btn;
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
	public void setActive(boolean act){
		this.getBtn().setActive(act);
	}
	public void setStyleParam(int longueur, int largeur){
		this.getBtn().setStyleParam(longueur, largeur);
	}
	public void setText(){
		this.getBtn().setText(Integer.toString(this.getValeur()));
	}
	public void setPrefSize(Double a, Double b){
		this.getBtn().setPrefSize(a, b);
	}
	public void setOnAction(){
		Btn b = this.getBtn();
		this.getBtn().setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
			  b.setActive(!(b.getActive()));
			}
		});
	}
	public void getStyleClass(String s){
		this.getBtn().getStyleClass().add(s);
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
			somme+=p.getNbTraits();
		}
		return somme;
	}

	/**
	 * Indique si le nombre de ponts est dépassé ou non
	 * @return vrai si le nombre de ponts est trop grand faux sinon
	 */
	public boolean pontDepasse(){
		return this.nbPont() > this.getValeur();
	}
	/**
	 * Indique si le nombre de ponts est valide
	 * @return vrai si le nombre de ponts est égale à la valeur false sinon
	 */
	public boolean pontValide(){
		return this.nbPont() == this.getValeur();
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
	/**
	 * Indique les voisins relier à l'ilot
	 * @return ArrayList<Ilot> : retourne la liste des voisins relier à l'ile
	 * @author Morgane
	 */
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
	 * Indique le pont entre deux ile
	 * @param ile : ile voisine au pont séléctionnner
	 * @return Pont : retourne le pont entre les deux iles
	 * @author Morgane
	 */
	public Pont liaisonP(Ilot ile){
		for(Pont p : this.ponts){
			if(p.voisin(this).equals(ile)){
				return p;
			}
		}
		return null;
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

  /**
   * Détermine si l'ilot courant et un autre ilot sont égaux en prenant en compte uniquement leurs coordonnées x et y
   *
   * @param obj ilot à comparer
   * @return true si les coordonnées x et y de l'objet courant et ilot sont égales
   * @throws ClassCastException si une instance d'une autre classe qu'Ilot est fourni en paramètre
   */
  @Override
  public boolean equals(Object obj) throws ClassCastException {
    if (!(obj instanceof Ilot)){
      throw new ClassCastException("Mauvais type en argument");
    }
    Ilot ilot = (Ilot) obj;
    if (this.getPosX() == ilot.getPosX() && this.getPosY() == ilot.getPosY()){
      return true;
    }
    return false;
  }

	public double getCanvasX() {
		return canvasX;
	}

	public void setCanvasX(double canvasX) {
		this.canvasX = canvasX;
	}

	public double getCanvasY() {
		return canvasY;
	}

	public void setCanvasY(double canvasY) {
		this.canvasY = canvasY;
	}

  public void calculValeur(int add){
    this.setValeur(this.getValeur()+add);
  }

  @Override
	public String toString() {
		return "Ilot{" +
				"posX=" + posX +
				", posY=" + posY +
				", valeur=" + valeur +
      ", ponts=" + ponts +
      ", pontsSolution=" + pontsSolution;
	}


}
