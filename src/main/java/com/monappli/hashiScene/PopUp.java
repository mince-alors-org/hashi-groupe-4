package com.monappli.hashiScene;

import javafx.scene.layout.Pane;
import javafx.fxml.*;

import com.monappli.handlers.*;

public class PopUp extends DynamicScene{

    public PopUp(Pane parent){
        super(parent);
    }

    public void paste(String name) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));

        Pane newP= loader.load();

        parentPane.getChildren().add(newP);
    }

    public void pasteAndHandle(String name, Handler hand) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));
        loader.setController(hand);

        Pane newP= loader.load();

        parentPane.getChildren().add(newP);
    }
}
