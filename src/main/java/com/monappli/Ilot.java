package com.monappli;



import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


/**
 * Cette classe permet de représenter un Ilot
 * @author nmention
 */
public class Ilot  implements Comparable<Ilot>, Serializable{
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

	private IlotBtn bouton;

	/**
	* Pour l'affichage
	*/
	private boolean active;


	/**
	 *
	 * @param posX position de l'ilot courant dans l'axe des abscisses (X)
	 * @param posY position de l'ilot courant dans l'axe des ordonnées (Y)
	 * @param valeur nombre de ponts supportés par this
	*/
	public Ilot(int posX, int posY, int valeur, boolean graphic) {
		this.posX = posX;
		this.posY = posY;
		canvasX = 0;
		canvasY = 0;
		this.valeur = valeur;
		ponts = new ArrayList<Pont>();
		this.pontsSolution = new ArrayList<Pont>() ;
		if(graphic)
			bouton= new IlotBtn(Integer.toString(valeur), posX, posY);
	}

	public Ilot(int posX, int posY, boolean graphic){
		this.valeur=0;
		canvasX = 0;
		canvasY = 0;
		this.posX = posX;
		this.posY = posY;
		ponts = new ArrayList<>();
		pontsSolution = new ArrayList<>();
		if(graphic)
			bouton= new IlotBtn("0", posX, posY);
	}
	public Ilot(int posX, int posY){
		this(posX,  posY, false);
	}
	public Ilot(int posX, int posY, int valeur){
		this(posX,  posY, valeur, false);
	}
	/**
	 * remise à zero de tout les pont d'une île
	 */
	public void remiseZero(Canvas c){
		for(Pont p : this.ponts){
			p.remiseZero();
			p.erase(c);
		}
	}

	/**
	* Set the style of an isle thanks to Parametre
	* @see Parametre
	* @author Ambre
	* @param longueur
	* @param largeur
	*/
	public void setStyleParam(){
		this.getBtn().setStyle(			 "-fx-background-radius: 200px;"+
									"\n-fx-background-insets: 0 0 0 0;"+
									"\n-fx-background-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_ilot())+";"+
									"\n-fx-font:"+ (int)(1.0*24/(1.0*Grille.longueur/6)) +" px;"
									);
	}

	/**
	* Activate or deactivate an isle
	* @author Ambre
	* @param act <code>true</code> to activate this isle, <code>false</code> to deactivate it 
	*/
	public void setActive(boolean act){
		this.active=act;
		if(act){
				this.getBtn().setBorder(new Border(new BorderStroke(
										Color.GREEN, 
										BorderStrokeStyle.SOLID, 
										new CornerRadii(200), 
										new BorderWidths(4)
							)));
		}
			
		else{
			this.getBtn().setBorder(new Border(new BorderStroke(
										Color.BLACK, 
										BorderStrokeStyle.SOLID, 
										new CornerRadii(200), 
										new BorderWidths(4)
							)));

		}
	}

	public void setRed(boolean act){
		if(act){
			this.getBtn().setBorder(new Border(new BorderStroke(
											Color.RED, 
											BorderStrokeStyle.SOLID, 
											new CornerRadii(200), 
											new BorderWidths(4)
								)));
		}
		else 
			this.setActive(getActive());
	}

	public IlotBtn getBtn(){
		return this.bouton;
	}

	/**
	 * Get if an isle is active or not
	 * @return <code>true</code> if the isle is active, <code>false</code> else
	 */
	public boolean getActive(){
		return active;
	}

	public boolean estAligne(Ilot i){
		return estAligneHorizontalement(i) || estAligneVerticalement(i);
	}

	public boolean estAligneVerticalement(Ilot i){
		return this.getPosX() == i.getPosX();
	}

	public boolean estAligneHorizontalement(Ilot i){
		return this.getPosY() == i.getPosY();
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

  public ArrayList<Pont> getSolPonts(){
	return this.pontsSolution;
  }

  public boolean pontInSol(Pont p){
	for (Pont Spont : this.getSolPonts()){
		if(Spont.equals(p))
			return true;
	}
	return false;
  }

  public boolean equalsSol(){
	int tot=0;
	for(Pont p : this.getPonts()){
		if (!this.pontInSol(p) && p.getNbTraits() != 0){
			return false;
		}
		if(p.getNbTraits() != 0)
			tot++;
	}
	if (tot == this.getSolPonts().size())
		return true;
	return false;
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
