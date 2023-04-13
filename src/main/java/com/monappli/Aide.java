package com.monappli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Cette classe permet de proposer différents types d'aides au joueur :
 * - Des techniques applicables pour débloquer le joueur 
 * - Des aides visuelles pour lui indiquer directement où il faut qu'il joue
 * - Des détecteurs et correcteurs d'erreurs 
 * @author Fonte Clément
 * @author Rollet Gabrielle
 * @version 1.0
 */
    public class Aide {

    private static Grille grille;

    private static HashMap<Ilot, Boolean> marque;

    private static final HashMap<String, String> technique = new HashMap<String, String>();
    static {
        technique.put("Iles avec autant de voisins que la moitié de leur cardinalité",
        "Une ile qui a une cardinalité égale à 2 fois son nombre de voisins est forcément connecté avec ces derniers");

        technique.put("Iles avec un seul voisin",
        "Les îles qui ont 1 ou 2 cardinalités et qui n'ont qu'un voisin sont forcement liées a celui-ci");

        technique.put("Iles 3 avec deux voisins, 5 avec trois voisins et 7 avec quatre voisins",
        "Une île marquée 3 avec 2 voisins doit obligatoirement être reliée au moins une fois avec tous ses voisins, il en va de même pour les îles de valeur 5 qui vont trois voisins et les iles de valeur 7 avec quatre voisins");

        technique.put("Cas spécial des 3 avec deux voisins, 5 avec trois voisins et 7 avec quatre voisins",
        "Si une île est de valeur 3 et qu'un de ses voisins est une île avec l'indice 1, alors toutes les conditions sont réunies et les 3 ponts peuvent être tracés, la même logique peut être appliquée si une île de valeur 5 sur le côté ou une île de valeur 7 au milieu a pour voisin une île marquée par un 1");

        technique.put("Iles aux millieux avec 6 de cardinalités", 
        "Si une île est de valeur 6 et a 4 voisins et que l'un d'entre eux a 1 de cardinalité alors il y a 2 possibilités: \n - si l'ilot avec 1 de cardinalité est forcement liée a l'ilot 6 alors celui-ci a minimum 1 pont avec tout ses autres voisins \n - si l'ilot avec 1 de cardinalité n'est pas liée a l'ilot 6 alors celui-ci a forcement 2 ponts avec tout ses voisins");

        technique.put("Isolation des segments",
        "Il est interdit qu'un bloc d'île soit isolé du reste des autres, lorsqu'il y a 2 îles de cardinalité 1 ou deux îles de cardinalités 2, il est interdit de les connecter entre eux s'ils peuvent être connectés à d'autres." );

        technique.put("Isolation lorsqu'un pont joint une île",
        "Veillez à ce que lorsque vous connectez les îles entre elles, elles forment toujours un seul et même chemin, il ne doit pas y avoir plusieurs blocs d'îles");

        technique.put("Pas de technique applicable",
        "Aucune technique applicable trouvée");
    }

    /**
     * Constructeur de la classe Aide qui permet d'associer l'aide à la grille
     */
    public static void setGrilleAide(Grille grille){
        Aide.grille = grille;
        Aide.marque = new HashMap<>();
    }

    /**
     * Constantes qui correspondent aux codes d'erreurs qui sont reconnus dans le reste du code
     */

    // Code d'erreur qui signifie qu'un ilot a trop de ponts (erreur de cardinalité) 
    final static String nbCardinaliteIncorrect="L'îlot colorié en rouge a trop de ponts";

    // Code d'erreur qui signifie qu'il n'y a pas le bon nombre de ponts
    final static String ilotIsole="L'îlot colorié en rouge se retrouve isolé et ne pourra être relié au reste de la grille";

    // Code d'erreur qui signifie qu'il y a un endroit fermé sur la grille
    final static String endroitLock="Le groupe d'îlots colorié en rouge est isolé et ne pourra pas être relié au reste de la grille";

    // Code qui signifie qu'il y a pas d'erreur
    final static String pasderreur="Aucune erreur n'a été détectée dans la grille actuelle";

    /**
     * pour cela la méthode parcours le plateau et avec une série de conditions détermine la bonne technique a renvoyer
     * @return le nom de la methode a appliquer ainsi qu'une rapide description
     */
    public static String getTechnique(){
        for(int i = 0 ; i < grille.getIlots().size() ; i++) {
            if (ile_1_voisin(grille.getIlots().get(i))) {
                return technique.get("Iles avec un seul voisin");
            }
            if (cas_3_coin_5_cote_7_milieu(grille.getIlots().get(i))) {
                return technique.get("Iles 3 avec deux voisins, 5 avec trois voisins et 7 avec quatre voisins");
            }
            if(cas_spe_3_coin_5_cote_7_milieu(grille.getIlots().get(i))){
                return technique.get("Cas spécial des 3 avec deux voisins, 5 avec trois voisins et 7 avec quatre voisins");
            }
            if(cas_4_6_8_cote(grille.getIlots().get(i))){
                return technique.get("Iles avec autant de voisins que la moitié de leur cardinalité");
            }
            
            if (cas_4_avec_3_voisin_dont_2_1(grille.getIlots().get(i))) { // aucun cas
                return technique.get("Iles sur un coté avec 4 de cardinalités");
            }
            if (cas_6_milieu(grille.getIlots().get(i))) {
                return technique.get("Iles aux millieux avec 6 de cardinalités");
            }
            /* 
            if (isolation_iles_3(grille.getIlots().get(i))) {
                System.out.print(grille.getIlots().get(i));
                return technique.get("Isolation lorsqu'un pont joint une île");
            }
            */
        }
        return technique.get("Pas de technique applicable");
    }

    
    /**
     * Verifie si le plateau actuel contient des erreurs ou non
     * @return String : contenant le code d'erreur correspondant a l'erreur trouver sur la grille
     */ 
    public static String checkErreur() {
        for (Ilot ile : grille.getIlots()) { 
            if (Aide.nbCardinalité(ile)) {
              return Aide.nbCardinaliteIncorrect;
            } 
            if (Aide.estEnsembleFerme(ile)) {
                return Aide.endroitLock;
            }
            if (Aide.ileIsole(ile)) {
                return Aide.ilotIsole;
            }
        }
        return Aide.pasderreur;
    }

    public static boolean ileIsole(Ilot ile) {
        int nbValidPont=0;
        for (Pont pont : grille.getAllValidPont(ile)) {
            nbValidPont+= pont.getNbTraits();
        }
        if (grille.getAllValidPont(ile).size()-ile.listeVoisinRelier().size()==0 && (nbValidPont==grille.getAllValidPont(ile).size()*2) && (nbValidPont != ile.getValeur())) {
            ile.setStyleRed();
            return true;
        } 
        return false;
    }



    /**
    * Fonction qui vérifie si un ilôt a son bon nombre de ponts 
    * @return Bool : Vrai ou faux en fonction de si l'ilôt a le nombre de ponts correspondant à celui qu'il devrait avoir 
    */
    public static boolean nbCardinalité(Ilot ile) {
        if(ile.getValeur() < nb_ponts(ile)) {
            ile.setStyleRed();
            return true;
        }
        return false;
    }

    public static void setMarque(Ilot ile, Boolean marque) {
        Aide.marque.put(ile, marque);
    }

    /**
    * Fonction qui détecte quand le jeu forme un endroit fermé qui ne peut plus être lié au reste du plateau sur le plateau
    * @return : true ou false si le plateau présente au moment T au moins un endroit fermé
    */
    public static void estEnsembleFermeParcours(Ilot ile) {
        Set<Ilot> ilesVisitees = new HashSet<>();
        dfs(ile, ilesVisitees); 
        for (Ilot i : ilesVisitees) {
            Aide.setMarque(i,false); 
        }
        for (Ilot i : ilesVisitees) {
            if ((i.getValeur() <= Aide.nb_ponts(i)) || (grille.getAllValidPont(i).size()==0)) {
                boolean ensembleFerme = true;
                for (Ilot voisin : i.listeVoisinRelier()) {
                    if (!ilesVisitees.contains(voisin)) {
                        ensembleFerme = false; 
                        break;
                    }
                }
                if (ensembleFerme) {
                    Aide.setMarque(i,true); 
                }
            }
        }
    }
    
    private static void dfs(Ilot ile, Set<Ilot> ilesVisitees) {
        ilesVisitees.add(ile); 
        for (Ilot voisin : ile.listeVoisinRelier()) {
            if (!ilesVisitees.contains(voisin) && ile.getValeur() == Aide.nb_ponts(ile)) {
                dfs(voisin, ilesVisitees);
            }
        }
    }

    private static boolean estEnsembleFerme(Ilot ile) {
        Aide.estEnsembleFermeParcours(ile);
        int tmp=0;
        for (Map.Entry<Ilot, Boolean> entry : Aide.marque.entrySet()) {
            Boolean value = entry.getValue();
            if (value) {
                tmp++;
            }
        }
        if (tmp==marque.size()) {
            for (Map.Entry<Ilot, Boolean> entry : Aide.marque.entrySet()) {
                Ilot key = entry.getKey(); 
                Boolean value = entry.getValue(); 
                if (value) {
                    key.setStyleRed();
                }
            }
            Aide.marque=new HashMap<>();
            return true;
        }
        else {
            Aide.marque=new HashMap<>();
            return false;
        }
    }
    



    /**
     * Fonction indiquant lorsque le plateau présente une île qui n'a qu'un seul voisin, et qui est donc facilement reliable sans possiblité d'erreur
     * @param : Ilot : Chaque ilot de la grille sera traité par la fonction
     * @return : Bool :True si un des îlots de la grille de jeu présente le cas. False sinon.
     */
     public static boolean ile_1_voisin(Ilot ile) {
        if (ile.listeVoisin().size()==1 && ile.listeVoisinRelier().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
     }
     /*
      * 
      */
     public static boolean cas_3_coin_5_cote_7_milieu(Ilot ile){
        if(ile.listeVoisin().size() > ile.listeVoisinRelier().size()){
            if (((ile.getValeur()+1)/2) == ile.listeVoisin().size()) {
                if (ile.getValeur() == 3 || ile.getValeur() == 5 || ile.getValeur() == 7) {
                    return true;
                }
            }
        }
        return false;
     }
    /**
     * Fonction qui vérifie le cas 3_coin_5_cote_7_milieu.
     * @param : Ilot : Chaque ilot de la grille sera traité par la fonction
     * @return : Bool : True si au moins un des ilots de la grille confirme le cas. False sinon 
     */
    public static boolean cas_spe_3_coin_5_cote_7_milieu(Ilot ile) {
        if(Aide.nb_ponts(ile)!=ile.getValeur()){
            if (((ile.getValeur()+1)/2) == ile.listeVoisin().size()) {
                if (ile.getValeur() == 3 || ile.getValeur() == 5 || ile.getValeur() == 7) {
                    for (Ilot neighbor : ile.listeVoisin()) {
                        if (neighbor.getValeur() == 1) {
                            return true;
                        }
                    } 
                }
            }
        }
        return false;
    }

    public static int nb_ponts(Ilot ile) {
        int tmp=0;
        ArrayList<Pont> listpont = ile.getPonts();
        for (int j=0;j<listpont.size();j++) {
            tmp+=listpont.get(j).getNbTraits();
        }
        return tmp;
    }

    public static boolean cas_4_6_8_cote(Ilot ile){
        if (Aide.nb_ponts(ile)< ile.getValeur()) {
            if(ile.getValeur() == 6 ){
                if(ile.listeVoisin().size()==3) {
                    return true;
                }
            }
            else if(ile.getValeur() == 4){
                if(ile.listeVoisin().size()==2) {
                    return true;
                }
            }
            else if(ile.getValeur() == 8){
                return true;
            }
        }
        return false;
    }

    /**
     * Fonction qui vérifie le cas 4_avec_3_voisin_dont_2_1
     * @param : Ilot : Chaque ilot de la grille sera traité par la fonction
     * @return : Bool :True si au moins un des ilots de la grille confirme le cas. False sinon
     */
    public static boolean cas_4_avec_3_voisin_dont_2_1(Ilot ile) {
        int compteur = 0;
        // si l'ilot a un voisin qui a une cardinalité de 4 et 3 voisins
        if (ile.getValeur() == 4 && ile.listeVoisin().size() == 3) {
            if(Aide.nb_ponts(ile) < 4){
                // compte le nombre de voisin avec une cardinalité de 1
                for (Ilot voisin : ile.listeVoisin()) {
                    if (voisin.getValeur()==1) {
                        compteur+=1;
                    }
                }    
                if (compteur == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Fonction qui vérifie le cas 6_milieu
     * @param : Ilot : Chaque ilot de la grille sera traité par la fonction
     * @return : Bool :True si au moins un des ilots de la grille confirme le cas. False sinon
     */
    public static boolean cas_6_milieu(Ilot ile) {
        if (ile.getValeur() == 6 && ile.listeVoisin().size() == 4) {
            if (Aide.nb_ponts(ile) < 4) {
                for (Ilot voisin : ile.listeVoisin()) {
                    if (voisin.getValeur()==1) {
                        return true;
                    }
                }
            }    
        }
        return false;
    }
    /**
     * Fonction qui vérifie le cas isolation_iles
     * @param : Ilot : Chaque ilot de la grille sera traité par la fonction
     * @return : Bool :True si au moins un des ilots de la grille confirme le cas. False sinon
     */
    public boolean isIsolatedSegment(Ilot ilot) {
        // Vérifier que l'îlot a une seule liaison manquante
        int nbPontManquant = ilot.getValeur()- Aide.nb_ponts(ilot);
        if (nbPontManquant != 1) {
            return false;
        }
        // Récupérer l'îlot voisin connecté par la liaison manquante
        Ilot neighborIlot = null;
        for (Ilot neighbor : ilot.listeVoisin()) {
            if (ilot.listeVoisinRelier().contains(neighbor)) {
                neighborIlot = neighbor;
                break;
            }
        }
        // Vérifier que l'îlot voisin est complété et a un nombre de ponts supérieur ou égal au nombre de l'îlot original
        if (!(neighborIlot.getValeur() == Aide.nb_ponts(neighborIlot)) || neighborIlot.getValeur() < ilot.getValeur()) {
            return false;
        }
        // Vérifier que la liaison de l'îlot original avec l'îlot voisin crée un segment isolé
        int bridges = ilot.listeVoisinRelier().size() + neighborIlot.listeVoisinRelier().size();
        if (bridges == (ilot.getValeur() + neighborIlot.getValeur())) {
            return true;
        } else {
            return false;
        }
    }
    

    /**
     * Fonction qui vérifie le cas isolation_iles
     * @param : Ilot : Chaque ilot de la grille sera traité par la fonction
     * @return : Bool :True si au moins un des ilots de la grille confirme le cas. False sinon
     */
    public static boolean isolation_iles_3(Ilot ile) {
        int nbVoisinRestant = ile.listeVoisin().size() - ile.listeVoisinRelier().size();
        if (ile.getValeur()- nbVoisinRestant > 0) {
            for (Ilot voisin : ile.listeVoisin()) {
                int voisinValeur = voisin.getValeur();
                int pontConnecteVoisin = voisin.listeVoisinRelier().size();
                int pontManquantVoisin = voisinValeur - pontConnecteVoisin;
                if (pontManquantVoisin >= ile.getValeur()- nbVoisinRestant) {
                    return true;
                }
            }
            return false;
        }
        for (Ilot voisin : ile.listeVoisin()) {
            if (voisin.getValeur() > ile.getValeur()) {
                int valeurIsole = voisin.getValeur() - ile.getValeur() + 1;
                int pontIsole = 0;
                for (Ilot voisinIsole : voisin.listeVoisin()) {
                    if (voisinIsole != ile && voisinIsole.listeVoisinRelier().contains(voisin)) {
                        pontIsole++;
                    }
                }
                if (valeurIsole == pontIsole) {
                    return false;
                }
            }
        }
        return true; 
    }

    /** 
    * Fonction qui retourne le voisin en fonction de l'ile et de la direction passées en paramètre
    * @param ile, l'île dont on veut connaître le voisin
    * @param positions : direction du voisin que l'on veut connaître
    * @return retourne le voisin correspondant aux critère, si une ile n'a aucun voisin (impossible) la méthode retourne l'ile passer en parametres
    */
    public static Ilot getVoisinDirection(Ilot ile,String positions) {
        for(int i=0;i<ile.listeVoisin().size();i++) {
            if (ile.listeVoisin().get(i).getPosX()>ile.getPosX() && positions=="gauche") {
                return ile.listeVoisin().get(i);
            }
            else if (ile.listeVoisin().get(i).getPosX()<ile.getPosX() && positions=="droit") {
                return ile.listeVoisin().get(i);
            }
            else if (ile.listeVoisin().get(i).getPosY()>ile.getPosY() && positions=="haut") {
                return ile.listeVoisin().get(i);
            }
            else if (ile.listeVoisin().get(i).getPosY()<ile.getPosY() && positions=="bas") {
                return ile.listeVoisin().get(i);
            }
        }
        return ile;
    }
}










