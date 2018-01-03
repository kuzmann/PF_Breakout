package de.pfbeuth.game.breakout.gameEngine;
import javafx.animation.AnimationTimer;
//GamePlayTimer manages the pulse and animation system
//runs at 60fps
public class GamePlayTimer extends AnimationTimer {

    private Breakout breakout; //creates context to Breakout-Class

    GamePlayTimer(Breakout ibreakout){
        breakout = ibreakout;
    }

    @Override
    //put in everything that should be updated in realtime
    public void handle(long now) {
        breakout.getPaddle().update();
        breakout.getBall().update();
    }
    @Override
    public void start() {
        super.start();
    }
    @Override
    public void stop() {
        super.stop();
    }
}

