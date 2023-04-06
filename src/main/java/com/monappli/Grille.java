package com.monappli;

import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.monappli.handlers.WinHandler;
import com.monappli.hashiScene.PopUp;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 * Cette classe permet de représenter une Grille
 *
 * @author Morgane Pechon et Ambre Collard
 */
public class Grille {
    /**
     * Taille de la grille
     */
    public static int longueur;
    public static int largeur;
    public List<Ilot> listeIlot;
    private Pane gridPlace;
    private GridPane grid;
    private Canvas fond;
    private Pane parent;
    private boolean graphic;

    /**
     * Initialisation de la grille
     * @author Morgane Penchon
     * @param nomF nom du fichier à lire pour creer la grille
     */

    public Grille(String nomF, boolean graphic){
      this.graphic= graphic;
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



             Ilot a = new Ilot(xa,ya,graphic);

             Ilot b = new Ilot(xb,yb,graphic);
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
             if(nbTraits!=0){
              new Pont(listeIlot.get(listeIlot.indexOf(a)),listeIlot.get(listeIlot.indexOf(b)),nbTraits);
             }

           }
           largeur= calculateHeight();
           longueur= calculateWidth();

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    public Grille(String nomF,boolean graphic, Pane gridPlace, Canvas canvas, Pane bgParent) {
      this(nomF, graphic);
      this.gridPlace= gridPlace;
      this.fond=canvas;
      this.parent= bgParent;

      if(this.graphic){
        grid = initGrid();
        gridPlace.getChildren().add(grid);
        for (Ilot i :this.getIlots()) {
            i.setCanvasX((1.0*gridPlace.getPrefWidth() / (largeur)) * (i.getPosX()+0.5));
            i.setCanvasY((1.0*gridPlace.getPrefHeight() / (longueur)) * (i.getPosY()+0.5));
        }
      }
    }

    public Grille(String nomF, Pane gridPlace, Canvas canvas, Pane bgParent) {
      this(nomF, true, gridPlace, canvas, bgParent);
    }

    public void remiseZero(){
      for(Ilot i: this.listeIlot){
        i.remiseZero(this.fond);
      }
    }


    public Canvas getCanvas(){
      return fond;
    }

    /**
     * Creates a grid from a list of isle and set their onAction behaviors
     * @see Grille#listeIlot
     * @see Ilot
     * @return <code>GridPane</code> the playing grid, with isle at their places
     * @author Ambre Collard
     */
    public GridPane initGrid(){
      GridPane grid= new GridPane();

      //Sets sizes of columns from size of pane to be added on
      for(int i=0; i<largeur; i++){
        grid.getColumnConstraints().add(new ColumnConstraints(1.0*gridPlace.getPrefWidth() / (largeur)));
      }

      //Sets sizes of rows from size of pane to be added on
      for(int i=0; i<longueur;i++)
        grid.getRowConstraints().add(new RowConstraints(1.0*gridPlace.getPrefHeight() / (longueur)));


      for(Ilot ilot : listeIlot ){
        //Set the isle at the center
        GridPane.setHalignment(ilot.getBtn(), HPos.CENTER);
        GridPane.setValignment(ilot.getBtn(), VPos.CENTER);

        ilot.getBtn().setText(Integer.toString(ilot.getValeur()));
        //Set the size of the isle to ~0.77 the size of the cell (too big if not)
        ilot.getBtn().setPrefSize(
              (grid.getColumnConstraints().get(ilot.getPosX()).getPrefWidth()) /1.3,
              (grid.getRowConstraints().get(ilot.getPosY()).getPrefHeight()) /1.3
        );

        //Set style of the ilse from Parametre
        ilot.setStyleParam();

        //Set Action
        //During this part, setActive and change active are manly for graphic purposes
        //IleAct is the current active isle and if there is already one, we want to make a bridge between isleAct and ilot
        ilot.getBtn().setOnAction(e -> {
          ilotOnAction(ilot);
        });

        //Add the isle to the grid
        grid.add(ilot.getBtn(), ilot.getPosX(), ilot.getPosY(),1,1);
        ilot.setActive(false);

      }


      return grid;
    }

    public void ilotOnAction(Ilot ilot){
      if (this.getGridPane().lookup("#pop") == null){
        unsetReds();
        Ilot ileAct = this.getIlotActif() ;
        if (ileAct != null){
          //If the active and clicked isle are neighbours
          if (this.sontVoisin(ileAct, ilot) ){

            //Get what could be the bridge
            Pont pont = ileAct.liaisonP(ilot);
            //If it doesn't cross another bridge
            if (!this.croisePont(pont)){
              pont.incrementer();
              pont.affiche(fond);

              ileAct.setActive(false);
              ilot.setActive(false);
            }

            else {
              ileAct.setActive(false);
              ilot.setActive(false);
              ilot.setRed(true);
              ileAct.setRed(true);
            }
          }
        }
        else
          ilot.setActive(!(ilot.getActive()));

        if (ilot.nbPont() > ilot.getValeur()){
          ilot.setRed(true);
        }

        if(ileAct == ilot || ileAct == null)
          changeActive(ilot);

        if (ileAct != null && ileAct.nbPont() > ileAct.getValeur()){
          ileAct.setRed(true);
        }
        if (this.isWin()){
          PopUp win = new PopUp(this.parent);
          try{
            win.pasteAndHandle("/view/winLayout.fxml", new WinHandler(this.parent));
          }
          catch (Exception ex){
            ex.printStackTrace();
          }
        }
      }
    }


