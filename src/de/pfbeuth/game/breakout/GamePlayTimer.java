package de.pfbeuth.game.breakout;
import javafx.animation.AnimationTimer;
//JavaFX Pulse System runs at 60 FPS
public class GamePlayTimer extends AnimationTimer {
    protected Breakout breakout;
    GamePlayTimer(Breakout ibreakout){
        breakout = ibreakout;
    }
    @Override
    public void handle(long now) {
        breakout.getPaddle().update();
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

