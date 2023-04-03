package com.monappli;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Chrono implements Runnable{
  boolean start;
  double time;


  Button timeDisplay;

  public Chrono(Button button) {
    this.start = false;
    this.time = 0;

    timeDisplay = button;

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

    while (start) {


      time += 0.1;
      timeDisplay.setText(String.valueOf(time));
      System.out.println(time);
    }
  }










  public void halt(){
    start = false;
  }
}
