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
     * Redo
     * @author TER Leon
     */
    public void retablir(){
        pileCoups.add(pileRetablissements.get(pileRetablissements.size()-1));
        pileRetablissements.remove(pileRetablissements.size()-1);
    }

    /**
     * Undo
     * @author TER Leon
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
        //Extrait couleur_texte
        String couleur_texteStr = paramString.split("couleur_texte=")[1].split(",")[0];
        Color couleur_texte = Color.getColor(couleur_texteStr);
        param.setCouleur_texte(couleur_texte);

        //Extrait couleur_ilot
        String couleur_ilotStr = paramString.split("couleur_ilot=")[1].split(",")[0];
        Color couleur_ilot = Color.getColor(couleur_ilotStr);
        param.setCouleur_ilot(couleur_ilot);

        //Extrait couleur_pont
        String couleur_pontStr = paramString.split("couleur_pont=")[1].split(",")[0];
        Color couleur_pont = Color.getColor(couleur_pontStr);
        param.setCouleur_pont(couleur_pont);


        //Extrait couleur_aide_erreur
        String couleur_aide_erreurStr = paramString.split("couleur_aide_erreur=")[1].split(",")[0];
        Color couleur_aide_erreur = Color.getColor(couleur_aide_erreurStr);
        param.setCouleur_aide_erreur(couleur_aide_erreur);

        //Extrait taille_texte
        String taille_texteStr = paramString.split("taille_texte=")[1].split(",")[0];
        int taille_texte = Integer.parseInt(taille_texteStr);
        param.setTaille_texte(taille_texte);

        //Extrait affichage_depassment_cardinalite
        String affichage_depassment_cardinaliteStr = paramString.split("affichage_depassment_cardinalite=")[1].split(",")[0];
        Boolean affichage_depassment_cardinalite = Boolean.parseBoolean(affichage_depassment_cardinaliteStr);
        param.setAffichage_depassment_cardinalite(affichage_depassment_cardinalite);

        //Extrait affichage_groupe_ferme
        String affichage_groupe_fermeStr = paramString.split("affichage_groupe_ferme=")[1].split(",")[0];
        Boolean affichage_groupe_ferme = Boolean.parseBoolean(affichage_groupe_fermeStr);
        param.setAffichage_groupe_ferme(affichage_groupe_ferme);
        return param;
    }


}
