package com.monappli.handlers;

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

    public void playClicked() {
        MainPanel game= new MainPanel(this.parentPane);
        System.out.println("Je suis jeux Libre");
    }
    public void quitClicked() {
        Stage stage = (Stage) powerButton.getScene().getWindow();
        stage.close();
        System.out.println("Je quitte");
    }

    public void tutoClicked() throws Exception {
        tutoButton.setStyle("-fx-background-color: #00ff00 ;");
        MainPanel tuto= new MainPanel(this.parentPane);
        tuto.pasteAndHandle("view/tuto.fxml", new TutorialEventHandler(this.parentPane));
        System.out.println("Je suis tuto");
    }

    public void challClicked() {
        challButton.setStyle("-fx-background-color: #0000ff ;");
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
