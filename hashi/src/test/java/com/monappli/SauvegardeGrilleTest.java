package com.monappli;

public class SauvegardeGrilleTest {
    public static void main(String[] args) {

        SauvegardeGrille s = new SauvegardeGrille();

        Ilot ilot1 = new Ilot(1, 2, 4);
        Ilot ilot2 = new Ilot(6, 7, 8);
        Ilot ilot3 = new Ilot(0, 0, 0);

        Pont p1 = new Pont(ilot1, ilot2);
        Pont p2 = new Pont(ilot1, ilot3);

        s.ajoutCoup(p1);
        s.ajoutCoup(p2);

        s.actualiserFichier();

    }
    

    
}
