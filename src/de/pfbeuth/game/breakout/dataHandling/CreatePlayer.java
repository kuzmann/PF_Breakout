package de.pfbeuth.game.breakout.dataHandling;


import de.pfbeuth.game.breakout.gameEngine.Breakout;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;

public class CreatePlayer {

    private FinalScore endscore = new FinalScore();
    private int score;
    private String username;

    private Breakout breakout;


    public CreatePlayer(String username) {

        this.username = username;
        //Score kommt aus der Klasse FinalScore. Der Score muss aber aus Gamelogic kommen
        this.score = endscore.getScore();

        // Es funkioniert nicht, weil es den letzten Wert nicht auslesen kann. Score = 0
        //this.score = breakout.getScoreCounter().getScoreNumber();
    }

    //TODO: diese leere Methode löschen? -> Jasper fragen
    /*public CreatePlayer(){
    }*/

    public String getUsername() { return username; }
    //LÖSCHEN??? -> public void setUsername(String username) { this.username = username; }
    public int getScore() { return score; }



}

