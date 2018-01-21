package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.*;
import javafx.beans.property.*;

public class ScoreCounter {

    private Breakout breakout;
    private SimpleStringProperty score;
    private int scoreNumber;

    /** ------ CONSTRUCTOR ------*/
    public ScoreCounter (Breakout breakout){
    	this.breakout = breakout;
    	scoreNumber = 0;
    	score = new SimpleStringProperty();
	}

	/** Enums constants for the bricks color */
	public enum BrickColor {
		GREEN, YELLOW, ORANGE, RED
	}

	/** Increases the score by an amount according to the colour of the brick which has been hit. */
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

	public SimpleStringProperty scoreProperty() {
		return score;
	}

	/** ------ GETTER ------ */

    public int getScoreNumber(){
        	return scoreNumber;
	}
}


