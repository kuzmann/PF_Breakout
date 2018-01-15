package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.gamelogic.Life;

class GameOver {

    private Breakout breakout;

    GameOver(Breakout breakout) {
        this.breakout = breakout;
    }

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
            breakout.getGuiNodes().getLifeInfo().setText("Lifes: " + Life.getLife());
            if(Life.getIsGameOver()) {
                gameOver();
            }
        }
    }

    // TODO screen design makeover
    void gameOver(){
        Life.setLife(3);
        breakout.getGameTimer().stop();

        breakout.getGuiNodes().getStartButton().setText(GUI.playAgainText);
        breakout.getGuiNodes().getStartButton().setVisible(true);
        breakout.getGuiNodes().getStartButton().setDisable(false);
        breakout.getGuiNodes().getStartButton().setCancelButton(false);

        /*breakout.getSpriteManager().resetCurrentObjects();
        breakout.getSpriteManager().resetRemovedObjects();
        breakout.getSpriteManager().resetCollideCheckList();*/

        breakout.createBrickGrid();
        breakout.createSpriteManager();
    }
}
