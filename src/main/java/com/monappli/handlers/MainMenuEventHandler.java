package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
        GridPane selGrid= game.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
        
    }

    /**
     * @throws Exception
     */
    public void tutoClicked() throws Exception {
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }

    /**
     * 
     */
    public void challClicked() {
    }

}
