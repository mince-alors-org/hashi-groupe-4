package com.monappli.handlers;

import javafx.scene.layout.Pane;

public class DynamicEventHandler implements Handler{
    private Pane parentPane;

    public DynamicEventHandler(Pane parentPane){
        this.parentPane=parentPane;
    }


    public Pane getParentPane(){
        return parentPane;
    }
}
