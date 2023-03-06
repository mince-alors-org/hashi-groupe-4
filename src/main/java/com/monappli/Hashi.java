package com.monappli;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import javafx.scene.Parent;
//import javafx.fxml.*;
//import javafx.event.ActionEvent;
//import javafx.scene.control.*;

import com.monappli.handlers.*;
import com.monappli.handlers.handlers.mainMenuEventHandler;

public class Hashi extends Application{
    Handler cont;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        
        //FXMLLoader backLoader= new FXMLLoader();
        //backLoader.setLocation(getClass().getResource("/view/Background.fxml"));
        //backLoader.setController(this);

        // Pane page= backLoader.load();
        
        // this.cont = new mainMenuEventHandler(page);
        
        // FXMLLoader menuLoader= new FXMLLoader(getClass().getResource("/view/main_menu.fxml"));
        // menuLoader.setController(this.cont);
        
        // Pane newP= menuLoader.load();

        // page.getChildren().setAll(newP);

        // Pane pane = new Pane();

        // mainMenuEventHandler event = new mainMenuEventHandler(pane);


        // Scene scene = backLoader.load();
        // primaryStage.setHeight(800);
        // primaryStage.setWidth(450);
        // primaryStage.setScene(scene); 



        // primaryStage.show();
        String javaVersion = System.getProperty("java.version");
String javafxVersion = System.getProperty("javafx.version");
Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
Scene scene = new Scene(new StackPane(l), 640, 480);
stage.setScene(scene);
stage.show();
    }
}