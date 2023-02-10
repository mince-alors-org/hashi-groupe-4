package com.monappli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class SauvegardeGrilleTest {
    private SauvegardeGrille sauvegarde;

    private Ilot ilot1;
    private Ilot ilot2;
    private Ilot ilot3;
    private Pont p1;
    private Pont p2;
    @BeforeAll
    void initAll(){
        sauvegarde = new SauvegardeGrille();

        ilot1 = new Ilot(1, 2, 4);
        ilot2 = new Ilot(6, 7, 8);
        ilot3 = new Ilot(0, 0, 0);
        p1 = new Pont(ilot1, ilot2);
        p2 = new Pont(ilot1, ilot3);
    }

    @AfterEach
    void afficheOk(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " Ok");
    }

    @Test
    void ajoutCoupDeuxValeurs(){
        sauvegarde.ajoutCoup(p1);
        sauvegarde.ajoutCoup(p2);
        assertEquals(2, sauvegarde.getPileCoupsSize());
        sauvegarde.actualiserFichier();
    
    }


}
