package com.monappli;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseDonneeJoueurTest {
    private BaseDonneeJoueur database;

    @BeforeAll
    public void initAll() {
        database = new BaseDonneeJoueur();
    }

    @AfterEach
    public void afficheOk(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " Ok");
        System.out.println(database.toString());  
    }

    @Test
    public void testAddJoueur() {
        Joueur joueur = new Joueur("Bonathan", "mdp1");
        database.addJoueur(joueur);

        Joueur recupJoueur = database.getJoueurNom("Bonathan");
        assertEquals(joueur, recupJoueur);
    }

    @Test
    public void testGetJoueurNom() {
        Joueur j2 = new Joueur("Karl", "mdp2");

        database.addJoueur(j2);

        Joueur recupJoueur = database.getJoueurNom("Bonathan");
        recupJoueur = database.getJoueurNom("Karl");
        assertEquals(j2, recupJoueur);
    }

    @Test
    public void testCréerFichierSauvegarde() throws IOException{
        Joueur joueur = new Joueur("Baptiste","mdp");

        joueur.initSave(joueur.getnom(), "1-1.niv");
        
    }
}
