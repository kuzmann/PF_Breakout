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

    public  void loseLife(){
        if(life > 0){
            life--;
            gameOver = false;
        }
        if(life <= 0)
        {
            gameOver = true;
        }
    }

    public boolean getIsGameOver(){
        return gameOver;
    }

    public int getActualLife() {
        return life;
    }

    public void setLife(int life){
        this.life = life;
    }
}

