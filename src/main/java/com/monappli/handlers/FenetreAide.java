package com.monappli.handlers;

import com.monappli.hashiScene.MainPanel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenetreAide extends Application {

    @Override
    public void start(Stage stage) throws Exception { 
        FXMLLoader loader= new FXMLLoader(getClass().getResource(""));
        Pane root = (Pane)loader.load();
        MainPanel menuLoader= new MainPanel(root);

        menuLoader.pasteAndHandle("/view/fenetreAide.fxml", new MainMenuEventHandler(root));

        Scene scene =new Scene(root,450,800);
        stage.setTitle("Hashi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();       
    }
}