package de.pfbeuth.game.breakout.dataHandling;


import de.pfbeuth.game.breakout.gameEngine.Breakout;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;

/**
 * This class create new Elemnet player to XML, if new user end his game
 */
public class CreatePlayer {

    //private FinalScore endscore = new FinalScore();
    private int score;
    private String username;
    private Breakout breakout;

    /** create a new player-element into XML*/
    public CreatePlayer (Breakout breakout, String username){
        // get player name to save as username
        this.username = username;
        //get finale score from gamelogic to save it
        this.score = breakout.getScoreCounter().getScoreNumber();
        this.breakout = breakout;
    }

    /* ------ GETTER ------ */
    /** @return username string */
    public String getUsername() { return username; }
    /** @return endscore value   */
    public int getScore() { return score; }
}
