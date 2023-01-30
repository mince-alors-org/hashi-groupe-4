import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SauvegardeGrille{  

    private FileWriter fileWriter;  //écrire dans un fichier
    ArrayList<Pont> pileCoups = new ArrayList<Pont>();
    ArrayList<Pont> pileRetablissements = new ArrayList<Pont>();
    
    
    public SauvegardeGrille(){
        try{
            fileWriter = new FileWriter("save_move.txt");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void retablir(){ //undo

    }

    public void annuler(){  //redo

    }

    /**
     * ajout du coup dans la pileCoups
     */
    public void ajoutCoup(){ 

    }

    /**
     * sauvegarde des coups dans le fichier
     */
    public void actualiserFichier(){    

    }
}
