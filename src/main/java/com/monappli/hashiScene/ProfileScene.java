package com.monappli.hashiScene;

import java.util.ArrayList;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Joueur;
import com.monappli.handlers.DynamicEventHandler;
import com.monappli.handlers.MainMenuEventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * 
 */
public class ProfileScene extends PopUp {
    private Pane gridP;


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
            Pane pane = new Pane();

            pane.getStyleClass().add("chip");

            HBox hBox = new HBox();
            
            hBox.getChildren().add(pane);
            hBox.getChildren().add(but);

            setStyleHBox(hBox);


            vBox.getChildren().add(hBox);
            setStyleButton(but);
            setStyleChip(pane, j);
            but.setOnAction(e ->{
                try{
                    btnOnAction(j);
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

    public void setStyleChip(Pane p, Joueur j) throws Exception{
        Background bg = new Background( new BackgroundFill(BaseDonneeJoueur.getChipColor(j),new CornerRadii(20),Insets.EMPTY));
        p.setBackground(bg);
    }

    public void setStyleHBox(HBox h){
        h.setAlignment(Pos.CENTER);
        h.setSpacing(20);
        h.setPrefSize(this.gridP.getPrefWidth(), this.gridP.getPrefHeight());
    }

    public void setStyleGrid(VBox v){
        v.setId("vBox");
        v.setAlignment(Pos.TOP_CENTER);
        v.setPrefSize(this.gridP.getPrefWidth(), this.gridP.getPrefHeight());
        v.setSpacing(20);
    }

    public void btnOnAction(Joueur j) throws Exception{
        BaseDonneeJoueur.setJoueur(j);
        this.remove();
    }

    public void remove () throws Exception{
        MainPanel main= new MainPanel(this.getParent());
        main.pasteAndHandle("/view/main_menu.fxml", new MainMenuEventHandler(this.getParent()));
        super.remove();
    }
}
