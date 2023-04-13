package com.monappli;

import java.io.Serializable;

import javafx.scene.control.Button;

/**
 * Button graphically representing an Isle
 * @author Ambre Collard
 */
public class IlotBtn extends Button implements Serializable{


    public IlotBtn(String lab, int x, int y){
        super(lab);
    } 
}
