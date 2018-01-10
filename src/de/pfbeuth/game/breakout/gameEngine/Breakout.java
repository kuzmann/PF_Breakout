 /**
 * @author Thomas Glaesser
 * */
package de.pfbeuth.game.breakout.gameEngine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import de.pfbeuth.game.breakout.controller.Controller;

 public class Breakout extends Application  {
    static final double WIDTH = 720, HEIGHT = 900;
    private StackPane root;
    public Scene scene;
    private Image backgroundImage, helpImage, creditsImage, highscoreImage;
    private Image paddleImage, playBackgroundImage;
    private ImageView backgroundLayer, menueOverlay, playBackground;
    private VBox masterButtonContainer;
    private HBox buttonContainer, startButtonContainer;
    private Button playButton, helpButton, highscoreButton, creditsButton, startButton;
    private Insets buttonContainerPadding;
    private GamePlayTimer gameTimer;
    private SpriteManager spriteManager;
    private String paddleCollision;
    private Paddle paddle;
    private Brick brick;
    private Image brickImage, brickImageRed, brickImageOrange, brickImageYellow, brickImageGreen;
    private Ball ball;
    private Image ballImage;
    private ArrayList<Brick> brickGrid;
    public Controller controller;

    @Override
    public void start(Stage primaryStage) {
        //Stage and Scene Setup
        primaryStage.setTitle("BREAKOUT");
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);
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
        brickImageRed = new Image("/brick_red.png", WIDTH/10, HEIGHT/24, false, false, true);
        brickImageOrange = new Image("/brick_orange.png", WIDTH/10, HEIGHT/24, false, false, true);
        brickImageYellow = new Image("/brick_yellow.png", WIDTH/10, HEIGHT/24, false, false, true);
        brickImageGreen = new Image("/brick_green.png", WIDTH/10, HEIGHT/24, false, false, true);
        ballImage = new Image("/ball.png", 200/12, 200/12, true, false, true);
        playBackgroundImage = new Image("/background_play.png", WIDTH, HEIGHT, false, false, true);

    }

    private void createGameObjects(){
        //TODO .4 als CONST anlegen
        paddle = new Paddle(this, "M5,0H394C399,0,400,2,400,6V46c0,4-2,5-4,5H7c-7,0-7-4-7-7V6C0,2,1,0,4,0Z", 0, HEIGHT*0.4, paddleImage);
        ball = new Ball(this, "M67,0c99,2,94,140,2,141C-22,142-23,1,67,0Z", 0, HEIGHT*0.1, ballImage);
   }

    //creates bricks which must be destroyed in the game
    private void createBrickGrid(){
        brickGrid = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
               if(j <= 1) brickImage = brickImageRed;
               else if(j >= 2 &&  j < 4) brickImage = brickImageOrange;
               else if(j >= 4 && j < 6) brickImage = brickImageYellow;
               else brickImage = brickImageGreen;
               brick = new Brick(this, "M.5,3.91V28.66c0,3.75,1.37,4.62,4.62,4.62H84c2.25,0,3.44-.75,3.44-3.44s-.08-22.06,0-26.19C87.5.45,88.07.5,84.29.5H3.5C.25.5.5,3.91.5,3.91Z", 0, 0, brickImage);
               brick.spriteImage.setTranslateX(-WIDTH/2+i*(brickImage.getRequestedWidth()+0.1)+(brickImage.getRequestedWidth()/2)+0.1);
               brick.spriteImage.setTranslateY(-HEIGHT/2+j*(brickImage.getRequestedHeight()+1)+(brickImage.getRequestedHeight()/2+1));
               root.getChildren().add(brick.spriteImage);
               brickGrid.add(brick);
             }
        }
    }

    private void addGameObjectsNodes(){
        //TODO uncomment this to see Paddle
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

    //creates UI buttons, event handler and layouts the buttons
    private void createGUINodes(){
        buttonContainerPadding = new Insets(0, 0, 12, 20);

        masterButtonContainer = new VBox(12);
        masterButtonContainer.setAlignment(Pos.BOTTOM_LEFT);

        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainer.setPadding(buttonContainerPadding);

        startButtonContainer = new HBox(12);
        startButtonContainer.setPrefHeight(HEIGHT/2);
        startButtonContainer.setAlignment(Pos.TOP_CENTER);
        startButtonContainer.setPadding(buttonContainerPadding);

        playButton = new Button();
        playButton.setPrefWidth(100);
        playButton.setText("PLAY");
        playButton.setOnAction(e -> {
            backgroundLayer.setVisible(false);
            menueOverlay.setVisible(false);
            playBackground.setVisible(true);
            playBackground.toBack();
            if (playButton.getText().equals("PLAY")){
                startButton.setVisible(true);
                startButton.setDisable(false);
            }
            else if (playButton.getText().equals("PAUSE")) {
                startButton.setVisible(false);
                gameTimer.stop();
                playButton.setText("RESUME");
                highscoreButton.setVisible(true);
                highscoreButton.setDisable(false);
                helpButton.setVisible(true);
                helpButton.setDisable(false);
                creditsButton.setVisible(true);
                creditsButton.setDisable(false);
            }
            else if (playButton.getText().equals("RESUME")) {
                startButton.setVisible(false);
                gameTimer.start();
                playButton.setText("PAUSE");
                highscoreButton.setVisible(false);
                highscoreButton.setDisable(true);
                helpButton.setVisible(false);
                helpButton.setDisable(true);
                creditsButton.setVisible(false);
                creditsButton.setDisable(true);
            }
        });
        highscoreButton = new Button();
        highscoreButton.setText("HIGH SCORES");
        highscoreButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(highscoreImage);
        });
        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        helpButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(helpImage);
        });
        creditsButton = new Button();
        creditsButton.setText("CREDITS");
        creditsButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menueOverlay.setVisible(true);
            menueOverlay.setImage(creditsImage);
        });
        startButton = new Button();
        startButton.setPrefSize(100, 100);
        startButton.setText("START");
        startButton.setVisible(false);
        startButton.setDisable(true);
        startButton.setOnAction(e -> {
            startButton.setVisible(false);
            startButton.setDisable(true);
            gameTimer.start();
            highscoreButton.setVisible(false);
            highscoreButton.setDisable(true);
            helpButton.setVisible(false);
            helpButton.setDisable(true);
            creditsButton.setVisible(false);
            creditsButton.setDisable(true);
            playButton.setText("PAUSE");
        });

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
        root.getChildren().add(playBackground);
        root.getChildren().add(backgroundLayer);
        root.getChildren().add(menueOverlay);
        root.getChildren().add(masterButtonContainer);
    }

    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer(this);
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
     Ball getBall() {
     return ball;
    }
}
