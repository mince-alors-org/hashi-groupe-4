package com.monappli;

import javafx.scene.control.Button;

public class IlotBtn extends Button {
    private int x;
    private int y;

    public IlotBtn(String lab, int x, int y){
        super(lab);
        this.x= x;
        this.y=y;
    } 
}
