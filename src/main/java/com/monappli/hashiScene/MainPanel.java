package com.monappli.hashiScene;

import  javafx.scene.layout.Pane;
import javafx.fxml.*;

import com.monappli.handlers.*;

public class MainPanel extends DynamicScene {

    public MainPanel(Pane parent){
        super(parent);
    }
    
    public void paste(String name) throws Exception{
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));

        Pane newP= loader.load();

        this.setCurPane(newP);
        this.getParent().getChildren().setAll(newP);
        this.setStyleParam();
    }

    public void  pasteAndHandle(String name, Handler hand) throws Exception  {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));
        loader.setController(hand);

        Pane newP= loader.load();

        this.setCurPane(newP);
        hand.setCurPane(newP);
        newP.setUserData(hand);

        this.getParent().getChildren().setAll(newP);
        this.setStyleParam();
        
    }

    public void  pasteAndHandle(Pane pane, Handler hand) throws Exception  {
        this.setCurPane(pane);
        hand.setCurPane(pane);
        pane.setUserData(hand);

        this.getParent().getChildren().setAll(pane);
        this.setStyleParam();
        
    }
}
