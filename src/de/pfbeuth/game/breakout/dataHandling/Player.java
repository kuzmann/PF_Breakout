package de.pfbeuth.game.breakout.dataHandling;


import de.pfbeuth.game.breakout.gameEngine.Breakout;

class Player implements Comparable<Player>{
    String playerScore;
    String playerName;

    //private Breakout breakout;
    //public Player (Breakout breakout){this.breakout = breakout;}

    public Player(String playerName, String playerScore) {
        this.playerScore = playerScore;
        this.playerName = playerName;
    }


    @Override
    public int compareTo(Player o) {
        return playerName.compareTo(o.playerName);
    }

    public String getPlayerScore() {
        return playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }
}