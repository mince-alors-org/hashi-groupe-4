package com.monappli;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SauvegardeGrille{  

    private FileWriter fileWriter;  //écrire dans un fichier
    private FileReader fileReader; //lire dans un fichier
    private ArrayList<Pont> pileCoups = new ArrayList<Pont>();  //pile des coups joués et actifs sur la grille
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
        pileCoups.add(p1);
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
     * lecture du fichier de sauvegarde
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
