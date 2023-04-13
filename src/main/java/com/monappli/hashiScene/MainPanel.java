package com.monappli.hashiScene;

import  javafx.scene.layout.Pane;
import javafx.fxml.*;

import com.monappli.handlers.*;


/**
 * This class allows to change the current graphic interface
 * @see DynamicScene
 * @author Ambre Collard
 */
public class MainPanel extends DynamicScene {

    /**
     * Initialization of MainPanel
     * @author Collard Ambre 
     * @param parent 
     */
    public MainPanel(Pane parent){
        super(parent);
    }
    
    /**
     * Change all the parent's children to a new Pane loaded thanks to the name of the FXML file. Resulting in changing the whole interface
     * @author Collard Ambre 
     * @param name Name of the FXML file
     * @throws Exception if the FXMLLoader can't load the resource
     * @see FXMLLoader
     */
    public void paste(String name) throws Exception{
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));

        Pane newP= loader.load();

        this.setCurPane(newP);
        this.getParent().getChildren().setAll(newP);
        this.setStyleParam();
    }

    /**
     * Change all the parent's children to a new Pane loaded thanks to the name of the FXML file. Resulting in changing the whole interface.
     * And sets a new controller to this new Pane.
     * @author Collard Ambre 
     * @param name Name of the FXML file
     * @param handler Handler specified to manage events occuring on this pane
     * @throws Exception if the FXMLLoader can't load the resource
     * @see FXMLLoader
     * @see MainPanel#setStyleParam
     */
    public <H extends DynamicEventHandler>  void  pasteAndHandle(String name, H handler) throws Exception  {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));
        loader.setController(handler);

        Pane newP= loader.load();

        this.setCurPane(newP);
        handler.setCurPane(newP);
        newP.setUserData(handler);

        this.getParent().getChildren().setAll(newP);
        this.setStyleParam();
        
    }

    /**
     * Change all the parent's children to the new Pane in the parameters. Resulting in changing the whole interface.
     * And sets a new controller to this new Pane.
     * @author Collard Ambre 
     * @param pane Pane to be set on
     * @param handler Handler specified to manage events occuring on this pane
     * @throws Exception if the FXMLLoader can't load the resource
     * @see FXMLLoader
     */
    public <H extends DynamicEventHandler>  void  pasteAndHandle(Pane pane, H hand) throws Exception  {
        this.setCurPane(pane);
        hand.setCurPane(pane);
        pane.setUserData(hand);

        this.getParent().getChildren().setAll(pane);
        this.setStyleParam();
        
    }
}
