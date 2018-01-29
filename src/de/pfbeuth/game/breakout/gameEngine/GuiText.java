package de.pfbeuth.game.breakout.gameEngine;

/**
 * This class provides the String Constants for the GUI.
 */

class GuiText {
	private final static String START_BUTTON_TEXT = "START\n(hit enter)";
	private final static String PLAY_BUTTON_TEXT = "PLAY";
	private final static String PAUSE_GAME_TEXT = "PAUSED\n(hit enter)";
	private final static String HELP_BUTTON_TEXT = "HELP";
	private final static String HIGHSCORE_BUTTON_TEXT = "HIGHSCORES";
	private final static String CONFIRM_BUTTON_TEXT = "OK";
	private final static String LEVEL_INFO_TEXT = "LEVEL: ";
	private final static String LIVES_INFO_TEXT = "LIVES: ";
	private final static String SCORE_INFO_TEXT = "SCORE: ";
	private final static String NAME_LABEL_TEXT = "PLAYER NAME:";
	private final static String NEXT_LEVEL_TEXT = "CONGRATULATIONS!\nSTART NEXT LEVEL";
	private final static String INSTRUCTIONS_INFO_TEXT = "The goal of the game is to get the most points and to set a new highscore.\n" +
			"To get points, you have to destroy the bricks with the ball.\n\n" +
			"Once you press Start, the ball will bounce around the screen. To prevent that the ball leaves the playing field, you have to catch the ball with the paddle. If you succeed, the ball will bounce back off from the paddle.\n" +
			"To move the paddle to the left you have to press the left-arrow or A key, for moving right press the right-arrow or D.\n\n" +
			"Destroying bricks will give you different amount of points depending on the brick-color:\n\n" +
			"  - Green: 5 points\n" +
			"  - Yellow: 10 points\n" +
			"  - Orange: 25 points\n" +
			"  - Red: 50 points\n\n" +
			"You start with 3 lives. If you fail catching the ball with the paddle, you lose a life.\n\n" +
			"When you have destroyed all bricks, you will enter the next level. But watch out! The ball gets faster and faster with each level!\n" +
			"To pause the game hit the ESCAPE-key and to resume hit the ENTER-key.\n\n" +
			"Have fun playing! ";

	/* ------ GETTER ------ */
	/** @return START_BUTTON_TEXT object */
	static String getSTART_BUTTON_TEXT() {
		return START_BUTTON_TEXT;
	}
	/** @return PLAY_BUTTON_TEXT object */
	static String getPLAY_BUTTON_TEXT() {
		return PLAY_BUTTON_TEXT;
	}

	/** @return PAUSE_GAME_TEXT object */
	static String getPAUSE_GAME_TEXT() {
		return PAUSE_GAME_TEXT;
	}

	/** @return HELP_BUTTON_TEXT object */
	static String getHELP_BUTTON_TEXT() {
		return HELP_BUTTON_TEXT;
	}

	/** @return HIGHSCORE_BUTTON_TEXT object */
	static String getHIGHSCORE_BUTTON_TEXT() {
		return HIGHSCORE_BUTTON_TEXT;
	}

	/** @return CONFIRM_BUTTON_TEXT object */
	static String getCONFIRM_BUTTON_TEXT() {
		return CONFIRM_BUTTON_TEXT;
	}

	/** @return INSTRUCTIONS_INFO_TEXT object */
	static String getINSTRUCTIONS_INFO_TEXT() {
		return INSTRUCTIONS_INFO_TEXT;
	}

	/** @return LEVEL_INFO_TEXT object */
	static String getLEVEL_INFO_TEXT() {
		return LEVEL_INFO_TEXT;
	}

	/** @return LIVES_INFO_TEXT object */
	static String getLIVES_INFO_TEXT() {
		return LIVES_INFO_TEXT;
	}

	/** @return SCORE_INFO_TEXT object */
	static String getSCORE_INFO_TEXT() {
		return SCORE_INFO_TEXT;
	}

	/** @return NAME_LABEL_TEXT object */
	static String getNAME_LABEL_TEXT() {
		return NAME_LABEL_TEXT;
	}

	/** @return NEXT_LEVEL_TEXT object */
	static String getNEXT_LEVEL_TEXT() {
		return NEXT_LEVEL_TEXT;
	}
}