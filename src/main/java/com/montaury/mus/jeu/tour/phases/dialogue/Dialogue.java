package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

import static com.montaury.mus.jeu.tour.phases.dialogue.TypeChoix.*;

public class Dialogue {
  private final List<ChoixJoueur> choix = new ArrayList<>();

  public final DialogueTermine derouler(AffichageEvenementsDeJeu affichage, Opposants opposants) {
    Iterator<Joueur> joueursParlant = opposants.dansLOrdre().iterator();
    List<TypeChoix> prochainsChoix = TypeChoix.INITIAUX;
    List<ChoixJoueur> choix = new ArrayList<>();
    LinkedList<Joueur> joueursTemporaires = new LinkedList<>();
    opposants.garderMemoire(joueursTemporaires);

    while (joueursParlant.hasNext()){
      Joueur parlant = joueursParlant.next();
      Choix choixJoueur = parlant.interfaceJoueur.faireChoixParmi(prochainsChoix);
      affichage.choixFait(parlant, choixJoueur);
      choix.add(new ChoixJoueur(choixJoueur, parlant));

      if(choixJoueur.estUneMise()){
        joueursParlant = opposants.joueursAdversaires(parlant).iterator();
        prochainsChoix = choixJoueur.prochainsChoixPossibles();
      }
      else if(choixJoueur.est(TIRA)){
        opposants.retirer(parlant);
      }
      else if(choixJoueur.est(KANTA) || choixJoueur.est(IDOKI)){
        joueursParlant = Collections.emptyIterator();
        prochainsChoix = Collections.emptyList();
      }
    }
    opposants.setOpposant(joueursTemporaires);
    return new DialogueTermine(choix);
  }

  void ajouter(Choix choix, Joueur joueur) {
    this.choix.add(new ChoixJoueur(choix, joueur));
  }

  boolean enCours() {
    return choix.size() <= 1 || (!dernierChoix().metFinAuDialogue() && !dernierChoix().est(PASO));
  }

  private Choix dernierChoix() {
    return choix.get(choix.size() - 1).choix;
  }

  private List<TypeChoix> prochainsChoixPossibles() {
    return choix.isEmpty() ? TypeChoix.INITIAUX : dernierChoix().prochainsChoixPossibles();
  }

  public static class ChoixJoueur {
    public final Choix choix;
    public final Joueur joueur;

    public ChoixJoueur(Choix choix, Joueur joueur) {
      this.choix = choix;
      this.joueur = joueur;
    }

    public int mise() {
      return choix.mise();
    }
  }
}
