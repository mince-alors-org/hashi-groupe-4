package com.monappli;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import com.monappli.handlers.*;

public class Hashi extends Application {
    Handler cont;

    public static void main(String[] args) {
        Application.launch(Hashi.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: gray");
        this.cont = new mainMenuEventHandler(root);
        FXMLLoader menuLoader= new FXMLLoader(getClass().getResource("/view/main_menu.fxml"));
        menuLoader.setController(this.cont);
        HBox box=new HBox();
        box.getChildren().addAll((Pane) menuLoader.load());
        root.getChildren().add(box);

        Scene scene =new Scene(root,450,800);
        stage.setTitle("Hashi");
        stage.setScene(scene);

        stage.show();
    }
}