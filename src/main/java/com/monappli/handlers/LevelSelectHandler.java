package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LevelSelectHandler extends DynamicEventHandler{
    public static int curDiff;

    @FXML
    private Button paramButton; 

    @FXML
    private Button powerButton;

    @FXML
    private Pane backGround;

    @FXML
    private Button diffButton;

    @FXML
    private Pane selectPane;

    public LevelSelectHandler(Pane parent){
        super(parent);
        curDiff=1;
    }

    public void paramClicked() throws Exception{
        if (backGround.lookup("#pop") == null){
            PopUp pop = new PopUp(backGround);
            ParamHandler paramH= new ParamHandler(backGround);
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
            System.out.println("Je suis Paramètre");
        }
    }

    public void quitClicked() {
        Stage stage = (Stage) powerButton.getScene().getWindow();
        stage.close();
        System.out.println("Je quitte");
    }

    public void diffClicked() throws Exception{
        curDiff= curDiff%LevelScene.nbDiff +1;
        diffButton.setText(intToDiff(curDiff));

        GridPane newGrid= LevelScene.initGrid(LevelScene.countLvl(curDiff), (int)selectPane.getPrefWidth(), (int)selectPane.getPrefHeight(), backGround) ;
        selectPane.getChildren().setAll(newGrid);

    }

    public String intToDiff(int diff){
        switch (diff){
            case (1):
                return "Facile";
            case(2):
                return "Moyen";
            case(3):
                return "Difficile";
            default:
                return "???";
        }
    }


    
}
