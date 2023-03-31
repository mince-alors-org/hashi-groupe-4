package com.monappli.handlers;

import com.monappli.Parametre;
import com.monappli.hashiScene.MainPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Handler to set new Parameters
 * @author Ambre Collard
 */
public class ParamHandler extends DynamicEventHandler{

    /**
     * Color of the chosen locally text Color
     */
    private Color newTextColor;

    /**
     * Color of the chosen locally background color
     */
    private Color newBgColor;

    /**
     * Color of the chosen locally isle Color
     */
    private Color newIsleColor;

    /**
     * Color of the chosen locally bridge color
     */
    private Color newbridgeColor;

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
     * Initialization of ParamHandler
     * @param parent parent of currently handled pane
     */
    public ParamHandler(Pane parent){
        super(parent);
        this.setCurPane(paramPane);
    }

    /**
     * Action when backButton is clicked. Remove the current PopUp (parameters)
     */
    public void backClicked(){
        this.getParentPane().getChildren().remove(this.getParentPane().lookup("#pop"));
    }

    /**
     * Sets all the color Picker to the colors in Parametre
     * @see Parametre
     */
    public void setAll(){
        textPicker.setValue(Parametre.getCouleur_texte());
        bgPicker.setValue(Parametre.getCouleur_fond());
        islePicker.setValue(Parametre.getCouleur_ilot());
        bridgePicker.setValue(Parametre.getCouleur_pont());
    }

    /**
     * Action when textPicker is clicked. Sets newTextColor to the new one.
     */
    public void textPickerClicked(){
        this.newTextColor= textPicker.getValue();
    }

    /**
     * Action when islePicker is clicked. Sets newIsleColor to the new one.
     */
    public void ilsePickerClicked(){
        this.newIsleColor= islePicker.getValue();
    }

    /**
     * Action when bridgePicker is clicked. Sets newBridgeColor to the new one.
     */
    public void bridgePickerClicked(){
        this.newbridgeColor= bridgePicker.getValue();
    }

    /**
     * Action when bgPicker is clicked. Sets newBgColor to the new one.
     */
    public void bgPickerClicked(){
        this.newBgColor= bgPicker.getValue();
    }

    /**
     * Changes to the new parameters if they were selected
     * @throws Exception if the MainPanel can't load
     */
    public void changeAll() throws Exception{

        if (newBgColor !=null)
            Parametre.setCouleur_fond(newBgColor);
        if(newIsleColor != null)
            Parametre.setCouleur_ilot(newIsleColor);
        if(newbridgeColor != null)
            Parametre.setCouleur_pont(newbridgeColor);
        if(newTextColor!=null)
            Parametre.setCouleur_texte(newTextColor);

        Handler mainH= (Handler) paramPane.getParent().getUserData();
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
