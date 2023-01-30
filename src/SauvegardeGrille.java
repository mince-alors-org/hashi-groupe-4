import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SauvegardeGrille{  

    private FileWriter fileWriter;  //écrire dans un fichier
    ArrayList<Pont> pileCoups = new ArrayList<Pont>();  //pile des coups joués et actifs sur la grille
    ArrayList<Pont> pileRetablissements = new ArrayList<Pont>();    //pile des coups précédemment joués mais annulés
    int taillePileCoups = pileCoups.size();
    
    public SauvegardeGrille(){
        try{
            fileWriter = new FileWriter("save_move.txt");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Undo
     */
    public void retablir(){
    }

    /**
     * Redo
     */
    public void annuler(){
    }

    /**
     * ajout du coup dans la pileCoups
     * @param p1 
     */
    public void ajoutCoup(Pont p1){ 
        pileCoups.add(p1);
    }

    /**
     * sauvegarde des coups dans le fichier
     */
    public void actualiserFichier(){
        Pont p1 = pileCoups.get(taillePileCoups-1);
        try{
            fileWriter.write(p1.coords[1] + p1.coords[2] + "\n");
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
