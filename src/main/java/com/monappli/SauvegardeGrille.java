package com.monappli;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SauvegardeGrille implements Serializable{

    private FileWriter fileWriter;  //écrire dans un fichier
    private FileReader fileReader; //lire dans un fichier
    private static ArrayList<Pont> pileCoups = new ArrayList<Pont>();  //pile des coups joués et actifs sur la grille

    public static double chrono_time = 0;

    private ArrayList<Pont> pileRetablissements = new ArrayList<Pont>();    //pile des coups précédemment joués mais annulés

    /**
     * Undo
     */
    public void annuler(){
        if(!pileCoups.isEmpty()){
            pileRetablissements.add(pileCoups.get(pileCoups.size()-1));
            pileCoups.remove(pileCoups.size()-1);
        }
    }

    /**
     * Redo
     */
    public void retablir(){
        if(!pileRetablissements.isEmpty()){
            pileCoups.add(pileRetablissements.get(pileRetablissements.size()-1));
            pileRetablissements.remove(pileRetablissements.size()-1);
        }
    }

    /**
     * ajout du coup dans la pileCoups
     * @param p1 pont joué et actif sur la grille
     */
    public void ajoutCoup(Pont p1){
        if(p1 != null){
            pileCoups.add(p1);
        }
    }

    /**
     * sauvegarde des coups dans le fichier
     */
    public void actualiserFichier(String fichier_sauvegarde) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fichier_sauvegarde))) {
          outputStream.writeObject(pileCoups);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  public void actualiserFichier(String fichier_sauvegarde,double chrono_time) {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fichier_sauvegarde))) {
      outputStream.writeObject(pileCoups);
      outputStream.writeDouble(chrono_time);
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }



    public ArrayList<Pont> getPileCoups(){
        return pileCoups;
    }

    public int getPileCoupsSize(){
        return pileCoups.size();
    }

    /**
     * ajoute les ponts dans pileCoups à partir du fichier save_move.txt
     * @throws Exception
     * @author Ter Leon
     */
    public void chargerFichier(String fichier_sauvegarde) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fichier_sauvegarde);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        //Créer une liste pour stocker les Pont lus
        ArrayList<Pont> ponts = new ArrayList<Pont>();

        try {
            while (true) {
                //Lit un objet Pont à partir du flux d'entrée
                Pont pont = (Pont) objectInputStream.readObject();
                ponts.add(pont);
            }
        } catch (EOFException e) {
            //On arrive à la fin du fichier, on ferme le flux
            objectInputStream.close();
            fileInputStream.close();
        }

        // Appelle ajoutCoup pour chacun des ponts
        for (Pont pont : ponts) {
            ajoutCoup(pont);
        }
    }


  public void chargerFichier2(String fichier_sauvegarde) throws IOException, ClassNotFoundException {
    FileInputStream fileInputStream = new FileInputStream(fichier_sauvegarde);
    ObjectInputStream ois = new ObjectInputStream(fileInputStream);
    pileCoups = (ArrayList<Pont>) ois.readObject();
    double chrono = ois.readDouble();
    chrono_time = chrono;


    System.out.println(pileCoups);
    System.out.println(chrono);






  }

  public double getTimeValueChrono(String fichier_sauvegarde) throws IOException, ClassNotFoundException {
    FileInputStream fileInputStream = new FileInputStream(fichier_sauvegarde);
    ObjectInputStream ois = new ObjectInputStream(fileInputStream);
    pileCoups = (ArrayList<Pont>) ois.readObject();
    double chrono = ois.readDouble();
    System.out.println(pileCoups);
    System.out.println(chrono);

    return chrono;






  }

    /**
     * Actualiser le fichier_parametre en serialisant un objet Parametre
     * @param fichier_parametre nom du fichier de paramètres à actualiser
     * @param param objet Parametre a serialiser
     */
    public void actualiserFichierParametre(String fichier_parametre, Parametre param, BaseDonneeJoueur bdd){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fichier_parametre));
            outputStream.writeObject(param);
            outputStream.writeObject(bdd);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge un objet Parametre depuis un fichier de parametre specifie
     * @param fichier_parametre Nom du fichier de parametre a charger
     * @return objet Parametre rempli avec les donnees chargees depuis le fichier
     */
    public Parametre chargerFichierParametre(String fichier_parametre) throws IOException, ClassNotFoundException{
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fichier_parametre));
        Parametre param = (Parametre) inputStream.readObject();
        inputStream.close();
        return param;
    }

    /**
     * Charge un objet BaseDonneeJoueur depuis un fichier de parametre specifie
     * @return objet BaseDonneeJoueur rempli avec les donnees chargees depuis le fichier
     */
    public BaseDonneeJoueur chargerFichierBdd(String fichier_parametre) throws IOException, ClassNotFoundException{
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fichier_parametre));
        //Saut de parametre dans fichier_parametre
        inputStream.readObject();
        BaseDonneeJoueur bdd = (BaseDonneeJoueur) inputStream.readObject();
        inputStream.close();
        return bdd;
    }

  /**
   * Permet d'effacer les fichiers de sauvegarde (le fichier deviendra vide)
   */
  public void effacerFichier(){
      PrintWriter writer = null;
      try {
        writer = new PrintWriter("src/test/java/com/monappli/save_move.txt");
        writer.print("");
        writer.close();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    }



    public void createSaveFile(String filename) throws IOException {
      File file = new File(filename);
      if (file.createNewFile()){
        System.out.println("Nv fichier de sauvegarde " + filename);
      }
      else {
        System.out.println("File existe déjà");
      }
    }

    public Pont getLastPileCoups(){
        if (pileCoups.isEmpty()){
            return null;
        }
        return pileCoups.get(pileCoups.size()-1);
    }

    public Pont getLastPileReta(){
        if (pileRetablissements.isEmpty()){
            return null;
        }
        return pileRetablissements.get(pileRetablissements.size()-1);
    }
}

