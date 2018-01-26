package de.pfbeuth.game.breakout.gamelogic;
import de.pfbeuth.game.breakout.gameEngine.*;
import javafx.beans.property.*;

/**
 * Defines the Score counter and raise the score accordingly to the bricks color.
 */

public class ScoreCounter {
    private Breakout breakout;
    private SimpleStringProperty score;
    private int scoreNumber;
    private final int SCORE_INITIAL = 0;

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
	/**
	 * Increases the score by an amount according to the
	 * colour of the brick which has been hit.
	 */
	public void counter(BrickColor brickcolor) {
    	switch (brickcolor) {
			case GREEN:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 5));
				break;
			case YELLOW:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 10));
				break;
			case ORANGE:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 25));
				break;
			case RED:
				breakout.getGuiNodes().updateScoreInfo();
				score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber += 50));
				break;
		}
	}

	/* ------ GETTER ------ */
	/** @return actual score value */
	public int getScoreNumber(){
		return scoreNumber;
	}
	/** @return actual score as StringProperty */
	public SimpleStringProperty scoreProperty() {
		return score;
	}
	/* ------ SETTER ------ */
	/** set the ScoreProperty String to the actual score value */
	public void setScoreProperty(){
		score.set(breakout.getGuiNodes().getSCORE_INFO_TEXT() + (scoreNumber));
	}
	/** reset score to the initial value */
	public void resetScoreNumber(){
		scoreNumber = SCORE_INITIAL;
	}
}