package com.monappli.hashiScene;

import com.monappli.Parametre;

import  javafx.scene.layout.Pane;

abstract class DynamicScene implements Poster{ 
    private Pane parentPane;
    private static Parametre param;

    public DynamicScene(Pane parent){
        parentPane=parent;
    }

    public DynamicScene(Pane parent, Parametre new_param){
        parentPane=parent;
        param= new_param;
    }

    public Pane getParent(){
        return parentPane;
    }

    public void setParent(Pane parent){
        parentPane=parent;
    }


    public static void setParameter(Parametre new_param){
        param=new_param;
    }

    public static Parametre getParameter(){
        return param;
    }

    public void setStyleParam(){
        parentPane.setStyle("-fx-text-fill: "+ Parametre.toRGBForCSS(MainPanel.getParameter().getCouleur_texte())+";"+
                            "\n-fx-background-color: "+ Parametre.toRGBForCSS(MainPanel.getParameter().getCouleur_fond())+";");
    }
}
