package com.monappli;

import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
    private Pane parent;

    private static Pattern pontsDetection = Pattern.compile("[A-Z],?[0-9](?=(\\)])|(\\),))");

    /**
     * Initialisation de la grille
     * @author nmention
     * @param nomF nom du fichier à lire pour creer la grille
     */
    Grille(String nomF, Pane parent) {
        this.parent=parent;
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
            line=br.readLine();
            System.out.println("line = "+line.split(" ")[0]);
            this.largeur=Integer.parseInt(line.split(" ")[0]);
            this.longueur=Integer.parseInt(line.split(" ")[1]);
            while ((line = br.readLine()) != null) {
                // ajoute la ligne au buffer
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            System.out.println(sb);




            Pattern ilotsCoord = Pattern.compile("[0-9] [0-9] [0-9] [0-9] [0-9]");
            Pattern tailleP = Pattern.compile("[0-9]+ [0-9]+");
            Matcher m = ilotsCoord.matcher(sb);
            //Matcher t = tailleP.matcher(sb);
            //System.out.println(t.group());

            while(m.find()){

              String s = m.group();

              String[] results = s.split(" ");
              System.out.println(s);
              int xa = Integer.parseInt(results[0]);
              int ya = Integer.parseInt(results[1]);


              int xb = Integer.parseInt(results[2]);
              int yb = Integer.parseInt(results[3]);

              int nbTraits = Integer.parseInt(results[4]);



              Ilot a = new Ilot(xa,ya);

              Ilot b = new Ilot(xb,yb);
              if (listeIlot.isEmpty()){
                listeIlot.add(a);
                listeIlot.add(b);
              }


              if (!listeIlot.contains(a)){
                listeIlot.add(a);
              }

              if (!listeIlot.contains(b)){
                listeIlot.add(b);
              }
              new Pont(listeIlot.get(listeIlot.indexOf(a)),listeIlot.get(listeIlot.indexOf(b)));
              new Pont(listeIlot.get(listeIlot.indexOf(a)),listeIlot.get(listeIlot.indexOf(b)),nbTraits);

              /*if (!contientMemeIlot(ilots,a)){
                ilots.add(a);

              }
              if (!contientMemeIlot(ilots,b)){
                ilots.add(b);

              }*/







            }

          for (Ilot i: listeIlot){
            System.out.println(i);
          }
           /* Map<String,Ilot> ilots = new HashMap<String,Ilot>();

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
            }*/

          //initGrid(largeur);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void initInterface(){

    }

    public GridPane initGrid(int size){
      GridPane grid= new GridPane();
      for(Ilot ilot : this.listeIlot ){
        grid.add(new Button("oui"), ilot.getPosX(), ilot.getPosY());
      }

      System.out.println(grid);

      return grid;
    }


    public boolean contientMemeIlot(List<Ilot> ilots, Ilot ilot){
      boolean result = false;
      for (Ilot i : ilots){
        if (i.equals(ilot)){
          return true;
        }

      }
      return false;
    }
}
