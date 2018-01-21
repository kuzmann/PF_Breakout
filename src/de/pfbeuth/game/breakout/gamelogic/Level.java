package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;
import javafx.beans.property.SimpleStringProperty;

public class Level {

    private int level;
    private final double LEVEL_SPEED_MULTIPLIER = 0.4;
    private Breakout breakout;
    private SimpleStringProperty levelString;

    public Level (Breakout breakout) {
        this.breakout = breakout;
        levelString = new SimpleStringProperty();
        level = 1;
    }

    public void raiseLevel(){
        level++;
        breakout.getBall().setVelocityX(breakout.getBall().getVelocityX()+ LEVEL_SPEED_MULTIPLIER);
        breakout.getBall().setVelocityY(breakout.getBall().getVelocityY()+ LEVEL_SPEED_MULTIPLIER);
    }

    /* -------- GETTER -------- */
    public int getLevelNumber() {
        return level;
    }

    public SimpleStringProperty levelStringProperty() {
        return levelString;
    }
}