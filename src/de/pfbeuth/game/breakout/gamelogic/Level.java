package de.pfbeuth.game.breakout.gamelogic;

import de.pfbeuth.game.breakout.gameEngine.Breakout;

public class Level {

    public static int level = 1;
    private static Breakout breakout;

    public static void riseLevel(){
        level++;
        System.out.println("Level gewonnen! Nächstes Level ist: " + level);
    }


    public static int getLevel() {
        return level;
    }

}
