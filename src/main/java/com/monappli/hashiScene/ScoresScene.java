package com.monappli.hashiScene;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Joueur;
import com.monappli.SauvegardeGrille;
import com.monappli.SauvegardeScore;
import com.monappli.handlers.DynamicEventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ScoresScene extends MainPanel{

  /**
   * Initialization of MainPanel
   *
   * @param parent
   * @author Collard Ambre
   */

  private Pane pane;



  private SauvegardeScore sauvegardeScore;
  public ScoresScene(Pane parent) throws Exception {
    super(parent);
    this.sauvegardeScore = new SauvegardeScore();
    /*pane = (Pane)getCurPane().lookup("mainPane");
    VBox vbox = initScores();*/

  }


  @Override
  public <H extends DynamicEventHandler> void pasteAndHandle(String name, H handler) throws Exception {
    super.pasteAndHandle(name, handler);
  }



  public VBox initScores() throws Exception {



    VBox vBox = new VBox();

    Label label = new Label();



    //setStyleHBox(hbox);

    for (Joueur joueur : BaseDonneeJoueur.getAllPlayers()){
      HBox hbox = new HBox();

      String emplacement_score = BaseDonneeJoueur.getJoueurEmplacementScores(joueur);
      SauvegardeScore.readScore(emplacement_score);
      System.out.println(emplacement_score);
      label.setText(Integer.toString(SauvegardeScore.currentScore.getValue()));

    }
    pane.getChildren().add(vBox);


    return vBox;



  }


  public void setStyleHBox(HBox h) {
    h.setAlignment(Pos.CENTER);
    h.setSpacing(20);
    try{
      h.setPrefSize(this.pane.getPrefWidth(), this.pane.getPrefHeight()/BaseDonneeJoueur.getNumberPlayers());
    }
    catch(Exception e){
      e.printStackTrace();
    }
    h.setMaxHeight(30);
  }







}
