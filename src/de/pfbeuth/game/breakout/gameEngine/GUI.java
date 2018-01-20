package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.dataHandling.*;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Text levelInfo, lifeInfo, scoreInfo, gameOverInfo, highscoreList, instructionText;
    private Insets buttonContainerPadding, topContainerPadding;
    private ImageView backgroundLayer, menueOverlay, playBackground;
    private Image playBackgroundImage, backgroundImage, helpImage, highscoreImage;
    private boolean gameIsPaused;
    final String startText = "START\n(hit enter)";
    final String playAgainText = "PLAY AGAIN\n(hit enter)";
    final String gameOver = "GAME OVER";
    final String pauseGameText = "PAUSED\n(hit enter)";

    /**NEUE HighscoreContrainer*/
    private GridPane playerInputContainer;
    private Insets highscoreContainerPadding;
    //dataHandling
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

        // Container for Gameinformation: Level, Life and Score
        infoContainer = new HBox(12);
        infoContainer.setPrefHeight(Breakout.HEIGHT / 22);
        infoContainer.setAlignment(Pos.BOTTOM_CENTER);
        infoContainer.setPadding(buttonContainerPadding);

        gameOverContainer = new HBox(12);
        gameOverContainer.setPrefHeight(Breakout.HEIGHT / 22);
        gameOverContainer.setAlignment(Pos.TOP_CENTER);
        gameOverContainer.setPadding(buttonContainerPadding);

        // Container for showing Highscore List
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

        //**Container for Enter the user name*/
        playerInputContainer = new GridPane();
        playerInputContainer.setAlignment(Pos.CENTER);
        int padding = 60;
        int Vgap = 8;
        int Hgap = 10;
        playerInputContainer.setPadding(new Insets(padding, padding, padding, padding));
        playerInputContainer.setVgap(Vgap);
        playerInputContainer.setHgap(Hgap);

        /** Add GUI Nodes to scene */
        infoContainer.getChildren().addAll(levelInfo, lifeInfo, scoreInfo);
        introductionContainer.getChildren().add(instructionText);
        highscoreContainer.getChildren().add(highscoreList);
        //textContainer.getChildren().addAll(introductionContainer,creditContainer,highscoreContainer);
        buttonContainer.getChildren().addAll(playButton, highscoreButton, helpButton);
        startButtonContainer.getChildren().add(startButton);
        playerInputContainer.getChildren().addAll(nameLabel, nameInput, confirmButton);
        masterButtonContainer.getChildren().addAll(startButtonContainer, buttonContainer);

        //**Label, input and Button for enter the user name*/
        Label nameLabel = new Label("Payer name:");
        nameLabel.setTextFill(Color.WHITE);
        playerInputContainer.setConstraints(nameLabel, 0, 0);
        //TextField nameInput = new TextField("Player");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Choose player name");
        playerInputContainer.setConstraints(nameInput, 1, 0);

        playerInputContainer.setConstraints(confirmButton, 1, 1);
    }

    private void createInfoText(){

        /** levelInfo, lifeInfo, ScoreInfo Setup */
        levelInfo = new InfoText();
        levelInfo.setText("Level: " + breakout.getLevel().getLevel());

        lifeInfo = new InfoText();
        lifeInfo.setText("Lives: " + breakout.getLife().getActualLife());

        scoreInfo = new InfoText();
        scoreInfo.setText("Score: " + ScoreCounter.score);

        gameOverInfo = new InfoText();
        gameOverInfo.setFont(Font.font(30));
        gameOverInfo.setFill(Color.RED);
        gameOverInfo.setText(gameOver);

        highscoreList = new InfoText();
        highscoreList.setText("Highscore Liste wird hier angezeigt: ");

        instructionText = new InfoText();
        instructionText.setText("Spielanteilung: ");
    }

    private void createButtons(){

        playButton = new Button();
        playButton.setPrefWidth(100);
        playButton.setText("PLAY");
        playButton.setOnAction(e -> {
            startButton.setVisible(true);
            startButton.setDisable(false);
            backgroundLayer.setVisible(false);
            menueOverlay.setVisible(false);
            playBackground.setVisible(true);
            playBackground.toBack();
            hideGameInfos();
        });
        startButton = new Button();
        startButton.setPrefSize(100, 100);
        startButton.setText("START");
        startButton.setTextAlignment(TextAlignment.CENTER);
        startButton.setVisible(false);
        startButton.setDisable(true);
        startButton.setOnAction(e -> {
            runGameEvents();
            if (startButton.getText().equals(playAgainText)) {
                lifeInfo.setText("Lives: " + breakout.getLife().getActualLife());
                startButton.setText(startText);
            }
        });
        highscoreButton = new Button();
        highscoreButton.setText("HIGH SCORES");
        highscoreButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(highscoreImage);
            startButton.setVisible(false);
            startButton.setDisable(true);
            highscoreList.setVisible(true);
            instructionText.setVisible(false);
            hideGameInfos();
        });
        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        helpButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(helpImage);
            startButton.setVisible(false);
            startButton.setDisable(true);
            hideGameInfos();
            highscoreList.setVisible(false);
            instructionText.setVisible(true);
        });
        confirmButton = new Button();
        confirmButton.setText("Confirm");
        /*confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((nameInput.getText() != null && !nameInput.getText().isEmpty())) {
                    nameLabel.setText(nameInput.getText() + ", " + "thank you for your comment!");
                } else {
                    nameLabel.setText("You have not left a player name.");
                }
            }
        });*/
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

    private void loadImageAssets(){
        backgroundImage = new Image("/assets/graphics/background.png", Breakout.WIDTH, Breakout.HEIGHT, false, false, true);
        helpImage = new Image("/assets/graphics/help.png", Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
        highscoreImage = new Image("/assets/graphics/highscore.png", Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
        playBackgroundImage = new Image("/assets/graphics/background_play.png", Breakout.WIDTH, Breakout.HEIGHT, false, false, true);
    }

    private void createGUIImages(){
        backgroundLayer = new ImageView();
        backgroundLayer.setImage(backgroundImage);
        menueOverlay = new ImageView();
        menueOverlay.setImage(highscoreImage);
        playBackground = new ImageView();
        playBackground.setImage(playBackgroundImage);
        playBackground.setVisible(false);
    }

    /** View: Start new Game*/
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
            getLevelInfo().setText("Level: " + breakout.getLevel().getLevel());
        }
        showGameInfos();
        gameIsPaused = false;
    }
    /** View: Pause new Game*/
    public void pauseGameEvents(){
        breakout.getGameTimer().stop();
        startButton.setVisible(true);
        startButton.setDisable(false);
        startButton.setCancelButton(false);
        startButton.setText(pauseGameText);
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
        return startText;
    }
    public String getPlayAgainText() {
        return playAgainText;
    }
    public String getPauseGameText() {
        return pauseGameText;
    }
}
