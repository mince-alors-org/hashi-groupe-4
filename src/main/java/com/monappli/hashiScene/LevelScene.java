package com.monappli.hashiScene;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.monappli.Grille;
import com.monappli.Parametre;
import com.monappli.handlers.GameHandler;
import com.monappli.handlers.LevelSelectHandler;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.canvas.Canvas;


/**
 * This class allows to create a special pane and grid for selecting a level
 * @see MainPanel
 * @author Ambre Collard
 */
public class LevelScene extends MainPanel {
    /**
     * Static integer to set the number of rows and column of the level select grid
     */
    public static int size= 3;

    /**
     * Static integer to set the number of difficulties in total
     */
    public static int nbDiff=3;

    /**
     * Initialization of LevelScene
     * @param parent parent pane
     */
    public LevelScene(Pane parent){
        super(parent);
    }

    /**
     * Creates a grid of size*size buttons to select a level
     * Needs to be static because of the function diffClicked in LevelSelectHandler, the event controller of the level selecting scene
     * @see LevelSelectHandler#diffClicked
     * @param nbLvl numbers of level from a certain difficulty (1 through nbDiff)
     * @see LevelScene#nbDiff
     * @param w Width of the pane where the grid will be added
     * @param h Height of the pane where the grid will be added
     * @param backPane Background pane, used to change the whole interface when clicking on a level Button
     * @return <code>GridPane</code> the GridPane created from the parameters
     * @throws Exception
     * @author Ambre Collard
     */
    public GridPane  initGrid(int nbLvl, int w, int h, Pane backPane) throws Exception{
        GridPane grid= new GridPane();

        /*
         * Set the sizes of the columns to fill the whole underlying pane
         */
        for(int i=0; i<size; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(1.0*w/ (size)));
        }

        /*
         * Set the sizes of the rows to fill the whole underlying pane
         */
        for(int i=0; i<size;i++)
            grid.getRowConstraints().add(new RowConstraints(1.0*h / (size)));

        for (int i=size*size*(LevelSelectHandler.curPage-1); i< nbLvl && i < size*size*LevelSelectHandler.curPage; i++){
            Button btn = new Button(Integer.toString(i+1));


            /*
             * sets the button to the center of the cell
             */
            GridPane.setHalignment(btn, HPos.CENTER);
            GridPane.setValignment(btn, VPos.CENTER);

            //style and border
            btn.setStyle("-fx-background-radius: " + 10*size+";"+
                         "\n-fx-background-insets: 0 0 0 0;"+
                         "\n-fx-font:"+ (int)(size*12) +" px;");
            btn.setBorder(new Border(new BorderStroke(
										Parametre.getCouleur_fond(),
										BorderStrokeStyle.SOLID,
										new CornerRadii(7*size),
										new BorderWidths(2*size)
							)));

            //Size of the button set to ~0.77 the size of the cell (too big if not)
            btn.setPrefSize(
                (grid.getColumnConstraints().get(i%3).getPrefWidth())/1.3 ,
                (grid.getRowConstraints().get((int)i/size).getPrefHeight())/1.3
            );

            //adding the button to the grid
            grid.add(btn, i%size, (int)(i/size),1,1);

            //Set event handler
            btn.setOnAction(e -> {
                //Get the file name of the level related to the button
                String lvlF = "../niveaux/" +LevelSelectHandler.curDiff+"-"+ btn.getText()+".niv";

                //Creates a new game grid and the graphic interface
                GameScene game = new GameScene(this.getParent());
                GameHandler gh = new  GameHandler(this.getParent());
                try {

                    game.pasteAndHandle("/view/gameLayout.fxml",gh,lvlF );


                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //Get the level button above the game grid
                Button lvlButton = (Button)game.getCurPane().lookup("#levelButton");
                //Change the label to the level assigned name
                lvlButton.setText(LevelSelectHandler.curDiff + "-" + btn.getText());
                gh.setChrono();



            });

        }

        return grid;
    }


    /**
     * Counts the numbers of level related to a certain difficulty
     * For example:
     *    Level 3 of difficulty 2 would be named : 2-3
     * @param diff Difficulty the program wants to search
     * @return <code>int</code> the number of levels in this difficulty
     * @author Ambre Collard
     */
    public static int countLvl(int diff){
        File directory=new File("src/main/java/com/monappli/niveaux");
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


}
