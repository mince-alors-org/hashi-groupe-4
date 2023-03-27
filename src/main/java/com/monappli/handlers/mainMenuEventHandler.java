package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainMenuEventHandler extends  DynamicEventHandler{

    @FXML
    private Button playButton;

    @FXML
    private Pane menuPane;

    @FXML
    private Button tutoButton;

    @FXML
    private Button challButton;

    @FXML
    private Button paramButton; 

    @FXML
    private Button powerButton;
    public mainMenuEventHandler(Pane parentPane){
        super(parentPane);
        setCurPane(menuPane);
    }

    public void playClicked() throws Exception {
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(menuPane));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= game.initGrid(game.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);

        

        System.out.println("Je suis jeux Libre");
    }
    public void quitClicked() {
        Stage stage = (Stage) powerButton.getScene().getWindow();
        stage.close();
        System.out.println("Je quitte");
    }

    public void tutoClicked() throws Exception {
        MainPanel tuto= new MainPanel(this.getParentPane());
        
        
        tuto.pasteAndHandle("/view/tuto.fxml", new TutorialEventHandler(this.getParentPane()));
        System.out.println("Je suis tuto");
    }

    public void challClicked() {
        System.out.println("Je suis defi");
    }

    public void paramClicked() throws Exception{
        if (menuPane.lookup("#pop") == null){
            PopUp pop = new PopUp(menuPane);
            ParamHandler paramH= new ParamHandler(this.menuPane);
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
            System.out.println("Je suis Paramètre");
        }
    }

}
