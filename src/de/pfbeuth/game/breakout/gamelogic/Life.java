package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;

public class Life {
    private int life;
    private boolean gameOver;
    private Breakout breakout;
    /** ------ CONSTRUCTOR ------ */
    public Life (Breakout breakout){
        this.breakout = breakout;
        life = 3;
    }
    /** decrements 'int life' when Ball hits bottom scene boundary */
    public void loseLife(){
        if(life > 0){
            life--;
            gameOver = false;
        }
        if(life <= 0)
        {
            gameOver = true;
        }
    }
    /** ------ GETTER ------ */
    public int getActualLife() {
        return life;
    }
    public boolean isGameOver(){
        return gameOver;
    }
    /** ------ SETTER ------ */
    public void setLife(int life){
        this.life = life;
    }
}

