package com.monappli;

import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;

/**
 * Cette classe permet de représenter une Grille
 * 
 * @author Morgane Pechon
 */
public class Grille {
    /**
     * Taille de la grille
     */
    private int longueur;
    private int largeur;
    private ArrayList<Ilot> listeIlot;

    /**
     * Initialisation de la grille
     */
    Grille(String nomF) {
        int x = 0, y = 0;
        // Le fichier d'entrée
        File file = new File("file.txt");
        // Créer l'objet File Reader
        FileReader fr = new FileReader(file);
        // Créer l'objet BufferedReader
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            // ajoute la ligne au buffer
            sb.append(line);
            sb.append("\n");
        }
        fr.close();

    }

}