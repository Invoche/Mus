package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;

public class Paires extends Phase {
  public Paires() {
    super("Paires");
  }

  @Override
  protected boolean peutParticiper(Joueur joueur) {
    return joueur.main().aDesPaires();
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurEsku = opposants.joueurEsku().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurZaku = opposants.joueurZaku().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesCoequipierJoueurEsku = opposants.coequipierJoueurEsku().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesCoequipierJoueurZaku = opposants.coequipierJoueurZaku().main().getPaires();

    if(pairesJoueurEsku.estMeilleureOuEgaleA(pairesJoueurZaku)){
      if(pairesJoueurEsku.estMeilleureOuEgaleA(pairesCoequipierJoueurZaku)){
        return opposants.joueurEsku();
      }
      else{
        return opposants.coequipierJoueurZaku();
      }
    }

    if (pairesJoueurZaku.estMeilleureOuEgaleA(pairesJoueurEsku)){
      if (pairesJoueurZaku.estMeilleureOuEgaleA(pairesCoequipierJoueurEsku)){
        return opposants.joueurZaku();
      }
      else
        {
        return opposants.coequipierJoueurEsku();
      }
    }
    return opposants.joueurEsku();
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
