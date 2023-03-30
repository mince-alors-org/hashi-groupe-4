package com.monappli;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une base de données comprenant une liste de joueurs
 */
public class BaseDonneeJoueur {
    private List<Joueur> joueurs;

    /**
     * Constructeur de BaseDonneeJoueur 
     */
    public BaseDonneeJoueur() {
        joueurs = new ArrayList<>();
    }

    /**
     * Ajoute un joueur dans la base de donnee
     * @param joueur joueur à inclure dans la base de donnee
     */
    public void addJoueur(Joueur joueur) {
        boolean joueurExists = false;
        for(Joueur j : joueurs) {
            if(j.getnom().equals(joueur.getnom())) {
                joueurExists = true;
                break;
            }
        }
        if(!joueurExists) {
            joueurs.add(joueur);
        } else {
            System.out.println("Joueur déjà existant !");
        }
    }
    
    /**
     * Retourne un joueur par le nom dans la base de donnee
     * @param name nom du joueur à récupérer
     * @return joueur au nom donnée, ou null si il n'existe pas
     */
    public Joueur getJoueurNom(String nom) {
        for (Joueur joueur : joueurs) {
            if (joueur.getnom().equals(nom)) {
                return joueur;
            }
        }
        return null;
    }

    public String toString() {
        String res = "Liste des joueurs: \n";
        for (Joueur joueur : joueurs) {
            res += joueur.getnom() + " - " + joueur.getmotdepasse() + "\n";
        }
        return res;
    }
}
    

