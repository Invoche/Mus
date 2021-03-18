package com.montaury.mus.jeu.joueur;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Opposants {
  private final LinkedList<Joueur> joueursOrdonnee = new LinkedList<>();

  public Opposants(Equipe equipeAvecJoueurEsku, Equipe equipeAvecJoueurZaku) {
    joueursOrdonnee.add(equipeAvecJoueurEsku.joueurA);
    joueursOrdonnee.add(equipeAvecJoueurZaku.joueurA);
    joueursOrdonnee.add(equipeAvecJoueurEsku.joueurB);
    joueursOrdonnee.add(equipeAvecJoueurZaku.joueurB);
  }

  public void tourner() {
    Joueur eskuProchainZaku = joueursOrdonnee.poll();
    joueursOrdonnee.add(eskuProchainZaku);
  }

  public Equipe deuxJoueursOpposants(Joueur joueurCourant, Equipe equipe1, Equipe equipe2) {
    Joueur adversaire1;
    Joueur adversaire2;

    if (joueurCourant == equipe1.joueurA || joueurCourant == equipe1.joueurB) {
      adversaire1 = equipe2.joueurA;
      adversaire2 = equipe2.joueurB;
    } else {
      adversaire1 = equipe1.joueurA;
      adversaire2 = equipe1.joueurB;
    }
    Equipe adversaire = new Equipe(adversaire1, adversaire2);
    return adversaire;
  }

  public Joueur joueurEsku() {
    return joueursOrdonnee.peek();
  }

  public Joueur joueurZaku() {
    return joueursOrdonnee.getLast();
  }

  public Iterator<Joueur> itererDansLOrdre() {
    return new IteratorInfini(this);
  }

  public List<Joueur> dansLOrdre() {
    return List.of(joueurEsku(), joueursOrdonnee.get(1), joueursOrdonnee.get(2), joueurZaku());
  }

  private static class IteratorInfini implements Iterator<Joueur> {
    private final Opposants opposants;
    private Joueur suivant;

    public IteratorInfini(Opposants opposants) {
      this.opposants = opposants;
      suivant = opposants.joueurEsku();
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public Joueur next() {
      Joueur next = suivant;
      for (int i = 0; i < 3; i++) {
        if (suivant == opposants.joueursOrdonnee.get(i)) {
          suivant = opposants.joueursOrdonnee.get(i+1);
          break;
        }
      }
      if(suivant == opposants.joueurZaku()){
        suivant = opposants.joueurEsku();
      }
      return next;
    }
  }
}
