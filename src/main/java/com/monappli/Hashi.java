package com.monappli;

import com.monappli.handlers.*;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Hashi extends Application {
    Handler cont;

    public static void main(String[] args) {
        Application.launch(Hashi.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: gray");
        this.cont = new TestController(root);
        FXMLLoader menuLoader= new FXMLLoader(getClass().getResource("/view/test.fxml"));
        menuLoader.setController(this.cont);
        HBox box=new HBox();
        box.getChildren().addAll((Pane) menuLoader.load());
        root.getChildren().add(box);

        TestButton oui=  new TestButton(53, "Bonjour!!");
        oui.setPrefSize(200, 100);

        root.getChildren().add(oui);
        Scene scene =new Scene(root,600,400);
        stage.setTitle("Hashi");
        stage.setScene(scene);

        stage.show();
    }
}