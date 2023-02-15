package com.monappli;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public void actualiserFichier(){
        try{
            fileWriter = new FileWriter("./save_move.txt");
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
    public void chargerFichier() throws IOException {
        FileReader fileReader = new FileReader("./save_move.txt");

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
    public void lireFichier() throws IOException{
        fileReader = new FileReader("./save_move.txt");
        int i;
        while((i=fileReader.read()) != -1){
            System.out.print((char)i);
        }
    }

}
