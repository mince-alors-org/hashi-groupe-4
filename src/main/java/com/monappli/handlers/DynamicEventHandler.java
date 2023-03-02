package com.monappli.handlers;

//import java.beans.EventHandler;

//import javax.swing.Action;

//import javafx.event.*;  
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.*;
import javafx.scene.layout.Pane;
//import javafx.scene.Parent;

//import com.monappli.hashiScene.*;

public class DynamicEventHandler implements Handler{
    public Pane parentPane;

    public DynamicEventHandler(Pane parentPane){
        this.parentPane=parentPane;
    }
}
