package com.monappli.handlers;

import com.monappli.Grille;
import com.monappli.Main;
import com.monappli.Parametre;
import com.monappli.hashiScene.MainPanel;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.*;
import javafx.scene.control.*;
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
    }

    public void playClicked() throws Exception {
        MainPanel game= new MainPanel(this.getParentPane());
        game.pasteAndHandle("/view/gameLayout.fxml", new GameHandler(this.getParentPane()));
        Grille grille = new Grille("../niveaux/1-1.niv", (Pane)game.getParent().lookup("#gridPlacement"));
        System.out.println(grille.getIlots().get(0).getLayoutX());
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
            pop.pasteAndHandle("/view/parameters.fxml", new ParamHandler(this.menuPane));
            System.out.println("Je suis Paramètre");
        }
    }

}
