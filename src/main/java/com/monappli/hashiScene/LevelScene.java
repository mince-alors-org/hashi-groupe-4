package com.monappli.hashiScene;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import com.monappli.Grille;
import com.monappli.Parametre;
import com.monappli.handlers.GameHandler;
import com.monappli.handlers.LevelSelectHandler;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.canvas.Canvas;

public class LevelScene extends MainPanel {
    public static int size= 3;
    public static int nbDiff=3;
    public static int curPage=1;

    public LevelScene(Pane parent){
        super(parent);
    }

    public static GridPane  initGrid(int nbLvl, int w, int h, Pane parent) throws Exception{
        GridPane grid= new GridPane();

        for(int i=0; i<size; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(1.0*w/ (size)));
        }
        
        for(int i=0; i<size;i++)
            grid.getRowConstraints().add(new RowConstraints(1.0*h / (size)));
        
        for (int i=size*size*(curPage-1); i< nbLvl && i < size*size*curPage; i++){
            Button btn = new Button(Integer.toString(i+1));
            GridPane.setHalignment(btn, HPos.CENTER);
            GridPane.setValignment(btn, VPos.CENTER);
            btn.setStyle("-fx-background-radius: " + 10*size+";"+
                         "\n-fx-background-insets: 0 0 0 0;"+
                         "\n-fx-font:"+ (int)(size*12) +" px;");
            btn.setBorder(new Border(new BorderStroke(
										Parametre.getCouleur_fond(), 
										BorderStrokeStyle.SOLID, 
										new CornerRadii(7*size), 
										new BorderWidths(2*size)
							)));
            btn.setPrefSize(
                (grid.getColumnConstraints().get(i%3).getPrefWidth())/1.3 , 
                (grid.getRowConstraints().get((int)i/size).getPrefHeight())/1.3 
            );
            grid.add(btn, i%size, (int)(i/size),1,1);

            btn.setOnAction(e -> {
                MainPanel game= new MainPanel(parent);
                try {
                    game.pasteAndHandle("/view/gameLayout.fxml", new GameHandler(parent));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String nomF = "../niveaux/" +LevelSelectHandler.curDiff+"-"+ btn.getText()+".niv";

                new Grille(nomF, (Pane)game.getParent().lookup("#gridPlacement"), (Canvas)game.getParent().lookup("#fond"));
                Button lvlButton = (Button)game.getCurPane().lookup("#levelButton");
                lvlButton.setText(LevelSelectHandler.curDiff + "-" + btn.getText());
            });

        }
        return grid;
    }

    public static int countLvl(int diff){
        File directory=new File("src/main/java/com/monappli/niveaux");
        ArrayList<String> res= new ArrayList<String>();
        ArrayList<String> search= new ArrayList<String>();

        for (String e : directory.list()){
            search.add(e);
        }

        Collections.sort(search);
        for (String e : search){
            if (Pattern.matches(diff +".*", e)){
                res.add(e);
            }
        }
        return res.size();
    }

    
}
