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
    private BaseDonneeJoueur bdd;

    @BeforeAll
    void initAll() throws Exception {
        this.sauvegarde = new SauvegardeGrille();
        this.bdd = new BaseDonneeJoueur();
        this.parametre = new Parametre();
        this.ilot1 = new Ilot(3, 2, 1,false);
        this.ilot2 = new Ilot(2, 1, 1,false);

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
    void sauvegarderParametre(){
        Color couleur_texte_test = Color.rgb(122, 100, 100);
        Color couleur_texte_init = parametre.getCouleur_texte();
        parametre.setCouleur_texte(couleur_texte_test);
        System.out.println("Modif de Couleur_texte = " + parametre.getCouleur_texte());
        sauvegarde.actualiserFichierParametre(fichier_parametre, parametre, null);
        parametre.setCouleur_texte(couleur_texte_init);
        sauvegarde.actualiserFichierParametre(fichier_parametre, parametre, null);
    }

    @Test
    void chargerFichierParametre() throws IOException, ClassNotFoundException{
        File testEmptyFile = new File(fichier_parametre);
        int[] n_taille_fenetre = {1920, 1080};
        if (!testEmptyFile.exists() || testEmptyFile.length() == 0){
            Parametre temp_param = new Parametre();
            BaseDonneeJoueur temp_bdd = new BaseDonneeJoueur();
            temp_param.setCouleur_texte(Color.PINK);
            temp_param.setCouleur_ilot(Color.BLUE);
            temp_param.setCouleur_pont(Color.BLUE);
            temp_param.setCouleur_aide_erreur(Color.BLUE);
            temp_param.setCouleur_fond(Color.BLUE);
            temp_param.setTaille_texte(12);
            temp_param.setTaille_fenetre(n_taille_fenetre);
            temp_param.setAffichage_depassment_cardinalite(false);
            temp_param.setAffichage_groupe_ferme(true);
            temp_param.setAffichage_ponts_possible(false);
            Joueur joueur1 = new Joueur("Bonathan" );
            Joueur joueur2 = new Joueur("Bob");
            Joueur joueur3 = new Joueur("Barry");
            Joueur joueur4 = new Joueur("Bartholomet");
            /*temp_bdd.addJoueur(joueur1);
            temp_bdd.addJoueur(joueur2);
            temp_bdd.addJoueur(joueur3);
            temp_bdd.addJoueur(joueur4);*/
            sauvegarde.actualiserFichierParametre(fichier_parametre, temp_param, temp_bdd);
            parametre = sauvegarde.chargerFichierParametre(fichier_parametre);
            bdd = sauvegarde.chargerFichierBdd(fichier_parametre);

        }
        else{
            parametre = sauvegarde.chargerFichierParametre(fichier_parametre);
            bdd = sauvegarde.chargerFichierBdd(fichier_parametre);
        }
        System.out.println(parametre.toString());
        System.out.println(bdd.toString());
        
    }

    
}

       




    






