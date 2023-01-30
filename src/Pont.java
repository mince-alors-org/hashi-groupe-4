import java.util.ArrayList;
import java.math.*;

public class Pont {
	int nombreTraits;
	ArrayList <Ilot> coords;
	Pont(Ilot ile1, Ilot ile2){
		this.nombreTraits=0;
		coords =new ArrayList <>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPont(this);
		ile2.addPont(this);
	}
	public void incrementer(){
		if(this.nombreTraits == 2)
			this.nombreTraits=0;
		else
			this.nombreTraits++;
	}

	public int tailleTrait(){
		return Math.sqlrt(Math.pow((coords.get(1).posX-coords.get(2).posX),2)+Math.pow((coords.get(1).posY-coords.get(2).posY),2));
	}
	public static void main(String[] args){
		Ilot ilot1 = new Ilot(2,5,3);
		Ilot ilot2 = new Ilot(4,3,5);
		Pont pont =new Pont(ilot1,ilot2);
		System.out.println(pont.tailleTrait());
		System.out.println(ilot1.valide());
	}

}
