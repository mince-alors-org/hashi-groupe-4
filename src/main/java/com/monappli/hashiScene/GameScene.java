package com.monappli.hashiScene;

import com.monappli.BaseDonneeJoueur;
import com.monappli.Aide;
import com.monappli.Grille;
import com.monappli.Hashi;
import com.monappli.handlers.GameHandler;
import com.monappli.handlers.TutoGameH;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameScene extends MainPanel{
    Grille grille;
    Pane gridPlacement;
    String lvlName;

    public  GameScene (Pane parent){
        super(parent);
    }

    public <H extends TutoGameH> void pasteAndHandle(String nomF, H hand, String lvlName) throws Exception{
        super.pasteAndHandle(nomF, hand);
        this.grille= new Grille(lvlName, (Pane)this.getParent().lookup("#gridPlacement"), (Canvas)this.getParent().lookup("#fond"), this.getParent());
        Aide.setGrilleAide(grille);
        hand.setGrille(this.grille);
        hand.setLvlNum(lvlName);
        hand.setLvlNum(BaseDonneeJoueur.getJoueurEmplacementSauvegarde(Hashi.joueur) + formateLvlName(lvlName));
        this.grille.setFichier_sauvegarde(BaseDonneeJoueur.getJoueurEmplacementSauvegarde(Hashi.joueur) + formateLvlName(lvlName));
        this.grille.setGh(hand);
        this.grille.chargerSauvegarde();
        System.out.println(this.grille.getFichier_sauvegarde());
        //hand.getSave().chargerFichier2(hand.getLvlNum());
        //System.out.println(hand.getLvlNum());





    }

  /**
   * Formatte un chemin
   * @author nmention
   *
   * @param input path à formatter
   * @return le chemin formatté pour construire le chemin final correspondant à l'emplacement de sauvegarde de niveau spécifique (e.g 1-1) pour chaque joueur
   *
   */
    public static String formateLvlName(String input){
      String result = "";
      Pattern pattern = Pattern.compile("(?<=/)[1-9].*");
      Matcher matcher = pattern.matcher(input);
      System.out.println(matcher.find());

      result = matcher.group(0);
      System.out.println(result);
      return "/" + result;



    }
}
