package com.monappli;

import java.io.*;

public class SauvegardeScore {

  public static Score currentScore;



  public static void saveScore(String lvlName, Score score) throws IOException {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(lvlName));
    oos.writeObject(lvlName);
    oos.writeObject(score);

  }



  public static void readScore(String lvlName) throws IOException, ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(lvlName));
    String name = (String) ois.readObject();
    Score score = (Score) ois.readObject();
    currentScore = score;

  }

  public static void createScoreFile(String path,String lvl) throws IOException {

    File file = new File(path);
    file.createNewFile();
  }






}
