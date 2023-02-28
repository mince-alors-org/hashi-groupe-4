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
    String fichier_sauvegarde = "./save_move.txt";
    @BeforeAll
    void initAll() throws Exception{
        this.sauvegarde = new SauvegardeGrille();

        this.ilot1 = new Ilot(3, 2, 1);
        this.ilot2 = new Ilot(2, 1, 1);

        this.p1 = new Pont(ilot2, ilot1);
        try {
            sauvegarde.chargerFichier(fichier_sauvegarde);

        } catch (IOException e) {
            fail("Exception thrown: " + e);
        }
        assertEquals(13, sauvegarde.getPileCoupsSize());
    }

    @AfterEach
    void afficheOk(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " Ok");
    }

    @Test
    public void ajoutUnPont(){
        System.out.println("Nom du fichier " + fichier_sauvegarde);
        p1 = new Pont(ilot2, ilot1);
        sauvegarde.ajoutCoup(p1);
        assertEquals(14, sauvegarde.getPileCoupsSize());
    }

    @Test
    void annulerRetablir(){
        sauvegarde.annuler();
        assertEquals(13, sauvegarde.getPileCoupsSize());
        sauvegarde.actualiserFichier(fichier_sauvegarde);
        sauvegarde.retablir();
        assertEquals(14, sauvegarde.getPileCoupsSize());
    }
}
