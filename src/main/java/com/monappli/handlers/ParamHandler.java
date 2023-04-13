package com.monappli.handlers;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Parametre;
import com.monappli.hashiScene.MainPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

/**
 * Handler to set new Parameters
 * @author Ambre Collard
 */
public class ParamHandler extends DynamicEventHandler{

    /**
     * Back Button
     */
    @FXML
    private Button backButton;

    /**
     * Color picker for the text color
     */
    @FXML
    private ColorPicker textPicker;

    /**
     * Color picker for the background color
     */
    @FXML
    private ColorPicker bgPicker;

    /**
     * Color picker for the isle color
     */
    @FXML
    private ColorPicker islePicker;

    /**
     * Color picker for the bridge color 
     */
    @FXML
    private ColorPicker bridgePicker;

    /**
     * Background Pane for the parameter Pop-up 
     */
    @FXML
    private Pane paramPane;

    /**
     * Toggel bouton on off
     */
    @FXML
    private ToggleButton aideVis;

    /**
     * Initialization of ParamHandler
     * @param parent parent of currently handled pane
     * @author Ambre Collard
     */
    public ParamHandler(Pane parent){
        super(parent);
    }

    /**
     * Action when backButton is clicked. Remove the current PopUp (parameters)
     * @author Ambre Collard
     */
    public void backClicked(){
        this.getParentPane().getChildren().remove(this.getParentPane().lookup("#pop"));
    }

    /**
     * Sets all the color Picker to the colors in Parametre
     * @see Parametre
     * @author Ambre Collard
     */
    public void setAll(){
        textPicker.setValue(Parametre.getCouleur_texte());
        bgPicker.setValue(Parametre.getCouleur_fond());
        islePicker.setValue(Parametre.getCouleur_ilot());
        bridgePicker.setValue(Parametre.getCouleur_pont());
    }
    /** */
    public void okAideVis(){
        if( aideVis.getText().equals("On")){
            aideVis.setText("Off");
        }else{
            aideVis.setText("On");
        }
    }
    /**
     * Changes to the new parameters if they were selected
     * @throws Exception if the MainPanel can't load
     * @author Ambre Collard
     */
    public <H extends DynamicEventHandler> void changeAll() throws Exception{
        Parametre.setCouleur_fond(bgPicker.getValue());
        Parametre.setCouleur_ilot(islePicker.getValue());
        Parametre.setCouleur_pont(bridgePicker.getValue());
        Parametre.setCouleur_texte(textPicker.getValue());
        Parametre.setAffichage_depassment_cardinalite(aideVis.getText().equals("On"));

        BaseDonneeJoueur.saveParam();

        H mainH= (H) paramPane.getParent().getUserData();
        Pane parent = (Pane)paramPane.getParent();

        Pane grandparent=(Pane)(parent.getParent());

        parent.getChildren().remove(paramPane);
        grandparent.getChildren().remove(parent);

        MainPanel main= new MainPanel(grandparent);
        main.pasteAndHandle(parent, mainH);

    }

    public Pane getParamPane(){
        return paramPane;
    }



}
