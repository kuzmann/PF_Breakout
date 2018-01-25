package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;
import javafx.beans.property.SimpleStringProperty;

/**
 * this class defines the level logic.
 */
public class Level {
    private int level;
    private final double LEVEL_SPEED_MULTIPLIER = 0.8;
    private Breakout breakout;
    private SimpleStringProperty levelString;
    private boolean levelAccomplished = false;
    /** ------ CONSTRUCTOR ------ */
    public Level (Breakout breakout) {
        this.breakout = breakout;
        levelString = new SimpleStringProperty();
        level = 1;
    }
    /** increases Level when all bricks are destroyed */
    public void raiseLevelNumber(){
        level++;
        //raise the speed of the ball
        breakout.getBall().setVelocityX(breakout.getBall().getVelocityX()+ LEVEL_SPEED_MULTIPLIER);
        breakout.getBall().setVelocityY(breakout.getBall().getVelocityY()+ LEVEL_SPEED_MULTIPLIER);
        //TODO implement Switch Statement for Level choice - look at BrickGrid class
    }

    //TODO: Anna testen, ob diese Methode wirklich gebraucht wird
    public SimpleStringProperty levelStringProperty() {
        return levelString;
    }

    /**
     * this method is called from the class ball
     * @return levelAccomplished if all brick are destroyed and a level was successfully completed
     * */
    public boolean isLevelAccomplished(){
        return levelAccomplished;

    }
    /** -------- GETTER -------- */
    public int getLevelNumber() {
        return level;
    }
    /** ------ SETTER ------ */
    public void setLevelNumber(int level){
        this.level = level;
    }
    public void setLevelAccomplished(boolean levelAccomplished){
        this.levelAccomplished = levelAccomplished;
    }
}
