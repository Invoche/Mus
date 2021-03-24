  package com.montaury.mus.jeu.tour.phases;

  import com.montaury.mus.jeu.carte.Carte;
  import com.montaury.mus.jeu.carte.ValeurCarte;
  import com.montaury.mus.jeu.joueur.Joueur;
  import com.montaury.mus.jeu.joueur.Main;
  import com.montaury.mus.jeu.joueur.Opposants;
  import java.util.List;

  import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_GRANDE;
  import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_PETITE;

  public class Petit extends Phase {
    public Petit() {
      super("Petit");
    }

    @Override
    protected Joueur meilleurParmi(Opposants opposants) {
      Joueur joueurEsku = opposants.joueurEsku();
      Joueur joueurZaku = opposants.joueurZaku();
      Joueur coequipierJoueurEsku = opposants.coequipierJoueurEsku();
      Joueur coequipierJoueurZaku = opposants.coequipierJoueurZaku();
      List<Carte> cartesJoueurEsku = joueurEsku.main().cartesDuPlusGrandAuPlusPetit();
      List<Carte> cartesJoueurZaku = joueurZaku.main().cartesDuPlusGrandAuPlusPetit();
      List<Carte> cartesCoequipierJoueurEsku = coequipierJoueurEsku.main().cartesDuPlusGrandAuPlusPetit();
      List<Carte> cartesCoequipierJoueurZaku = coequipierJoueurZaku.main().cartesDuPlusGrandAuPlusPetit();

      for (int i = Main.TAILLE - 1; i >= 0; i--) {
        ValeurCarte.Comparaison compare = cartesJoueurEsku.get(i).comparerAvec(cartesJoueurZaku.get(i));
        ValeurCarte.Comparaison compareCoequipierZaku = cartesJoueurEsku.get(i).comparerAvec(cartesCoequipierJoueurZaku.get(i));
        ValeurCarte.Comparaison compareCoequipierEsku = cartesJoueurZaku.get(i).comparerAvec(cartesCoequipierJoueurEsku.get(i));
        if (compare == PLUS_PETITE) {
          if(compareCoequipierZaku == PLUS_PETITE){
            return joueurEsku;
          }
          else{
            return coequipierJoueurZaku;
          }
        }
        if (compare == PLUS_GRANDE) {
          if(compareCoequipierEsku == PLUS_PETITE){
            return joueurZaku;
          }
          else{
            return coequipierJoueurEsku;
          }
      }

    }
      return joueurEsku;
  }
  }
