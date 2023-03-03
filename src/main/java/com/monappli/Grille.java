package com.monappli;

import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private List<Ilot> listeIlot;

    /**
     * Initialisation de la grille
     */
    Grille(String nomF) {
        listeIlot = new ArrayList<>();
        // Le fichier d'entrée
        File file = new File("src/main/java/com/monappli/niveaux/" + nomF);
        // Créer l'objet File Reader
        FileReader fr = null;
        try {
            fr = new FileReader(file);
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
            System.out.println(sb);
            Pattern ilotsDetection = Pattern.compile("[A-Z],?[1-9](?=\\))");
            Matcher m = ilotsDetection.matcher(sb);

            while (m.find()){
                String s = m.group();
                String[] values = s.split(",");
                System.out.println(values[0]);
                System.out.println(values[1]);
                Ilot ilot = new Ilot(values[0],Integer.parseInt(values[1]));
                System.out.println();
                System.out.println(ilot);


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}