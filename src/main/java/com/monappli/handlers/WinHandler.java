package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class WinHandler extends DynamicEventHandler {

    public WinHandler(Pane parent){
        super(parent);
    }

    public void menuClicked() throws Exception{
        MainPanel main= new MainPanel(this.getParentPane());
        main.pasteAndHandle("/view/main_menu.fxml", new MainMenuEventHandler((Pane)this.getParentPane()));
    }

    public void lvlClicked() throws Exception{
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler((Pane)this.getParentPane(), game));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }


}
