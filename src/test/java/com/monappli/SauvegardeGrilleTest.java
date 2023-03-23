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
import java.util.Arrays;
import java.awt.*;

@TestInstance(Lifecycle.PER_CLASS)
public class SauvegardeGrilleTest {
    private SauvegardeGrille sauvegarde;
    private Parametre parametre;
    private Ilot ilot1;
    private Ilot ilot2;
    private Pont p1;
    String fichier_sauvegarde = "save_move.txt";
    String fichier_parametre = "param.txt";
    @BeforeAll
    void initAll() throws Exception{
        this.sauvegarde = new SauvegardeGrille();
        this.parametre = new Parametre();
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

    @Test
    void chargerFichierParametre() throws IOException{
        sauvegarde.chargerFichierParametre(fichier_parametre, parametre);
        System.out.println("Couleur_texte = " + parametre.getCouleur_texte());
        System.out.println("Couleur_ilot = " + parametre.getCouleur_ilot());
        System.out.println("Couleur_pont = " + parametre.getCouleur_pont());
        System.out.println("Couleur_aide_erreur = " + parametre.getCouleur_aide_erreur());
        System.out.println("Couleur_fond = "+ parametre.getCouleur_fond());
        System.out.println("Taille_texte = " + parametre.getTaille_texte());
        System.out.println("Taille_fenetre = " + Arrays.toString(parametre.getTaille_fenetre()));
        System.out.println("Affichage_depassement_cardinalite = " + parametre.isAffichage_depassment_cardinalite());
        System.out.println("Affichage_groupe_ferme = " + parametre.isAffichage_groupe_ferme());
        System.out.println("Affichage_ponts_possible = " + parametre.isAffichage_ponts_possible());
        
    }

    // @Test
    // void actualiserFichierParametre(){
    //     Color couleur_texte_test = new Color(122, 100, 100);
    //     Color couleur_texte_init = parametre.getCouleur_texte();
    //     parametre.setCouleur_texte(couleur_texte_test);
    //     System.out.println("Modif de Couleur_texte = " + parametre.getCouleur_texte());
    //     sauvegarde.actualiserFichierParametre(fichier_parametre, parametre);
    //     parametre.setCouleur_texte(couleur_texte_init);
    //     sauvegarde.actualiserFichierParametre(fichier_parametre, parametre);
    // }
}
    
