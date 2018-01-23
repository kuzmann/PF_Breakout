package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;
import javafx.beans.property.SimpleStringProperty;

public class Level {
    private int level;
    private final double LEVEL_SPEED_MULTIPLIER = 0.4;
    private Breakout breakout;
    private SimpleStringProperty levelString;
    /** ------ CONSTRUCTOR ------ */
    public Level (Breakout breakout) {
        this.breakout = breakout;
        levelString = new SimpleStringProperty();
        level = 1;
    }
    /** increases Level when all bricks are destroyed */
    public void raiseLevelNumber(){
        level++;
        breakout.getBall().setVelocityX(breakout.getBall().getVelocityX()+ LEVEL_SPEED_MULTIPLIER);
        breakout.getBall().setVelocityY(breakout.getBall().getVelocityY()+ LEVEL_SPEED_MULTIPLIER);
        //TODO implement Switch Statement for Level choice - look at BrickGrid class
    }
    /** -------- GETTER -------- */
    public int getLevelNumber() {
        return level;
    }
    public SimpleStringProperty levelStringProperty() {
        return levelString;
    }
    /** ------ SETTER ------ */
    public void setLevelNumber(int level){
        this.level = level;
    }
}