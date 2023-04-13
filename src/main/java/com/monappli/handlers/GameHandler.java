package com.monappli.handlers;

import java.io.IOException;

import com.monappli.Grille;
import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
/**
 * In game handler 
 * @author Ambre Collard
 */
public class GameHandler extends DynamicEventHandler {

    @FXML
    private Pane gridPlacement;

    private Grille grille;

    //private Popup po;


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
        System.out.println("Je suis Aide");
    }
    public void errClicked(){
        System.out.println("Je detecte 1 erreur");
        Popup po=new Popup();
        po.setX(205);
        po.setY(304);
        Pane newP=null;
        Stage s = (Stage) backGround.getScene().getWindow();
        PopUpEH p= new PopUpEH(po);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popErr.fxml"));
        loader.setController(p);
        //loader.setStyle("background-color: green;");

        try {
            newP = (Pane)loader.load();
            po.getContent().add(newP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        po.show(s);
    }
    public void redoClicked(){
        System.out.println("Je suis redo");
        this.grille.retablirAction();
    }
    public void undoClicked(){
        System.out.println("Je suis undo");
        this.grille.annulerAction();
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
