package com.monappli;

import javafx.scene.control.Label;

public class Chrono extends Thread{
  boolean start;
  double time;


  Label timeDisplay;



  public Chrono() {
    this.start = false;
    this.time = 0;

  }


  public boolean isStart() {
    return start;
  }

  public Label getTimeDisplay() {
    return timeDisplay;
  }

  public void setTimeDisplay(Label timeDisplay) {
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

    while (start){

      try {
        this.sleep(100);
        time+=0.001;
        System.out.println(time);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }





    }
  }

  public void halt(){
    start = false;
  }
}
