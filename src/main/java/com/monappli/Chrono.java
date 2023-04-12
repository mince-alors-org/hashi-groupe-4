package com.monappli;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class Chrono extends Thread{
  boolean start;
  double time;


  Button timeDisplay;

  public Chrono(Button button, double time) {
    this.start = false;
    this.time = time;

    timeDisplay = button;

  }
  public Chrono(Button button) {
    this(button,0);

  }

  public Chrono() {
    this.start = false;
    this.time = 0;

  }




  public boolean isStart() {
    return start;
  }

  public Button getTimeDisplay() {
    return timeDisplay;
  }

  public void setTimeDisplay(Button timeDisplay) {
    this.timeDisplay = timeDisplay;
  }

  public void setStart(boolean start) {
    this.start = start;
  }

  public double getTime() {
    return time;
  }

  public void setTime(double time) {
    this.time = time;
  }


  @Override
  public void run() {
    start = true;
    while(start){
    try{
    Thread.sleep(100);
    }
    catch(Exception e){
      e.printStackTrace();
    }
    time+=1;
    Platform.runLater ( new Runnable() {
      public void run(){
        refreshTime();
      }
    });
  }

  }

  public void refreshTime(){
      timeDisplay.setText(String.valueOf(time));
  }










  public void halt(){
    start = false;
  }
}
