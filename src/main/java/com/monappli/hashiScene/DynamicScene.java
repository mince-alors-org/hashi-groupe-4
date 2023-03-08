package com.monappli.hashiScene;

import  javafx.scene.layout.Pane;

abstract class DynamicScene implements Poster{ 
    public Pane parentPane;

    public DynamicScene(Pane parent){
        parentPane=parent;
    }
}
