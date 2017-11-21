package de.pfbeuth.game.breakout;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import static de.pfbeuth.game.breakout.Breakout.paddle;

//JavaFX Pulse System runs at 60 FPS
public class GamePlayTimer extends AnimationTimer {
    Pos location;

    GamePlayTimer(){
        super();
    }
    @Override
    public void handle(long now) {
        paddle.update();
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

