package com.monappli;

import java.io.IOException;

public class SauvegardeGrilleTest {
    public static void main(String[] args) throws IOException{

        SauvegardeGrille s = new SauvegardeGrille();

        Ilot ilot1 = new Ilot(1, 2, 4);
        Ilot ilot2 = new Ilot(6, 7, 8);
        Ilot ilot3 = new Ilot(0, 0, 0);

        Pont p1 = new Pont(ilot1, ilot2);
        Pont p2 = new Pont(ilot1, ilot3);

        s.ajoutCoup(p1);
        s.ajoutCoup(p2);

        System.out.print("Affichage de la premiere sauvegarde : \n\n");
        
        s.actualiserFichier();

        s.lireFichier();

        System.out.print("Affichage de la deuxieme sauvegarde : \n\n");

        s.annuler();    
        
        s.actualiserFichier();

        s.lireFichier();

        System.out.print("Affichage de la troisieme sauvegarde : \n\n");

        s.retablir();
        s.actualiserFichier();
        s.lireFichier();
    }
}
