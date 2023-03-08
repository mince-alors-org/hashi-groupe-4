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
        technique.put("Iles avec autant de voisin que la moitié de leur cardinalité","Une ile qui a une cardinalité égale à 2 fois son nombre de voisins est forcément connecté avec ces derniers")
        technique.put("Iles avec un seul voisin", 
        "Les îles qui ont 1 ou 2 cardinalités et qui n'ont qu'un voisin sont forcement liée a celui-ci");
        technique.put("Iles avec 3 dans les coins, 5 sur les côtés et 7 au milieu",
        "Une île marquée 3 dans un coin a 2 voisins avec un pont connecté à un voisin et 2 ponts connectés à l'autre. De manière similaire, un îlot de getValeur() 5 doit au minimum avoir un pont qui le connecte à ses 3 voisins et une île de getValeur() 7 doit avoir au minimum un pont qui la connecte à ses 4 voisins");
        technique.put("Cas spécial des 3 dans les coins, 5 sur les côtés et 7 au milieu",
        "Si une île est de getValeur() 3 et qu'un de ses voisins est une île avec l'indice 1, alors toutes les conditions sont réunies et les 3 ponts peuvent être tracés, la même logique peut être appliquée si une île de getValeur() 5 sur le côté ou une île de getValeur() 7 au milieu a pour voisin une île marquée par un 1");
        technique.put("Iles sur un coté avec 4 de cardinalités",                        
        "Si un Ilot a 3 voisins avec 4 de cardinalité et que 2 des ces voisins ont 1 de cardinalité, ces deux voisins sont forcement connecté a l'Ilot 4 et celui-ci a forcement 2 ponds avec son dernier voisin");
        technique.put("Iles aux millieux avec 6 de cardinalités", 
        "Si un Ilot a 6 cardinalité a 4 voisins et que l'un d'entre eux a 1 de cardinalité alors il y a 2 possibilités: \n - si l'ilot avec 1 de cardinalité est forcement liée a l'ilot 6 alors celui-ci a minimum 1 pond avec tout ces autres voisins \n - si l'ilot avec 1 de cardinalité n'est pas liée a l'ilot 6 alors celui-ci a forcement 2 ponds avec tout ces voisins");
        technique.put("Isolation des segments","Il est interdit qu'un bloc d'île soit isolé du reste des autres, lorsqu'il y a 2 îles de cardinalité 1 ou deux îles de cardinalités 2, il est interdit de les connecter entre eux s'ils peuvent être connectés à d'autres." );
        technique.put("Isolation lorsqu'un pont joint une île","Veillez à ce que lorsque vous connectez les îles entre elles, elles forment toujours un seul et même chemin, il ne doit pas y avoir plusieurs blocs d'îles");
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
            if (ile_debut(grille.listeIlots[i])) {
                return this.technique.get("Iles avec autant de voisin que la moitié de leur cardinalité");
            }
            else if (ile_1_voisin(grille.listeIlots[i])) {
                return this.technique.get("Iles avec un seul voisin");
            }
            else if (cas_3_coin_5_cote_7_milieu(grille.listeIlots[i])) {
                return this.technique.get("Iles avec 3 dans les coins, 5 sur les côtés et 7 au milieu");
            }
            else if (cas_4_avec_3_voisin_dont_2_1(grille.listeIlots[i])) {
                return this.technique.get("Iles sur un coté avec 4 de cardinalités");
            }
            else if (6_milieu(grille.listeIlots[i])) {
                return this.technique.get("Iles aux millieux avec 6 de cardinalités");
            }
            else if (isolation_iles(grille.listeIlots[i])) {
                return this.technique.get("Isolation des segments");
            }
            else if (isolation_iles_3(grille.listeIlots[i])) {
                return this.technique.get("");
            }
        }
    }
    
    /**
     * Verifie si le plateau actuel contient des erreurs ou non
     * @return Bool : True si le plateau contient une erreur, false sinon
     */ 
    public Boolean checkErreur(){
        
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
        if ((ile.nbVoisinRestant()== ile.getValeur()/2) {
            return true;
        }
        else {
            return false;
        }
     }


     public boolean ile_1_voisin(Ilot ile) {
        if (ile.listeVoisin().size()==1 && ile.listeVoisinRelie().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
     }

    public boolean cas_3_coin_5_cote_7_milieu(Ilot ile) {
        if (ile.getValeur() == 3 || ile.getValeur() == 5 || ile.getValeur() == 7) {
            for (Ilot neighbor : ile.listeVoisin()) {
                if (neighbor.getValeur() == 1) {
                    return true;
                }
                return false;
            }
        }
    }

    public boolean cas_4_avec_3_voisin_dont_2_1(Ilot ile) {
        int compteur;
        // si l'ilot a un voisin qui a une cardinalité de 4 et 3 voisins
        if (ile.getValeur() == 4 && ile.listeVoisin().size() == 3) {
            // compte le nombre de voisin avec une cardinalité de 1
            for (Ilot voisin : ile.listeVoisin()) {
                if (voisin.getValeur()==1) {
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

    public boolean 6_milieu(Ilot ile) {
        if (ile.getValeur() == 6 && ile.listeVoisin().size() == 4) {
            for (Ilot voisin : ile.voisins) {
                if (voisin.getValeur()==1) {
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    public boolean isolation_iles(Ilot ile) {
        boolean condition = false;
        if (ile.getPosX == grille.taille-1 && ile.getValeur() == 2) {
            if (ile.listeVoisin().size() == 2) {
                Ilot voisinGauche = getVoisinDirection(ile,"gauche");
                condition = (voisin.getValeur() == 1 && voisinGauche.listeVoisin().size() == 1);
            } else {
                int requiredCol = ile.getPosY() + 2; // a revoir
                Ilot voisin = getIle(ile.getPosX(), requiredCol);
                if (ile.listeVoisin().contains(voisin)) {
                    condition = true;
                }
            }
        } else if (ile.getPosY() == 0 && ile.getValeur() == 3) {
            if (ile.listeVoisin().size() == 3) {
                Ilot voisinHaut = getVoisinDirection(ile,"haut");
                condition = (voisinHaut.getValeur() == 2 && voisinHaut.listeVoisin().size() == 2);
            } else {
                int requiredRow = ile.getPosX() - 2; // a revoir
                Ilot voisin = getIle(requiredRow, ile.getPosY());
                if (ile.listeVoisin().contains(voisin)) {
                    condition = true;
                }
            }
        } else {
            condition = false;
        }
        return condition;
    }

    public boolean isolation_iles_3(Ilot ile) {
        if (ile.getValeur()-ile.nbVoisinRestant() > 0) {
            for (Ilot voisin : ile.listeVoisin()) {
                int voisinValeur = voisin.getValeur();
                int pontConnecteVoisin = voisin.listeVoisinRelie().size();
                int pontManquantVoisin = voisinValeur - pontConnecteVoisin;
                if (pontManquantVoisin >= ile.getValeur()-ile.nbVoisinRestant()) {
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
                    if (voisinIsole != ile && voisinIsole.listeVoisinRelie.contains(voisin)) {
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


    public static Ilot getVoisinDirection(Ilot ile,String positions) {
        for(int i=0;i<ile.listeVoisin().size;i++) {
            if (ile.listeVoisin()[i].getPosX()>ile.getPosX() && positions=="gauche") {
                return ile.listeVoisin()[i];
            }
            else if (ile.listeVoisin()[i].getPosX()<ile.getPosX() && positions=="droit") {
                return ile.listeVoisin()[i];
            }
            else if (ile.listeVoisin()[i].getPosY()>ile.getPosY() && positions=="haut") {
                return ile.listeVoisin()[i];
            }
            else if (ile.listeVoisin()[i].getPosY()<ile.getPosY() && positions=="bas") {
                return ile.listeVoisin()[i];
            }
        }
    }
}










