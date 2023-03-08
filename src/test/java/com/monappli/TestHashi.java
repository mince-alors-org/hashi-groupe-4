package com.monappli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class TestHashi {
    
	@BeforeEach
	public void init(TestInfo testInfo){
        System.out.println("Debut test");
	}
    @Test
    public void afficheRes(){
        new Grille("1-1.niv");
    }
}
