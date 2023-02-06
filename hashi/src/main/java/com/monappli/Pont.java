package main.java.com.monappli;
import java.util.ArrayList;
//import java.math.*;

/**
 *
 * @author
 */
public class Pont {
	int nombreTraits;
	ArrayList <Ilot> coords;
	/**
	 * @param ile1 :
	 * @param ile2 :
	 * @author
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
	 * @author
	 */
	public void incrementer(){
		if(this.nombreTraits == 2)
			this.nombreTraits=0;
		else
			this.nombreTraits++;
	}

	/**
	 * @return
	 * @author
	 */
	public ArrayList<Ilot> getCoords() {
		return coords;
	}

	public double tailleTrait(){
		return Math.sqrt(Math.pow((coords.get(0).getPosX()-coords.get(1).getPosX()),2)+Math.pow((coords.get(0).getPosY()-coords.get(1).getPosY()),2));
	}

}
