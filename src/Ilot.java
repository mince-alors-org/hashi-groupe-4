import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ilot {
    private int posX;
    private int posY;

    private int valeur;

    private ArrayList<Pont> listePontsSolution;


    public Ilot(int posX, int posY, int valeur) {
        this(posX,posY,valeur, new ArrayList<>());
    }

    public Ilot(int posX, int posY, int valeur, ArrayList<Pont> listePontsSolution) {
        this.posX = posX;
        this.posY = posY;
        this.valeur = valeur;
        this.listePontsSolution = listePontsSolution;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public ArrayList<Pont> getListePontsSolution() {
        return listePontsSolution;
    }

    public void setListePontsSolution(ArrayList<Pont> listePontsSolution) {
        this.listePontsSolution = listePontsSolution;
    }


    public boolean valide(){
        return true;
    }
}
