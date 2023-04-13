package com.monappli.handlers;

import java.io.File;
import java.util.ArrayList;

import com.monappli.Score;
import com.monappli.hashiScene.GameScene;
import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class WinHandler extends DynamicEventHandler {

    private String curLvl;

    private Score score;

    @FXML
    private Label scores;










    public WinHandler(Pane parent, String curLvl,Score score){
        super(parent);
        this.curLvl=curLvl;
        this.score = score;

    }

    /**
     * Action performed when clicking on "Retour au Menu".
     * @throws Exception
     */
    public void menuClicked() throws Exception{
        MainPanel main= new MainPanel(this.getParentPane());
        main.pasteAndHandle("/view/main_menu.fxml", new MainMenuEventHandler((Pane)this.getParentPane()));
    }

    /**
     * Action performed when clicking on "Prochain niveau" Button. If there is no next level, brings back to the main menu
     * @throws Exception
     */
    public void lvlClicked() throws Exception{
        String nextLvl = getNextLvl();
        if (nextLvl==null){
            menuClicked();
        }
        else{
            //Creates a new game grid and the graphic interface
            GameScene game = new GameScene(this.getParentPane());
            try {
                game.pasteAndHandle("/view/gameLayout.fxml", new GameHandler(this.getParentPane()),nextLvl+".niv" );
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            //Get the level button above the game grid
            Button lvlButton = (Button)game.getCurPane().lookup("#levelButton");
            //Change the label to the level assigned name
            lvlButton.setText( nextLvl);
        }
    }


    /**
     * Returns the string for the next level
     * @return the <code>String</code> representing the next Level
     */
    public String getNextLvl(){
        int diff= Integer.parseInt(this.curLvl.replaceAll("-(\\d)*", ""));
        int niv=Integer.parseInt(this.curLvl.replaceAll("(\\d)*-", ""));
        System.out.print(niv + " " + diff);
        if (lvlExists(Integer.toString(diff) + "-" + Integer.toString(niv+1)))
            return Integer.toString(diff) + "-" + Integer.toString(niv+1);
        else if (lvlExists(Integer.toString(diff+1) + "-1"))
            return Integer.toString(diff+1) + "-1";
        return null;
    }

    /**
     * Verifies if a level exists in the niveaux folder
     * @param lvl said level
     * @return <code>true</code> if it exists, <code>false</code> otherwise.
     */
    public boolean lvlExists(String lvl){
        File dossier=new File("src/main/java/com/monappli/niveaux");
        ArrayList<String> search= new ArrayList<String>();

        for (String e : dossier.list()){
            search.add(e);
        }
        return search.contains(lvl + ".niv" );
    }

  public Score getScore() {
    return score;
  }

  public void setScore(Score score) {
    this.score = score;

  }

  public void displayScore(){
    System.out.println(score);
    System.out.println(scores);
    scores.setText("avec un score de : " + Integer.toString(score.getValue()));
  }



}
