package com.monappli;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
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
    public void start(Stage primaryStage) throws Exception {

        
        FXMLLoader backLoader= new FXMLLoader(getClass().getResource("view/Background.fxml"));
        

        Pane page= backLoader.load();
        
        this.cont = new mainMenuEventHandler(page);
        
        FXMLLoader menuLoader= new FXMLLoader(getClass().getResource("view/main_menu.fxml"));
        menuLoader.setController(this.cont);
        
        Pane newP= menuLoader.load();

        page.getChildren().setAll(newP);


        Scene scene = new Scene(page);
        primaryStage.setHeight(800);
        primaryStage.setWidth(450);
        primaryStage.setScene(scene); 



        primaryStage.show();
    }
}