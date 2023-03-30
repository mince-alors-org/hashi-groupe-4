package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * In game handler 
 * @author Ambre Collard
 */
public class GameHandler extends DynamicEventHandler {

    /**
     * Parameters button
     */
    @FXML
    private Button paramButton; 

    /**
     * Power Button. To quit
     */
    @FXML
    private Button powerButton;

    /**
     * Reset button. To reset the whole grid
     */
    @FXML
    private Button restButton;

    /**
     * Help Button
     */
    @FXML
    private Button helpButton;

    @FXML
    private Button lvlTitle;

    /**
     * Initialization of GameHandler
     * @param parent parent pane of the currently handled pane
     */
    public GameHandler(Pane parent){
        super(parent);
    }

    public void restClicked(){
        System.out.println("Je suis rest");
    }

    public void helpClicked(){
        System.out.println("Je suis Aide");
    }
    public void redoClicked(){
        System.out.println("Je suis redo");
    }
    public void undoClicked(){
        System.out.println("Je suis undo");
    }

    public void lvlTitleClicked() throws Exception{
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane()));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= LevelScene.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }
}
