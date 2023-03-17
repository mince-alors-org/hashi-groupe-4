package com.monappli;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.*;

public class SauvegardeGrille{  

    private FileWriter fileWriter;  //écrire dans un fichier
    private FileReader fileReader; //lire dans un fichier
    private static ArrayList<Pont> pileCoups = new ArrayList<Pont>();  //pile des coups joués et actifs sur la grille
    private ArrayList<Pont> pileRetablissements = new ArrayList<Pont>();    //pile des coups précédemment joués mais annulés
    
    /**
     * Undo
     */
    public void retablir(){
        pileCoups.add(pileRetablissements.get(pileRetablissements.size()-1));
        pileRetablissements.remove(pileRetablissements.size()-1);
    }

    /**
     * Redo
     */
    public void annuler(){ 
        pileRetablissements.add(pileCoups.get(pileCoups.size()-1));
        pileCoups.remove(pileCoups.size()-1);
    }

    /**
     * ajout du coup dans la pileCoups
     * @param p1 pont joué et actif sur la grille
     */
    public void ajoutCoup(Pont p1){ 
        if(p1 != null){
            this.pileCoups.add(p1);
        }
    }

    /**
     * sauvegarde des coups dans le fichier
     */
    public void actualiserFichier(String fichier_sauvegarde){
        try{
            fileWriter = new FileWriter(fichier_sauvegarde);
            for (Pont p1 : pileCoups){
                fileWriter.write(p1.getCoords() + "\n");
                fileWriter.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }

    public int getPileCoupsSize(){
        return pileCoups.size();
    }

    /**
     * ajoute les ponts dans pileCoups à partir du fichier save_move.txt
     * @throws Exception
     * @author Ter Leon
     */
    public void chargerFichier(String fichier_sauvegarde) throws IOException {
        FileReader fileReader = new FileReader(fichier_sauvegarde);

        // Lit les lignes du fichier
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //Créer une liste pour stocker les Pont lus 
        ArrayList<Pont> ponts = new ArrayList<Pont>();

        //Lit ligne par ligne et convertit en objet Pont
        String ligne;
        while ((ligne = bufferedReader.readLine()) != null) {
            if(!ligne.isEmpty()){
                Pont pont = parsePontString(ligne);
                ponts.add(pont);
            }
            
        }

        bufferedReader.close();
        fileReader.close();

        // Appelle ajoutCoup pour chacun des ponts
        for (Pont pont : ponts) {
            ajoutCoup(pont);
        }
    }

    /**
     * Créer un pont à partir d'une ligne du fichier save_move.txt
     * @param pontString : Chaine de caractère correspondant à une ligne du fichier save_move.txt
     * @return Pont : Retourne un pont
     * @author TER Leon
     */
    private static Pont parsePontString(String pontString) {
        // Extrait deux ilots du param String
        String[] ilotStrings = pontString.split("}, ");

        if (ilotStrings.length != 2){
            throw new IllegalArgumentException("Nb ilots incorrect: " + ilotStrings.length);
        }

        // Créer deux ilots à partir d'une ligne du fichier
        Ilot ilot1 = parseIlotString(ilotStrings[0]);
        Ilot ilot2 = parseIlotString(ilotStrings[1]);

        // Créer et retourne un pont à partir des deux ilots crée
        return new Pont(ilot1, ilot2);
    }

    /**
     * Créer un ilot à partir du fichier save_move.txt
     * @param ilotString
     * @return Ilot : Retourne un ilot
     * @author TER Leon
     */
    private static Ilot parseIlotString(String ilotString) {
        // Extrait posX
        String posXStr = ilotString.split("posX=")[1].split(",")[0];
        int posX = Integer.parseInt(posXStr);

        // Extrait posY
        String posYStr = ilotString.split("posY=")[1].split(",")[0];
        int posY = Integer.parseInt(posYStr);

        // Extrait valeur
        String valeurStr = ilotString.split("valeur=")[1].split(",")[0];
        int valeur = Integer.parseInt(valeurStr);

        // Créer et retourne un ilot
        return new Ilot(posX, posY, valeur);
    }

    /**
     * Lit le fichier de sauvegarde sur le terminal 
     * @throws IOException
     */
    public void lireFichier(String fichier_sauvegarde) throws IOException{
        fileReader = new FileReader(fichier_sauvegarde);
        int i;
        while((i=fileReader.read()) != -1){
            System.out.print((char)i);
        }
    }

    public void actualiserFichierParametre(String fichier_parametre, Parametre param){
        try{
            fileWriter = new FileWriter(fichier_parametre);
            fileWriter.write(param + "\n");
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Parametre chargerFichierParametre(String fichier_parametre, Parametre param) throws IOException{
        FileReader fileReader = new FileReader(fichier_parametre);

        //Lit ligne par ligne
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String ligne;

        while((ligne = bufferedReader.readLine()) != null){
            if(!ligne.isEmpty()){
                param = parseParametreString(ligne, param);
            }
        }

        bufferedReader.close();
        fileReader.close();

        return param;

    }

    public Parametre parseParametreString(String paramString, Parametre param) throws IOException{
        int red, green, blue;
        //Extrait couleur_texte
        String[] couleur_texteStr = paramString.split("couleur_texte=")[1].split(",");
        red = Integer.parseInt(couleur_texteStr[0].trim());
        green = Integer.parseInt(couleur_texteStr[1].trim());
        blue = Integer.parseInt(couleur_texteStr[2].trim());
        Color couleur_texte = new Color(red, green, blue);
        param.setCouleur_texte(couleur_texte);

        //Extrait couleur_ilot
        String[] couleur_ilotStr = paramString.split("couleur_ilot=")[1].split(",");
        red = Integer.parseInt(couleur_ilotStr[0].trim());
        green = Integer.parseInt(couleur_ilotStr[1].trim());
        blue = Integer.parseInt(couleur_ilotStr[2].trim());
        Color couleur_ilot = new Color(red, green, blue);
        param.setCouleur_ilot(couleur_ilot);

        //Extrait couleur_pont
        String[] couleur_pontStr = paramString.split("couleur_pont=")[1].split(",");
        red = Integer.parseInt(couleur_pontStr[0].trim());
        green = Integer.parseInt(couleur_pontStr[1].trim());
        blue = Integer.parseInt(couleur_pontStr[2].trim());
        Color couleur_pont = new Color(red, green, blue);
        param.setCouleur_pont(couleur_pont);


        //Extrait couleur_aide_erreur
        String[] couleur_aide_erreurStr = paramString.split("couleur_aide_erreur=")[1].split(",");
        red = Integer.parseInt(couleur_aide_erreurStr[0].trim());
        green = Integer.parseInt(couleur_aide_erreurStr[1].trim());
        blue = Integer.parseInt(couleur_aide_erreurStr[2].trim());
        Color couleur_aide_erreur = new Color(red, green, blue);
        param.setCouleur_aide_erreur(couleur_aide_erreur);

        //Extrait couleur_fond
        String[] couleur_fondStr = paramString.split("couleur_fond=")[1].split(",");
        red = Integer.parseInt(couleur_fondStr[0].trim());
        green = Integer.parseInt(couleur_fondStr[1].trim());
        blue = Integer.parseInt(couleur_fondStr[2].trim());
        Color couleur_fond = new Color(red, green, blue);
        param.setCouleur_fond(couleur_fond);

        //Extrait taille_texte
        String taille_texteStr = paramString.split("taille_texte=")[1].split(",")[0];
        int taille_texte = Integer.parseInt(taille_texteStr);
        param.setTaille_texte(taille_texte);

        //Extrait taille_fenetre
        String[] taille_fenetreStr = paramString.split("taille_fenetre=")[1].split(",");
        int width = Integer.parseInt(taille_fenetreStr[0].trim());
        int height = Integer.parseInt(taille_fenetreStr[1].trim());
        int[] taille_fenetre = {width, height};
        param.setTaille_fenetre(taille_fenetre);

        //Extrait affichage_depassment_cardinalite
        String affichage_depassment_cardinaliteStr = paramString.split("affichage_depassment_cardinalite=")[1].split(",")[0];
        Boolean affichage_depassment_cardinalite = Boolean.parseBoolean(affichage_depassment_cardinaliteStr);
        param.setAffichage_depassment_cardinalite(affichage_depassment_cardinalite);

        //Extrait affichage_groupe_ferme
        String affichage_groupe_fermeStr = paramString.split("affichage_groupe_ferme=")[1].split(",")[0];
        Boolean affichage_groupe_ferme = Boolean.parseBoolean(affichage_groupe_fermeStr);
        param.setAffichage_groupe_ferme(affichage_groupe_ferme);

        //Extrait affichage_ponts_possible
        String affichage_ponts_possibleStr = paramString.split("affichage_ponts_possible=")[1].split(",")[0];
        Boolean affichage_ponts_possible = Boolean.parseBoolean(affichage_ponts_possibleStr);
        param.setAffichage_ponts_possible(affichage_ponts_possible);
        return param;
    }


}
