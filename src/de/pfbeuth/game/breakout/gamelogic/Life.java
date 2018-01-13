package de.pfbeuth.game.breakout.gamelogic;


import de.pfbeuth.game.breakout.gameEngine.Breakout;

public class Life {

    public static int life = 3;

    public void start(){
        life = 3;
    }


    public void loseLife(){

        if(life > 0){
            life--;
        }
        else{

        }
    }
}

