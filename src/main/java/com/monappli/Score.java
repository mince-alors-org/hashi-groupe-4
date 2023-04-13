package com.monappli;

import java.io.Serializable;

public class Score implements Serializable {


  static final int seuil1 = 500;
  static final int seuil2 = 1000;


  static final int seuil3 = 1500;
  static int base_value = 250;



  private int value;
  private int cptAide;

  public Score() {
    value = base_value;
    cptAide = 0;
  }

  public int getCptAide() {
    return cptAide;
  }

  public void setCptAide(int cptAide) {
    this.cptAide = cptAide;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }



  public void computeValue(double chrono_time){

    if (chrono_time > seuil3){
      this.setValue(value - 200);
      return;
    }
    if (chrono_time > seuil2){
      this.setValue(value - 100);
      return;
    }
    if (chrono_time > seuil1 ){
      this.setValue(value - 30);
      return;
    }

  }

  @Override
  public String toString() {
    return "Score{" +
      "value=" + value +
      '}';
  }
}
