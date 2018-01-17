package de.pfbeuth.game.breakout.gamelogic;


import de.pfbeuth.game.breakout.gameEngine.*;

public class ScoreCounter {

    private static Breakout breakout;
    public static int score = 0;
    public static int endscore;


    /** Increases the score by an amount according to the colour of the brick which has been hit. */
    //TODO refactor to Switch statement or ENUMs
    public static int countgreen() {
        score +=5;
        System.out.println("Score nach Grün:" + score);

        return score;
    }

    public static int countyellow() {
        score +=10;
        System.out.println("Scoren nach Gelb:" + score);

        return score;
    }

    public static int countorange() {
        score +=25;
        System.out.println("Score nach Orange:" + score);

        return score;
    }

    public static int countred() {
        score +=50;
        System.out.println("Score nach Rot:" + score);

        return score;
    }

    //TODO: Überprüfen ob, mann diese Methode noch braucht. Sonst löschen.
    public static int stopcounting(){
        endscore = score;
        System.out.println("EndScore:" + endscore);
        return endscore;
    }

    public static int getScore() {
        return score;
    }

}


