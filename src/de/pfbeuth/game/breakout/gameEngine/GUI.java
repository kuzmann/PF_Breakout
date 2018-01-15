package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.gamelogic.Level;
import de.pfbeuth.game.breakout.gamelogic.LevelDesign;
import de.pfbeuth.game.breakout.gamelogic.Life;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GUI {

    private Breakout breakout;
    private HBox buttonContainer, startButtonContainer, infoContainer, gameOverContainer;

    private VBox masterButtonContainer;

    private Button playButton, helpButton, highscoreButton, creditsButton, startButton;
    private Text levelInfo, lifeInfo, scoreInfo, gameOverInfo;
    private Insets buttonContainerPadding;
    private ImageView backgroundLayer;
    private ImageView menueOverlay;
    private ImageView playBackground;
    private Image playBackgroundImage, backgroundImage, helpImage, creditsImage, highscoreImage;
    private boolean gameIsPaused;
    public static String startText = "START\n(hit enter)";
    public static String playAgainText = "PLAY AGAIN\n(hit enter)";
    public static String pauseGameText = "PAUSED\n(hit enter)";

    GUI(Breakout breakout){
        this.breakout = breakout ;
    }

    void createGUINodes(){
        buttonContainerPadding = new Insets(0, 0, 12, 0);

        masterButtonContainer = new VBox(12);
        masterButtonContainer.setAlignment(Pos.BOTTOM_LEFT);

        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(buttonContainerPadding);

        startButtonContainer = new HBox(12);
        startButtonContainer.setPrefHeight(Breakout.HEIGHT/2);
        startButtonContainer.setAlignment(Pos.TOP_CENTER);
        startButtonContainer.setPadding(buttonContainerPadding);

        //**Container for Gameinformation: Level, Life and Score
        infoContainer = new HBox(12);
        infoContainer.setPrefHeight(Breakout.HEIGHT/22);
        infoContainer.setAlignment(Pos.BOTTOM_CENTER);
        infoContainer.setPadding(buttonContainerPadding);

        gameOverContainer = new HBox(12);
        gameOverContainer.setPrefHeight(Breakout.HEIGHT/22);
        gameOverContainer.setAlignment(Pos.TOP_CENTER);
        gameOverContainer.setPadding(buttonContainerPadding);

        /** levelInfo, lifeInfo, ScoreInfo Setup */
        levelInfo = new Text();
        levelInfo.setVisible(false);
        levelInfo.setFont(new Font("arial", 18));
        levelInfo.setWrappingWidth(200);
        levelInfo.setFill(Color.WHITE);
        levelInfo.setTextAlignment(TextAlignment.CENTER);
        levelInfo.setText("Level: " + Level.getLevel());

        lifeInfo = new Text();
        lifeInfo.setVisible(false);
        lifeInfo.setFont(new Font("arial", 18));
        lifeInfo.setWrappingWidth(200);
        lifeInfo.setFill(Color.WHITE);
        lifeInfo.setTextAlignment(TextAlignment.CENTER);
        lifeInfo.setText("Lifes: " + Life.getLife());

        scoreInfo = new Text();
        scoreInfo.setVisible(false);
        scoreInfo.setFont(new Font("arial", 18));
        scoreInfo.setWrappingWidth(200);
        scoreInfo.setFill(Color.WHITE);
        scoreInfo.setTextAlignment(TextAlignment.CENTER);
        scoreInfo.setText("Score: " + ScoreCounter.score);

        gameOverInfo = new Text();
        gameOverInfo.setFont(new Font(30));
        gameOverInfo.setWrappingWidth(200);
        gameOverInfo.setFill(Color.RED);
        gameOverInfo.setTextAlignment(TextAlignment.JUSTIFY);
        gameOverInfo.setText("GAME OVER");

        /** Buttons Setup */
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
            resumeGameEvents();
            if (startButton.getText().equals(playAgainText)) {
                lifeInfo.setText("Lifes: " + Life.getLife());
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
        });
        creditsButton = new Button();
        creditsButton.setText("CREDITS");
        creditsButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(creditsImage);
            startButton.setVisible(false);
            startButton.setDisable(true);

            hideGameInfos();
        });

        /** Add GUI Nodes to scene */
        infoContainer.getChildren().addAll(levelInfo, lifeInfo, scoreInfo);
        buttonContainer.getChildren().addAll(playButton, highscoreButton, helpButton, creditsButton);
        startButtonContainer.getChildren().add(startButton);
        masterButtonContainer.getChildren().addAll(startButtonContainer, buttonContainer);

        backgroundLayer = new ImageView();
        backgroundLayer.setImage(backgroundImage);

        menueOverlay = new ImageView();
        menueOverlay.setImage(highscoreImage);

        playBackground = new ImageView();
        playBackground.setImage(playBackgroundImage);
        playBackground.setVisible(false);


    }

     void loadImageAssets(){
        backgroundImage = new Image("/background.png", Breakout.WIDTH, Breakout.HEIGHT, false, false, true);
        helpImage = new Image("/help.png", Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
        highscoreImage = new Image("/highscore.png", Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
        creditsImage = new Image("/credits.png", Breakout.WIDTH, Breakout.HEIGHT, true, false, true);
        playBackgroundImage = new Image("/background_play.png", Breakout.WIDTH, Breakout.HEIGHT, false, false, true);

    }

    public void resumeGameEvents() {
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

        creditsButton.setVisible(false);
        creditsButton.setDisable(true);

        //TODO: Ausgabe des Levels aktuell halten. Nocg wird immer hur »1« agezeigt
        if(breakout.getBall().getLevelWon()) {
            getLevelInfo().setText("Level neu: " + Level.getLevel());
        }

        showGameInfos();

        gameIsPaused = false;
    }

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

        creditsButton.setVisible(true);
        creditsButton.setDisable(false);

        hideGameInfos();

        gameIsPaused = true;
    }

    void showGameInfos(){
        levelInfo.setVisible(true);
        lifeInfo.setVisible(true);
        scoreInfo.setVisible(true);
    }

    void hideGameInfos(){
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

    //Brauchen wir das?
    public Text getLevelInfo() {
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

    public VBox getMasterButtonContainer() {
        return masterButtonContainer;
    }

    public ImageView getMenueOverlay() {
        return menueOverlay;
    }

    public boolean isGameIsPaused() {
        return gameIsPaused;
    }

}
