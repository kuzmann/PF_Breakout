package de.pfbeuth.game.breakout.gameEngine;

class GameOver {

    private Breakout breakout;

    GameOver(Breakout breakout) {
        this.breakout = breakout;
    }

    //* View, if player loose a life*/
    void ballDied(){
        if(breakout.getBall().getBallIsDead()){
            breakout.getGameTimer().stop();
            breakout.getGuiNodes().getGameOverInfo().setVisible(true);
            breakout.getPaddle().resetState();
            breakout.getBall().resetState();
            breakout.getGuiNodes().getStartButton().setVisible(true);
            breakout.getGuiNodes().getStartButton().setDisable(false);
            breakout.getGuiNodes().getStartButton().setCancelButton(false);
            breakout.getGuiNodes().getStartButton().setVisible(true);
            breakout.getLife().loseLife();
            breakout.getGuiNodes().getLifeInfo().setText("LIVES: " + breakout.getLife().getActualLife());
            if(breakout.getLife().getIsGameOver()) {
                gameOver();
            }
        }
    }
    /** View, if player win a level*/
    void endLevel(){
        if(breakout.getBall().getLevelWon()){
            breakout.getGameTimer().stop();
            breakout.getGuiNodes().getGameOverInfo().setVisible(true);
            breakout.getPaddle().resetState();
            breakout.getBall().resetState();
            breakout.getGuiNodes().getStartButton().setVisible(true);
            breakout.getGuiNodes().getStartButton().setDisable(false);
            breakout.getGuiNodes().getStartButton().setCancelButton(false);
            breakout.getGuiNodes().getStartButton().setVisible(true);
            breakout.getLevel().riseLevel();
            breakout.getGuiNodes().getLevelInfo().setText("LEVEL: " + breakout.getLevel().getLevelNumber());
        }
    }



    // TODO screen design makeover
    /** View, if player loose whole lives*/
    private void gameOver(){
        breakout.getLife().setLife(3);
        System.out.println("GAME OVER");
        breakout.getGameTimer().stop();
        //TODO: Hier muss der User seinen Namen eingeben
        //breakout.getGuiNodes().getStartButton().setText(GUI.playAgainText);
        //breakout.getGuiNodes().getStartButton().setVisible(true);
        breakout.getGuiNodes().getGameOverInfo().setVisible(true);
        breakout.getGuiNodes().getStartButton().setDisable(false);
        breakout.getGuiNodes().getStartButton().setCancelButton(false);
        breakout.getGuiNodes().getPlayerInputContainer().setVisible(true);

        /*breakout.getSpriteManager().resetCurrentObjects();
        breakout.getSpriteManager().resetRemovedObjects();
        breakout.getSpriteManager().resetCollideCheckList();*/

        breakout.createInitBrickGrid();
        breakout.createSpriteManager();
    }
}
