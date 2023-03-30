package com.monappli.handlers;


import javafx.scene.layout.Pane;

/**
 * This class is the core of our different event handlers
 * @see Handler
 * @author Matthis Collard
 */

public class DynamicEventHandler implements Handler{
    /**
     * Parent pane of the current handled Pane
     */
    private Pane parentPane;

    /**
     * Current handled pane
     */
    private Pane curPane;

    /**
     * Initialization of the DynamicEventHandler
     * @param parentPane
     */
    public DynamicEventHandler(Pane parentPane){
        this.parentPane=parentPane;
    }

    /**
     * Get the parent pane
     * @return parentPane
     * @see DynamicEventHandler#parentPane
     */
    public Pane getParentPane(){
        return parentPane;
    }

    /**
     * Get the current pane
     * @return curPane
     * @see DynamicEventHandler#curPane
     */
    public Pane getCurPane(){
        return curPane;
    }

    /**
     * Set the pane to be handled
     * @param curPane Pane handled
     * @see DynamicEventHandler#curPane
     */
    public void setCurPane(Pane curPane){
        this.curPane=curPane;
    }
    
}