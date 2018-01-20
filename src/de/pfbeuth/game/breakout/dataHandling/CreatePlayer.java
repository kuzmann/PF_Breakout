package de.pfbeuth.game.breakout.dataHandling;


public class CreatePlayer {

    private FinalScore endscore = new FinalScore();
    private int score;
    private String username;

/*    public static int playerID;
*/


    public CreatePlayer(String username) {

        this.username = username;
        this.score = endscore.getScore();
    }

    //TODO: diese leere Methode löschen? -> Jasper fragen
    /*public CreatePlayer(){
    }*/

    public String getUsername() { return username; }
    //LÖSCHEN??? -> public void setUsername(String username) { this.username = username; }
    public int getScore() { return score; }



}

