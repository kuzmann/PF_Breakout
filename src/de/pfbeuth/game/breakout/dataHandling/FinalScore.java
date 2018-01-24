package de.pfbeuth.game.breakout.dataHandling;

//import de.pfbeuth.game.breakout.SpriteManager;

import java.util.Set;

//TODO: Kann man diese ganze Klasse löschen?
public class FinalScore {

    final static int numberBricks = 16;
    final static int pointsPerBrick = 10;
    private int score = 12;
   // private SpriteManager spriteScores = new SpriteManager();
    private Set objects;
    private int level;
    private int numberObjects;


    //public FinalScore() {
        //objects = spriteScores.getRemovedObjects();
       // numberObjects = objects.size();
        // level = spriteScores.getLevel(); //TODO method getLevel in SpriteManager erstellen
        // score = numberObjects * level;

        //score = calculateScore(level);
        /*switch (level){
            case 1:
                score = calculateScore(1);
                break;
            default:
                break;
        }
    }*/

    private int calculateScore(int level){
        int levelscore = (level-1)*numberBricks*pointsPerBrick;
        int destroyedBricks = numberObjects%level;
        int calculatedScore = levelscore+(destroyedBricks*pointsPerBrick);
        return calculatedScore;
    }

    public int getScore(){
        return score;
    }

}
