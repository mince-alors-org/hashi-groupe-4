package com.monappli.handlers;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Joueur;
import com.monappli.hashiScene.MainPanel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProfileHandler extends DynamicEventHandler{

    /**
     * The text field for creating a new player
     */
    @FXML
    private TextField newJ;
    
    public ProfileHandler(Pane p){
        super (p);
    }

    /**
     * Action performed when clicking apply, creating a new <code>Joueur</code>
     */
    public void applyClicked(){
       Joueur j = new Joueur(newJ.getText());
       try {
            if(!newJ.getText().trim().isEmpty() &&  BaseDonneeJoueur.addJoueur(j) == true){
                BaseDonneeJoueur.changePlayer(j);
                reload();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Reloads the panes and removes the active PopUp
     * @param <H> Event handler
     * @throws Exception
     */
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
