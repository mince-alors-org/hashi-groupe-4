package com.monappli.hashiScene;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.monappli.Parametre;
import com.monappli.handlers.FenetreAide;
import com.monappli.handlers.GameHandler;
import com.monappli.handlers.LevelSelectHandler;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * This class allows to create a special pane and grid for selecting a level
 * @see MainPanel
 * @author Ambre Collard
 */
public class LevelScene extends ResolScene {

    public LevelScene(Pane parent) {
        super(parent);
    }

    /**
     * Counts the numbers of level related to a certain difficulty 
     * For example:
     *    Level 3 of difficulty 2 would be named : 2-3
     * @param diff Difficulty the program wants to search
     * @return <code>int</code> the number of levels in this difficulty
     * @author Ambre Collard
     */
    @Override
    public int countLvl(int diff){
        File directory=new File("src/main/java/com/monappli/niveaux/");
        ArrayList<String> res= new ArrayList<String>();
        ArrayList<String> search= new ArrayList<String>();

        for (String e : directory.list()){
            search.add(e);
        }

        for (String e : search){
            if (Pattern.matches(diff +".*", e)){
                res.add(e);
            }
        }
        return res.size();
    }
    /**
     * Action performed when clicking on a button from the selection grid.
     * @param btn
     * @author Ambre Collard
     */
    @Override
    public void onAction(Button btn){

                //Get the file name of the level related to the button
                String lvlF = "../niveaux/" +LevelSelectHandler.curDiff+"-"+ btn.getText()+".niv";

                //Creates a new game grid and the graphic interface
                GameScene game = new GameScene(this.getParent());
                try {
                    game.pasteAndHandle("/view/gameLayout.fxml", new GameHandler(this.getParent()),lvlF );
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //Get the level button above the game grid
                Button lvlButton = (Button)game.getCurPane().lookup("#levelButton");
                //Change the label to the level assigned name
                lvlButton.setText(LevelSelectHandler.curDiff + "-" + btn.getText());
    }
    
}
