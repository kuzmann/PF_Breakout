package de.pfbeuth.game.breakout.gameEngine;
import javafx.animation.AnimationTimer;

/**
 * GamePlayTimer manages the pulse and animation system.
 * It runs at 60fps.
 */

public class GamePlayTimer extends AnimationTimer {
    private Breakout breakout; //creates context to Breakout-Class

    GamePlayTimer(Breakout ibreakout){
        breakout = ibreakout;
    }

    /** Handles everything that must be updated in realtime */
    @Override
    public void handle(long now) {
        breakout.getPaddle().update();
        breakout.getBall().update();
    }
    /** start the AnimationTimer */
    @Override
    public void start() {
        super.start();
    }
    /** stops the AnimationTimer */
    @Override
    public void stop() {
        super.stop();
    }
}
