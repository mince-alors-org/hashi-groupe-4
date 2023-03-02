import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SauvegardeGrille{  

    private FileWriter fileWriter;  //écrire dans un fichier
    private ArrayList<Pont> pileCoups = new ArrayList<Pont>();  //pile des coups joués et actifs sur la grille
    private ArrayList<Pont> pileRetablissements = new ArrayList<Pont>();    //pile des coups précédemment joués mais annulés
    private int taillePileCoups = pileCoups.size();
    
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
        pileCoups.add(pileRetablissements.get(taillePileCoups-1));
        pileRetablissements.remove(taillePileCoups-1);
    }

    /**
     * Redo
     */
    public void annuler(){ 
        pileRetablissements.add(pileCoups.get(taillePileCoups-1));
        pileCoups.remove(taillePileCoups-1);
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
        
        for (int i = 0; i < taillePileCoups; i++){
            Pont p1 = pileCoups.get(i);
            try{
                fileWriter.write(p1.getCoords1()+ p1.getCoords2() + "\n");
                fileWriter.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
     }
 }

       
    }

}
