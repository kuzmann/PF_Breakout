package de.pfbeuth.game.breakout.gameEngine;

/**
 * This class defines the different views for the states which occur during
 * the gameplay.
 */
public class GameStates {
    private Breakout breakout;
	private boolean gamePaused = true;
	/** ------ CONSTRUCTOR ------ */
	GameStates(Breakout breakout) {
        this.breakout = breakout;
    }

	/** Triggers events to create View: Start new Game */
	public void runGameEvents() {
		breakout.getGameTimer().start();
		breakout.getGuiNodes().startButtonVisibliy(false, 100d);
		breakout.getGuiNodes().playButtonVisibliy(false);
		breakout.getGuiNodes().highscoreButtonVisibliy(false);
		breakout.getGuiNodes().helpButtonVisibliy(false);
		breakout.getGuiNodes().getGameOverInfo().setVisible(false);
		breakout.getGuiNodes().showGameInfos();
		gamePaused = false;
	}
	/** Triggers events to create View: Pause new Game */
	public void pauseGameEvents(){
		breakout.getGameTimer().stop();
		breakout.getGuiNodes().startButtonVisibliy(true, 100d);
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getPauseGameText());
		breakout.getGuiNodes().playButtonVisibliy(true);
		breakout.getGuiNodes().highscoreButtonVisibliy(true);
		breakout.getGuiNodes().helpButtonVisibliy(true);
		breakout.getGuiNodes().hideGameInfos();
		gamePaused = true;
	}
	/** Triggers events to create View: if player lost all lives*/
	private void gameOver(){
		breakout.getGameTimer().stop();
		gamePaused = true;
		breakout.getGuiNodes().getStartButtonContainer().toBack();
		breakout.getGuiNodes().startButtonVisibliy(false, 100d);
		breakout.getGuiNodes().getMenueOverlay().setImage(breakout.getGuiNodes().getGameOverImage());
		breakout.getGuiNodes().getMenueOverlay().setVisible(true);
		breakout.getGuiNodes().hideGameInfos();
		breakout.getGuiNodes().getPlayerInputContainer().setVisible(true);
		breakout.getGuiNodes().getPlayerInputContainer().setDisable(false);
		breakout.getGuiNodes().confirmButtonVisibliy(true);
		breakout.getGuiNodes().getGameOverInfo().setVisible(true);
		breakout.getGuiNodes().getGameOverInfo().setText(breakout.getGuiNodes().getScoreInfo().getText());
		breakout.getBall().resetVelocity();
		breakout.getBrickGrid().deleteAllBrickFromScene();
		breakout.getBall().setBallToBack();
		breakout.getPaddle().setPaddleToBack();
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getStartText());
	}
	/** Triggers events to create View: if player loose a life */
	void ballDied(){
		if(breakout.getBall().getBallIsDead() && !(breakout.getLife().isGameOver())){
			breakout.getGameTimer().stop();
			breakout.getGuiNodes().getGameOverInfo().setVisible(false);
			breakout.getPaddle().resetState();
			breakout.getBall().resetState();
			breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getStartText());
			breakout.getGuiNodes().startButtonVisibliy(true, 100d);
			breakout.getLife().loseLife();
			breakout.getGuiNodes().getLifeInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLife().getActualLife());
			gamePaused = true;
		}
		if(breakout.getLife().isGameOver()) {
			gameOver();
		}
	}
	void startNextLevelEvents(){
		breakout.getGuiNodes().getLevelInfo().setText(breakout.getGuiNodes().getLEVEL_INFO_TEXT() + breakout.getLevel().getLevelNumber());
		if (breakout.getLife().isGameOver()){
			resetGameInfos();
			breakout.getLife().setGameOver(false);
		}
		createNextLevel();
	}
	/** Triggers events to create View: if player accomplished a level */
	void levelFinishedEvents() {
		breakout.getGameTimer().stop();
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getNEXT_LEVEL_TEXT());
		breakout.getGuiNodes().startButtonVisibliy(true, 175d);
		breakout.getLevel().raiseLevelNumber();
		startNextLevelEvents();
		if(breakout.getLevel().getLevelNumber() > 10){
			gameOver();
		}
	}
	private void createNextLevel(){
		breakout.getPaddle().resetState();
		breakout.getBall().resetState();
		/* ------ Reset SpriteManager Lists ------ */
		breakout.getSpriteManager().resetCurrentObjects();
		breakout.getSpriteManager().resetRemovedObjects();
		breakout.getBrickGrid().getBrickGridList().clear();
		breakout.getSpriteManager().addCurrentObjects(breakout.getPaddle());
		breakout.getSpriteManager().addCurrentObjects(breakout.getBall());
		/* ------ choose next level design ------ */
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
			case (6):
				breakout.getBrickGrid().createLevelThreeGrid();
				break;
			case (7):
				breakout.getBrickGrid().createLevelThreeGrid();
				break;
			case (8):
				breakout.getBrickGrid().createLevelThreeGrid();
				break;
			case (9):
				breakout.getBrickGrid().createLevelThreeGrid();
				break;
			case (10):
				breakout.getBrickGrid().createLevelThreeGrid();
				break;
			default:
				breakout.getBrickGrid().createLevelOneGrid();
		}
		breakout.getBall().getSpriteImage().toFront();
	}
	private void resetGameInfos() {
		breakout.getScoreCounter().resetScoreNumber();
		breakout.getScoreCounter().setScoreProperty();
		breakout.getLife().setLife(breakout.getLife().getLIFE_INIT());
		breakout.getLevel().setLevelNumber(breakout.getLevel().getLevelInit());
		breakout.getGuiNodes().getLifeInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLife().getActualLife());
		breakout.getGuiNodes().getLevelInfo().setText(breakout.getGuiNodes().getLEVEL_INFO_TEXT() + breakout.getLevel().getLevelNumber());
	}
	/* ------ GETTER ------ */
	/** @return true if game is in pause mode */
	public boolean isGamePaused() {
		return gamePaused;
	}
}
