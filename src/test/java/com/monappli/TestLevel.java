package com.monappli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.monappli.hashiScene.LevelScene;

public class TestLevel {
    @BeforeEach
	public void init(TestInfo testInfo){
        System.out.println("Debut test");
	}
    @Test
    public void LancementInterface(){
        System.out.println("Level initiation Test :");
        LevelScene niv = new LevelScene(null);
        System.out.println( niv.countLvl(0));
    }
}
