package de.pfbeuth.game.breakout.gameEngine;
import javafx.animation.AnimationTimer;
//GamePlayTimer manages the pulse and animation system
//runs at 60fps
public class GamePlayTimer extends AnimationTimer {
    private Boolean gameIsOn;
    private Breakout breakout; //creates context to Breakout-Class

    GamePlayTimer(Breakout ibreakout){
        breakout = ibreakout;
    }

    @Override
    //put in everything that should be updated in realtime
    public void handle(long now) {
        breakout.getPaddle().update();
        breakout.getBall().update();
        //if(breakout.getBall().isLevelWon()) breakout.getBrickGrid().update();
       /* if(breakout.getLife().getIsGameOver()) {
            breakout.getBrickGrid().update();
            breakout.getLife().setGameOver(false);
        }*/
    }
    @Override
    public void start() {
        super.start();
        gameIsOn = true;
    }
    @Override
    public void stop() {
        super.stop();
        gameIsOn = false;
    }

    public boolean gameIsOn(){
        return gameIsOn;
    }

}


