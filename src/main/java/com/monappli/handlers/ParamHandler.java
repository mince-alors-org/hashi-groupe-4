package com.monappli.handlers;

import com.monappli.Parametre;
import com.monappli.hashiScene.MainPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ParamHandler extends DynamicEventHandler{

    private Color newTextColor;
    private Color newBgColor;
    private Color newIsleColor;
    private Color newbridgeColor;

    @FXML
    private Button backButton;

    @FXML
    private ColorPicker textPicker;

    @FXML
    private ColorPicker bgPicker;

    @FXML
    private ColorPicker islePicker;

    @FXML
    private ColorPicker bridgePicker;

    @FXML
    private Pane paramPane;

    public ParamHandler(Pane parent){
        super(parent);
        this.setCurPane(paramPane);
    }

    public void backClicked(){
        this.getParentPane().getChildren().remove(this.getParentPane().lookup("#pop"));
    }

    public void setAll(){
        textPicker.setValue(Parametre.getCouleur_texte());
        bgPicker.setValue(Parametre.getCouleur_fond());
        islePicker.setValue(Parametre.getCouleur_ilot());
        bridgePicker.setValue(Parametre.getCouleur_pont());
    }

    public void textPickerClicked(){
        this.newTextColor= textPicker.getValue();
    }

    public void ilsePickerClicked(){
        this.newIsleColor= islePicker.getValue();
    }

    public void bridgePickerClicked(){
        this.newbridgeColor= bridgePicker.getValue();
    }

    public void bgPickerClicked(){
        this.newBgColor= bgPicker.getValue();
    }

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
