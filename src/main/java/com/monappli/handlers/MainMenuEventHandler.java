package com.monappli.handlers;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Joueur;
import com.monappli.SauvegardeScore;
import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;
import com.monappli.hashiScene.ProfileScene;
import com.monappli.hashiScene.TutoScene;

import com.monappli.hashiScene.ScoresScene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

/**
 * Handler of the main menu
 * @see DynamicEventHandler 
 * @author Collard Ambre
 */
public class MainMenuEventHandler extends  DynamicEventHandler{


    /**
     * Initialization of MainMenuEventHandler
     * @param parentPane 
     */
    public MainMenuEventHandler(Pane parentPane){
        super(parentPane);
    }

    /**
     * Action when playButton is clicked. Creates a LevelScene grid and changes the handler to LevelSelectHandler
     * @throws Exception if the levelScene can't load
     * @see LevelScene
     * @see LevelSelectHandler
     * @author Ambre Collard
     */
    public void playClicked() throws Exception {
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(game.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent(),new GameHandler(this.getParentPane()));
        select.getChildren().add(selGrid);
        
    }

    /**
     * @throws Exception
     */
    public void tutoClicked() throws Exception {
        TutoScene game= new TutoScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(game.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }

    /**
     * 
     */
    public void challClicked() {
    }

    public void profileClicked(){
        if (backGround.lookup("#pop") == null){
            ProfileScene prof= new ProfileScene(this.getCurPane());
            try{
                prof.pasteAndHandle("/view/profileSelection.fxml", new ProfileHandler(this.getCurPane()));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void rulesClicked(){
        MainPanel rules= new MainPanel(backGround);
        try{
            rules.pasteAndHandle("/view/rulesLayout.fxml", new DynamicEventHandler(backGround));
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void scoresClicked() throws Exception {
      ScoresScene scores = new ScoresScene(this.getParentPane());
      scores.pasteAndHandle("/view/scores.fxml",new DynamicEventHandler(this.getParentPane()));

      ArrayList<Label> labels = new ArrayList<>();
      Label label1 = (Label) scores.getCurPane().lookup("#label1");

      Label label2 = (Label) scores.getCurPane().lookup("#label2");
      Label label3 = (Label) scores.getCurPane().lookup("#label3");
      Label label4 = (Label) scores.getCurPane().lookup("#label4");





      labels.add(label1);
      labels.add(label2);
      labels.add(label3);
      labels.add(label4);




      int cpt = 0;

      for (Joueur joueur : BaseDonneeJoueur.getAllPlayers()){

        String emplacement = BaseDonneeJoueur.getJoueurEmplacementScores(joueur);
        File file = new File(emplacement);
        if (file.isFile()){
          SauvegardeScore.readScore(emplacement);
          labels.get(cpt).setText(joueur.getnom() + " : " + Integer.toString(SauvegardeScore.currentScore.getValue()));
          System.out.println(emplacement);
          cpt ++;
        }





      }






    }


}
