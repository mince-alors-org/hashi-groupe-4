import java.util.HashMap;

/**
 * Cette classe permet de proposer différents types d'aides au joueur :
 * - Des techniques applicables pour débloquer le joueur 
 * - Des aides visuelles pour lui indiquer directement où il faut qu'il joue
 * - Des détecteurs et correcteurs d'erreurs 
 * @author Fonte Clément
 * @author Rollet Gabrielle 
 * @author Nail Léo
 * @version 1.0
 */
public class Aide {
    private String type;    
    private static final HashMap<String, String> technique = new HashMap<String, String>();
    static {
        technique.put("Iles avec un seul voisin", 
        "Les îles qui ont 1 ou 2 cardinalités et qui n'ont qu'un voisin sont forcement liée a celui-ci");
        technique.put("Iles avec 3 dans les coins, 5 sur les côtés et 7 au milieu",
        "Une île marquée 3 dans un coin a 2 voisins avec un pont connecté à un voisin et 2 ponts connectés à l'autre. De manière similaire, un îlot de valeur 5 doit au minimum avoir un pont qui le connecte à ses 3 voisins et une île de valeur 7 doit avoir au minimum un pont qui la connecte à ses 4 voisins");
        technique.put("Cas spécial des 3 dans les coins, 5 sur les côtés et 7 au milieu",
        "Si une île est de valeur 3 et qu'un de ses voisins est une île avec l'indice 1, alors toutes les conditions sont réunies et les 3 ponts peuvent être tracés, la même logique peut être appliquée si une île de valeur 5 sur le côté ou une île de valeur 7 au milieu a pour voisin une île marquée par un 1");
        technique.put("Iles sur un coté avec 4 de cardinalités",                        
        "Si un Ilot a 3 voisins avec 4 de cardinalité et que 2 des ces voisins ont 1 de cardinalité, ces deux voisins sont forcement connecté a l'Ilot 4 et celui-ci a forcement 2 ponds avec son dernier voisin");
        technique.put("Iles aux millieux avec 6 de cardinalités", 
        "Si un Ilot a 6 cardinalité a 4 voisins et que l'un d'entre eux a 1 de cardinalité alors il y a 2 possibilités: \n - si l'ilot avec 1 de cardinalité est forcement liée a l'ilot 6 alors celui-ci a minimum 1 pond avec tout ces autres voisins \n - si l'ilot avec 1 de cardinalité n'est pas liée a l'ilot 6 alors celui-ci a forcement 2 ponds avec tout ces voisins");
        technique.put("", 
        "");
    }

    /**
     * Constantes qui correspondent aux codes d'erreurs qui sont reconnus dans le reste du code
     */

    // Code d'erreur qui signifie qu'un ilot a trop de pond (erreur de cardinalité) 
    final String nbCardinaliteIncorrect="ERR101";

    // Code d'erreur qui signifie qu'il n'y a pas le bon nombre de pond
    final String nbPontIncorrectCorrects="ERR102";

    // Code d'erreur qui signifie qu'il y a un endroit fermé sur la grille
    final String endroitLock="ERR103";

    /**
     * pour cela la méthode parcours le plateau et avec une série de conditions détermine la bonne technique a renvoyer
     * @return le nom de la methode a appliquer ainsi qu'une rapide description
     */
    public HashMap<String, String> getTechnique(){
        for(int i=0;i<grille.listeIlots;i++) {
            if(ile_debut(grille.listeIlots[i])) {
                    return this.technique.get("");
                }
            }
            else if (ile_1_voisin) {
                return this.technique.get("Iles avec un seul voisin");
            }
            else if ()
    }
    
    /**
     * Verifie si le plateau actuel contient des erreurs ou non
     * @return Bool : True si le plateau contient une erreur, false sinon
     */ 
    public Boolean checkErreur(){
        Boolean erreur = false;
        for(Ilot.getIles()){
            int nbPontCorrects = ilot.valeur(); 
            int nbPontsActuels = ilot.getNombreDePonts();
            if(nbPontCorrects != nbPontsActuels){
                erreur = true;
            }
        }
        return erreur;
    }

    /**
     * Colorie des ilots a changer pour aider le joueur 
     * La couleur depend de la valeur de la variable d'instance de couleur_aide_erreur de la classe Parametre  @see {@link #setCouleur_aide_erreur()}
     * @return void
     */
    public void indiceVisuel(){

    }
    /** 
    * Corrige les erreurs trouvees dans le plateau de jeu
    * @return void
    */
    public void fixErreurs(){

    }  

    /**
     * 
     */
     public boolean ile_debut(Ilot ile) {
        if ((ile.nbVoisinRestant()/2)== ile.valeur) {
            return true;
        }
        else {
            return false;
        }
     }

     public boolean ile_1_voisin(Ilot ile) {
        if (ile.nbVoisin==1 && ile.ponts.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
     }

    public boolean cas_3_coin_5_cote_7_milieu(Ilot ile) {
        if (ile.valeur == 3 || ile.valeur == 5 || ile.valeur == 7) {
            for (Ilot neighbor : ile.voisins) {
                if (neighbor.valeur == 1) {
                    return true;
                }
                return false;
            }
        }
    }

    public boolean cas_4_avec_3_voisin_dont_2_1(Ilot ile) {
        int compteur;
        if (ile.valeur == 4 && ile.voisins.length == 3) {
            for (Ilot voisin : ile.voisins) {
                if (voisin.valeur==1) {
                    compteur+=1;
                }
            }    
            if (compteur==2) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean 6_milieu(Ilot iles) {
        if (ile.valeur == 6 && ile.voisins.length == 4) {
            for (Ilot voisin : ile.voisins) {
                if (voisin.valeur==1) {
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    public boolean isolation_3_iles(Ilot ile) {
        
    }
}