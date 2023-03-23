package com.monappli.handlers;

import javafx.scene.layout.Pane;

public interface Handler { 
    public Pane getCurPane();
    public void setCurPane(Pane curPane);
    public Pane getParentPane();
}
