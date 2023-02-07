package main.java.com.monappli;
import java.util.ArrayList;
//import java.math.*;

/**
 *cette Objet permet l'utilisation des pont qui lie de ile entre elle
 * @author Morgane
 */
public class Pont {
	int nombreTraits;
	ArrayList <Ilot> coords;
	/**
	 * @param ile1 : la où commence le pont
	 * @param ile2 : la où termine le pont
	 * @author Morgane
	 */
	public Pont(Ilot ile1, Ilot ile2){
		this.nombreTraits=0;
		coords =new ArrayList <>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPont(this);
		ile2.addPont(this);
	}

	/**
	 * Permet d'incrémenté le nombre de pont entre deux ile
	 * @author Morgane
	 */
	public void incrementer(){
		if(this.nombreTraits == 2)
			this.nombreTraits=0;
		else
			this.nombreTraits++;
	}
	/**
	 * Permet de conaitre le voisin de l'île entrée
	 * @param Ilot : ile qui demande sont voisin
	 * @return Ilot : ile qui ce situe de l'autre côter du pont
	 */
	public Ilot voisin(Ilot demande){
		return demande == coords.get(0) ? coords.get(1) : coords.get(0);
	}

	/**
	 * @return la liste des coordonné d'un pont
	 * @author
	 */
	public ArrayList<Ilot> getCoords() {
		return coords;
	}

	/**
	 *Permet de connaître la taille d'un pont
	 *@return la taille du pont
	 @author Morgane
	 */

	public double tailleTrait(){
		return Math.sqrt(Math.pow((coords.get(0).getPosX()-coords.get(1).getPosX()),2)+Math.pow((coords.get(0).getPosY()-coords.get(1).getPosY()),2));
	}

}
