package com.monappli.handlers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * In game handler 
 * @author Matthis Collard
 */
public class GameHandler extends DynamicEventHandler {

    /**
     * Current game background pane
     */
    @FXML
    private Pane backGround;

    /**
     * Parameters button
     */
    @FXML
    private Button paramButton; 

    /**
     * Power Button. To quit
     */
    @FXML
    private Button powerButton;

    /**
     * Reset button. To reset the whole grid
     */
    @FXML
    private Button restButton;

    /**
     * Help Button
     */
    @FXML
    private Button helpButton;

    /**
     * Initialization of GameHandler
     * @param parent parent pane of the currently handled pane
     */
    public GameHandler(Pane parent){
        super(parent);
    }

    public void restClicked(){
        System.out.println("Je suis rest");
    }

    public void helpClicked(){
        System.out.println("Je suis Aide");
    }
    public void redoClicked(){
        System.out.println("Je suis redo");
    }
    public void undoClicked(){
        System.out.println("Je suis undo");
    }
}
