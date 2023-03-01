package hashiScene;

import  javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import handlers.*;

abstract class DynamicScene implements Poster{ 
    public Pane parentPane;

    public DynamicScene(Pane parent){
        parentPane=parent;
    }
}
