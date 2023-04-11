package com.monappli.handlers;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Joueur;
import com.monappli.hashiScene.MainPanel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProfileHandler extends DynamicEventHandler{

    @FXML
    private TextField newJ;
    
    public ProfileHandler(Pane p){
        super (p);
    }

    public void newClicked(){

    }

    public void applyClicked(){
       System.out.println("coucou "+newJ.getText()); 
       Joueur j = new Joueur(newJ.getText());
       try {
            BaseDonneeJoueur.writeNewPlayer(j);
            BaseDonneeJoueur.changePlayer(j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
