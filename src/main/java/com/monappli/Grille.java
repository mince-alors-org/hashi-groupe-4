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

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
    private GridPane grid;

    private static Pattern pontsDetection = Pattern.compile("[A-Z],?[0-9](?=(\\)])|(\\),))");

    /**
     * Initialisation de la grille
     * @author nmention
     * @param nomF nom du fichier à lire pour creer la grille
     */
    public Grille(String nomF, Pane parent, Canvas fond) {
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
            //Pattern tailleP = Pattern.compile("[0-9]+ [0-9]+");
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



              Ilot a = new Ilot(xa,ya,fond);

              Ilot b = new Ilot(xb,yb,fond);
              if (listeIlot.isEmpty()){
                listeIlot.add(a);
                listeIlot.add(b);
              }

              else  {
                if (!listeIlot.contains(a)){
                  listeIlot.add(a);
                }

                if (!listeIlot.contains(b)){
                  listeIlot.add(b);
                }
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

            largeur= calculateHeight();
            longueur= calculateWidth();

            grid = initGrid();
            parent.getChildren().add(grid);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    public GridPane initGrid(){
      GridPane grid= new GridPane();

      for(int i=0; i<largeur; i++)
        grid.getColumnConstraints().add(new ColumnConstraints(1.0*parent.getPrefWidth() / (largeur)));
      
      for(int i=0; i<longueur;i++)
        grid.getRowConstraints().add(new RowConstraints(1.0*parent.getPrefHeight() / (longueur)));

      for(Ilot ilot : this.listeIlot ){

        GridPane.setHalignment(ilot, HPos.CENTER);
        GridPane.setValignment(ilot, VPos.CENTER);

        ilot.setText(Integer.toString(ilot.getValeur()));

        ilot.setPrefSize(
              (grid.getColumnConstraints().get(ilot.getPosX()).getPrefWidth()) /1.3, 
              (grid.getRowConstraints().get(ilot.getPosY()).getPrefHeight()) /1.3 
        );
        
        ilot.getStyleClass().add("gameIsle");
         ilot.setStyleParam(longueur, largeur);

        grid.add(ilot, ilot.getPosX(), ilot.getPosY(),1,1);
        ilot.setOnAction(
                      new EventHandler<ActionEvent>() {
                      @Override public void handle(ActionEvent e) {
                        ilot.setActive(!ilot.getActive());
                      }
                      }
        );
        

      }


      return grid;
    }


    public boolean contientMemeIlot(List<Ilot> ilots, Ilot ilot){
      for (Ilot i : ilots){
        if (i.equals(ilot)){
          return true;
        }

      }
      return false;
    }

    public int calculateWidth(){
      int max=-1;
      for (Ilot ilot : listeIlot){
        max = ilot.getPosX() > max ? ilot.getPosX() : max; 
      }
      return max+1;
    }

    public int calculateHeight(){
      int max=-1;
      for (Ilot ilot : listeIlot){
        max = ilot.getPosY() > max ? ilot.getPosY() : max; 
      }
      return max+1;
    }

    public int getLongeur(){
      return longueur;
    }

    public int getLargeur(){
      return largeur;
    }

    public List<Ilot> getIlots(){
      return listeIlot;
    }

    public GridPane getGrid(){
      return grid;
    }

    public Pane getParentPane(){
      return parent;
    }
}
