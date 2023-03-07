package com.monappli;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;

import com.monappli.handlers.*;
import com.monappli.handlers.handlers.mainMenuEventHandler;

public class Hashi extends Application {
    Handler cont;

    public static void main(String[] args) {
        Application.launch(Hashi.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       FXMLLoader backLoader = new FXMLLoader(new File("src/main/resources/view/Background.fxml").toURI().toURL());

        Node page= backLoader.load();
        Pane root = new Pane(page);
        this.cont = new mainMenuEventHandler(root);
        backLoader.setController(this);

        FXMLLoader menuLoader= new FXMLLoader(new File("src/main/resources/view/main_menu.fxml").toURI().toURL());
        menuLoader.setController(this.cont);
        HBox box=new HBox();
        box.getChildren().addAll((Pane)menuLoader.load());
        root.getChildren().add(box);

        Scene scene =new Scene(root,450,800);
        stage.setTitle("Hashi");
        stage.setScene(scene);

        stage.show();
    }
}