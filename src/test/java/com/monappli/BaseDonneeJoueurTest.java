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
    }

    @AfterEach
    public void afficheOk(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " Ok");
    }

    @Test
    public void testAddJoueur() throws Exception{
        Joueur j2 = new Joueur("Karl");

        System.out.println( BaseDonneeJoueur.addJoueur(j2));
        BaseDonneeJoueur.deletePlayer(j2);
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
        Joueur j2 = new Joueur("123");
        if (!BaseDonneeJoueur.exists(j2)){
            BaseDonneeJoueur.addJoueur(j2);
        }
        BaseDonneeJoueur.setJoueur(j2);
        BaseDonneeJoueur.addScore( "1-3", 300);
        BaseDonneeJoueur.deletePlayer(j2);
        
    }

    @Test
    public void testLoadparam() throws Exception{
        Joueur j2 = new Joueur("123");
        if (!BaseDonneeJoueur.exists(j2)){
            BaseDonneeJoueur.addJoueur(j2);
        }
        BaseDonneeJoueur.setJoueur(j2);
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.loadParam();
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.deletePlayer(j2);
    }

    @Test
    public void testChangePlayer() throws Exception{
        Joueur j2 = new Joueur("123");
        Joueur j1 = new Joueur("345");
        if (!BaseDonneeJoueur.exists(j1)){
            BaseDonneeJoueur.addJoueur(j1);
        }
        if (!BaseDonneeJoueur.exists(j2)){
            BaseDonneeJoueur.addJoueur(j2);
        }
        BaseDonneeJoueur.addJoueur(j2);
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.loadParam();
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.changePlayer(j2);
        System.out.println(Parametre.affiche());
        BaseDonneeJoueur.deletePlayer(j2);
        BaseDonneeJoueur.deletePlayer(j1);
    }

    @Test
    public void testGetJoueur() throws Exception{
        System.out.println( BaseDonneeJoueur.getJoueur("Michel"));
    }

    @Test
    public void testGetChip() throws Exception{
        Joueur j= BaseDonneeJoueur.getJoueur("Karl");
        if(j!=null)
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
        Joueur j= new Joueur("123");
        if (!BaseDonneeJoueur.exists(j)){
            BaseDonneeJoueur.addJoueur(j);
        }
        BaseDonneeJoueur.addJoueur(j);
        BaseDonneeJoueur.changePlayer(j);
        Parametre.setCouleur_texte(Color.RED);
        System.out.println( Parametre.affiche());
        BaseDonneeJoueur.saveParam();
        BaseDonneeJoueur.loadParam();
        System.out.println( Parametre.affiche());
        BaseDonneeJoueur.deletePlayer(j);
    }

    @Test 
    public void testDelete() throws Exception{
        Joueur j= BaseDonneeJoueur.getJoueur("Michel");
        if(j != null)
            System.out.println( BaseDonneeJoueur.deletePlayer(j));
    }
}
