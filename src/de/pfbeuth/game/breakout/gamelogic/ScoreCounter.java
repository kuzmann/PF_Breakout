package de.pfbeuth.game.breakout.gamelogic;


import de.pfbeuth.game.breakout.gameEngine.*;

public class ScoreCounter {

    private static Breakout breakout;
    public static int score = 0;
    public static int endscore;

    /** Increases the score by an amount according to the colour of the brick which has been hit. */
    public enum BrickColor {
        GREEN, YELLOW, ORANGE, RED
    }

    public static void counter(BrickColor brickcolor){
        switch (brickcolor){

            case GREEN:
                score +=5;
                System.out.println("Score nach Grün:" + score);
                break;
            case YELLOW:
                score +=10;
                System.out.println("Score nach Gelb:" + score);
                break;
            case ORANGE:
                score +=25;
                System.out.println("Score nach Organge:" + score);
                break;
            case RED:
                score +=50;
                System.out.println("Score nach Rot:" + score);
                break;
        }
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


