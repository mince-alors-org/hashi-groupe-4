package com.monappli;
import java.util.ArrayList;

/**
 * Cette classe permet de représenter un Ilot
 * @author nmention
 */
public class Ilot {
    /**
     * position de this dans l'axe des abscisses (X)
     */
    private int posX;
    /**
     * position de this dans l'axe des ordonnées (Y)
     */
    private int posY;
    /**
     * nombre de ponts supportés par this
     */
    private int valeur;

    /**
     * ponts reliés à this
     */
    private ArrayList<Pont> ponts;

    private ArrayList<Pont> pontsSolution;

    /**
     *
     * @param posX position du l'ilot courant dans l'axe des abscisses (X)
     * @param posY position de l'ilot courant dans l'axe des ordonnées (Y)
     * @param valeur nombre de ponts supportés par this
     * @param pontsSolution
     */
    public Ilot(int posX, int posY, int valeur) {
        this.posX = posX;
        this.posY = posY;
        this.valeur = valeur;
        ponts = new ArrayList<>();
        this.pontsSolution = new ArrayList<>() ;
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


    public ArrayList<Pont> getPonts() {
        return ponts;
    }

    public void setPonts(ArrayList<Pont> ponts) {
        this.ponts = ponts;
    }

    /**
     * Ajout d'un pont connecté au noeud courant
     * @param pont : le pont à ajouter
     */
    public void addPont(Pont pont){
        ponts.add(pont);
    }


    public void addPontSolution(Pont pont){
        pontsSolution.add(pont);
    }

    /**
     * Suppression d'un pont connecté au noeud courant
     * @param pont : le pont à supprimer
     */
    public void deletePont(Pont pont){
        ponts.remove(pont);
    }


    /**
     * Indique si l'ilot peut être connecté à un pont
     * @return true si l'ilot peut faire parti d'un pont
     */
    public boolean valide(){
        return ponts.size() < valeur;
    }

    @Override
    public String toString() {
        return "Ilot{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", valeur=" + valeur +
                ", listePontsSolution=" + ponts +
                '}';
    }


}
