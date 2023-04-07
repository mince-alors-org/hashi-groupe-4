package com.monappli.handlers;


import com.monappli.Aide;
import com.monappli.Grille;
import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * In game handler 
 * @author Ambre Collard
 */
public class GameHandler extends DynamicEventHandler {

    @FXML
    private Pane gridPlacement;

    private Grille grille;


    /**
     * Initialization of GameHandler
     * @param parent parent pane of the currently handled pane
     */
    public GameHandler(Pane parent){
        super(parent);
    }

    public void setGrille(Grille grille ){
        this.grille=grille;
    }

    public void restClicked(){
        System.out.println("Je suis rest");
        this.grille.remiseZero();
    }

    public void helpClicked(){
        System.out.println(Aide.getTechnique());
    }
    public void errClicked(){
        System.out.println(Aide.checkErreur());
    }
    public void redoClicked(){
        System.out.println("Je suis redo");
    }
    public void undoClicked(){
        System.out.println("Je suis undo");
    }

    public void lvlTitleClicked() throws Exception{
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }

    @Override
    public void paramClicked() throws Exception{
        if (backGround.lookup("#pop") == null){
            PopUp pop = new PopUp(this.getCurPane());
            GameParamHandler paramH= new GameParamHandler(this.backGround, this.grille );
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
        }
    }
}