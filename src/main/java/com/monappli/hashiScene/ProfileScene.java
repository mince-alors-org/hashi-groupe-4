package com.monappli.hashiScene;

import java.util.ArrayList;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Joueur;
import com.monappli.handlers.DynamicEventHandler;
import com.monappli.handlers.MainMenuEventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * Profile scene appearing when launching the application
 * @author Ambre Collard
 */
public class ProfileScene extends PopUp {

    /**
     * Variable of the Pane for the profile selection grid
     */
    private ScrollPane gridP;



    /**
     * Initialization of the profile scene
     * @param parent the parent pane
     * @author Ambre Collard
     */
    public ProfileScene (Pane parent){
        super(parent);
    }

    /**
     * Sets the function attribute as the pane for the gridPlacement
     * @param pane the pane on which the grid will be pasted
     */
    public void setGridPlace(ScrollPane pane){
        this.gridP= pane;
    }


    /**
     * @return the pane where the selection grid is pasted
     */
    public ScrollPane getGridPane(){
        return this.gridP;
    }

    @Override
    public <H extends DynamicEventHandler> void pasteAndHandle(String res, H hand) throws Exception{
        super.pasteAndHandle(res,hand);
        this.setGridPlace((ScrollPane)this.getParent().lookup("#gridP"));
        VBox pBox= initProfiles();
        getGridPane().setContent(pBox);
        setStyleGrid(pBox);
    }

    /**
     * Initialize and return a vertical box to select the players profile from
     * @return the <code>VBox</code> with all profile on it
     * @throws Exception
     * @author Ambre Collard
     */
    public VBox initProfiles() throws Exception{
        VBox vBox= new VBox();
        ArrayList<Joueur> tabJ= BaseDonneeJoueur.getAllPlayers();
        for(Joueur j : tabJ){
            Button but= new Button(j.getnom());
            Pane pane = new Pane();

            pane.getStyleClass().add("chip");

            HBox hBox = new HBox();
            
            hBox.getChildren().add(pane);
            hBox.getChildren().add(but);

            setStyleHBox(hBox);


            vBox.getChildren().add(hBox);
            setStyleButton(but);
            setStyleChip(pane, j);
            but.setOnAction(e ->{
                try{
                    btnOnAction(j);
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
            });
        }
        return vBox;
    }


    /**
     * Sets the style of a button
     * @param b said <code>Button</code>
     * @author Ambre Collard
     */
    public void setStyleButton(Button b){
        b.getStyleClass().add("playerButton");
    }

    /**
     * Sets the style of a Pane considered as a chip to make the profile chosing more enjoyable
     * @param p the "chip" <code>Pane</code>
     * @param j the <code>Joueur</code> representing the profile
     * @throws Exception
     * @author Ambre Collard
     */
    public void setStyleChip(Pane p, Joueur j) throws Exception{
        Background bg = new Background( new BackgroundFill(BaseDonneeJoueur.getChipColor(j),new CornerRadii(20),Insets.EMPTY));
        p.setBackground(bg);
    }

    /**
     * Sets the style of an horizontal box
     * @param h said <code>HBox</code>
     * @author Ambre Collard
     */
    public void setStyleHBox(HBox h) {
        h.setAlignment(Pos.CENTER);
        h.setSpacing(20);
        try{
            h.setPrefSize(this.gridP.getPrefWidth(), this.gridP.getPrefHeight()/BaseDonneeJoueur.getNumberPlayers());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        h.setMaxHeight(30);
    }

    /**
     * Sets the style of an vertical box
     * @param v said <code>VBox</code>
     * @author Ambre Collard
     */
    public void setStyleGrid(VBox v){
        v.setId("vBox");
        v.setAlignment(Pos.TOP_CENTER);
        v.setPrefSize(this.gridP.getPrefWidth(), this.gridP.getPrefHeight());
        v.setSpacing(20);
    }

    /**
     * Action performed when clicking on a profile
     * @param j the said <code>Joueur</code> representing the profile
     * @throws Exception
     * @author Ambre Collard
     */
    public void btnOnAction(Joueur j) throws Exception{
        BaseDonneeJoueur.setJoueur(j);
        this.remove();
    }

    /**
     * Removes the pop up and reload the background panes to match the current players parameters
     * @throws Exception
     * @author Ambre Collard
     * @see MainPanel#pasteAndHandle
     */
    @Override
    public void remove () throws Exception{
        super.remove();
        MainPanel main= new MainPanel(this.getParent());
        main.pasteAndHandle("/view/main_menu.fxml", new MainMenuEventHandler(this.getParent()));
    }
}
