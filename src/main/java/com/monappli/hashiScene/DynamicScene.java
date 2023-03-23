package com.monappli.hashiScene;

import com.monappli.Parametre;

import  javafx.scene.layout.Pane;

abstract class DynamicScene implements Poster{ 
    private Pane parentPane;
    private Pane curPane;

    public DynamicScene(Pane parent){
        parentPane=parent;
    }

    public Pane getParent(){
        return parentPane;
    }

    public void setParent(Pane parent){
        parentPane=parent;
    }

    public Pane getCurPane(){
        return curPane;
    }

    public void setCurPane(Pane pane){
        curPane=pane;
    }

    public void setStyleParam(){
        curPane.setStyle("-fx-text-base-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_texte())+";"+
                            "\n-fx-background-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_fond())+";");
    }
}
