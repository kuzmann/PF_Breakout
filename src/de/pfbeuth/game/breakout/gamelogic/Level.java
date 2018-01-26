package de.pfbeuth.game.breakout.gamelogic;
import de.pfbeuth.game.breakout.gameEngine.Breakout;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class defines the level logic and provides logic when a level is accomplished.
 */

public class Level {
    private static final double LEVEL_SPEED_MULTIPLIER = 0.8;
    private final int LEVEL_INIT = 1;
    private Breakout breakout;
    private int level;
    private boolean levelAccomplished = false;

    /** ------ CONSTRUCTOR ------ */
    public Level (Breakout breakout) {
        this.breakout = breakout;
        level = LEVEL_INIT;
    }
    /** increases Level when all bricks are destroyed */
    public void raiseLevelNumber(){
        level++;
        /* raise the speed of the ball */
        breakout.getBall().setVelocityX(breakout.getBall().getVelocityX() + LEVEL_SPEED_MULTIPLIER);
        breakout.getBall().setVelocityY(breakout.getBall().getVelocityY() + LEVEL_SPEED_MULTIPLIER);
    }
    /* -------- GETTER -------- */
    /**
     * this method is called from the class Ball
     * @return true if all brick are destroyed and a level was successfully
     * completed, else return value is fals
     */
    public boolean isLevelAccomplished(){
        return levelAccomplished;
    }
    /** returns the actual level */
    public int getLevelNumber() {
        return level;
    }
    /** @return the initial level value to reset afte game is over */
    public int getLevelInit() {
        return LEVEL_INIT;
    }
    /* ------ SETTER ------ */
    /** @param level: sets the actual level to this number */
    public void setLevelNumber(int level){
        this.level = level;
    }
    /** @param levelAccomplished: is set when all bricks are destroyed */
    public void setLevelAccomplished(boolean levelAccomplished){
        this.levelAccomplished = levelAccomplished;
    }
}