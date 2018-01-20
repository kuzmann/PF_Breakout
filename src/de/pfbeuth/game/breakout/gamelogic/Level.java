package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;

public class Level {

    private int level = 1;
    private int levelSpeed = 5;
    private Breakout breakout;

    public Level (Breakout breakout) {
        this.breakout = breakout;
    }

    public  void riseLevel(){
        level++;
        breakout.getBall().setVelocityX(breakout.getBall().getVelocityX()+ levelSpeed);
        breakout.getBall().setVelocityY(breakout.getBall().getVelocityY()+ levelSpeed);
    }
    
    public  int getLevelNumber() {
        return level;
    }

}