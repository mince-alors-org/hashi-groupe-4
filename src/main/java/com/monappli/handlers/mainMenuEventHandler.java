package com.monappli.handlers;

import com.monappli.hashiScene.LevelScene;
import com.monappli.hashiScene.MainPanel;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Handler of the main menu
 * @see DynamicEventHandler 
 *  @author Collard Matthis
 */
public class mainMenuEventHandler extends  DynamicEventHandler{

    /**
     * Play Button
     */
    @FXML
    private Button playButton;

    /**
     * Current Pane of the menu
     */
    @FXML
    private Pane menuPane;

    /**
     * Tutorial Button
     */
    @FXML
    private Button tutoButton;

    /**
     * Challenge Button
     */
    @FXML
    private Button challButton;

    /**
     * Parameters Button
     */
    @FXML
    private Button paramButton; 

    /**
     * Power Button (to quit the game)
     */
    @FXML
    private Button powerButton;

    /**
     * Initialization of mainMenuEventHandler
     * @param parentPane 
     */
    public mainMenuEventHandler(Pane parentPane){
        super(parentPane);
        setCurPane(menuPane);
    }

    /**
     * Action when playButton is clicked. Creates a LevelScene grid and changes the handler to LevelSelectHandler
     * @throws Exception
     * @see LevelScene
     * @see LevelSelectHandler
     */
    public void playClicked() throws Exception {
        LevelScene game= new LevelScene(this.getParentPane());
        game.pasteAndHandle("/view/levelSelect.fxml", new LevelSelectHandler(menuPane));
        Pane select= (Pane)game.getCurPane().lookup("#selectPane");
        GridPane selGrid= LevelScene.initGrid(LevelScene.countLvl(1),(int) select.getPrefWidth(), (int)select.getPrefHeight(), game.getParent());
        select.getChildren().add(selGrid);
    }

    /**
     * Action when powerButton is clicked. Quits the application
     */
    public void quitClicked() {
        Stage stage = (Stage) powerButton.getScene().getWindow();
        stage.close();
    }

    /**
     * @throws Exception
     */
    public void tutoClicked() throws Exception {
        MainPanel tuto= new MainPanel(this.getParentPane());
        
        
        tuto.pasteAndHandle("/view/tuto.fxml", new TutorialEventHandler(this.getParentPane()));
        System.out.println("Je suis tuto");
    }

    /**
     * 
     */
    public void challClicked() {
        System.out.println("Je suis defi");
    }

    /**
     * Action when paramButton is clicked. Creates a PopUp to change the parameters and sets a new ParamHandler on this PopUp
     * Clicking on the parameters button deactivates can not make another PopUp
     * @see PopUp
     * @see ParamHandler
     * @throws Exception
     */
    public void paramClicked() throws Exception{
        if (menuPane.lookup("#pop") == null){
            PopUp pop = new PopUp(menuPane);
            ParamHandler paramH= new ParamHandler(this.menuPane);
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
            System.out.println("Je suis Paramètre");
        }
    }

}
