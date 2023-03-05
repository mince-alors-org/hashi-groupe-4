package com.monappli;

import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static Pattern pontsDetection = Pattern.compile("[A-Z],?[0-9](?=(\\)])|(\\),))");

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
            Map<String,Ilot> ilots = new HashMap<String,Ilot>();

            Pattern ilotsDetection = Pattern.compile("[A-Z],?[1-9](?=\\)\\[)");
            Matcher m = ilotsDetection.matcher(sb);


            Matcher m2 = Grille.pontsDetection.matcher(sb);

            while (m.find()){
                String s = m.group();
                String[] values = s.split(",");
                ilots.put(values[0],new Ilot(values[0],Integer.parseInt(values[1])));



            }

            m = ilotsDetection.matcher(sb);


            while (m2.find() && m.find()){
                String s = m.group();
                String s2 = m2.group();
                String[] values2 = s2.split(",");


                String[] values = s.split(",");

                Pont pont = new Pont(ilots.get(values[0]),ilots.get(values2[0]));

            }
            for (String key : ilots.keySet()){
                System.out.println(key + " " + ilots.get(key));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}