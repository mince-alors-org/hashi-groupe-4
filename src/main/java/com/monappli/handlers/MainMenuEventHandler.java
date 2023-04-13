package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;
import com.monappli.hashiScene.ProfileScene;
import com.monappli.hashiScene.TutoScene;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Handler of the main menu
 * @see DynamicEventHandler 
 * @author Collard Ambre
 */
public class MainMenuEventHandler extends  DynamicEventHandler{


    /**
     * Initialization of MainMenuEventHandler
     * @param parentPane 
     */
    public MainMenuEventHandler(Pane parentPane){
        super(parentPane);
    }

    /**
     * Action when playButton is clicked. Creates a LevelScene grid and changes the handler to LevelSelectHandler
     * @throws Exception if the levelScene can't load
     * @see LevelScene
     * @see LevelSelectHandler
     * @author Ambre Collard
     */
    public void playClicked() throws Exception {
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(game.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
        
    }

    /**
     * @throws Exception
     */
    public void tutoClicked() throws Exception {
        TutoScene game= new TutoScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(game.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }

    /**
     * 
     */
    public void challClicked() {
    }

    public void profileClicked(){
        if (backGround.lookup("#pop") == null){
            ProfileScene prof= new ProfileScene(this.getCurPane());
            try{
                prof.pasteAndHandle("/view/profileSelection.fxml", new ProfileHandler(this.getCurPane()));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void rulesClicked(){
        MainPanel rules= new MainPanel(backGround);
        try{
            rules.pasteAndHandle("/view/rulesLayout.fxml", new DynamicEventHandler(backGround));
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
