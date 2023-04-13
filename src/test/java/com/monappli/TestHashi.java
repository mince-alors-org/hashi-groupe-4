package com.monappli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;


import javafx.application.Application;

public class TestHashi {
    
	@BeforeEach
	public void init(TestInfo testInfo){
        System.out.println("Debut test");
	}
    @Test
    public void LancementInterface(){
        System.out.println("Test interface globale");
        Application.launch(Hashi.class);
        
    }
}
