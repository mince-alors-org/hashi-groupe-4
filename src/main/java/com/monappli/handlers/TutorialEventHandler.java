package com.monappli.handlers;

import com.monappli.Grille;
import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.PopUp;
import com.monappli.hashiScene.ResolScene;
import com.monappli.hashiScene.TutoScene;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * In game handler 
 * @author Ambre Collard
 */
public class TutorialEventHandler extends GameHandler{

    /**
     * Initialization of GameHandler
     * @param parent parent pane of the currently handled pane
     */
    public TutorialEventHandler(Pane parent){
        super(parent);
    }
    public void lvlTitleClicked() throws Exception{
        TutoScene game= new TutoScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(game.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }
}
