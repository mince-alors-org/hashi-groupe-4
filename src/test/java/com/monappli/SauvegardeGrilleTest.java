package com.monappli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.util.Arrays;
import javafx.scene.paint.Color;
import java.awt.*;

@TestInstance(Lifecycle.PER_CLASS)
public class SauvegardeGrilleTest {
    private SauvegardeGrille sauvegarde;
    private Parametre parametre;
    private Ilot ilot1;
    private Ilot ilot2;
    private Pont p1;
    String fichier_sauvegarde = "src/test/java/com/monappli/save_move.txt";
    String fichier_parametre = "src/test/java/com/monappli/param.txt";

    @BeforeAll
    void initAll() throws Exception{
        this.sauvegarde = new SauvegardeGrille();
        this.parametre = new Parametre();
        this.ilot1 = new Ilot(3, 2, 1);
        this.ilot2 = new Ilot(2, 1, 1);

        this.p1 = new Pont(ilot2, ilot1);
        File testEmptyFile = new File(fichier_sauvegarde);
        if (!testEmptyFile.exists() || testEmptyFile.length() == 0){
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.ajoutCoup(p1);
            sauvegarde.actualiserFichier(fichier_sauvegarde);
        }
        else{
            sauvegarde.chargerFichier(fichier_sauvegarde);
        }
        
        assertEquals(14, sauvegarde.getPileCoupsSize());
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
        assertEquals(15, sauvegarde.getPileCoupsSize());

    }

    @Test
    void annulerRetablir() throws IOException{
        sauvegarde.annuler();
        assertEquals(14, sauvegarde.getPileCoupsSize());
        sauvegarde.actualiserFichier(fichier_sauvegarde);
        sauvegarde.retablir();
        assertEquals(15, sauvegarde.getPileCoupsSize());
    }

    @Test
    void chargerFichierParametre() throws IOException, ClassNotFoundException{
        File testEmptyFile = new File(fichier_parametre);
        int[] n_taille_fenetre = {1920, 1080};
        if (!testEmptyFile.exists() || testEmptyFile.length() == 0){
            
            parametre.setCouleur_texte(Color.BLUE);
            parametre.setCouleur_ilot(Color.BLUE);
            parametre.setCouleur_pont(Color.BLUE);
            parametre.setCouleur_aide_erreur(Color.BLUE);
            parametre.setCouleur_fond(Color.BLUE);
            parametre.setTaille_texte(12);
            parametre.setTaille_fenetre(n_taille_fenetre);
            parametre.setAffichage_depassment_cardinalite(false);
            parametre.setAffichage_groupe_ferme(true);
            parametre.setAffichage_ponts_possible(false);
            sauvegarde.actualiserFichierParametre(fichier_parametre, parametre);
        }
        else{
            sauvegarde.chargerFichierParametre(fichier_parametre, parametre);
        }
        System.out.print(parametre.toString());
        
    }

    @Test
    void sauvegarderParametre(){
        Color couleur_texte_test = Color.rgb(122, 100, 100);
        Color couleur_texte_init = parametre.getCouleur_texte();
        parametre.setCouleur_texte(couleur_texte_test);
        System.out.println("Modif de Couleur_texte = " + parametre.getCouleur_texte());
        sauvegarde.actualiserFichierParametre(fichier_parametre, parametre);
        parametre.setCouleur_texte(couleur_texte_init);
        sauvegarde.actualiserFichierParametre(fichier_parametre, parametre);
    }
}
    
