package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.dataHandling.*;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GUI {

    private Breakout breakout;
    private HBox buttonContainer, startButtonContainer, infoContainer, gameOverContainer, highscoreContainer, introductionContainer,creditContainer ;
    private VBox masterButtonContainer;
    private Button playButton, helpButton, highscoreButton, startButton, confirmButton;
    private Text levelInfo, lifeInfo, scoreInfo, gameOverInfo, highscoreList, helpText;
    private Insets buttonContainerPadding, topContainerPadding;
    private ImageView backgroundLayer, menueOverlay, playBackground;
    private Image playBackgroundImage, backgroundImage, helpImage, highscoreImage;
    private boolean gameIsPaused;

    private final String START_BUTTON_TEXT = "START\n(hit enter)";
    private final String PLAY_BUTTON_TEXT = "PLAY";
    private final String PLAY_AGAIN_TEXT = "PLAY AGAIN\n(hit enter)";
    private final String GAME_OVER_TEXT = "GAME OVER";
    private final String PAUSE_GAME_TEXT = "PAUSED\n(hit enter)";
    private final String HELP_BUTTON_TEXT = "HELP";
    private final String HIGHSCORE_BUTTON_TEXT = "HIGHSCORES";
	private final String CONFIRM_BUTTON_TEXT = "CONFIRM";
	private final String LEVEL_INFO_TEXT = "LEVEL";
	private final String LIVES_INFO_TEXT = "LIVES";
	private final String SCORE_INFO_TEXT = "SCORE";

	/* ------ NEUE HighscoreContrainer ------ */
    private GridPane playerInputContainer;
    private Insets highscoreContainerPadding;
    /* ------ dataHandling ------ */
    private CreatePlayer Player;
    private String playerName;
    private TextField nameInput, nameLabel;

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
    }

    private void createGUIImages(){
        backgroundLayer = new ImageView(backgroundImage);
        menueOverlay = new ImageView(highscoreImage);
        playBackground = new ImageView(playBackgroundImage);
        playBackground.setVisible(false);
    }

    private void createGUIContainer() {
        buttonContainerPadding = new Insets(0, 0, 12, 0);
        topContainerPadding = new Insets(250, 0, 0, 0);

        masterButtonContainer = new VBox(12);
        masterButtonContainer.setAlignment(Pos.BOTTOM_LEFT);

        /*textContainer = new VBox(12);
        textContainer.setAlignment(Pos.TOP_CENTER);
        textContainer.setPadding(topContainerPadding); */

        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(buttonContainerPadding);

        startButtonContainer = new HBox(12);
        startButtonContainer.setPrefHeight(Breakout.HEIGHT / 2);
        startButtonContainer.setAlignment(Pos.TOP_CENTER);
        startButtonContainer.setPadding(buttonContainerPadding);

        /* ------ Container for Gameinformation: Level, Life and Score ------ */
        infoContainer = new HBox(12);
        infoContainer.setPrefHeight(Breakout.HEIGHT / 22);
        infoContainer.setAlignment(Pos.BOTTOM_CENTER);
        infoContainer.setPadding(buttonContainerPadding);

        gameOverContainer = new HBox(12);
        gameOverContainer.setPrefHeight(Breakout.HEIGHT / 22);
        gameOverContainer.setAlignment(Pos.TOP_CENTER);
        gameOverContainer.setPadding(buttonContainerPadding);

        /* ------ Container for showing Highscore List ------ */
        highscoreContainer = new HBox(12);
        highscoreContainer.setPrefHeight(Breakout.HEIGHT / 50);
        highscoreContainer.setAlignment(Pos.TOP_CENTER);
        highscoreContainer.setPadding(topContainerPadding);

        introductionContainer = new HBox(12);
        introductionContainer.setPrefHeight(Breakout.HEIGHT / 50);
        introductionContainer.setAlignment(Pos.TOP_CENTER);
        introductionContainer.setPadding(topContainerPadding);

        creditContainer = new HBox(12);
        creditContainer.setPrefHeight(Breakout.HEIGHT / 50);
        creditContainer.setAlignment(Pos.TOP_CENTER);
        creditContainer.setPadding(topContainerPadding);

        /*

        //Container for Enter the user name
        playerInputContainer = new GridPane();
        playerInputContainer.setAlignment(Pos.CENTER);
        int padding = 60;
        int Vgap = 8;
        int Hgap = 10;
        playerInputContainer.setPadding(new Insets(padding, padding, padding, padding));
        playerInputContainer.setVgap(Vgap);
        playerInputContainer.setHgap(Hgap);

        */

        /** Add GUI Nodes to scene */
        infoContainer.getChildren().addAll(levelInfo, lifeInfo, scoreInfo);
        introductionContainer.getChildren().add(helpText);
        highscoreContainer.getChildren().add(highscoreList);
        //textContainer.getChildren().addAll(introductionContainer,creditContainer,highscoreContainer);
        buttonContainer.getChildren().addAll(playButton, highscoreButton, helpButton);
        startButtonContainer.getChildren().add(startButton);
       // playerInputContainer.getChildren().addAll(nameLabel, nameInput, confirmButton);
        masterButtonContainer.getChildren().addAll(startButtonContainer, buttonContainer);


/* //Label, input and Button for enter the user name*//*

        Label nameLabel = new Label("Payer name:");
        nameLabel.setTextFill(Color.WHITE);
        playerInputContainer.setConstraints(nameLabel, 0, 0);
        //TextField nameInput = new TextField("Player");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Choose player name");
        playerInputContainer.setConstraints(nameInput, 1, 0);

        playerInputContainer.setConstraints(confirmButton, 1, 1);
*/
    }

    private void createInfoText(){

        /** levelInfo, lifeInfo, ScoreInfo Setup */
        levelInfo = new InfoText();
        levelInfo.setText(LEVEL_INFO_TEXT + breakout.getLevel().getLevelNumber());

        lifeInfo = new InfoText();
        lifeInfo.setText(LIVES_INFO_TEXT + breakout.getLife().getActualLife());

        scoreInfo = new InfoText();
        scoreInfo.setText(SCORE_INFO_TEXT + ScoreCounter.score);

        gameOverInfo = new InfoText();
        gameOverInfo.setFont(Font.font(30));
        gameOverInfo.setFill(Color.RED);
        gameOverInfo.setText(GAME_OVER_TEXT);

        highscoreList = new InfoText();
        highscoreList.setText("Highscore Liste wird hier angezeigt: ");
        highscoreList.setVisible(true);

        helpText = new InfoText();
        helpText.setText("Spielanteilung: ");	//TODO convert String to Constant
    }

    private void createButtons(){
		startButton = new Button();
		startButton.setPrefSize(100, 100);
		startButton.setText(START_BUTTON_TEXT);
		startButton.setTextAlignment(TextAlignment.CENTER);
		startButton.setVisible(false);
		startButton.setDisable(true);
		startButton.setOnAction(e -> {
			runGameEvents();
			if (startButton.getText().equals(PLAY_AGAIN_TEXT)) {
				lifeInfo.setText("Lives: " + breakout.getLife().getActualLife());
				startButton.setText(START_BUTTON_TEXT);
			}
		});

		playButton = new Button();
		playButton.setPrefWidth(100);
		playButton.setText(PLAY_BUTTON_TEXT);
		playButton.setOnAction(e -> {
			startButton.setVisible(true);
			startButton.setDisable(false);
			backgroundLayer.setVisible(false);
			menueOverlay.setVisible(false);
			playBackground.setVisible(true);
			playBackground.toBack();
			highscoreList.setVisible(false);
			hideGameInfos();
		});

        highscoreButton = new Button();
		highscoreButton.setPrefWidth(100);
        highscoreButton.setText(HIGHSCORE_BUTTON_TEXT);
        highscoreButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(highscoreImage);
            startButton.setVisible(false);
            startButton.setDisable(true);
            highscoreList.setVisible(true);
            helpText.setVisible(false);
            hideGameInfos();
        });

        helpButton = new Button();
		helpButton.setPrefWidth(100);
        helpButton.setText(HELP_BUTTON_TEXT);
        helpButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(helpImage);
            startButton.setVisible(false);
            startButton.setDisable(true);
            hideGameInfos();
            highscoreList.setVisible(false);
            helpText.setVisible(true);
        });

        confirmButton = new Button();
        confirmButton.setText(CONFIRM_BUTTON_TEXT);
        /*
        confirmButton.setOnAction(e -> {
            if ((nameInput.getText() != null && !nameInput.getText().isEmpty())) {
				nameLabel.setText(nameInput.getText() + ", " + "thank you for your comment!");
                }
		  	else{
				nameLabel.setText("You have not left a player name.");
                }
        });
        */
        confirmButton.setOnAction(e -> {
            playerName = nameInput.getText();
            System.out.println("Player name is: " + playerName);
            //HighscoreContainer.setVisible(false);
            Player = new CreatePlayer(playerName);
            UpdateXMLTable updater = new UpdateXMLTable();
            updater.add(Player);
            LoadXMLTable loader = new LoadXMLTable();
            loader.loadTable();
            loader.displayHighscore();
            Scoreboard scoreboard = new Scoreboard();
            //scoreboard.display();
            //menueOverlay.setVisible(false);
        });
    }

	private void showGameInfos(){
		levelInfo.setVisible(true);
		lifeInfo.setVisible(true);
		scoreInfo.setVisible(true);
	}
	private void hideGameInfos(){
		levelInfo.setVisible(false);
		lifeInfo.setVisible(false);
		scoreInfo.setVisible(false);
	}
	/* ------ View: Start new Game ------ */
    public void runGameEvents() {

        /* //TODO countdown before game starts
         for (int i = 3; i > 0; i--) {
            startButton.setText("" + i);
            System.out.println("" + startButton.getText());
            try {
                Thread.sleep(500);
            }
            catch(InterruptedException e1) {}
        }*/
        breakout.getGameTimer().start();
        startButton.setVisible(false);
        startButton.setDisable(true);
        startButton.setCancelButton(true);
        playButton.setVisible(false);
        playButton.setDisable(true);
        highscoreButton.setVisible(false);
        highscoreButton.setDisable(true);
        helpButton.setVisible(false);
        helpButton.setDisable(true);
        //TODO: Ausgabe des Levels aktuell halten. Wird erst wieder angezeigt, wenn
        if(breakout.getBall().getLevelWon()) {
            getLevelInfo().setText(LEVEL_INFO_TEXT + breakout.getLevel().getLevelNumber());
        }
        showGameInfos();
        gameIsPaused = false;
    }
	/* ------ View: Pause new Game ------ */
    public void pauseGameEvents(){
        breakout.getGameTimer().stop();
        startButton.setVisible(true);
        startButton.setDisable(false);
        startButton.setCancelButton(false);
        startButton.setText(PAUSE_GAME_TEXT);
        startButton.setTextAlignment(TextAlignment.CENTER);
        playButton.setVisible(true);
        playButton.setDisable(false);
        highscoreButton.setVisible(true);
        highscoreButton.setDisable(false);
        helpButton.setVisible(true);
        helpButton.setDisable(false);
        hideGameInfos();
        gameIsPaused = true;
    }

    /* ------ GETTER ------ */
    public Button getStartButton() {
        return startButton;
    }
    public Text getGameOverInfo() {
        return gameOverInfo;
    }
    public Text getLifeInfo() {
        return lifeInfo;
    }
    public Text getLevelInfo(){
        return levelInfo;
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
    public HBox getIntroductionContainer() {
        return introductionContainer;
    }
    public HBox getCreditContainer() {
        return creditContainer;
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
    public boolean isGameIsPaused() {
        return gameIsPaused;
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
}
