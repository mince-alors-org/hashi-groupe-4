package com.monappli.handlers;

import javafx.scene.layout.Pane;


/**
 * Interface that helps gathering all the event handlers
 * @author Ambre Collard
 */
public interface Handler { 
    public Pane getCurPane();
    public void setCurPane(Pane curPane);
    public Pane getParentPane();
}
