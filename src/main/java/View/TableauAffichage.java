package View;

import java.util.Arrays;
import java.util.List;

public class TableauAffichage {
    public void updateBestScores(int newBestScore) {
        if(!connecte) {
            throw new IllegalStateException("Impossible, non connecté au tableau d'affichage");
        }

        System.out.println("Enregistrer best score");
    }

    public enum StrikeSerie {
        PREMIER,
        SECOND,
        TROISIEME_ET_PLUS
    }

    private boolean connecte = false;

    public boolean seConnecter() {
        System.out.println("Connexion en cours");
        try {
            Thread.sleep(20000);
            if(true) {
                throw new RuntimeException("Impossible de se connecter au tableau");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        connecte = true;
        System.out.println("Connexion effectuée");
        return connecte;
    }

    public void showStrike(StrikeSerie strikeSerie) {
        if(!connecte) {
            throw new IllegalStateException("Impossible, non connecté au tableau d'affichage");
        }

        switch (strikeSerie) {
            case PREMIER:
                System.out.println("Affiche du premier strike");
                break;
            case SECOND:
                System.out.println("Affiche du second strike");
                break;
            case TROISIEME_ET_PLUS:
                System.out.println("Affiche à partir du troisième strike");
                break;
        }
    }

    public void showSpare() {
        if(!connecte) {
            throw new IllegalStateException("Impossible, non connecté au tableau d'affichage");
        }

        System.out.println("Affiche spare");
    }

    public List<Integer> bestScores() {
        if(!connecte) {
            throw new IllegalStateException("Impossible, non connecté au tableau d'affichage");
        }

        return Arrays.asList(280,274,270);
    }
}
