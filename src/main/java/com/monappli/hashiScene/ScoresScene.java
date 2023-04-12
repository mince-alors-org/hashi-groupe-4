package com.monappli.hashiScene;

import com.monappli.SauvegardeGrille;
import com.monappli.handlers.DynamicEventHandler;
import javafx.scene.layout.Pane;

public class ScoresScene extends MainPanel{

  /**
   * Initialization of MainPanel
   *
   * @param parent
   * @author Collard Ambre
   */


  private SauvegardeGrille sauvegardeGrille;
  public ScoresScene(Pane parent) {
    super(parent);
    this.sauvegardeGrille = new SauvegardeGrille();
  }


  @Override
  public <H extends DynamicEventHandler> void pasteAndHandle(String name, H handler) throws Exception {
    super.pasteAndHandle(name, handler);
  }







}
