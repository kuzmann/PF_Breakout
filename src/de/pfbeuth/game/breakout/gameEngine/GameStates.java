package de.pfbeuth.game.breakout.gameEngine;

import javafx.scene.text.TextAlignment;

public class GameStates {
    private Breakout breakout;
	private boolean gameIsPaused;
	/** ------ CONSTRUCTOR ------ */
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
		breakout.getGuiNodes().getGameOverInfo().setVisible(false);
		/* ------ Level accomplished events ------ */
		if(breakout.getBall().isLevelWon()) {
			startNextLevelEvents();
			breakout.getLife().setGameOver(false);
		}
		if(breakout.getLife().getIsGameOver()){
			startNextLevelEvents();
			breakout.getLife().setGameOver(false);
		}
		breakout.getGuiNodes().showGameInfos();
		gameIsPaused = false;
	}
	/** View: Pause new Game */
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
	/** View, if player lost all lives*/
	//TODO game loop nach GameOver checken
	private void gameOver(){
		breakout.getGameTimer().stop();
		breakout.getGuiNodes().getStartButton().setPrefWidth(100);
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getStartText());
		breakout.getGuiNodes().startButtonVisibliy(false);
		breakout.getGuiNodes().getStartButton().setCancelButton(false);
		breakout.getGuiNodes().getStartButtonContainer().setDisable(true);
		breakout.getGuiNodes().getStartButtonContainer().toBack();

		breakout.getGuiNodes().getMenueOverlay().setImage(breakout.getGuiNodes().getGameOverImage());
		breakout.getGuiNodes().getMenueOverlay().setVisible(true);
		breakout.getGuiNodes().getPlayerInputContainer().setVisible(true);
		breakout.getGuiNodes().getPlayerInputContainer().setDisable(false);
		breakout.getGuiNodes().confirmButtonVisibliy(true);
		breakout.getGuiNodes().getGameOverInfo().setVisible(true);
		//Ausagbe des Scores
		breakout.getGuiNodes().getGameOverInfo().setText(breakout.getGuiNodes().getScoreInfo().getText());

		breakout.getLife().setLife(3);
		breakout.getGuiNodes().getLifeInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLife().getActualLife());
		breakout.getLevel().setLevelNumber(1);
		breakout.getGuiNodes().getLevelInfo().setText(breakout.getGuiNodes().getLEVEL_INFO_TEXT() + breakout.getLevel().getLevelNumber());
		breakout.getScoreCounter().resetScoreNumber();
		breakout.getScoreCounter().resetScoreProperty();

		breakout.getBrickGrid().update();
	}
	public void resetGame(){
		breakout.getPaddle().resetState();
		breakout.getBall().resetState();

		breakout.getSpriteManager().resetCurrentObjects();
		breakout.getSpriteManager().resetCollideCheckList();
		breakout.getSpriteManager().resetRemovedObjects();

		breakout.getSpriteManager().addCurrentObjects(breakout.getPaddle());
		breakout.getSpriteManager().addCurrentObjects(breakout.getBall());

		breakout.getBrickGrid().getBrickGridList().clear();

		breakout.getBrickGrid().createLevelOneGrid();

		breakout.getBrickGrid().updateAdd();
	}
	private void startNextLevelEvents(){
		breakout.getLevel().raiseLevelNumber();
		breakout.getGuiNodes().getLevelInfo().setText(breakout.getGuiNodes().getLEVEL_INFO_TEXT() + breakout.getLevel().getLevelNumber());
		createNextLevel();
	}
	/** View, if player accomplished a level*/
	void levelFinishedEvents() {
		breakout.getGameTimer().stop();
		breakout.getGuiNodes().getStartButton().setPrefWidth(175);
		breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getNEXT_LEVEL_TEXT());
		breakout.getGuiNodes().startButtonVisibliy(true);
		breakout.getGuiNodes().getStartButton().setCancelButton(false);
	}
	private void createNextLevel(){
		breakout.getPaddle().resetState();
		breakout.getBall().resetState();

		breakout.getSpriteManager().resetCurrentObjects();
		breakout.getSpriteManager().resetCollideCheckList();
		breakout.getSpriteManager().resetRemovedObjects();

		breakout.getSpriteManager().addCurrentObjects(breakout.getPaddle());
		breakout.getSpriteManager().addCurrentObjects(breakout.getBall());

		breakout.getBrickGrid().getBrickGridList().clear();

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
	}
	/** View, if player loose a life */
	void ballDied(){
        if(breakout.getBall().getBallIsDead()){
            breakout.getGameTimer().stop();
            breakout.getGuiNodes().getGameOverInfo().setVisible(false);

            breakout.getPaddle().resetState();
            breakout.getBall().resetState();

            breakout.getGuiNodes().getStartButton().setPrefWidth(100);
			breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getStartText());
			breakout.getGuiNodes().startButtonVisibliy(true);
            breakout.getGuiNodes().getStartButton().setCancelButton(false);

            breakout.getLife().loseLife();
            breakout.getGuiNodes().getLifeInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLife().getActualLife());
            if(breakout.getLife().getIsGameOver()) {
                gameOver();
            }
        }
    }
	/** ------ GETTER ------ */
	public boolean isGameIsPaused() {
		return gameIsPaused;
	}




}
