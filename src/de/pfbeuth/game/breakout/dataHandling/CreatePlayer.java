package de.pfbeuth.game.breakout.dataHandling;
import de.pfbeuth.game.breakout.gameEngine.Breakout;

/**
 * After ending a game, this class creates a new player element,
 * including player name and player score
 */

public class CreatePlayer {
    private int score;
    private String username;
    private Breakout breakout;

    /** create a new player-element into XML*/
    public CreatePlayer (Breakout breakout, String username){
        //get player name to save as username
        this.username = username;
        //get final score from game logic to save it
        this.score = breakout.getScoreCounter().getScoreNumber();
        this.breakout = breakout;
    }

    /* ------ GETTER ------ */
    /** @return username string */
    String getUsername() { return username; }
    /** @return endscore value */
    int getScore() { return score; }
}
