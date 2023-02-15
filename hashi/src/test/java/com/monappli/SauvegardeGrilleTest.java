package com.monappli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

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
    private Pont p1;
    @BeforeAll
    void initAll() throws Exception{
        this.sauvegarde = new SauvegardeGrille();

        this.ilot1 = new Ilot(3, 2, 1);
        this.ilot2 = new Ilot(2, 1, 1);

        this.p1 = new Pont(ilot2, ilot1);
        try {
            sauvegarde.chargerFichier();

        } catch (IOException e) {
            fail("Exception thrown: " + e);
        }
    }

    @AfterEach
    void afficheOk(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " Ok");
    }

    @Test
    public void ajoutUnPont(){
        p1 = new Pont(ilot2, ilot1);
        sauvegarde.ajoutCoup(p1);
        assertEquals(14, sauvegarde.getPileCoupsSize());
        sauvegarde.actualiserFichier();
    }

    @Test
    void annulerRetablir(){
        sauvegarde.annuler();
        assertEquals(13, sauvegarde.getPileCoupsSize());
        sauvegarde.actualiserFichier();
        sauvegarde.retablir();
        assertEquals(14, sauvegarde.getPileCoupsSize());
        sauvegarde.actualiserFichier();
    }
}
