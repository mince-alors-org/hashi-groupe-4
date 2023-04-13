package com.monappli.handlers;

import com.monappli.Aide;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class PopUpEH{
    @FXML
    private Text typeErreur = new Text();
    private Popup pp;
    PopUpEH(Popup pp){
        this.pp=pp;
    }
    public void exit(){
        this.pp.hide();
    }   

    public void setText(String text) {
        this.typeErreur.setText(text);
    }
}
