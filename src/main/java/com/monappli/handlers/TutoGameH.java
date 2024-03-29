package com.monappli.handlers;

import java.io.IOException;

import com.monappli.Aide;
import com.monappli.Grille;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public abstract class TutoGameH extends DynamicEventHandler{
 
    @FXML
    private Pane gridPlacement;

    private String lvlNum;

    private Grille grille;

    public TutoGameH(Pane parent){
        super(parent);
        lvlNum = "";
    }

    public abstract void setScene(Grille grille,String formattage) throws IOException, ClassNotFoundException;


    public void setGrille(Grille grille ){
        this.grille=grille;
    }

    public void restClicked(){
        this.grille.remiseZero();
    }

    public void helpClicked(){
        Popup po=new Popup();
        po.setX(205);
        po.setY(304);
        Pane newP=null;
        Stage s = (Stage) backGround.getScene().getWindow();
        PopUpEH p= new PopUpEH(po);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popTech.fxml"));
        loader.setController(p);
        //loader.setStyle("background-color: green;");

        try {
            newP = (Pane)loader.load();
            po.getContent().add(newP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        po.show(s);
        p.setText(Aide.getTechnique());
    }
    public void errClicked(){
        Popup po=new Popup();
        po.setX(205);
        po.setY(304);
        Pane newP=null;
        Stage s = (Stage) backGround.getScene().getWindow();
        PopUpEH p= new PopUpEH(po);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popErr.fxml"));
        loader.setController(p);
        //loader.setStyle("background-color: green;");

        try {
            newP = (Pane)loader.load();
            po.getContent().add(newP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        po.show(s);
        p.setText(Aide.checkErreur());
    }
    public void redoClicked(){
        this.grille.retablirAction();
    }
    public void undoClicked(){
        this.grille.annulerAction();
    }
    @Override
    public void paramClicked() throws Exception{
        if (backGround.lookup("#pop") == null){
            PopUp pop = new PopUp(this.getCurPane());
            GameParamHandler paramH= new GameParamHandler(this.backGround, this.grille );
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
        }
    }

    public Grille getGrille(){
        return this.grille;
    }

    public String getLvlNum() {
        return lvlNum;
    }

    public void setLvlNum(String lvlNum) {
        this.lvlNum = lvlNum;
    }
}
