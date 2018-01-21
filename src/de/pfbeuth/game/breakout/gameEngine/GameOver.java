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
            breakout.getGuiNodes().getLifeInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLife().getActualLife());
            if(breakout.getLife().getIsGameOver()) {
                gameOver();
            }
        }
    }
    /** View, if player win a level*/
    void endLevel(){
            breakout.getGameTimer().stop();
            breakout.getGuiNodes().getGameOverInfo().setVisible(true);
            breakout.getPaddle().resetState();
            breakout.getBall().resetState();
            breakout.getGuiNodes().getStartButton().setVisible(true);
            breakout.getGuiNodes().getStartButton().setDisable(false);
            breakout.getGuiNodes().getStartButton().setCancelButton(false);
            breakout.getGuiNodes().getStartButton().setVisible(true);
            breakout.getLevel().raiseLevel();
            //breakout.getGuiNodes().updateLivesInfo();
            //breakout.getGuiNodes().getLevelInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLevel().getLevelNumber());
    }

    // TODO screen design makeover
    /** View, if player loose whole lives*/
    private void gameOver(){
        breakout.getLife().setLife(3);
        breakout.getGameTimer().stop();

        //TODO: Hier muss der User seinen Namen eingeben
        //breakout.getGuiNodes().getStartButton().setText(GUI.playAgainText);
        //breakout.getGuiNodes().getStartButton().setVisible(true);

        breakout.getGuiNodes().getGameOverInfo().setVisible(true);
        breakout.getGuiNodes().getStartButton().setDisable(false);
        breakout.getGuiNodes().getStartButton().setCancelButton(false);
        breakout.getGuiNodes().getPlayerInputContainer().setVisible(true);


    }

    private void resetGame(){
        breakout.getSpriteManager().resetCurrentObjects();
        breakout.getSpriteManager().resetCollideCheckList();
        breakout.getSpriteManager().resetRemovedObjects();

        breakout.getBrickGridList().clear();
        switch(breakout.getLevel().getLevelNumber()){
            case (1):
                breakout.getBrickGrid().createLevelOneGrid();
                break;
            case (2):
                breakout.getBrickGrid().createLevelTwoGrid();
                break;
            case (3):
                breakout.getBrickGrid().createLevelThreeGrid();
                break;
            case (4):
                breakout.getBrickGrid().createLevelFourGrid();
                break;
            case (5):
                breakout.getBrickGrid().createLevelFiveGrid();
                break;
            default:
                breakout.getBrickGrid().createLevelOneGrid();
        }

        breakout.getSpriteManager().addCurrentObjects(breakout.getPaddle());
        breakout.getSpriteManager().addCurrentObjects(breakout.getBall());
        for (Brick aBrickGrid : breakout.getBrickGridList()) {
            Brick brick = aBrickGrid;
            breakout.getSpriteManager().addCurrentObjects(brick);
        }

    }
}
