package com.monappli.hashiScene;

import  javafx.scene.layout.Pane;
//import javafx.fxml.*;
//import com.monappli.handlers.*;

abstract class DynamicScene implements Poster{ 
    public Pane parentPane;

    public DynamicScene(Pane parent){
        parentPane=parent;
    }
}
