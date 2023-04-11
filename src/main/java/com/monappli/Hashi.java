package com.monappli;



import com.monappli.handlers.*;
import com.monappli.hashiScene.*;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Hashi extends Application {
    public static Joueur joueur;

    public static void main(String[] args) {
        /*try{
            joueur= BaseDonneeJoueur.getJoueur("Michel", "mdp1");
            BaseDonneeJoueur.loadParam();
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        Application.launch(Hashi.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception { 
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/mainBG.fxml"));
        Pane root = (Pane)loader.load();
        MainPanel menuLoader= new MainPanel(root);

        menuLoader.pasteAndHandle("/view/main_menu.fxml", new MainMenuEventHandler(root));

        ProfileScene prof= new ProfileScene(menuLoader.getCurPane());
        prof.pasteAndHandle("/view/profileSelection.fxml", new ProfileHandler(menuLoader.getCurPane()));

        Scene scene =new Scene(root,450,800);
        stage.setTitle("Hashi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}