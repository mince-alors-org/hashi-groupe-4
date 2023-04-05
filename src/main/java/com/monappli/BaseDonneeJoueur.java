package com.monappli;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javafx.scene.paint.Color;

/**
 * Représente une base de données comprenant une liste de joueurs
 */
public class BaseDonneeJoueur implements Serializable{
    public static String directory="src/main/resources/profiles/";


    /**
     * Ajoute un joueur dans la base de donnee
     * @param joueur joueur à inclure dans la base de donnee
     */
    public static boolean addJoueur(Joueur joueur) throws Exception {
        if (exists(joueur))
            return false;
        File dossier=new File(directory);
        ArrayList<String> search= new ArrayList<String>();

        for (String e : dossier.list()){
            search.add(e);
            if (e.contains(joueur.getnom()+".prof"))
                return false;
        }
        writeNewPlayer(joueur);
        return true;
    }

    public boolean addScore( String lvl, int score) throws Exception{
        Path path = Paths.get(directory+Hashi.joueur.getnom()+ ".prof");
        List<String> lines= Files.readAllLines(path);
        for (int i=0; i<lines.size(); i++){
            if (Pattern.matches(lvl +".*", lines.get(i))){
                FileWriter fWriter= new FileWriter(directory + Hashi.joueur.getnom()+".prof");
                lines.set(i, lvl + " "+ score);
                String toWrite=new String();
                for (String str : lines){
                    toWrite+=str+"\n";
                }
                fWriter.write(toWrite);
                fWriter.close();
                return true;
            }
        }
        FileWriter fWriter= new FileWriter(directory + Hashi.joueur.getnom()+".prof", true);
        fWriter.write("\n"+lvl + " "+ score);
        fWriter.close();
        return false;
    }

    public static void loadParam() throws Exception{
        Path path = Paths.get(directory+Hashi.joueur.getnom()+ ".prof");
        List<String> lines= Files.readAllLines(path);
        Parametre.load(lines.subList(2, 7));
    }

    public static void writeNewPlayer(Joueur joueur) throws Exception{
        FileWriter myWriter = new FileWriter(directory + joueur.getnom()+".prof");
        myWriter.write(joueur.getnom()+
                "\n" + joueur.getmotdepasse()+
                "\n#000000"+
                "\n#ffffff"+
                "\n#000000"+
                "\n#000000"+
                "\n#457BF8"
                );
        System.out.println("Successfully wrote to the file.");
        myWriter.close();
    }
    
    /**
     * Retourne un joueur par le nom dans la base de donnee
     * @param name nom du joueur à récupérer
     * @return joueur au nom donnée, ou null si il n'existe pas
     */
    /*public Joueur getJoueurNom(String nom) {
        for (Joueur joueur : joueurs) {
            if (joueur.getnom().equals(nom)) {
                return joueur;
            }
        }
        return null;
    }*/

    public static String getPassword(Joueur j) throws Exception{
        if (exists(j)){
            Path path = Paths.get(directory+j.getnom()+ ".prof");
            List<String> lines= Files.readAllLines(path);
            return lines.get(1);
        }
        return null;
    }

    public static boolean exists(String nomF){
        File directory=new File("src/main/resources/profiles");
        ArrayList<String> search= new ArrayList<String>();

        for (String e : directory.list()){
            search.add(e);
        }
        return search.contains(nomF + ".prof");
    }

    public static boolean exists(Joueur j){
        return exists(j.getnom());
    }

    public static boolean changePlayer( Joueur nextJ, String pwd) throws Exception{
        if (!exists(nextJ))
            return false;

        if(pwd.equals(getPassword(nextJ))) {
            setJoueur(nextJ);
            return true;
        }
        return false;  
    }

    public static void setJoueur(Joueur j) throws Exception{
        Hashi.joueur= j;
        loadParam();
    }

    public static Color getChipColor(Joueur j) throws Exception{
        if (exists(j)){
            Path path = Paths.get(directory+j.getnom()+ ".prof");
            List<String> lines= Files.readAllLines(path);
            return Color.web( lines.get(6));
        }
        return null;
    }

    public static Joueur getJoueur(String nom, String pwd) throws Exception{
        if (!exists(nom)){
            return null;
        }
        Joueur joueur = new Joueur(nom, pwd);
        if(getPassword(joueur).equals(pwd)){
            return joueur;
        }
        return null;
    }

    /*public String toString() {
        String res = "Liste des joueurs: \n";
        for (Joueur joueur : joueurs) {
            res += joueur.getnom() + " - " + joueur.getmotdepasse() + "\n";
        }
        return res;
    }*/
}
    

