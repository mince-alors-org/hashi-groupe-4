package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class WinHandler extends DynamicEventHandler {

    @FXML
    private Button menuButton;

    @FXML
    private Button lvlButton;

    public WinHandler(Pane parent){
        super(parent);
    }

    public void menuClicked() throws Exception{
        MainPanel main= new MainPanel((Pane)this.getParentPane().getParent());
        main.pasteAndHandle("/view/main_menu.fxml", new mainMenuEventHandler((Pane)this.getParentPane().getParent()));
    }

    public void lvlClicked() throws Exception{
        LevelScene game= new LevelScene((Pane)this.getParentPane().getParent());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler((Pane)this.getParentPane().getParent()));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= LevelScene.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }


}
