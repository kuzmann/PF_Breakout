package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.dataHandling.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GUI {
    private Breakout breakout;
    private HBox  infoContainer,  highscoreContainer, helpContainer, startButtonContainer;
    private VBox masterButtonContainer;
    private Button playButton, helpButton, highscoreButton, startButton, confirmButton;
    private ImageView backgroundLayer, menueOverlay, playBackground;
    private Image playBackgroundImage, backgroundImage, helpImage, highscoreImage, gameOverImage;

    private Text levelInfo, lifeInfo, scoreInfo, highscoreList, helpText, gameOverInfo;
    private final String START_BUTTON_TEXT = "START\n(hit enter)";
    private final String PLAY_BUTTON_TEXT = "PLAY";
    private final String PLAY_AGAIN_TEXT = "PLAY AGAIN\n(hit enter)";
    private final String GAME_OVER_TEXT = "GAME OVER";
    private final String PAUSE_GAME_TEXT = "PAUSED\n(hit enter)";
    private final String HELP_BUTTON_TEXT = "HELP";
    private final String HIGHSCORE_BUTTON_TEXT = "HIGHSCORES";
	private final String CONFIRM_BUTTON_TEXT = "OK";



	private final String LEVEL_INFO_TEXT = "LEVEL: ";
	private final String LIVES_INFO_TEXT = "LIVES: ";
	private final String SCORE_INFO_TEXT = "SCORE: ";
	private final String NAME_LABEL_TEXT = "PLAYER NAME:";
	private final String NEXT_LEVEL_TEXT = "CONGRATULATIONS!\nSTART NEXT LEVEL";
	private String HIGHSCORELIST;

	/* ------ NEUE HighscoreContrainer ------ */
    private GridPane playerInputContainer;
    private Insets highscoreContainerPadding;
    /* ------ dataHandling ------ */
    private CreatePlayer Player;
    private String playerName;
    private TextField nameInput;
	private Label nameLabel;
	private LoadXMLTable loader;

    /* ------ CONSTRUCTOR ------ */
    GUI(Breakout breakout){
        this.breakout = breakout ;
        loadImageAssets();
        createGUIImages();
        createInfoText();
        createButtons();
        createGUIContainer();
    }
    private void loadImageAssets(){
        backgroundImage = new Image("/assets/graphics/background.png",
						  Breakout.WIDTH, Breakout.HEIGHT, false, false, true);
        helpImage = new Image("/assets/graphics/help.png",
						  Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
        highscoreImage = new Image("/assets/graphics/highscore.png",
					   	  Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
        playBackgroundImage = new Image("/assets/graphics/background_play.png",
						  Breakout.WIDTH, Breakout.HEIGHT, false, false, true);
		gameOverImage = new Image("/assets/graphics/gameOver.png",
						  Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
    }
    private void createGUIImages(){
        backgroundLayer = new ImageView(backgroundImage);
        menueOverlay = new ImageView(highscoreImage);
        playBackground = new ImageView(playBackgroundImage);
        playBackground.setVisible(false);
       /* gameOver = new ImageView(gameOverImage);
        gameOver.setVisible(false);*/
    }
    private void createGUIContainer() {
        Insets buttonContainerPadding = new Insets(0, 0, 12, 0);
        Insets topContainerPadding = new Insets(250, 0, 0, 0);

        masterButtonContainer = new VBox(12);
        masterButtonContainer.setAlignment(Pos.BOTTOM_LEFT);
		masterButtonContainer.setFocusTraversable(false);

		HBox buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(buttonContainerPadding);

		startButtonContainer = new HBox(12);
        startButtonContainer.setPrefHeight(Breakout.HEIGHT / 2);
        startButtonContainer.setAlignment(Pos.CENTER);
        startButtonContainer.setPadding(new Insets(0, 0, 200, 0));

        /* ------ Container for Gameinformation: Level, Lives and Score ------ */
        infoContainer = new HBox(12);
        infoContainer.setPrefHeight(Breakout.HEIGHT / 22);
        infoContainer.setAlignment(Pos.BOTTOM_CENTER);
        infoContainer.setPadding(buttonContainerPadding);

        HBox gameOverContainer = new HBox(12);
        gameOverContainer.setPrefHeight(Breakout.HEIGHT / 22);
        gameOverContainer.setAlignment(Pos.TOP_CENTER);
        gameOverContainer.setPadding(buttonContainerPadding);

        /* ------ Container for showing Highscore List ------ */
        highscoreContainer = new HBox(12);
        highscoreContainer.setPrefHeight(Breakout.HEIGHT / 50);
        highscoreContainer.setAlignment(Pos.TOP_CENTER);
        highscoreContainer.setPadding(topContainerPadding);

        helpContainer = new HBox(12);
		helpContainer.setPrefHeight(Breakout.HEIGHT / 50);
		helpContainer.setAlignment(Pos.TOP_CENTER);
		helpContainer.setPadding(topContainerPadding);

        /* ------ Label, input and button for enter the user name ------ */
        nameLabel = new Label(NAME_LABEL_TEXT);
        nameLabel.setTextFill(Color.WHITE);

        //TextField nameInput = new TextField("Player");
        nameInput = new TextField();
        nameInput.setPromptText("Choose player name");

        /* ------ Container for Enter the user name ------ */
        playerInputContainer = new GridPane();

        playerInputContainer.setTranslateY(-200);
		playerInputContainer.setVisible(false);
		playerInputContainer.setDisable(true);
		playerInputContainer.setAlignment(Pos.CENTER);
		playerInputContainer.setPadding(new Insets(0, 0, 0, 0));
		playerInputContainer.setVgap(10);
		playerInputContainer.setHgap(10);
		playerInputContainer.setConstraints(nameLabel, 0, 0);
		playerInputContainer.setConstraints(nameInput, 1, 0);
		playerInputContainer.setConstraints(confirmButton, 1, 1);

        /* ------ Add GUI Nodes to scene ------ */
        infoContainer.getChildren().addAll(levelInfo, lifeInfo, scoreInfo);
        helpContainer.getChildren().add(helpText);
        highscoreContainer.getChildren().add(highscoreList);
        buttonContainer.getChildren().addAll(playButton, highscoreButton, helpButton);
		playerInputContainer.getChildren().addAll(nameLabel, nameInput, confirmButton);
		startButtonContainer.getChildren().addAll(startButton);
		masterButtonContainer.getChildren().addAll(startButtonContainer, playerInputContainer, buttonContainer);
	}
    private void createInfoText(){
        levelInfo = new InfoText();
        levelInfo.setText(LEVEL_INFO_TEXT + breakout.getLevel().getLevelNumber());

        lifeInfo = new InfoText();
        lifeInfo.setText(LIVES_INFO_TEXT + breakout.getLife().getActualLife());

        scoreInfo = new InfoText();
		scoreInfo.setText(SCORE_INFO_TEXT + breakout.getScoreCounter().getScoreNumber());

        highscoreList = new InfoText();
        highscoreList.setVisible(true);

        helpText = new InfoText();
        helpText.setText("INSTRUCTIONS: ");	//TODO convert String to Constant

		gameOverInfo = new InfoText();
    }
    private void createButtons(){
		startButton = new Button();
		startButton.setPrefSize(100, 100);
		startButton.setText(START_BUTTON_TEXT);
		startButton.setTextAlignment(TextAlignment.CENTER);
		startButtonVisibliy(false);
		startButton.setOnAction(e -> {
			breakout.getGameStates().runGameEvents();
			//TODO trigger resetGame when game over or level up
			/*if (startButton.getText().equals(PLAY_AGAIN_TEXT)) {
				lifeInfo.setText(LIVES_INFO_TEXT + breakout.getLife().getActualLife());
				startButton.setText(START_BUTTON_TEXT);
			}*/
		});
		/* ------ Menue Buttons ------ */
		playButton = new Button();
		playButton.setFocusTraversable(false);
		playButton.setPrefWidth(100);
		playButton.setText(PLAY_BUTTON_TEXT);
		playButton.setOnAction(e -> {
			startButtonVisibliy(true);
			startButtonContainer.setVisible(true);
			startButtonContainer.setDisable(false);
			playerInputContainer.toBack();
			backgroundLayer.setVisible(false);
			menueOverlay.setVisible(false);
			playBackground.setVisible(true);
			playBackground.toBack();
			highscoreList.setVisible(false);
            helpText.setVisible(false);
			highscoreList.setVisible(false);
			hideGameInfos();


		});

        highscoreButton = new Button();
        highscoreButton.setFocusTraversable(false);
		highscoreButton.setPrefWidth(100);
        highscoreButton.setText(HIGHSCORE_BUTTON_TEXT);
        highscoreButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(highscoreImage);
            startButtonVisibliy(false);
			highscoreList.setVisible(true);
            helpText.setVisible(false);
            hideGameInfos();
        });

        helpButton = new Button();
        helpButton.setFocusTraversable(false);
		helpButton.setPrefWidth(100);
        helpButton.setText(HELP_BUTTON_TEXT);
        helpButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(helpImage);
            startButtonVisibliy(false);
			highscoreList.setVisible(false);
			helpText.setVisible(true);
			hideGameInfos();
		});

         /* ------ Confirm-button with actions ------ */
		confirmButton = new Button();
		confirmButton.setText(CONFIRM_BUTTON_TEXT);
		confirmButtonVisibliy(false);
		confirmButton.setOnAction(e ->
			confirmButtonEvents());
    }
	/** Displays game info texts */
	public void showGameInfos(){
		levelInfo.setVisible(true);
		lifeInfo.setVisible(true);
		scoreInfo.setVisible(true);
	}
	/** Hides game info texts */
	public void hideGameInfos(){
		levelInfo.setVisible(false);
		lifeInfo.setVisible(false);
		scoreInfo.setVisible(false);
		playerInputContainer.setVisible(false);
		playerInputContainer.setDisable(true);
	}
	/** Updates score info text through property binding */
	public void updateScoreInfo (){
		scoreInfo.textProperty().bind(breakout.getScoreCounter().scoreProperty());
	}
	public void createHighScoreScreen(){
		loader = new LoadXMLTable();
		loader.loadTable();
		//TODO Was passiert wenn die Liste leer ist
		if (loader.getHighscoreList().size() != 0) {
			for (int i = 0; i < loader.getHighscoreList().size() && i < 10; i++) {
				if( i < 9){
					highscoreList.setText(loader.getHighscoreList().get(i).getPlayerName() + ".........." + loader.getHighscoreList().get(i).getPlayerScore() + "\n");
				} else highscoreList.setText(loader.getHighscoreList().get(i).getPlayerName() + ".........." + loader.getHighscoreList().get(i).getPlayerScore());
			}
		}
	}

	/** Triggers confirm button events */
	public void confirmButtonEvents() {

		if ((nameInput.getText() != null && !nameInput.getText().isEmpty())) {
			nameLabel.setText(NAME_LABEL_TEXT);
			playerName = nameInput.getText();
			Player = new CreatePlayer(playerName);

			//TODO Ausgabe der Highscoreliste fertigstellen
			UpdateXMLTable updater = new UpdateXMLTable();
			updater.add(Player);
			loader.loadTable();
			loader.displayHighscore();
			createHighScoreScreen();

			backgroundLayer.setVisible(true);
			menueOverlay.setVisible(true);
			menueOverlay.setImage(highscoreImage);

			startButtonVisibliy(false);
			startButtonContainer.setVisible(true);
			startButtonContainer.setDisable(false);

			highscoreList.setVisible(true);
			helpText.setVisible(false);
			hideGameInfos();

			playButtonVisibliy(true);
			highscoreButtonVisibliy(true);
			helpButtonVisibliy(true);
			confirmButtonVisibliy(false);
			gameOverInfo.setVisible(false);
			breakout.getLife().setGameOver(false);
		} else {
			nameLabel.setText("PLEASE ENTER\nYOUR NAME!");
		}
	}

	/** sets Play Button visible and enabled without focus consume */
	public void playButtonVisibliy(Boolean visibility){
		playButton.setVisible(visibility);
		playButton.setDisable(!visibility);
		playButton.setFocusTraversable(!visibility);
	}
	/** sets Highscore Button visible and enabled without focus consume */
	public void highscoreButtonVisibliy(Boolean visibility){
		highscoreButton.setVisible(visibility);
		highscoreButton.setDisable(!visibility);
		highscoreButton.setFocusTraversable(!visibility);
	}
	/** sets Help Button visible and enabled without focus consume */
	public void helpButtonVisibliy(Boolean visibility){
		helpButton.setVisible(visibility);
		helpButton.setDisable(!visibility);
		helpButton.setFocusTraversable(!visibility);
	}
	/** sets Start Button visible and enabled without focus consume */
	public void startButtonVisibliy(Boolean visibility){
		startButton.setVisible(visibility);
		startButton.setDisable(!visibility);
		startButton.setFocusTraversable(!visibility);
	}
	/** sets Confirm Button visible and enabled without focus consume */
	public void confirmButtonVisibliy(Boolean visibility){
		confirmButton.setVisible(visibility);
		confirmButton.setDisable(!visibility);
		confirmButton.setFocusTraversable(!visibility);
	}

    /* ------ GETTER ------ */
	public String getLEVEL_INFO_TEXT() {
		return LEVEL_INFO_TEXT;
	}
    public Button getStartButton() {
        return startButton;
    }
	public HBox getStartButtonContainer() {
		return startButtonContainer;
	}
	public Button getConfirmButton() {
		return confirmButton;
	}
	public Button getPlayButton() {
		return playButton;
	}
	public Button getHighscoreButton() {
		return highscoreButton;
	}
	public Button getHelpButton() {
		return helpButton;
	}
    public Text getLifeInfo() {
        return lifeInfo;
    }
    public Text getLevelInfo(){
        return levelInfo;
    }
    public Text getScoreInfo(){
    	return scoreInfo;
	}
    public ImageView getBackgroundLayer() {
        return backgroundLayer;
    }
    public ImageView getPlayBackground() {
        return playBackground;
    }
    public HBox getInfoContainer() {
        return infoContainer;
    }
    public HBox getHighscoreContainer() {
        return highscoreContainer;
    }
    public HBox getHelpContainer() {
        return helpContainer;
    }
    public GridPane getPlayerInputContainer() {
        return playerInputContainer;
    }
    public VBox getMasterButtonContainer() {
        return masterButtonContainer;
    }
    public ImageView getMenueOverlay() {
        return menueOverlay;
    }
    public String getStartText() {
        return START_BUTTON_TEXT;
    }
    public String getPlayAgainText() {
        return PLAY_AGAIN_TEXT;
    }
    public String getPauseGameText() {
        return PAUSE_GAME_TEXT;
    }
    public String getLIVES_INFO_TEXT(){
    	return LIVES_INFO_TEXT;
	}
	public String getSCORE_INFO_TEXT(){
    	return SCORE_INFO_TEXT;
	}
	public String getNEXT_LEVEL_TEXT(){
		return NEXT_LEVEL_TEXT;
	}
	public String getGAME_OVER_TEXT(){
		return GAME_OVER_TEXT;
	}
	public Text getGameOverInfo() {
		return gameOverInfo;
	}
	public Image getGameOverImage() {
		return gameOverImage;
	}
}
