package com.monappli.hashiScene;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.geometry.Insets;

import java.net.URL;

import com.monappli.Parametre;
import com.monappli.handlers.*;

public class PopUp extends DynamicScene{

    public PopUp(Pane parent){
        super(parent);
    }

    public void paste(String name) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));

        Pane newP= loader.load();
        this.setCurPane(newP);

        this.getParent().getChildren().add(newP);
        this.setStyleParam();
    }

    public void pasteAndHandle(String name, Handler hand) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));
        loader.setController(hand);

        Pane newP= loader.load();
        this.setCurPane(newP);
        hand.setCurPane(newP);
        newP.setUserData(hand);
        
        BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT, 
                                          CornerRadii.EMPTY, Insets.EMPTY);


        newP.setId("pop");
        this.getParent().getChildren().add(newP);
        this.setStyleParam();

    }

    public void setStyleParam(){

        this.getCurPane().setStyle("-fx-text-base-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_texte())+";");

        BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT, 
                                          CornerRadii.EMPTY, Insets.EMPTY);
  
        Background background = new Background(background_fill);
        this.getCurPane().setBackground(background);
    }
}
