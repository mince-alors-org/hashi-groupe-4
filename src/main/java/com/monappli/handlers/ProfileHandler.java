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
       Joueur j = new Joueur(newJ.getText());
       try {
            if( BaseDonneeJoueur.addJoueur(j) == false){
                System.out.println("La.e joueuse.r existe déjà !");
            }
            else {
                BaseDonneeJoueur.changePlayer(j);
                reload();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <H extends DynamicEventHandler> void reload() throws Exception{

        H mainH= (H) this.getCurPane().getParent().getUserData();
        Pane parent = (Pane)this.getCurPane().getParent();

        Pane grandparent=(Pane)(parent.getParent());

        parent.getChildren().remove(this.getCurPane());
        grandparent.getChildren().remove(parent);

        MainPanel main= new MainPanel(grandparent);
        main.pasteAndHandle(parent, mainH);

    }
}
