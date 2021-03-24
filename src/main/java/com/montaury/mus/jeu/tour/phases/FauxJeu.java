package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;

import static com.montaury.mus.jeu.tour.phases.Jeu.aLeJeu;

public class FauxJeu extends Phase {
  public FauxJeu() {
    super("Faux Jeu");
  }

  @Override
  protected boolean peutParticiper(Joueur joueur) {
    return !aLeJeu(joueur);
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    int pointsJoueurEsku = opposants.joueurEsku().main().pointsPourJeu();
    int pointsJoueurZaku = opposants.joueurZaku().main().pointsPourJeu();
    int pointsCoequipierJoueurEsku = opposants.coequipierJoueurEsku().main().pointsPourJeu();
    int pointsCoequipierJoueurZaku = opposants.coequipierJoueurZaku().main().pointsPourJeu();

    if(pointsJoueurEsku >= pointsJoueurZaku){
      if(pointsJoueurEsku >= pointsCoequipierJoueurZaku){
        return opposants.joueurEsku();
      }
      else{
        return opposants.coequipierJoueurZaku();
      }
    }

    if(pointsJoueurZaku >= pointsJoueurEsku){
      if(pointsJoueurZaku >= pointsCoequipierJoueurEsku){
        return opposants.joueurZaku();
      }
      else{
        return opposants.coequipierJoueurEsku();
      }
    }
    return opposants.joueurEsku();
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return 1;
  }
}
