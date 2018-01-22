package de.pfbeuth.game.breakout.gameEngine;

import javafx.scene.text.TextAlignment;

public class GameStates {
    private Breakout breakout;
	private boolean gameIsPaused;

	GameStates(Breakout breakout) {
        this.breakout = breakout;
    }

	/** ------ View: Start new Game ------ */
	public void runGameEvents() {
		//TODO countdown before game starts
        /*
         for (int i = 3; i > 0; i--) {
            startButton.setText("" + i);
            System.out.println("" + startButton.getText());
            try {
                Thread.sleep(500);
            }
            catch(InterruptedException e1) {}
        }*/
		breakout.getGameTimer().start();
		breakout.getGuiNodes().getStartButton().setVisible(false);
		breakout.getGuiNodes().getStartButton().setDisable(true);
		breakout.getGuiNodes().getStartButton().setCancelButton(true);
		breakout.getGuiNodes().getPlayButton().setVisible(false);
		breakout.getGuiNodes().getPlayButton().setDisable(true);
		breakout.getGuiNodes().getHighscoreButton().setVisible(false);
		breakout.getGuiNodes().getHighscoreButton().setDisable(true);
		breakout.getGuiNodes().getHelpButton().setVisible(false);
		breakout.getGuiNodes().getHelpButton().setDisable(true);
		//TODO: Ausgabe des Levels aktuell halten. Wird erst wieder angezeigt, wenn
		if(breakout.getBall().getLevelWon()) {
			breakout.getGuiNodes().getLevelInfo().setText(breakout.getGuiNodes().getNEXT_LEVEL_TEXT() + breakout.getLevel().getLevelNumber());
		}
		breakout.getGuiNodes().showGameInfos();
		gameIsPaused = false;
	}
	/* ------ View: Pause new Game ------ */
	public void pauseGameEvents(){
		breakout.getGameTimer().stop();
		breakout.getGuiNodes().getStartButton().setVisible(true);
		breakout.getGuiNodes().getStartButton().setDisable(false);
		breakout.getGuiNodes().getStartButton().setCancelButton(false);
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getPauseGameText());
		breakout.getGuiNodes().getStartButton().setTextAlignment(TextAlignment.CENTER);
		breakout.getGuiNodes().getPlayButton().setVisible(true);
		breakout.getGuiNodes().getPlayButton().setDisable(false);
		breakout.getGuiNodes().getHighscoreButton().setVisible(true);
		breakout.getGuiNodes().getHighscoreButton().setDisable(false);
		breakout.getGuiNodes().getHelpButton().setVisible(true);
		breakout.getGuiNodes().getHelpButton().setDisable(false);
		breakout.getGuiNodes().hideGameInfos();
		gameIsPaused = true;
	}

	/** View, if player loose whole lives*/
	private void gameOver(){
		breakout.getGameTimer().stop();
		breakout.getGuiNodes().getStartButton().setVisible(false);
		breakout.getGuiNodes().getStartButton().setDisable(true);
		breakout.getGuiNodes().getStartButton().setCancelButton(false);
		breakout.getGuiNodes().getStartButtonContainer().setDisable(true);
		breakout.getGuiNodes().getStartButtonContainer().toBack();

		breakout.getGuiNodes().getMenueOverlay().setImage(breakout.getGuiNodes().getGameOverImage());
		breakout.getGuiNodes().getMenueOverlay().setVisible(true);

		breakout.getGuiNodes().getPlayerInputContainer().setVisible(true);
		breakout.getGuiNodes().getPlayerInputContainer().setDisable(false);
		breakout.getGuiNodes().getConfirmButton().setVisible(true);
		breakout.getGuiNodes().getConfirmButton().setDisable(false);
		breakout.getGuiNodes().getGameOverInfo().setText(breakout.getGuiNodes().getScoreInfo().getText());

	}
	/** View, if player win a level*/
	void levelFinished(){
		breakout.getGameTimer().stop();
		breakout.getPaddle().resetState();
		breakout.getBall().resetState();
		breakout.getLevel().raiseLevel();
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getNEXT_LEVEL_TEXT());
		breakout.getGuiNodes().getStartButton().setVisible(true);
		breakout.getGuiNodes().getStartButton().setDisable(false);
		breakout.getGuiNodes().getStartButton().setCancelButton(false);
		//breakout.getGuiNodes().updateLivesInfo();
		//breakout.getGuiNodes().getLevelInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLevel().getLevelNumber());
	}

	/* View, if player loose a life */
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
	// TODO screen design makeover

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
	public boolean isGameIsPaused() {
		return gameIsPaused;
	}

}
