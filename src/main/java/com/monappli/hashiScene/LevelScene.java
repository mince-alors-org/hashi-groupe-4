package com.monappli.hashiScene;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import javafx.scene.layout.Pane;

public class LevelScene extends MainPanel {
    

    public LevelScene(Pane parent){
        super(parent);
    }

    public void  initGrid(int nb_lvl){
        
    }

    public int countLvl(int diff){
        File directory=new File("src/main/java/com/monappli/niveaux");
        ArrayList<String> res= new ArrayList<String>();
        ArrayList<String> search= new ArrayList<String>();

        for (String e : directory.list()){
            search.add(e);
        }

        Collections.sort(search);
        for (String e : search){
            if (Pattern.matches("1.*", e)){
                System.out.println(e);
                res.add(e);
            }
        }
        return res.size();
    }

    
}
