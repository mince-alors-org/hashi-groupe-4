package com.monappli.hashiScene;

import javafx.scene.layout.Pane;
import javafx.fxml.*;


import com.monappli.Parametre;
import com.monappli.handlers.*;


/**
 * This class allows to make a Pop-Up in front of the current on screen Pane
 * @see DynamicScene
 * @author Ambre Collard
 */
public class PopUp extends DynamicScene{

    /**
     * Initialization of PopUp
     * @param parent 
     * @author Collard Ambre 
     */
    public PopUp(Pane parent){
        super(parent);
    }

    /**
     * Adds to the parent's children a new Pane loaded thanks to the name of the FXML file. Resulting Popping-up this pane
     * @author Collard Ambre 
     * @param name Name of the FXML file
     * @throws Exception if the FXMLLoader can't load the resource
     * @see FXMLLoader
     */
    public void paste(String name) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));

        Pane newP= loader.load();
        this.setCurPane(newP);

        this.getParent().getChildren().add(newP);
        this.setStyleParam();
    }
    

    /**
     * Adds to the parent's children a new Pane loaded thanks to the name of the FXML file. Resulting Popping-up this pane
     * And sets a new controller to this new Pane.
     * @author Collard Ambre 
     * @param name Name of the FXML file
     * @param handler Handler specified to manage events occuring on this pane
     * @throws Exception if the FXMLLoader can't load the resource
     * @see FXMLLoader
     */
    public <H extends DynamicEventHandler>  void pasteAndHandle(String name, H hand) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource(name));
        loader.setController(hand);

        Pane newP= loader.load();
        this.setCurPane(newP);
        hand.setCurPane(newP);
        newP.setUserData(hand);


        newP.setId("pop");
        this.getParent().getChildren().add(newP);
        this.setStyleParam();
        
    }

    /**
     * Adds to the parent's children the new Pane. Resulting Popping-up this pane
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

    /**
     * Set the different aspects of the graphic interface with the attributes of Parametre.
     * Slightly different from setStyleParam from DynamicScene because of the transparent background
     * @see DynamicScene#setStyleParam()
     * @see Parametre
     * @author Collard Ambre 
     */
    public void setStyleParam(){
        this.getCurPane().setStyle("-fx-text-base-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_texte())+";");
        this.getCurPane().toFront();
    }
}
