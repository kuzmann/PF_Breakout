package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;

public class Life {
    private int life;
    private boolean gameOver;
    private Breakout breakout;
    /** ------ CONSTRUCTOR ------ */
    public Life (Breakout breakout){
        this.breakout = breakout;
        life = 1;
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
    public boolean getIsGameOver(){
        return gameOver;
    }
    /** ------ SETTER ------ */
    public void setGameOver(Boolean gameOver){
        this.gameOver = gameOver;
    }
    public void setLife(int life){
        this.life = life;
    }
}

