package com.monappli.hashiScene;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.*;

import java.net.URL;

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
        newP.setId("pop");
        parentPane.getChildren().add(newP);
    }

    public Object getContent() {
        return null;
    }

    public void show(Stage stage) {
    }

    public void pasteAndHandle(URL url, TutorialEventHandler hand) {
    }
}
