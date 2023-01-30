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
    private String type;
    
    /**
     * Propose une technique au joueur selon la situation dans laquelle il se trouve 
     * pour cela la méthode parcours le plateau et avec une série de conditions détermine la bonne technique a renvoyer
     * @return le nom de la methode a appliquer ainsi qu'une rapide description
     */
    public String getTechnique(){

    }
    
    /**
     * Verifie si le plateau actuel contient des erreurs ou non
     * @return Bool : True si le plateau contient une erreur, false sinon
     */ 
    public Boolean checkErreur(){
        
    }

    /**
     * Colorie des ilots a changer pour aider le joueur 
     * La couleur depend de la valeur de la variable d'instance de couleur_aide_erreur de la classe Parametre  @see {@link #setColor()}
     * @return void
     */
    public Void indiceVisuel(){

    }
    /** 
    * Corrige les erreurs trouvees dans le plateau de jeu
    * @return void
    */
    public Void fixErreurs(){

    }  
}