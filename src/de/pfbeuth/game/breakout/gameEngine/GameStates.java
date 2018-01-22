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
		breakout.getGameTimer().start();
		breakout.getGuiNodes().startButtonVisibliy(false);
		breakout.getGuiNodes().getStartButton().setCancelButton(true);
		breakout.getGuiNodes().playButtonVisibliy(false);
		breakout.getGuiNodes().highscoreButtonVisibliy(false);
		breakout.getGuiNodes().helpButtonVisibliy(false);
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
		breakout.getGuiNodes().startButtonVisibliy(true);
		breakout.getGuiNodes().getStartButton().setCancelButton(false);
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getPauseGameText());
		breakout.getGuiNodes().getStartButton().setTextAlignment(TextAlignment.CENTER);

		breakout.getGuiNodes().playButtonVisibliy(true);
		breakout.getGuiNodes().highscoreButtonVisibliy(true);
		breakout.getGuiNodes().helpButtonVisibliy(true);

		breakout.getGuiNodes().hideGameInfos();
		gameIsPaused = true;
	}

	/** View, if player loose whole lives*/
	private void gameOver(){
		breakout.getGameTimer().stop();
		breakout.getGuiNodes().startButtonVisibliy(false);
		breakout.getGuiNodes().getStartButton().setCancelButton(false);
		breakout.getGuiNodes().getStartButtonContainer().setDisable(true);
		breakout.getGuiNodes().getStartButtonContainer().toBack();

		breakout.getGuiNodes().getMenueOverlay().setImage(breakout.getGuiNodes().getGameOverImage());
		breakout.getGuiNodes().getMenueOverlay().setVisible(true);

		breakout.getGuiNodes().getPlayerInputContainer().setVisible(true);
		breakout.getGuiNodes().getPlayerInputContainer().setDisable(false);

		breakout.getGuiNodes().confirmButtonVisibliy(true);
		breakout.getGuiNodes().getGameOverInfo().setText(breakout.getGuiNodes().getScoreInfo().getText());

	}
	/** View, if player win a level*/
	void levelFinished(){
		breakout.getGameTimer().stop();
		breakout.getPaddle().resetState();
		breakout.getBall().resetState();
		breakout.getLevel().raiseLevel();

		breakout.getGuiNodes().startButtonVisibliy(true);
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getNEXT_LEVEL_TEXT());
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

            breakout.getGuiNodes().startButtonVisibliy(true);
            breakout.getGuiNodes().getStartButton().setCancelButton(false);
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
