package com.monappli.handlers;

import com.monappli.Grille;

import javafx.scene.layout.Pane;

public class GameParamHandler extends ParamHandler {
    
    public GameParamHandler(Pane parent){
        super(parent);
        this.setCurPane(this.getParamPane());
    }

    @Override
    public void changeAll() throws Exception{
        super.changeAll();
        Grille.setAllIsleStyle();
    }
}
