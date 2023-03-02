package com.monappli;

import java.util.ArrayList;

public class Pont {
	int nombreTraits;
	ArrayList <Ilot> coords;
	Pont(Ilot ile1, Ilot ile2){
		this.nombreTraits=0;
		coords =new ArrayList <Ilot>();
		coords.add(ile1);
		coords.add(ile2);
		ile1.addPont(this);
		ile2.addPont(this);
	}
	public int getNbTraits(){
		return this.nombreTraits;
	}
	public void incrementer(){
		if(this.nombreTraits == 2)
			this.nombreTraits=0;
		else
			this.nombreTraits++;
	}

	public int tailleTrait(){
		return (int) Math.sqrt(Math.pow((coords.get(1).getPosX()-coords.get(2).getPosX()),2)+Math.pow((coords.get(1).getPosY()-coords.get(2).getPosY()),2));
	}
	public Ilot voisin(Ilot a){
		if(coords.get(0)==a){
			return coords.get(1);
		}
		return coords.get(0);
	}

}
