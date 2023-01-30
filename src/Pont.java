class Pont{

    private int nbTraits;
    private int [] coords = new int[2];  //TODO Sera une liste de deux Ilot

    /**
     * 
     * @param a Premier ilot auquel le pont est relié
     * @param b Deuxième ilot
     */
    Pont(int a, int b){
        nbTraits = 0;
        
        //TODO changer les int en Ilot

        this.coords[0] = a;
        this.coords[1] = b;

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


}