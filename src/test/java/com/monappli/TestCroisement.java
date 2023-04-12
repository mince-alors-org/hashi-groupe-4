package com.monappli;

import com.monappli.hashiScene.GameScene;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCroisement{

  @Test
  public void test(){
    GameScene.formateLvlName("../niveaux/2-1.niv");
  }

}
