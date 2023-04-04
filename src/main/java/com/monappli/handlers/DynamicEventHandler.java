package com.monappli.handlers;


import com.monappli.hashiScene.MainPanel;
import com.monappli.hashiScene.PopUp;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class is the core of our different event handlers
 * @see Handler
 * @author Ambre Collard
 */

public class DynamicEventHandler implements Handler{
    /**
     * backGround Pane
     */
    @FXML
    public Pane backGround;

    /**
     * Parent pane of the current handled Pane
     */
    private Pane parentPane;

    /**
     * Current handled pane
     */
    private Pane curPane;

    /**
     * Initialization of the DynamicEventHandler
     * @param parentPane
     */
    public DynamicEventHandler(Pane parentPane){
        this.parentPane=parentPane;
        setCurPane(backGround);
    }

    /**
     * Get the parent pane
     * @return parentPane
     * @see DynamicEventHandler#parentPane
     */
    public Pane getParentPane(){
        return parentPane;
    }

    /**
     * Get the current pane
     * @return curPane
     * @see DynamicEventHandler#curPane
     */
    public Pane getCurPane(){
        return curPane;
    }

    /**
     * Set the pane to be handled
     * @param curPane Pane handled
     * @see DynamicEventHandler#curPane
     */
    public void setCurPane(Pane curPane){
        this.curPane=curPane;
    }

    /**
     * Action when powerButton is clicked. Quits the application
     */
    public void quitClicked(){
        if (backGround.lookup("#pop") == null){
            Stage stage = (Stage) backGround.getScene().getWindow();
            stage.close();
        }
    }
 
    /**
     * Action when paramButton is clicked. Creates a PopUp to change the parameters and sets a new ParamHandler on this PopUp
     * Clicking on the parameters button deactivates can not make another PopUp
     * @see PopUp
     * @see ParamHandler
     * @throws Exception if the PopUp can't load
     * @author Ambre Collard
     */
    public void paramClicked() throws Exception{
        if (backGround.lookup("#pop") == null){
            PopUp pop = new PopUp(this.getCurPane());
            ParamHandler paramH= new ParamHandler(this.backGround);
            pop.pasteAndHandle("/view/parameters.fxml", paramH);
            paramH.setAll();
        }
    }

    public void hashiClicked() throws Exception{
        MainPanel main= new MainPanel(this.getParentPane());
        main.pasteAndHandle("/view/main_menu.fxml", new mainMenuEventHandler(this.getParentPane()));
    }
}