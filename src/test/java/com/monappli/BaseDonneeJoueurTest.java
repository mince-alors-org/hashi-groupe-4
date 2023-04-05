package com.monappli;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.sound.midi.MidiChannel;

import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseDonneeJoueurTest {
    private BaseDonneeJoueur database;

    @BeforeAll
    public void initAll() throws Exception{
        database = new BaseDonneeJoueur();
        Joueur j = new Joueur("Jean", "4444");
        BaseDonneeJoueur.addJoueur(j);
        BaseDonneeJoueur.setJoueur(j);
    }

    @AfterEach
    public void afficheOk(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " Ok");
        System.out.println(database.toString());  
    }

    @Test
    public void testAddJoueur() throws Exception{
        Joueur j2 = new Joueur("Karl", "mdp2");

        System.out.println( database.addJoueur(j2));
    }

    @Test
    public void testCréerFichierSauvegarde() throws IOException{
        Joueur joueur = new Joueur("Baptiste","mdp");

        joueur.initSave(joueur.getnom(), "1-1.niv");
        
    }

    @Test
    public void testExists(){
        System.out.println(BaseDonneeJoueur.exists("michel"));
    }

    @Test 
    public void testaddScore()throws Exception{
        Joueur j2 = new Joueur("Karl", "mdp2");
        database.addScore( "1-3", 800);
    }

    @Test
    public void testLoadparam() throws Exception{
        Joueur j2 = new Joueur("Karl", "mdp2");
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.loadParam();
        System.out.println(Parametre.affiche());
    }

    @Test
    public void testChangePlayer() throws Exception{
        Joueur j2 = new Joueur("Michel", "mdp1");
        Joueur j1 = new Joueur("Karl", "mdp2");
        database.addJoueur(j2);
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.loadParam();
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.changePlayer( j2, "mdp1");
        System.out.println(Parametre.affiche());
    }

    @Test
    public void testGetJoueur() throws Exception{
        System.out.println( BaseDonneeJoueur.getJoueur("Michel", "mdp1"));
    }

    @Test
    public void testGetChip() throws Exception{
        System.out.println(BaseDonneeJoueur.getChipColor(Hashi.joueur));
        Joueur j= BaseDonneeJoueur.getJoueur("Michel", "mdp1");
        System.out.println(BaseDonneeJoueur.getChipColor(j));
    }
}
