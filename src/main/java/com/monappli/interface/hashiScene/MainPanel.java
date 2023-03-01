package hashiScene;

import  javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import handlers.*;

public class MainPanel extends DynamicScene {

    public MainPanel(Pane parent){
        super(parent);
    }
    
    public void paste(String name) throws Exception{
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));

        Pane newP= loader.load();

        parentPane.getChildren().setAll(newP);
    }

    public void  pasteAndHandle(String name, Handler hand) throws Exception  {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));
        loader.setController(hand);

        Pane newP= loader.load();

        parentPane.getChildren().setAll(newP);
        
    }

}
