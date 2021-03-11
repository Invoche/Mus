package com.montaury.mus.jeu.joueur;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Opposants {
  private final LinkedList<Joueur> joueursOrdonnee = new LinkedList<>();

  public Opposants(Equipe equipeAvecJoueurEsku, Equipe equipeAvecJoueurZaku) {
    joueursOrdonnee.add(equipeAvecJoueurEsku.joueur);
    joueursOrdonnee.add(equipeAvecJoueurZaku.joueur);
  }

  public void tourner() {
    Joueur eskuProchainZaku = joueursOrdonnee.poll();
    joueursOrdonnee.add(eskuProchainZaku);
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
    return List.of(joueurEsku(), joueurZaku());
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
      suivant = suivant == opposants.joueurEsku() ? opposants.joueurZaku() : opposants.joueurEsku();
      return next;
    }
  }
}
