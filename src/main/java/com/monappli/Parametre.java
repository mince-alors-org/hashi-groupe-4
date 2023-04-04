package com.monappli;

import java.io.Serializable;

import javafx.css.FontCssMetaData;
import javafx.scene.paint.Color;

/**
 * Cette classe sert à enregistrer durant l'exécution du programme les paramètres, de manière à ce que les autres classes puissent lui demander
 * @author NAIL Leo
 * @version 1.0
 */
public  class Parametre implements Serializable{
    /**
     * Cette variable sert à connaitre de quelles couleurs seront les messages écrits de l'aplication
     */
    private static Color couleur_texte=Color.BLACK;
    private static Color couleur_ilot= Color.WHITE;
    private static Color couleur_pont= Color.BLACK;
    /**
     * Cette variable sert à stocker la couleur des différentes aides qui peuvent être mise sur la grille
     */
    private static Color couleur_aide_erreur=Color.BLACK;
    private static Color couleur_fond=new Color(1.0*69/255,1.0*123/255,1.0*248/255,1);
    private static int taille_texte;
    private static int[] taille_fenetre = {800,450};
    /**
     * Cette variable représente la possibilitée d'afficher automatiquement une aide visuelle quand trop de pont relie une île
     */
    private static boolean affichage_depassment_cardinalite;
    /**
     * Cette variable représente la possibilité de prévenir le jouueur lorsqu'il créer un groupe d'île ne pouvant pas être relié à un autre groupe d'île
     */
    private static boolean affichage_groupe_ferme;
    /**
     * doit-on afficher tous les ponts que l'on peut créer quand on click sur une île
     */
    private static boolean affichage_ponts_possible;


    public static Color getCouleur_texte() {
        return couleur_texte;
    }

    public static void setCouleur_texte(Color n_couleur_texte) {
        couleur_texte = n_couleur_texte;
    }

    public static void setCouleur_fond(Color fond){
        couleur_fond=fond;
    }

    public static Color getCouleur_fond(){
        return couleur_fond;
    }

    public static Color getCouleur_ilot() {
        return couleur_ilot;
    }

    public static void setCouleur_ilot(Color couleur_ile) {
        couleur_ilot = couleur_ile;
    }

    public static Color getCouleur_pont() {
        return couleur_pont;
    }

    public static void setCouleur_pont(Color n_couleur_pont) {
        couleur_pont = n_couleur_pont;
    }

    public static Color getCouleur_aide_erreur() {
        return couleur_aide_erreur;
    }

    public static void setCouleur_aide_erreur(Color n_couleur_aide_erreur) {
        couleur_aide_erreur = n_couleur_aide_erreur;
    }


    public static int getTaille_texte() {
        return taille_texte;
    }

    public static void setTaille_texte(int n_taille_texte) {
        taille_texte = n_taille_texte;
    }

    public static  int[] getTaille_fenetre() {
        return taille_fenetre;
    }

    public static void setTaille_fenetre(int[] n_taille_fenetre) {
        taille_fenetre = n_taille_fenetre;
    }

    public static  boolean isAffichage_depassment_cardinalite() {
        return affichage_depassment_cardinalite;
    }

    public static  void setAffichage_depassment_cardinalite(boolean n_affichage_depassment_cardinalite) {
        affichage_depassment_cardinalite = n_affichage_depassment_cardinalite;
    }

    public static boolean isAffichage_groupe_ferme() {
        return affichage_groupe_ferme;
    }

    public static void setAffichage_groupe_ferme(boolean n_affichage_groupe_ferme) {
        affichage_groupe_ferme = n_affichage_groupe_ferme;
    }

    public static boolean isAffichage_ponts_possible() {
        return affichage_ponts_possible;
    }

    public static void setAffichage_ponts_possible(boolean n_affichage_ponts_possible) {
        affichage_ponts_possible = n_affichage_ponts_possible;
    }

    @Override
    public String toString(){
        return "Param{" +
                "couleur_texte=" + toRGBForCSS(couleur_texte) + 
                ", couleur_ilot=" + toRGBForCSS(couleur_ilot) +
                ", couleur_pont=" + toRGBForCSS(couleur_pont) +
                ", couleur_aide_erreur=" + toRGBForCSS(couleur_aide_erreur) +
                ", couleur_fond=" + toRGBForCSS(couleur_fond) +
                ", taille_texte=" + taille_texte +
                ", taille_fenetre=" + taille_fenetre[0] + "," + taille_fenetre[1] +
                ", affichage_depassment_cardinalite=" + affichage_depassment_cardinalite +
                ", affichage_groupe_ferme=" + affichage_groupe_ferme +
                ", affichage_ponts_possible=" + affichage_ponts_possible +
                "}";
    }

    public static String toRGBForCSS(Color c){
        return String.format( "#%02X%02X%02X",
            (int)( c.getRed() * 255 ),
            (int)( c.getGreen() * 255 ),
            (int)( c.getBlue() * 255 ) );
    }

    public static Color  toColor(String str){
        return Color.web(str);
    }

    public void chargerFichierParametre(String fichier_parametre) {
    }
}