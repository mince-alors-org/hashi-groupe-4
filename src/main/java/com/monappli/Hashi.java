package com.monappli;



import com.monappli.handlers.*;
import com.monappli.hashiScene.*;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class Hashi extends Application {
    Handler cont;

    public static void main(String[] args) {
        Application.launch(Hashi.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parametre param= new Parametre();

        MainPanel.setParameter(param);
        param.setCouleur_texte(Color.PURPLE);
        param.setCouleur_ilot(Color.BLUE);


        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #5995ED");
        this.cont = new mainMenuEventHandler(root);
        MainPanel menuLoader= new MainPanel(root);
        menuLoader.pasteAndHandle("/view/main_menu.fxml", cont);



        Scene scene =new Scene(root,450,800);
        stage.setTitle("Hashi");
        stage.setScene(scene);

        stage.show();
    }
}