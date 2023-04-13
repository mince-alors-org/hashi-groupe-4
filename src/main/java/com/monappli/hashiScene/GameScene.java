package com.monappli.hashiScene;

import com.monappli.Aide;
import com.monappli.Grille;
import com.monappli.handlers.GameHandler;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;

public class GameScene extends MainPanel{
    Grille grille;
    Pane gridPlacement;
    String lvlName;

    public  GameScene (Pane parent){
        super(parent);
    }

    public void pasteAndHandle(String nomF, GameHandler hand, String lvlName) throws Exception{
        super.pasteAndHandle(nomF, hand);
        this.grille= new Grille(lvlName, (Pane)this.getParent().lookup("#gridPlacement"), (Canvas)this.getParent().lookup("#fond"), this.getParent());
        Aide.setGrilleAide(grille);
        hand.setGrille(this.grille);
    }
}
