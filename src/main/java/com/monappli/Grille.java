package com.monappli;

import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.monappli.handlers.GameHandler;
import com.monappli.handlers.TutoGameH;
import com.monappli.handlers.WinHandler;
import com.monappli.hashiScene.PopUp;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
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
    private SauvegardeGrille sauvegarde;
    private boolean undoOuRedo = false;
    private String fichier_sauvegarde;
    private Pont dernierPont;
    private int nbTraitAvantModif;
    private String nomF;

  public GameHandler getGh() {
    return gh;
  }
  public  void setGh(GameHandler gh) {
    this.gh = gh;
  }



  private GameHandler gh;



  /**
     * Initialisation de la grille
     * @author Morgane Penchon
     * @param nomF nom du fichier à lire pour creer la grille
     */

    public Grille(String nomF, boolean graphic){
      this.nomF= nomF;
      this.graphic= graphic;
      this.sauvegarde = new SauvegardeGrille();

      this.fichier_sauvegarde = "src/main/resources/profiles/" + Hashi.joueur.getnom() +"/" + this.getLvlName() +".niv";
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


    public Grille(String nomF,boolean graphic, Pane gridPlace, Canvas canvas, Pane bgParent) throws ClassNotFoundException, IOException {
      
      this(nomF, graphic);
      this.gridPlace= gridPlace;
      this.fond=canvas;
      this.parent= bgParent;
      if(this.graphic){
        grid = initGrid();
        /**
         * On charge les ponts déjà initié depuis la sauvegarde
         * Il est vérifé dans la méthode si le fichier de sauvegarde existe
         */
        //chargerSauvegarde();
        gridPlace.getChildren().add(grid);
        for (Ilot i :this.getIlots()) {
            i.setCanvasX((1.0*gridPlace.getPrefWidth() / (largeur)) * (i.getPosX()+0.5));
            i.setCanvasY((1.0*gridPlace.getPrefHeight() / (longueur)) * (i.getPosY()+0.5));
        }
      }
    }

    public Grille(String nomF, Pane gridPlace, Canvas canvas, Pane bgParent) throws ClassNotFoundException, IOException {
      this(nomF, true, gridPlace, canvas, bgParent);
    }

    public void remiseZero(){
      for(Ilot i: this.listeIlot){
        i.remiseZero(this.fond);
      }
      sauvegarde.viderPiles();
      sauvegarde.actualiserFichier(fichier_sauvegarde);
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
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public GridPane initGrid() throws ClassNotFoundException, IOException{
      GridPane grid= new GridPane();

      //Sets sizes of columns from size of pane to be added on
      for(int i=0; i<largeur; i++){
        grid.getColumnConstraints().add(new ColumnConstraints(1.0*gridPlace.getPrefWidth() / (longueur)));
      }
      
      //Sets sizes of rows from size of pane to be added on
      for(int i=0; i<longueur;i++)
        grid.getRowConstraints().add(new RowConstraints(1.0*gridPlace.getPrefHeight() / (largeur)));

      
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
    /**
     * Charge une sauvegarde du jeu à partir d'un fichier donné
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void chargerSauvegarde() throws ClassNotFoundException, IOException {


          sauvegarde.chargerFichier2(fichier_sauvegarde);
          /**
           * On itère sur un ilot de la listeIlot et on vérifie ses voisins pour former un pont
           */
      //for (Pont pont : sauvegarde.getPileCoups())
      //  System.out.println(pont);
      for (Ilot ilot : this.listeIlot) {
        for (Pont pont : sauvegarde.getPileCoups()) {

          for(Ilot ilot2: this.listeIlot){
            if(this.sontVoisin(ilot2, ilot) && !ilot.equals(ilot2)){
              Pont pontVoisin = ilot.liaisonP(ilot2);

              /**
               * En comparant les 2 ilots voisins aux ilots composant un pont depuis la sauvegarde, on affiche le pont et on met le nombre de trait du pont de listeIlot à celui du pont de la sauvegarde
               */
              if (ilot.equals(pont.getIle1()) && ilot2.equals(pont.getIle2())) {
                pontVoisin.setNombreTraits(pont.getNbTraits());
                pontVoisin.affiche(fond);

              }
            }
          }
        }
      }
    }
  

  public void ilotOnAction(Ilot ilot) {
    if (this.getGridPane().lookup("#pop") == null) {
      unsetReds();
      Ilot ileAct = this.getIlotActif();
      for (int i = 0; i < this.getIlots().size(); i++) {
        this.getIlots().get(i).setStyleBase();
      }
      if (ileAct != null) {
        // If the active and clicked isle are neighbours
        if (this.sontVoisin(ileAct, ilot)) {

          // Get what could be the bridge
          Pont pont = ileAct.liaisonP(ilot);

          // If it doesn't cross another bridge
          if (!this.croisePont(pont)) {
            nbTraitAvantModif = pont.getNbTraits();
            pont.incrementer();
            pont.affiche(fond);

            sauvegarde.ajoutCoup(pont);
            sauvegarde.actualiserFichier(fichier_sauvegarde);

            ileAct.setActive(false);
            ilot.setActive(false);
            dernierPont = pont;
          }

          else {
            if (Parametre.isAffichage_depassment_cardinalite()) {
              ileAct.setActive(false);
              ilot.setActive(false);
                ileAct.setRed(true);
                ilot.setRed(true);
              }
            }
          }
          else{
            changeActive(ilot);
          }
        }
        else 
          ilot.setActive(!(ilot.getActive()));

        if(ileAct == ilot){
          ilot.setActive(false);
        } 

        if (Parametre.isAffichage_depassment_cardinalite()){
          if (ilot.nbPont() > ilot.getValeur())
            ilot.setRed(true);
          if(ileAct != null && ileAct.nbPont() > ileAct.getValeur())
            ileAct.setRed(true);
          if (ilot.nbPont() == ilot.getValeur())
            ilot.setBlue(true);
          if(ileAct != null && ileAct.nbPont() == ileAct.getValeur())
            ileAct.setBlue(true);

        }

        if (isWin()){
          PopUp win = new PopUp(this.parent);
          Score score = new Score();
          //gh.getChronometre().halt();
         // double time = gh.getChronometre().getTime();
          //System.out.println("WINNN " + time);
          score.computeValue(200);



          String[] save = this.getFichier_sauvegarde().split("niveaux/[1-9]-[1-9].*");
          String saveScore = save[0] + "scores.txt";

          try {
            SauvegardeScore.createScoreFile(saveScore);
            SauvegardeScore.saveScore(saveScore,score);
            SauvegardeScore.readScore(saveScore);


          } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
          }


          sauvegarde.effacerFichier(this.getFichier_sauvegarde());

          try{
            win.pasteAndHandle("/view/winLayout.fxml", new WinHandler(this.parent, getLvlName(),score));

            Label label1 = (Label) win.getCurPane().lookup("#winScore");

            label1.setText("score : " + score.getValue());





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

    public String getLvlName(){
      return this.nomF.replace("../niveaux/", "").replace(".niv", "");
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

    /**
     * Undo/Annule la dernière action et controle l'affichage sur la grille 
     * Appelle de la méthode dans GameHandler
     */
    public void annulerAction(){
      sauvegarde.getLastPileCoups().setNombreTraits(sauvegarde.getLastPileCoups().getNbTraits()-1);
      sauvegarde.getLastPileCoups().erase(fond);

      sauvegarde.annuler();
      sauvegarde.actualiserFichier(fichier_sauvegarde);
    }

    /**
     * Redo/Rétablir la dernière action et controle l'affichage sur la grille 
     * Appelle de la méthode dans GameHandler
     */
    public void retablirAction(){
      sauvegarde.retablir();
      sauvegarde.getLastPileCoups().setNombreTraits(sauvegarde.getLastPileCoups().getNbTraits()+1);
      sauvegarde.getLastPileCoups().affiche(fond);


      sauvegarde.actualiserFichier(fichier_sauvegarde);
    }
  public String getFichier_sauvegarde() {
    return fichier_sauvegarde;
  }

  public void setFichier_sauvegarde(String fichier_sauvegarde) {
    this.fichier_sauvegarde = fichier_sauvegarde;
  }





    public ArrayList<Pont> getAllValidPont(Ilot ilot){
      ArrayList<Pont> tab = new ArrayList<Pont>();
        for(Ilot search : this.listeIlot){
          if (sontVoisin(ilot,search)){
            Pont p = ilot.liaisonP(search);
            if(!croisePont(p)) {
              tab.add(p);
            }
          }
        }
        return tab;
      }

}