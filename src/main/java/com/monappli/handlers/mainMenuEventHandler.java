package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Handler of the main menu
 * @see DynamicEventHandler 
 * @author Collard Ambre
 */
public class mainMenuEventHandler extends  DynamicEventHandler{


    /**
     * Initialization of mainMenuEventHandler
     * @param parentPane 
     */
    public mainMenuEventHandler(Pane parentPane){
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
        MainPanel tuto= new MainPanel(this.getParentPane());
        
        
        tuto.pasteAndHandle("/view/tuto.fxml", new TutorialEventHandler(this.getParentPane()));
    }

    /**
     * 
     */
    public void challClicked() {
    }

}
