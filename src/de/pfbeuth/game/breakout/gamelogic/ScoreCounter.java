package de.pfbeuth.game.breakout.gamelogic;

import java.awt.*;

public class ScoreCounter {

    public static int score = 0;
    public static int newscore;
    public static int endscore;

    /*public static int counter() {

        score++;
        return score;
    }*/

    /** Increases the score by an amount according to the colour of the brick which has been hit. */
    public static int counter() {

        score +=10;
        System.out.println("Score:" + score);

        //TODO: Herausfinden welche Farbe zerstört worden ist
        /*Color color = brick.getColor();
        if (color == Color.GREEN) {
            score += 5;
        }
        else if (color == Color.YELLOW){
            score += 10;
        }
        else if (color == Color.ORANGE){
            score += 25;
        }
        else if (color == Color.RED){
            score += 50;
        }*/
        return score;
    }

    public static int stopcounting(){
        endscore = score;
        System.out.println("EndScore:" + endscore);
        return endscore;
    }


}

