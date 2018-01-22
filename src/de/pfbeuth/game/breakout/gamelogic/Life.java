package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;

public class Life {

    private int life;
    private boolean gameOver;
    private Breakout breakout;

    /* ------ CONSTRUCTOR ------ */
    public Life (Breakout breakout){
        this.breakout = breakout;
        life = 3;
    }

    public boolean loseLife(){
        if(life > 0){
            life--;
            gameOver = false;
        }
        if(life <= 0)
        {
            gameOver = true;
        }
        return gameOver;
    }

    public boolean getIsGameOver(){
        return gameOver;
    }
    public void setGameOver(Boolean gameOver){
        this.gameOver = gameOver;
    }

    public int getActualLife() {
        return life;
    }

    public void setLife(int life){
        this.life = life;
    }
}

