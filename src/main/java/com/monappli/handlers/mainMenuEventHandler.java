package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Handler of the main menu
 * @see DynamicEventHandler 
 * @author Collard Matthis
 */
public class mainMenuEventHandler extends  DynamicEventHandler{

    /**
     * Play Button
     */
    @FXML
    private Button playButton;

    /**
     * Current Pane of the menu
     */
    @FXML
    private Pane backGround;

    /**
     * Tutorial Button
     */
    @FXML
    private Button tutoButton;

    /**
     * Challenge Button
     */
    @FXML
    private Button challButton;

    /**
     * Initialization of mainMenuEventHandler
     * @param parentPane 
     */
    public mainMenuEventHandler(Pane parentPane){
        super(parentPane);
        setCurPane(backGround);
    }

    /**
     * Action when playButton is clicked. Creates a LevelScene grid and changes the handler to LevelSelectHandler
     * @throws Exception if the levelScene can't load
     * @see LevelScene
     * @see LevelSelectHandler
     * @author Matthis Collard
     */
    public void playClicked() throws Exception {
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(backGround));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= LevelScene.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }

    /**
     * @throws Exception
     */
    public void tutoClicked() throws Exception {
        MainPanel tuto= new MainPanel(this.getParentPane());
        
        
        tuto.pasteAndHandle("/view/tuto.fxml", new TutorialEventHandler(this.getParentPane()));
        System.out.println("Je suis tuto");
    }

    /**
     * 
     */
    public void challClicked() {
        System.out.println("Je suis defi");
    }

}
