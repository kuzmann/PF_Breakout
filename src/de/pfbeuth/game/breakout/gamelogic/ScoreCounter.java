package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.*;

public class ScoreCounter {

    private Breakout breakout;
    private int score = 0;
    private int endscore;

    public ScoreCounter (Breakout breakout){
    	this.breakout = breakout;
	}

    /** Increases the score by an amount according to the colour of the brick which has been hit. */
    public enum BrickColor {
        GREEN, YELLOW, ORANGE, RED
    }

    public void counter(BrickColor brickcolor){
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
    public int stopcounting(){
        endscore = score;
        System.out.println("EndScore:" + endscore);
        return endscore;
    }

    public int getScore() {
        return score;
    }
}


