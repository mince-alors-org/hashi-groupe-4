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
    private Button undoButton;

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
            ParamHandler paramH= new GameParamHandler(this.gameBG);
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
            System.out.println("Je suis Paramètre");
        }
    }

    public void undoClicked(){
        System.out.println("Je suis undo");
    }

    public void helpClicked(){
        System.out.println("Je suis Aide");
    }
}
