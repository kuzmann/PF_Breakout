 /**
 * @author Thomas Glaesser
 * */
package de.pfbeuth.game.breakout.gameEngine;
import de.pfbeuth.game.breakout.gamelogic.LevelDesign;
import de.pfbeuth.game.breakout.gamelogic.Life;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import de.pfbeuth.game.breakout.controller.Controller;

 public class Breakout extends Application  {
    static final double WIDTH = 540, HEIGHT = 675;

    private StackPane root;
    public Scene scene;

    private HBox buttonContainer, startButtonContainer, infoContainer, gameOverContainer;
    private Button playButton, helpButton, highscoreButton, creditsButton, startButton;
    private Text levelInfo, lifeInfo, scoreInfo, gameOverInfo;
    private Insets buttonContainerPadding;
    public Controller controller;
    //Game Objects
    public GamePlayTimer gameTimer;
    private SpriteManager spriteManager;
    private Paddle paddle;
    private Brick brick;
    private Ball ball;
    private ArrayList<Brick> brickGrid;
    //Images
    private Image backgroundImage, helpImage, creditsImage, highscoreImage;
    private Image paddleImage, playBackgroundImage;
    private ImageView backgroundLayer, menueOverlay, playBackground;
    private VBox masterButtonContainer;
    public Image brickImage, brickImageRed, brickImageOrange, brickImageYellow, brickImageGreen;
    private Image ballImage;


    @Override
    public void start(Stage primaryStage) {
        //Stage and Scene Setup
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setTitle("BREAKOUT");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        //createSceneEventHandling();
        controller = new Controller(this);
        controller.createSceneEventHandling();
        loadImageAssets();
        createGameObjects();
        addGameObjectsNodes();
        createGUINodes();
        addNodesToStackPane();
        createSpriteManager();
        createStartGamePlayTimer();

        System.out.println("scene width " + scene.getWidth());
        System.out.println("scene height " + scene.getHeight());

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void loadImageAssets(){
        backgroundImage = new Image("/background.png", WIDTH, HEIGHT, false, false, true);
        helpImage = new Image("/help.png", WIDTH, HEIGHT, true, false, true);
        creditsImage = new Image("/credits.png", WIDTH, HEIGHT, true, false, true);
        highscoreImage = new Image("/highscore.png", WIDTH, HEIGHT, true, false, true);
        paddleImage = new Image("/paddle.png", 100, 25, true, false, true);
        brickImageRed = new Image("/brick_red.png", WIDTH/10-5, 25, true, false, true);
        brickImageOrange = new Image("/brick_orange.png", WIDTH/10-5, 25, true, false, true);
        brickImageYellow = new Image("/brick_yellow.png", WIDTH/10-5, 25, true, false, true);
        brickImageGreen = new Image("/brick_green.png", WIDTH/10-5, 25, true, false, true);
        ballImage = new Image("/ball.png", 200/12, 200/12, true, false, true);
        playBackgroundImage = new Image("/background_play.png", WIDTH, HEIGHT, false, false, true);

    }

    private void createGameObjects(){
        //TODO .4 als CONST anlegen
        paddle = new Paddle(this, "M5,0H394C399,0,400,2,400,6V46c0,4-2,5-4,5H7c-7,0-7-4-7-7V6C0,2,1,0,4,0Z", 0, 0, paddleImage);
        paddle.resetState();
        ball = new Ball(this, "M67,0c99,2,94,140,2,141C-22,142-23,1,67,0Z", 0, 0, ballImage);
        ball.resetState();
   }

    //creates bricks which must be destroyed in the game
     // TODO:muss das Leveldesign nicht in die Gamelogic???
    private void createBrickGrid(){
        brickGrid = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
               if(j <= 1) brickImage = brickImageRed;
               else if(j >= 2 &&  j < 4) brickImage = brickImageOrange;
               else if(j >= 4 && j < 6) brickImage = brickImageYellow;
               else brickImage = brickImageGreen;
               brick = new Brick(this, "M.5,3.91V28.66c0,3.75,1.37,4.62,4.62,4.62H84c2.25,0,3.44-.75,3.44-3.44s-.08-22.06,0-26.19C87.5.45,88.07.5,84.29.5H3.5C.25.5.5,3.91.5,3.91Z", 0, 0, brickImage);
               brick.spriteImage.setTranslateX(-WIDTH/2+i*(brickImage.getRequestedWidth()+5)+(brickImage.getRequestedWidth()/2)+ 2);
               brick.spriteImage.setTranslateY(-HEIGHT/2+j*(brickImage.getRequestedHeight()+2.5)+(brickImage.getRequestedHeight()/2+ 50));
               root.getChildren().add(brick.spriteImage);
               brickGrid.add(brick);
                System.out.println("brick hoehe " + brickImage.getRequestedHeight() + "brick breite " + brickImage.getRequestedWidth());
             }
        }
    }

    private void addGameObjectsNodes(){
        createBrickGrid();
        root.getChildren().add(paddle.spriteImage);
        root.getChildren().add(ball.spriteImage);
    }

    private void createSpriteManager(){
        spriteManager = new SpriteManager();
        spriteManager.addCurrentObjects(paddle);
        spriteManager.addCurrentObjects(ball);
        for (Brick aBrickGrid : brickGrid) {
            brick = aBrickGrid;
            spriteManager.addCurrentObjects(brick);
            }
    }

    //creates UI buttons, event handler and layout the buttons
    private void createGUINodes(){
        buttonContainerPadding = new Insets(0, 0, 12, 0);

        masterButtonContainer = new VBox(12);
        masterButtonContainer.setAlignment(Pos.BOTTOM_LEFT);

        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(buttonContainerPadding);

        startButtonContainer = new HBox(12);
        startButtonContainer.setPrefHeight(HEIGHT/2);
        startButtonContainer.setAlignment(Pos.TOP_CENTER);
        startButtonContainer.setPadding(buttonContainerPadding);

        //Container for Gameinformation: Level, Life and Score
        infoContainer = new HBox(12);
        infoContainer.setPrefHeight(HEIGHT/22);
        infoContainer.setAlignment(Pos.BOTTOM_CENTER);
        infoContainer.setPadding(buttonContainerPadding);

        gameOverContainer = new HBox(12);
        gameOverContainer.setPrefHeight(HEIGHT/22);
        gameOverContainer.setAlignment(Pos.TOP_CENTER);
        gameOverContainer.setPadding(buttonContainerPadding);

        //private String levelInfo, lifeInfo, ScoreInfo;
        levelInfo = new Text();
        levelInfo.setVisible(false);
        levelInfo.setFont(new Font("arial", 18));
        levelInfo.setWrappingWidth(200);
        levelInfo.setFill(Color.WHITE);
        levelInfo.setTextAlignment(TextAlignment.CENTER);
        levelInfo.setText("Level: " + LevelDesign.levelnumber);

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
        startButton.setVisible(false);
        startButton.setDisable(true);
        startButton.setOnAction(e -> {
            gameIsOnEvents();
            if (startButton.getText().equals("PLAY AGAIN")) {
                lifeInfo.setText("Lifes: " + Life.getLife());
                startButton.setText("START");
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

    private void addNodesToStackPane(){
        root.getChildren().add(infoContainer);
        root.getChildren().add(playBackground);
        root.getChildren().add(backgroundLayer);
        root.getChildren().add(menueOverlay);
        root.getChildren().add(masterButtonContainer);
    }

    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer(this);
    }

    public void ballDied(){
        if(ball.getBallIsDead()){
            gameTimer.stop();
            gameOverInfo.setVisible(true);
            paddle.resetState();
            ball.resetState();
            startButton.setVisible(true);
            startButton.setDisable(false);
            startButton.setCancelButton(false);
            infoContainer.setVisible(true);
            Life.loseLife();
            lifeInfo.setText("Life: " + Life.getLife());
            if(Life.getIsGameOver()) {
                gameOver();
            }
        }
    }

     public void gameIsOnEvents() {
         gameTimer.start();
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

         showGameInfos();
     }

     public void gameIsPausedEvents(){
         gameTimer.stop();

         startButton.setVisible(true);
         startButton.setDisable(false);
         startButton.setCancelButton(false);

         playButton.setVisible(true);
         playButton.setDisable(false);

         highscoreButton.setVisible(true);
         highscoreButton.setDisable(false);

         helpButton.setVisible(true);
         helpButton.setDisable(false);

         creditsButton.setVisible(true);
         creditsButton.setDisable(false);

         hideGameInfos();
     }

     // TODO screen design makeover
     public void gameOver(){
         gameTimer.stop();

         Life.setLife(3);

         startButton.setText("PLAY AGAIN");
         startButton.setVisible(true);
         startButton.setDisable(false);
         startButton.setCancelButton(false);

         spriteManager.resetCurrentObjects();
         spriteManager.resetRemovedObjects();
         spriteManager.resetCollideCheckList();
         createBrickGrid();
         createSpriteManager();


     }

     public void hideGameInfos(){
         levelInfo.setVisible(false);
         lifeInfo.setVisible(false);
         scoreInfo.setVisible(false);
     }

     public void showGameInfos(){
         levelInfo.setVisible(true);
         lifeInfo.setVisible(true);
         scoreInfo.setVisible(true);
     }

    //GETTER and SETTER
     StackPane getRoot() {
     return root;
    }
     Paddle getPaddle() {
    return paddle;
    }
     Image getPaddleImage() {
    return paddleImage;
    }
     Image getBrickImage() {
         return brickImage;
    }
     SpriteManager getSpriteManager() {
     return spriteManager;
    }
     public Ball getBall() {
         return ball;
    }


 }
