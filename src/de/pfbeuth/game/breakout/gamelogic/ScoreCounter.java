package de.pfbeuth.game.breakout.gamelogic;


import de.pfbeuth.game.breakout.gameEngine.*;

public class ScoreCounter {

    private static Breakout breakout;
    public static int score = 0;
    public static int endscore;


    /** Increases the score by an amount according to the colour of the brick which has been hit. */
    public static int counter() {

        score +=10;

        /*if ((breakout.getBall().getDestroyedBrick().getSpriteImage().getImage().equals(breakout.brickImageGreen)) {
            ScoreCounter.counter();
            System.out.println("blababadsfadsf");
        } else System.out.println("kein vergleich");*/


        System.out.println("Score:" + score);

        return score;
    }

    public static int stopcounting(){
        endscore = score;
        System.out.println("EndScore:" + endscore);
        return endscore;
    }

}

