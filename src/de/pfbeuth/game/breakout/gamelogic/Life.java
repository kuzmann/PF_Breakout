package de.pfbeuth.game.breakout.gamelogic;

/**
 * This class defines the logic when lives are lost and reports when the game is over.
 */

public class Life {
    private int life;
    private boolean gameOver;
    private final int LIFE_INIT = 3;

    /** ------ CONSTRUCTOR ------ */
    public Life (){
        life = LIFE_INIT;
    }

    /**
     * decrements <int life> when Ball hits bottom scene boundary and
     *  sets <gameOver> accordingly to the remaining lives
     */
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
     * @return true if the ball hits bottom scene boundary, else false
     */
    public boolean isGameOver(){
        return gameOver;
    }
    /* ------ GETTER ------ */
    /** @return remaining lives */
    public int getActualLife() {
        return life;
    }
    /** @return initial lives */
    public int getLIFE_INIT() {
        return LIFE_INIT;
    }
    /* ------ SETTER ------ */
    /** @param life: set actual lives */
    public void setLife(int life){
        this.life = life;
    }
}