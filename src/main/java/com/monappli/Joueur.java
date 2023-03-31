package com.monappli;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/**
 * Représente un joueur
 */
public class Joueur implements Serializable{
    private String nom;
    private String motdepasse;

    /**
     * Constructeur d'un joueur
     * @param nom nom du joueur
     * @param motdepasse mot de passe du joueur
     */
    public Joueur(String nom, String motdepasse) {
        this.nom = nom;
        this.motdepasse = motdepasse;

        File fichier = new File("src/main/java/com/monappli/Profiles/" + nom);
        fichier.mkdir();
    }

    public void initSave(String nomJoueur, String nomNiveaux) throws IOException{
        File fichier = new File("src/main/java/com/monappli/Profiles/"+nomJoueur+"/"+nomNiveaux);
        fichier.createNewFile();
    }

    public String getnom() {
        return nom;
    }

    public String getmotdepasse() {
        return motdepasse;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public void setmotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    
    @Override
    public String toString(){
        return "Nom= "+nom+" MDP = "+motdepasse;
    }

    /**
     * Redéfinition de equals
     * @param obj objet à comparer
     * @return true si les objets sont égaux, faux autrement
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) obj;
        return Objects.equals(nom, other.nom) && Objects.equals(motdepasse, other.motdepasse);
    }

}

