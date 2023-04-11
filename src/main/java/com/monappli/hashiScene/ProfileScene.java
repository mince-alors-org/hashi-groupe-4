package com.monappli.hashiScene;

import java.util.ArrayList;
import java.util.logging.Handler;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Joueur;
import com.monappli.handlers.DynamicEventHandler;
import com.monappli.handlers.MainMenuEventHandler;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ProfileScene extends PopUp {
    private Pane gridP;
    private TextField newJ;
    //private Button apply;


    public ProfileScene (Pane parent){
        super(parent);
    }

    public void setGridPlace(Pane pane){
        this.gridP= pane;
    }

    public Pane getGridPane(){
        return this.gridP;
    }

    public <H extends DynamicEventHandler> void pasteAndHandle(String res, H hand) throws Exception{
        super.pasteAndHandle(res,hand);
        this.setGridPlace((Pane)this.getParent().lookup("#gridP"));
        VBox pBox= initProfiles();
        getGridPane().getChildren().add(pBox);
        setStyleGrid(pBox);
    }

    public VBox initProfiles() throws Exception{
        VBox vBox= new VBox();
        ArrayList<Joueur> tabJ= BaseDonneeJoueur.getAllPlayers();
        for(Joueur j : tabJ){
            Button but= new Button(j.getnom());
            vBox.getChildren().add(but);
            setStyleButton(but);
            but.setOnAction(e ->{
                try{
                    btnOnAction(j);
                    System.out.println("coucou ="+newJ.getText());
                    MainPanel main= new MainPanel(this.getParent());
                    main.pasteAndHandle("/view/main_menu.fxml", new MainMenuEventHandler(this.getParent()));
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
            });
        }
        return vBox;
    }


    public void setStyleButton(Button b){
        b.getStyleClass().add("playerButton");
    }

    public void setStyleGrid(VBox v){
        v.setId("vBox");
        v.setAlignment(Pos.TOP_CENTER);
        v.setPrefSize(this.gridP.getPrefWidth(), this.gridP.getPrefHeight());
        v.setSpacing(20);
    }

    public void btnOnAction(Joueur j) throws Exception{
        BaseDonneeJoueur.setJoueur(j);
    }
}
