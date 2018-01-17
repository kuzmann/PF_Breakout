package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.gamelogic.Life;

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
            Life.loseLife();
            breakout.getGuiNodes().getLifeInfo().setText("Lifes nach dem Tod: " + Life.getLife());
            if(Life.getIsGameOver()) {
                gameOver();
            }
        }
    }

    // TODO screen design makeover
    /** View, if player loose whole lifes*/
    void gameOver(){
        Life.setLife(3);
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

        breakout.createBrickGrid();
        breakout.createSpriteManager();
    }
}
