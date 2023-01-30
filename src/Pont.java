public class Pont {
	int nombreTraits;
	ArrayList <Ilot> coords;
	Pont(Ilot ile1, Ilot ile2){
		this.nombreTraits;
		coords =new ArrayList <>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPont(this);
		ile2.addPont(this);
	}
	public void incrementer(){
		if(this.nombreTraits.equals(2))
			this.nombreTraits=0;
		else
			this.nombreTraits++;
	}

	public int tailleTrait(){
		return sqlrt((coords.get(1).posX-coords.get(2).posX)**2+(coords.get(1).posY-coords.get(2).posY)**2);
	}
}
