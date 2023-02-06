import java.util.ArrayList;
import java.math.*;

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
	Pont(Ilot ile1, Ilot ile2){
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
	public int tailleTrait(){
		return (int) Math.sqrt(Math.pow((coords.get(1).getPosX()-coords.get(2).getPosX()),2)+Math.pow((coords.get(1).getPosY()-coords.get(2).getPosY()),2));
	}

}
