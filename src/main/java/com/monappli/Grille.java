package com.monappli;

import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 * Cette classe permet de représenter une Grille
 *
 * @author Morgane Pechon
 */
public class Grille {
    /**
     * Taille de la grille
     */
    public static int longueur;
    public static int largeur;
    public static List<Ilot> listeIlot;
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
           while ((line = br.readLine()) != null) {
               // ajoute la ligne au buffer
               sb.append(line);
               sb.append("\n");
           }
           fr.close();




           Pattern ilotsCoord = Pattern.compile("[0-9] [0-9] [0-9] [0-9] [0-9]");
           Matcher m = ilotsCoord.matcher(sb);

           while(m.find()){

             String s = m.group();

             String[] results = s.split(" ");
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
             new Pont(listeIlot.get(listeIlot.indexOf(a)),listeIlot.get(listeIlot.indexOf(b)),this);
             if(nbTraits!=0){
              new Pont(listeIlot.get(listeIlot.indexOf(a)),listeIlot.get(listeIlot.indexOf(b)),nbTraits,this);
             }

           }
           largeur= calculateHeight();
           longueur= calculateWidth();

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
            grid = initGrid();
            parent.getChildren().add(grid);
            for (Ilot i :this.getIlots()) {
                i.setCanvasX((1.0*parent.getPrefWidth() / (largeur)) * (i.getPosX()+0.5));
                i.setCanvasY((1.0*parent.getPrefHeight() / (longueur)) * (i.getPosY()+0.5));
            }
            System.out.println( "MilieuX :" +(1.0*parent.getPrefWidth() / (largeur)) * (this.getIlots().get(1).getPosX()+0.5)  );
            System.out.println( "MilieuY: " +(1.0*parent.getPrefHeight() / (longueur)) * (this.getIlots().get(1).getPosY()+0.5)  );
            System.out.println("MilieuXC :"+ (this.getIlots().get(1).getCanvasX()));
            System.out.println("MilieuYC :"+(this.getIlots().get(1).getCanvasY()));





    }


    public GridPane initGrid(){
      GridPane grid= new GridPane();

      for(int i=0; i<largeur; i++){
        grid.getColumnConstraints().add(new ColumnConstraints(1.0*parent.getPrefWidth() / (largeur)));
      }
      
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
        
        //ilot.setStyleClass("gameIsle");
        ilot.getStyleClass().add("gameIsle");

        ilot.setStyleParam();
        ilot.setOnAction(e -> {
            ilot.setActive(!(ilot.getActive()));
          }
        );

        grid.add(ilot, ilot.getPosX(), ilot.getPosY(),1,1);
        ilot.setActive(false);

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

    /**
     * Permet d'obtenir une liste de tous les ponts ayant au moins 1 en nb de ponts.
     *
     * @return Un ArrayList de Pont
     */
    public List<Pont> listePontExistant()
    {
      List<Pont> ret = new ArrayList<>();
      for(Ilot i : listeIlot)
      {
        for(Pont p : i.getPonts())
        {
          if(p.getNbTraits() > 0 && !ret.contains(p))
            ret.add(p);
        }
      }
      return ret;
    }

    public int getLongeur(){
      return longueur;
    }

    public static int getLargeur(){
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

<<<<<<< HEAD
    public static void setAllIsleStyle(){
      for (Ilot i : listeIlot){
        i.setStyleParam();
      }
=======
    public void setIlots(List<Ilot> list )
    {
      listeIlot = list ;
>>>>>>> 2e6b83f6fac92b780feddace07bbffdd2b52aed8
  public static void setAllIsleStyle(){
  for (Ilot i : listeIlot){
    i.setStyleParam();
  }

  public void setIlots(List<Ilot> list )
    {
      listeIlot = list ;
    }
}