    public boolean contientMemeIlot(List<Ilot> ilots, Ilot ilot){
      for (Ilot i : ilots){
        if (i.equals(ilot)){
          return true;
        }

      }
      return false;
    }

    /**
     * Calculate width of the isle grid
     * @return <code>int</code> width of the grid
     * @author Ambre Collard
     */
    public int calculateWidth(){
      int max=-1;
      for (Ilot ilot : listeIlot){
        max = ilot.getPosX() > max ? ilot.getPosX() : max;
      }
      return max+1;
    }

    /**
     * Calculate width of the isle grid
     * @return height of the grid
     * @author Ambre Collard
     */
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
     * @return Un <code>ArrayList</code> de Pont
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

    public List<Ilot> getIlots(){
      return listeIlot;
    }

    public GridPane getGameGrid(){
      return grid;
    }

    public Pane getGridPane(){
      return gridPlace;
    }

    /**
     * Sets the style of the isle thanks to Parametre
     * @see Parametre
     * @author Ambre Collard
     */
    public void setAllIsleStyle(){
      for (Ilot i : listeIlot){
        i.setStyleParam();
      }
    }

    public void setBridgeStyle(){
      GraphicsContext gc = getCanvas().getGraphicsContext2D();
      gc.setFill(Parametre.getCouleur_fond());
      gc.clearRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
      for (Pont p : listePontExistant()){
        p.affiche(fond);
      }
    }

  public void setIlots(List<Ilot> list )
    {
      listeIlot = list ;
    }

    /**
     * Returns the the currently in game active isle
     * @return <code>Ilot</code> the currently in game active isle
     * @author Ambre Collard
     */
    public Ilot getIlotActif(){
      for (Ilot i : listeIlot){
        if (i.getActive())
          return i;
      }
      return null;
    }

    /**
     * Verifies if two isle are neighbours
     * @param il1
     * @param il2
     * @return <code>true</code> if the two isle are neighbours, <code>false</code> if not
     * @author Ambre Collard
     */
    public boolean sontVoisin(Ilot il1, Ilot il2){
      if (il1 == il2){
        return false;
      }
      if (il1.estAligneVerticalement(il2)){
        for(Ilot i : listeIlot){
          if (
              i.getPosX() == il1.getPosX() &&
              i.getPosY() > (il1.getPosY() < il2.getPosY() ? il1 : il2).getPosY() &&
              i.getPosY() < (il1.getPosY() > il2.getPosY() ? il1 : il2).getPosY()){
            return false;
          }
        }
        return true;
      }
      else if (il1.estAligneHorizontalement(il2)){
        for (Ilot i : listeIlot){
          if (
              i.getPosY() == il1.getPosY() &&
              i.getPosX() > (il1.getPosX() < il2.getPosX() ? il1 : il2).getPosX() &&
              i.getPosX() < (il1.getPosX() > il2.getPosX() ? il1 : il2).getPosX()){
                return false;
          }
        }
        return true;
      }
      return false;
    }

    /**
     * For all bridges, verify if the bridge in parameters doesn't cross any of the already existing bridges
     * @param pont Bridge to verify
     * @return <code>true</code> if the bridge crosses another one, <code>false</code> if not
     * @author Ambre Collard
     */
    public boolean croisePont(Pont pont){
      for (Ilot i : listeIlot){
        for (Pont p : i.getPonts()){
          if (pont.croise(p))
            return true;
        }
      }
      return false;
    }

    /**
     * Sets a new active isle while deactivate the currently active one
     * @param i <code>Ilot</code> to change to active
     * @author Collard Ambre
     */
    public void changeActive(Ilot i){
      if (this.getIlotActif() != null)
        this.getIlotActif().setActive(false);
      i.setActive(true);
    }

    /**
     * Verify if the grid is completed, resulting in the player to win.
     * @return <code>true</code> if the grid is completed, <code>false</code> otherwise.
     */
    public boolean isWin(){
      for(Ilot ilot : listeIlot){
        System.out.println(ilot.equalsSol());
        if(!ilot.equalsSol())
          return false;
      }
      return true;
    }

    /**
     * Reset the ilses' border to their active border state if they are currently red bordered.
     * @see Ilot#setRed
     */
    public void unsetReds(){
      for(Ilot ilot : listeIlot){
        if (ilot.nbPont() < ilot.getValeur())
        ilot.setRed(false);
      }
    }

}
