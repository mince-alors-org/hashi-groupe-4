package com.monappli.handlers;

import com.monappli.Grille;

import javafx.scene.layout.Pane;

/**
 * Parameter event handler espacially made for in game changes
 * @author Ambre Collard
 */
public class GameParamHandler extends ParamHandler {
    
    /**
     * Initialization of GameParamHandler
     * @param parent parent pane of the currently handled pane
     */
    public GameParamHandler(Pane parent){
        super(parent);
    }

    /**
     * function the same as the super class except all isle also changes their style
     */
    @Override
    public void changeAll() throws Exception{
        super.changeAll();
        Grille.setAllIsleStyle();
    }
}
