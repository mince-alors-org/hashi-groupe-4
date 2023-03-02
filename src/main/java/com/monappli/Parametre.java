package com.monappli;

import java.awt.*;

/**
 * Cette classe sert à enregistrer durant l'exécution du programme les paramètres, de manière à ce que les autres classes puissent lui demander
 * @author NAIL Leo
 * @version 1.0
 */
public class Parametre {
    /**
     * Cette variable sert à connaitre de quelles couleurs seront les messages écrits de l'aplication
     */
    Color couleur_texte;
    Color couleur_ilot;
    Color couleur_pont;
    /**
     * Cette variable sert à stocker la couleur des différentes aides qui peuvent être mise sur la grille
     */
    Color couleur_aide_erreur;
    Color couleur_fond;
    int taille_texte;
    int[] taille_fenetre;
    /**
     * Cette variable représente la possibilitée d'afficher automatiquement une aide visuelle quand trop de pont relie une île
     */
    boolean affichage_depassment_cardinalite;
    /**
     * Cette variable représente la possibilité de prévenir le jouueur lorsqu'il créer un groupe d'île ne pouvant pas être relié à un autre groupe d'île
     */
    boolean affichage_groupe_ferme;
    /**
     * doit-on afficher tous les ponts que l'on peut créer quand on click sur une île
     */
    boolean affichage_ponts_possible;

    Parametre() {
        super();
    }

    public Color getCouleur_texte() {
        return couleur_texte;
    }

    public void setCouleur_texte(Color couleur_texte) {
        this.couleur_texte = couleur_texte;
    }

    public Color getCouleur_ilot() {
        return couleur_ilot;
    }

    public void setCouleur_ilot(Color couleur_ile) {
        this.couleur_ilot = couleur_ile;
    }

    public Color getCouleur_pont() {
        return couleur_pont;
    }

    public void setCouleur_pont(Color couleur_pont) {
        this.couleur_pont = couleur_pont;
    }

    public Color getCouleur_aide_erreur() {
        return couleur_aide_erreur;
    }

    public void setCouleur_aide_erreur(Color couleur_aide_erreur) {
        this.couleur_aide_erreur = couleur_aide_erreur;
    }

    public int getTaille_texte() {
        return taille_texte;
    }

    public void setTaille_texte(int taille_texte) {
        this.taille_texte = taille_texte;
    }

    public int[] getTaille_fenetre() {
        return taille_fenetre;
    }

    public void setTaille_fenetre(int[] taille_fenetre) {
        this.taille_fenetre = taille_fenetre;
    }

    public boolean isAffichage_depassment_cardinalite() {
        return affichage_depassment_cardinalite;
    }

    public void setAffichage_depassment_cardinalite(boolean affichage_depassment_cardinalite) {
        this.affichage_depassment_cardinalite = affichage_depassment_cardinalite;
    }

    public boolean isAffichage_groupe_ferme() {
        return affichage_groupe_ferme;
    }

    public void setAffichage_groupe_ferme(boolean affichage_groupe_ferme) {
        this.affichage_groupe_ferme = affichage_groupe_ferme;
    }

    public boolean isAffichage_ponts_possible() {
        return affichage_ponts_possible;
    }

    public void setAffichage_ponts_possible(boolean affichage_ponts_possible) {
        this.affichage_ponts_possible = affichage_ponts_possible;
    }
}