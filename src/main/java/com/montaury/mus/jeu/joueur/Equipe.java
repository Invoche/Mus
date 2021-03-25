package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.Manche;

public class Equipe {
    public final Joueur joueurA;
    public final Joueur joueurB;

    public Equipe(Joueur joueurA, Joueur joueurB){
        this.joueurA = joueurA;
        this.joueurB = joueurB;
    }
}
