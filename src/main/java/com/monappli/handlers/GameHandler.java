package com.monappli.handlers;

import com.monappli.hashiScene.PopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameHandler extends DynamicEventHandler {
    @FXML
    private Pane gameBG;

    @FXML
    private Button paramButton; 

    @FXML
    private Button powerButton;

    @FXML
    private Button restButton;

    @FXML
    private Button helpButton;


    public GameHandler(Pane parent){
        super(parent);
    }

    public void quitClicked(){
        Stage stage = (Stage) powerButton.getScene().getWindow();
        stage.close();
        System.out.println("Je quitte");
    }

    public void paramClicked() throws Exception{
        if (gameBG.lookup("#pop") == null){
            PopUp pop = new PopUp(this.getCurPane());
            ParamHandler paramH= new ParamHandler(this.gameBG);
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
            System.out.println("Je suis Paramètre");
        }
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
}
