package com.monappli.handlers;


import javafx.scene.layout.Pane;

public class DynamicEventHandler implements Handler{
    private Pane parentPane;
    private Pane curPane;

    public DynamicEventHandler(Pane parentPane){
        this.parentPane=parentPane;
    }


    public Pane getParentPane(){
        return parentPane;
    }

    public Pane getCurPane(){
        return curPane;
    }

    public void setCurPane(Pane curPane){
        this.curPane=curPane;
    }
    
}