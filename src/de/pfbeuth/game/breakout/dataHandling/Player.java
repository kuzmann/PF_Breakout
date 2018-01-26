package de.pfbeuth.game.breakout.dataHandling;

/**
 * This class assigns player name und score to current player.
 */

public class Player implements Comparable<Player>{
    private String playerScore;
    private String playerName;

    /** create a player Object to be able to make a comparison with the current player */
    Player(String playerName, String playerScore) {
        this.playerScore = playerScore;
        this.playerName = playerName;

    }

    /* ------ GETTER ------ */
    @Override
    public int compareTo(Player o) {
        return playerName.compareTo(o.playerName);
    }
    /** @return player score */
    public String getPlayerScore() {
        return playerScore;
    }
    /** @return player name */
    public String getPlayerName() {
        return playerName;
    }
}