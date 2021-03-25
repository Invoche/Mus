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

  public LinkedList<Joueur> joueursAdversaires(Joueur joueurCourant) {
    Joueur adversaire1;
    Joueur adversaire2;

    if (joueurCourant == joueursOrdonnee.peek() || joueurCourant == joueursOrdonnee.get(2)) {
      adversaire1 = joueursOrdonnee.get(1);
      adversaire2 = joueursOrdonnee.getLast();
    } else {
      adversaire1 = joueursOrdonnee.peek();
      adversaire2 = joueursOrdonnee.get(2);
    }
    LinkedList<Joueur> adversaires = new LinkedList<>();
    adversaires.add(adversaire1);
    adversaires.add(adversaire2);
    return adversaires;
  }

  public LinkedList<Joueur> garderMemoire(LinkedList<Joueur> joueurTemporaire) {
  joueurTemporaire.add(joueursOrdonnee.peek());
  joueurTemporaire.add(joueursOrdonnee.get(1));
  joueurTemporaire.add(joueursOrdonnee.get(2));
  joueurTemporaire.add(joueursOrdonnee.getLast());

  return joueurTemporaire;
  }

  public LinkedList<Joueur> setOpposant(LinkedList<Joueur> joueursInitiaux){
    while(!joueursOrdonnee.isEmpty()){
      joueursOrdonnee.poll();
    }
    joueursOrdonnee.add(joueursInitiaux.peek());
    joueursOrdonnee.add(joueursInitiaux.get(1));
    joueursOrdonnee.add(joueursInitiaux.get(2));
    joueursOrdonnee.add(joueursInitiaux.getLast());

    return joueursOrdonnee;
  }

  public LinkedList<Joueur> retirer(Joueur joueurCourant){
    Joueur j;
    while(joueursOrdonnee.peek() == joueurCourant){
      j = joueursOrdonnee.poll();
      joueursOrdonnee.add(j);
    }
    joueursOrdonnee.poll();
    return joueursOrdonnee;
    }


  public Joueur joueurEsku() {
    return joueursOrdonnee.peek();
  }

  public Joueur coequipierJoueurEsku() {
    return joueursOrdonnee.get(2);
  }

  public Joueur coequipierJoueurZaku() {
    return joueursOrdonnee.get(1);
  }

  public Joueur joueurZaku() {
    return joueursOrdonnee.getLast();
  }

  public List<Joueur> dansLOrdre() {
    return List.of(joueurEsku(), coequipierJoueurZaku(), coequipierJoueurEsku(), joueurZaku());
  }

  public Iterator<Joueur> itererDansLOrdre() {
    return new IteratorInfini(this);
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
