package com.monappli;

import javafx.scene.control.Button;

public class TestButton extends Button{
    int i;
    public TestButton (int n, String text){
        super(text);
        i=n;
        this.setOnAction(event -> {
            System.out.println("Bonjour");
        });
    }
}
