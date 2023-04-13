package com.monappli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javafx.scene.paint.Color;

/**
 * Représente une base de données comprenant une liste de joueurs
 * @author Ambre Collard
 */
public class BaseDonneeJoueur implements Serializable{
    public static String directory="src/main/resources/profiles/";


    /**
     * Creates a File if the player doesn't already exists in the database.
     * @param joueur the player
     * @author Ambre Collard
     */
    public static boolean addJoueur(Joueur joueur) throws Exception {
        if (exists(joueur))
            return false;
        File dossier=new File(directory);
        ArrayList<String> search= new ArrayList<String>();

        for (String e : dossier.list()){
            search.add(e);
            if (e.equals(joueur.getnom()))
                return false;
        }
        File nouv_dossier=new File(directory+joueur.getnom());
        String niv_path = "/niveaux";
        File niveaux = new File(directory+joueur.getnom()+niv_path);
        nouv_dossier.mkdir();
        niveaux.mkdir();
        writeNewPlayer(joueur);
        return true;
    }

    public static boolean addSauvegardeJoueur(Joueur joueur) throws Exception {
      if (exists(joueur)){
        return false;
      }
      File dossier=new File(directory);
      ArrayList<String> search= new ArrayList<String>();

      for (String e : dossier.list()){
        search.add(e);
        if (e.equals(joueur.getnom()))
          return false;
      }
      File nouv_dossier=new File(directory+joueur.getnom());
      nouv_dossier.mkdir();
      writeNewPlayer(joueur);
      return true;


    }
    /**
     * Adds the score of a level in the database file
     * @param lvl the level
     * @param score the score
     * @return <code>true</code> if the level was already in the database for the current player, <code>false</code> otherwise.
     * @throws Exception If the player's file couldn't be opened
     * @author Ambre Collard
     */
    public static boolean addScore( String lvl, int score) throws Exception{
        Path path = Paths.get(directory+Hashi.joueur.getnom()+ "/"+ Hashi.joueur.getnom() + ".prof");
        List<String> lines= Files.readAllLines(path);
        for (int i=0; i<lines.size(); i++){
            if (Pattern.matches(lvl +".*", lines.get(i))){
                FileWriter fWriter= new FileWriter(directory+Hashi.joueur.getnom()+ "/"+ Hashi.joueur.getnom() + ".prof");
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
        FileWriter fWriter= new FileWriter(directory+Hashi.joueur.getnom()+ "/"+ Hashi.joueur.getnom() + ".prof", true);
        fWriter.write("\n"+lvl + " "+ score);
        fWriter.close();
        return false;
    }


    /**
     * Set the parameters of from the player's file
     * @throws Exception if the player's file couldn't be opened
     * @author Ambre Collard
     */
    public static void loadParam() throws Exception{
        Path path = Paths.get(directory+Hashi.joueur.getnom()+ "/" + Hashi.joueur.getnom() +".prof");
        List<String> lines= Files.readAllLines(path);
        Parametre.load(lines.subList(1, 6));
    }

    /**
     * Writes the default parameters for a new player in the database
     * @param joueur the player
     * @throws Exception if the file couldn't be created
     * @author Ambre Collard
     */
    public static void writeNewPlayer(Joueur joueur) throws Exception{
        FileWriter myWriter = new FileWriter(directory +"/"+joueur.getnom()+"/"+joueur.getnom()+".prof");
        myWriter.write(joueur.getnom()+
                "\n#000000"+
                "\n#ffffff"+
                "\n#000000"+
                "\n#457BF8"+
                "\n#000000"
                );
        myWriter.close();
    }

    /**
     * Verify if a players already exists in the database
     * @param nomF the file name
     * @return <code>true</code> if the profile's folder contains the file name, <code>false</code> otherwise
     * @author Ambre Collard
     */
    public static boolean exists(String nomF){
        File dossier=new File(directory);
        ArrayList<String> search= new ArrayList<String>();

        for (String e : dossier.list()){
            search.add(e);
        }
        return search.contains(nomF );
    }

    /**
     * Verify if a players already exists in the database
     * @param j the player
     * @return <code>true</code> if the profile's folder contains the file name, <code>false</code> otherwise
     * @author Ambre Collard
     */
    public static boolean exists(Joueur j){
        return exists(j.getnom());
    }

    /**
     * Changes the current player to another if it exists in the database
     * @param nextJ the new current player
     * @return <code>true</code> if the new player exists in the database, <code>false</code> otherwise
     * @throws Exception if the folder couldn't be opened
     * @author Ambre Collard
     */
    public static boolean changePlayer( Joueur nextJ) throws Exception{
        if (!exists(nextJ))
            return false;

        setJoueur(nextJ);
        return true;
    }

    /**
     * Sets the new current player and changes the parameters
     * @param j the new player
     * @throws Exception if the folder couldn't be opened
     * @author Ambre Collard
     */
    public static void setJoueur(Joueur j) throws Exception{
        Hashi.joueur= j;
        loadParam();
    }

    /**
     * Get the chip color for graphic use
     * @param j the player to get the chip color
     * @return the <code>Color</code> of the chip color if the new player exists, <code>null</code> otherwise
     * @throws Exception if the player's file couldn't be opened
     * @author Ambre Collard
     */
    public static Color getChipColor(Joueur j) throws Exception{
        if (exists(j)){
            Path path = Paths.get(directory+j.getnom()+"/"+  j.getnom()+ ".prof");
            List<String> lines= Files.readAllLines(path);
            return Color.web( lines.get(4));
        }
        return null;
    }

    /**
     * Creates a Joueur from a file
     * @param nom name of the player
     * @return  A <code>Joueur</code> if the player exists in the database, <code>null</code> otherwise
     * @throws Exception
     * @author Ambre Collard
     */
    public static Joueur getJoueur(String nom) throws Exception{
        if (!exists(nom)){
            return null;
        }

        return new Joueur(nom);
    }

    public static String getJoueurEmplacementSauvegarde(Joueur joueur){
      return directory + joueur.getnom() + "/niveaux";
    }

    /**
     * Return a array of all the players in the database
     * @return the <code>ArrayList</code> of all <code>Joueur</code> in the database
     * @throws Exception
     * @author Penchon Morgane et Ambre Collard
     */
    public static ArrayList<Joueur> getAllPlayers() throws Exception{
        ArrayList<Joueur> tab = new ArrayList<Joueur>();
        File dossier=new File(directory);
        File[] liste2File=dossier.listFiles();
        for(int i=0;i<liste2File.length;i++){
            File nomJ=  liste2File[i];
            if(!nomJ.isFile()){
                Joueur j= getJoueur(nomJ.getName());
                if(j!=null){
                    tab.add(j);
                }
            }
        }
        return tab;
    }

    /**
     * Saves the current <code>Parametre</code> in the current player's file
     * @author Ambre Collard
     */
    public static void saveParam(){
        try{
            FileWriter myWriter = new FileWriter(directory +"/"+Hashi.joueur.getnom()+"/"+Hashi.joueur.getnom()+".prof");
            myWriter.write(Hashi.joueur.getnom()+
                "\n" + Parametre.toRGBForCSS( Parametre.getCouleur_texte())+
                "\n"+ Parametre.toRGBForCSS( Parametre.getCouleur_ilot())+
                "\n"+ Parametre.toRGBForCSS( Parametre.getCouleur_pont())+
                "\n"+ Parametre.toRGBForCSS( Parametre.getCouleur_fond())+
                "\n" + Parametre.toRGBForCSS( Parametre.getCouleur_aide_erreur())
                );
            myWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Deletes a player from the database
     * @param j the player to be deleted
     * @return <code>false</code> if the player doesn't exist, <code>true</code> if it has been deleted
     * @throws Exception
     * @author Ambre Collard
     */
    public static boolean deletePlayer(Joueur j) throws Exception{
        if (!exists(j))
            return false;
        Path path = Paths.get(directory+j.getnom());
        deleteDirectoryRecursion(path);
        return true;
    }

    /**
     * Recursively deletes a folder/file
     * @param path <code>Path</code> to the folder
     * @throws IOException
     * @author Ambre Collard
     */
    private static void deleteDirectoryRecursion(Path path) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
          try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
            for (Path entry : entries) {
              deleteDirectoryRecursion(entry);
            }
          }
        }
        Files.delete(path);
    }

    /**
     * @return the number of players in the database
     * @throws Exception
     * @author Ambre Collard
     */
    public static int getNumberPlayers() throws Exception{
            return  getAllPlayers().size();
    }
}


