package de.pfbeuth.game.breakout.gamelogic;

public class Life {

    private static int life = 3;
    private static boolean gameOver;

    public static void loseLife(){
        if(life > 0){
            life--;
            gameOver = false;
        }
        if(life <= 0)
        {
            gameOver = true;
        }
    }

    public static boolean getIsGameOver(){
        return gameOver;
    }

    public static int getLife() {
        return life;
    }

    public static void setLife(int life) {
        Life.life = life;
    }
}

