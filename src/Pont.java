class Pont{

    private int nbTraits;
    private int coords[];  //TODO Sera une liste de deux Ilot


    /**
     * 
     * @param a Premier ilot auquel le pont est relié
     * @param b Deuxième ilot
     */
    Pont(int a, int b){
        nbTraits = 0;
        
        //TODO changer les int en Ilot

        coords[1] = a;
        coords[2] = b;

    }

    /**
     * Ajoute un trait sur le pont
     * Si le pont a deux traits, il repasse à 0.
     * @return Le nombre de traits après modification
     * @author Camille Remoué
     */
    public int incrementer(){
        
        nbTraits = (nbTraits + 1) % 3;
        return nbTraits;
    }






    /**
     * Ajoute un trait sur le pont
     * Si le pont a deux traits, il repasse à 0.
     * @return Le nombre de traits après modification
     * @author Camille Remoué
     */
    public int decrementer(){
        
        nbTraits = (nbTraits + 1) % 3;
        return nbTraits;
    }

    public int getCoords1(){
        return coords[1];
    }

    public int getCoords2(){
        return coords[2];
    }
}