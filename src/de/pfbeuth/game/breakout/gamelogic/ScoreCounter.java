package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.*;
import javafx.beans.property.*;

public class ScoreCounter {

    private Breakout breakout;
    public SimpleStringProperty score;
    private int scoreNumber;

    private int endscore;
    private StringProperty scoreAsString;

    public ScoreCounter (Breakout breakout){
    	this.breakout = breakout;
    	scoreNumber = 0;
    	score = new SimpleStringProperty();
    	//score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber = 0));


	}

    /** Increases the score by an amount according to the colour of the brick which has been hit. */
    public enum BrickColor {
        GREEN, YELLOW, ORANGE, RED
    }

    public void counter(BrickColor brickcolor) {
    	switch (brickcolor) {
			case GREEN:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 5));
				System.out.println("Score nach Grün:" + score);
				break;
			case YELLOW:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 10));
				System.out.println("Score nach Gelb:" + score);
				break;
			case ORANGE:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 25));
				System.out.println("Score nach Organge:" + score);
				break;
			case RED:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 50));
				System.out.println("Score nach Rot:" + score);
				break;
		}
	}

    public int getScoreNumber(){
        	return scoreNumber;
	}


	/*public IntegerProperty scoreProperty() {
    	if(score == null) {
    		score = new SimpleIntegerProperty();
		}
		return score;
	}
*/


	/*public int getScore(){
		return score.get();
	}*/
	//TODO: Überprüfen ob, mann diese Methode noch braucht. Sonst löschen.

    /*stopcounting(){
        endscore = score.get();
        System.out.println("EndScore:" + endscore);
        return endscore;
    }*/



}


