package com.monappli.handlers.handlers;


//import javax.swing.Action;

//import org.w3c.dom.events.MouseEvent;

import com.monappli.handlers.*;
import com.monappli.hashiScene.*;
//import javafx.event.*;  
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
//import javafx.scene.Parent;
//import java.io.IOException;

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

    public mainMenuEventHandler(Pane parentPane){
        super(parentPane);
    }

    public void playClicked() {
        playButton.setStyle("-fx-background-color: #ff0000 ;"); 
    }

    public void tutoClicked() throws Exception {
        tutoButton.setStyle("-fx-background-color: #00ff00 ;");
        MainPanel tuto= new MainPanel(this.parentPane);
        tuto.pasteAndHandle("../view/tuto.fxml", new TutorialEventHandler(this.parentPane));
    }

    public void challClicked() {
        challButton.setStyle("-fx-background-color: #0000ff ;");
    }

    public void paramClicked() throws Exception{
        paramDeac();
        PopUp pop = new PopUp(menuPane);
        pop.pasteAndHandle("../view/parameters.fxml", new TutorialEventHandler(this.menuPane));
    }


    public void paramDeac(){
        paramButton.setOnAction(null);
    }

    public void paramAct(){
        paramButton.setOnAction(action -> {
            try {
                paramClicked();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        } );
    }


}
