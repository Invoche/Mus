package com.montaury.mus.jeu.joueur;

public class Equipe implements Joueur {

    private String nomEquipe;
    private Joueur j1;
    private Joueur j2;

    public Equipe (String nomEquipe, Joueur j1, Joueur j2) {
        this.nomEquipe  = nomEquipe;
        this.j1 = j1;
        this.j2 = j2;
    }

}
