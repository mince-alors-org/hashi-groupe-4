package com.monappli.handlers;

import com.monappli.*;

import com.monappli.Aide;
import java.io.IOException;

import com.monappli.Grille;
import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.PopUp;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
/**
 * In game handler
 * @author Ambre Collard
 */
public class GameHandler extends TutoGameH {



    private int cptActivationAide;


    private String lvlNum;





    private Grille grille;





    @FXML
    private Button chrono;



    private Chrono chronometre;
    /**
     * Initialization of GameHandler
     * @param parent parent pane of the currently handled pane
     */
    public GameHandler(Pane parent){
        super(parent);
        cptActivationAide = 0;
        lvlNum = "";



    }

    public void lvlTitleClicked() throws Exception{
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(game.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
        this.getSave().sauverNiveau(this.getLvlNum(), chronometre.getTime());


        /*ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/java/com/monappli/save_move.txt"));
        double result = ois.readDouble();
        ois.close();
        System.out.println("RESSSULT " + result);*/




    }


    @Override
    public void paramClicked() throws Exception{
        if (backGround.lookup("#pop") == null){
            PopUp pop = new PopUp(this.getCurPane());
            GameParamHandler paramH= new GameParamHandler(this.backGround, this.getGrille() );
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
        }
    }

    public void setChrono(){
      File save = new File(this.getLvlNum());
      if (save.isFile()){
        chronometre = new Chrono(chrono,SauvegardeGrille.chrono_time);
        chronometre.getTimeDisplay().setText(String.valueOf(chronometre.getTime()));
      }
      else {
        chronometre = new Chrono(chrono);
      }

    }

  public String getLvlNum() {
    return lvlNum;
  }

  public void setLvlNum(String lvlNum) {
    this.lvlNum = lvlNum;
  }

  public Chrono getChronometre() {
    return chronometre;
  }

  public void chronoStart(){
    System.out.println("oui");
    chrono.setDisable(true);
    chronometre.start();
    }

  @Override
  public void quitClicked() {
      //this.getSave().actualiserFichier("src/test/java/com/monappli/save_move.txt",chronometre.getTime());
    try {
      //this.getSave().effacerFichier();
      //this.getSave().actualiserFichier("src/test/java/com/monappli/save_move.txt",chronometre.getTime());
      this.getSave().sauverNiveau(this.getLvlNum(), chronometre.getTime());

      System.out.println(Hashi.joueur);



    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    super.quitClicked();
  }
}