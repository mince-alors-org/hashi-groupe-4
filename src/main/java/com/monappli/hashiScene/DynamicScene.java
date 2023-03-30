package com.monappli.hashiScene;

import com.monappli.Parametre;

import  javafx.scene.layout.Pane;


/**
 * This class allows the creation of a graphic interface
 * 
 * @author Matthis Collard
 */
abstract class DynamicScene { 
    
    /**
     * parentPane : Pane curPane parent's Pane
     * @see Pane 
     */
    private Pane parentPane;

    /**
     * curPane : Pane current Pane (usually the one on screen)
     * @see Pane 
     */
    private Pane curPane;

    /**
     * Initialization of DynamicScene
     * @param parent 
     */
    public DynamicScene(Pane parent){
        parentPane=parent;
    }

    /**
     * Returns the parent Pane of the currentPane
     * @return parentPane
     * 
     */
    public Pane getParent(){
        return parentPane;
    }

    /**
     * Set the parent Pane to the pane in the parameters
     * @param parent to set a new parentPane
     * 
     */
    public void setParent(Pane parent){
        parentPane=parent;
    }

    /**
     * Returns the current Pane (usually on scene)
     * @return curPane
     * 
     */
    public Pane getCurPane(){
        return curPane;
    }

    /**
     * Set the current Pane to the one in the parameters
     * @param pane to set a new curPane
     * 
     */
    public void setCurPane(Pane pane){
        curPane=pane;
    }

    /**
     * Set the different aspects of the graphic interface with the attributes of Parametre 
     * @see Parametre
     */
    public void setStyleParam(){
        curPane.setStyle("-fx-text-base-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_texte())+";"+
                            "\n-fx-background-color: "+ Parametre.toRGBForCSS(Parametre.getCouleur_fond())+";");
    }
}
