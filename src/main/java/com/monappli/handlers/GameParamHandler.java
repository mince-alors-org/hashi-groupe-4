package com.monappli.handlers;

import java.util.ArrayList;

import com.monappli.Grille;
import com.monappli.Ilot;

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

    private Grille grille;

    public GameParamHandler(Pane parent, Grille grille){
        super(parent);
        this.grille=grille;
    }

    /**
     * function the same as the super class except all isle also changes their style
     */
    @Override
    public void changeAll() throws Exception{
        super.changeAll();
        grille.setAllIsleStyle();
    }
}
