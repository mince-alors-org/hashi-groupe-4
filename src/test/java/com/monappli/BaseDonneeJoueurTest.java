package com.monappli;

import org.junit.jupiter.api.*;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
public class BaseDonneeJoueurTest {

    @BeforeAll
    public void initAll() throws Exception{
        Joueur j = new Joueur("Jean");
        BaseDonneeJoueur.addJoueur(j);
        BaseDonneeJoueur.setJoueur(j);
    }

    @AfterEach
    public void afficheOk(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " Ok");
    }

    @Test
    public void testAddJoueur() throws Exception{
        Joueur j2 = new Joueur("Karl");

        System.out.println( BaseDonneeJoueur.addJoueur(j2));
    }

    @Test
    public void testCréerFichierSauvegarde() throws IOException{
        Joueur joueur = new Joueur("Baptiste");

        joueur.initSave(joueur.getnom(), "1-1.niv");
        
    }

    @Test
    public void testExists(){
        System.out.println(BaseDonneeJoueur.exists("Michel"));
    }

    @Test 
    public void testaddScore()throws Exception{
        Joueur j2 = new Joueur("Karl");
        BaseDonneeJoueur.setJoueur(j2);
        BaseDonneeJoueur.addScore( "1-3", 300);
    }

    @Test
    public void testLoadparam() throws Exception{
        Joueur j2 = new Joueur("Karl");
        BaseDonneeJoueur.setJoueur(j2);
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.loadParam();
        System.out.println(Parametre.affiche());
    }

    @Test
    public void testChangePlayer() throws Exception{
        Joueur j2 = new Joueur("Michel");
        Joueur j1 = new Joueur("Karl");
        BaseDonneeJoueur.addJoueur(j2);
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.loadParam();
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.changePlayer(j2);
        System.out.println(Parametre.affiche());
    }

    @Test
    public void testGetJoueur() throws Exception{
        System.out.println( BaseDonneeJoueur.getJoueur("Michel"));
    }

    @Test
    public void testGetChip() throws Exception{
        System.out.println(BaseDonneeJoueur.getChipColor(Hashi.joueur));
        Joueur j= BaseDonneeJoueur.getJoueur("Karl");
        System.out.println(BaseDonneeJoueur.getChipColor(j));
    }

    @Test
    public void testGetAll() throws Exception{
        ArrayList<Joueur> tab= BaseDonneeJoueur.getAllPlayers();
        for (Joueur j : tab){
            System.out.println(j);
        }
    }

    @Test
    public void testSaveParam() throws Exception{
        System.out.println( Parametre.affiche());
        Joueur j= new Joueur("Michel");
        BaseDonneeJoueur.addJoueur(j);
        BaseDonneeJoueur.changePlayer(j);
        Parametre.setCouleur_texte(Color.RED);
        System.out.println( Parametre.affiche());
        BaseDonneeJoueur.saveParam();
        BaseDonneeJoueur.loadParam();
        System.out.println( Parametre.affiche());
    }

    @Test 
    public void testDelete() throws Exception{
        Joueur j= BaseDonneeJoueur.getJoueur("Michel");
        System.out.println( BaseDonneeJoueur.deletePlayer(j));
    }
}
