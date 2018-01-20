package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Observer {

    private IntegerProperty playerScore  = new SimpleIntegerProperty();

    public IntegerProperty playerScoreProperty(){
        return playerScore;
    }



    /* ------ GETTER & SETTER ------ */
    public Integer getPlayerScore(){
        return playerScore.get();
    }

    public void setPlayerScore(Integer score){
        playerScore.set(score);
    }
}
