package com.monappli;

import javafx.scene.control.Button;

import java.io.Serializable;

/**
 * Button graphically representing an Isle
 * @author Ambre Collard
 */
public class IlotBtn extends Button implements Serializable {


    public IlotBtn(String lab, int x, int y){
        super(lab);
    }
}
