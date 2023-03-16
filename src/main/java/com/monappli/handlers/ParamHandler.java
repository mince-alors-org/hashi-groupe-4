package com.monappli.handlers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ParamHandler extends DynamicEventHandler{
    
    @FXML
    private Button backButton;

    public ParamHandler(Pane parent){
        super(parent);
    }

    public void backClicked(){
        System.out.println("Je supprime");
        this.getParentPane().getChildren().remove(this.getParentPane().lookup("#pop"));
    }


}
