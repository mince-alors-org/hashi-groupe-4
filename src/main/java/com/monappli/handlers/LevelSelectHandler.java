package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Handler of the level selecting pane
 * @author Matthis Collard
 */
public class LevelSelectHandler extends DynamicEventHandler{
    
    /**
     * Static Integer of the current difficulty set on the grid (useful for creating the level selecting grid)
     * @see LevelScene#initGrid
     */
    public static int curDiff;

    /**
     * Current background Panel
     */
    @FXML
    private Pane backGround;

    /**
     * Difficulty button
     */
    @FXML
    private Button diffButton;

    /**
     * Pane on which the selectiong grid will be added
     */
    @FXML
    private Pane selectPane;

    /**
     * Initialization of LevelSelectHandler
     * @param parent parent of the current Pane
     */
    public LevelSelectHandler(Pane parent){
        super(parent);
        curDiff=1;
    }

    /**
     * Action when diffButton is clicked. Changes the difficulty to chose from and reloads the level selection grid depending on the difficulty
     * @throws Exception if the grid can't load
     * @see LevelScene#initGrid
     * @author Matthis Collard
     */
    public void diffClicked() throws Exception{
        curDiff= curDiff%LevelScene.nbDiff +1;
        diffButton.setText(intToDiff(curDiff));

        GridPane newGrid= LevelScene.initGrid(LevelScene.countLvl(curDiff), (int)selectPane.getPrefWidth(), (int)selectPane.getPrefHeight(), backGround) ;
        selectPane.getChildren().setAll(newGrid);

    }

    /**
     * Returns the difficulty as a string from a number
     * @param diff difficulty (1,2 or 3)
     * @return String representing difficulty (Example : 1 returns Facile)
     * @author Matthis Collard
     */
    public String intToDiff(int diff){
        switch (diff){
            case (1):
                return "Facile";
            case(2):
                return "Moyen";
            case(3):
                return "Difficile";
            default:
                return "???";
        }
    }


    
}