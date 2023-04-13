package com.monappli.handlers;

import javafx.stage.Popup;

public class PopUpEH{
    private Popup pp;
    PopUpEH(Popup pp){
        this.pp=pp;
    }
    public void exit(){
        this.pp.hide();
    }
}
