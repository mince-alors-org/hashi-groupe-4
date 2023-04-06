package com.monappli;

public class Score {



  static int base_value = 50;



  private int value;

  public Score() {
    value = 50;
  }


  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }



  public void computeValue(double chrono_time){
    if (chrono_time > 500 ){
      this.setValue(value - 30);
    }

  }
}
