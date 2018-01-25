package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;

/**
 * this class defines the life logic.
 */
public class Life {
    private int life;
    private boolean gameOver;
    private Breakout breakout;
    /** ------ CONSTRUCTOR ------ */
    public Life (Breakout breakout){
        this.breakout = breakout;
        //The game start with 3 lifes
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

    /**
     * this method is called from the class GameStates
     * @return gameOver if all paddle does not hit the ball and the ball hits bottom scene boundary
     * */
    public boolean isGameOver(){
        return gameOver;
    }

    /** ------ GETTER ------ */
    public int getActualLife() {
        return life;
    }
    /** ------ SETTER ------ */
    public void setLife(int life){
        this.life = life;
    }
}

