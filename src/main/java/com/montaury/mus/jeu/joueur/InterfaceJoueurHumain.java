package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.Tira;
import com.montaury.mus.jeu.tour.phases.dialogue.TypeChoix;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InterfaceJoueurHumain implements InterfaceJoueur {

    private final Scanner scanner = new Scanner(System.in);
    private Main main;

    @Override
    public boolean veutAllerMus() {
        println("Souhaitez-vous aller mus ? (o/n)");
        while(true) {
            String choix = scanner.next();
            if (choix.equals("o"))
                return true;
            if (choix.equals("n"))
                return false;
            else
                println("Message Incorrect ! Veuillez écrire les mots comme indiqué : (o/n)");

        }

    }

    @Override
    public List<Carte> cartesAJeter() {
        println("Veuillez saisir les cartes à jeter (ex: 1,2,3 sachant que 1= Votre première carte) :");
        String aJeter = scanner.next();
        return Arrays.stream(aJeter.split(","))
                .mapToInt(Integer::parseInt)
                .mapToObj(indiceCarte -> main.cartesDuPlusGrandAuPlusPetit().get(indiceCarte - 1))
                .collect(Collectors.toList());
    }

    @Override
    public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {

        print("Faites un choix entre (en toutes lettres): ");

        while (true) {
            println(choixPossibles.stream().map(TypeChoix::nom).collect(Collectors.joining(" | ")));
            String choix = scanner.next();

            /*if (choix !=  choixPossibles.stream().map(TypeChoix::nom).collect(Collectors.joining(" | ")))
            {
                print("Message incorrect ! Veuilez écrire les mots comme indiqué : ");
            }
            */
            if (choix.equalsIgnoreCase("Paso")) {
                return new Paso();
            }
            if
            (choix.equalsIgnoreCase("Imido")) {
                return new Imido();
            }
            if
            (choix.equalsIgnoreCase("Hordago")) {
                return new Hordago();
            }
            if
            (choix.equalsIgnoreCase("Idoki")) {
                return new Idoki();
            }
            if
            (choix.equalsIgnoreCase("Tira")) {
                return new Tira();
            }
            if
            (choix.equalsIgnoreCase("Gehiago")) {
                return new Gehiago(1);
            }
            if
            (choix.equalsIgnoreCase("Kanta")) {
                return new Kanta();
            } else {
                print("Message incorrect ! Veuilez écrire les mots comme indiqué : ");
            }
        }
    }

    @Override
    public void nouvelleMain(Main main) {
        this.main = main;
    }

    private void println(String string) {
        System.out.println(string);
    }

    private void print(String string) {
        System.out.print(string);
    }
}
