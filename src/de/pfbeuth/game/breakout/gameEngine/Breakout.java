 /**
 * @author Thomas Glaesser
 * */
package de.pfbeuth.game.breakout.gameEngine;
import de.pfbeuth.game.breakout.gamelogic.Level;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.Random;

import de.pfbeuth.game.breakout.controller.Controller;

 public class Breakout extends Application  {
    static final double WIDTH = 540, HEIGHT = 675;

    private StackPane root;
     Scene scene;
    public Controller controller;
    //Game Objects
    private GamePlayTimer gameTimer;
    private SpriteManager spriteManager;
    private Paddle paddle;
    private Brick brick;
    private Ball ball;
    private ArrayList<Brick> brickGrid;
    //Images
    private Image paddleImage, brickImage, brickImageRed, brickImageOrange, brickImageYellow, brickImageGreen, ballImage;
    private GUI guiNodes;
    private GameOver gameOver;


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
        gameOver = new GameOver(this);
        guiNodes = new GUI(this);
        controller = new Controller(this);
        controller.createSceneEventHandling();
        loadImageAssets();
        guiNodes.loadImageAssets();
        createGameObjects();
        addGameObjectsNodes();
        guiNodes.createGUINodes();
        addNodesToStackPane();
        createSpriteManager();
        createStartGamePlayTimer();

        //System.out.println("scene width " + scene.getWidth());
        //System.out.println("scene height " + scene.getHeight());

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void loadImageAssets(){
        paddleImage = new Image("/assets/graphics/paddle.png", 100, 25, true, false, true);
        brickImageRed = new Image("/assets/graphics/brick_red.png", WIDTH/10-2, 22, true, false, true);
        brickImageOrange = new Image("/assets/graphics/brick_orange.png", WIDTH/10-2, 22, true, false, true);
        brickImageYellow = new Image("/assets/graphics/brick_yellow.png", WIDTH/10-2, 22, true, false, true);
        brickImageGreen = new Image("/assets/graphics/brick_green.png", WIDTH/10-2, 22, true, false, true);
        ballImage = new Image("/assets/graphics/ball.png", 200/12, 200/12, true, false, true);
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
     void createBrickGrid() {
         brickGrid = new ArrayList<>();
         for (int i = 0; i < 10; i++) {
             for (int j = 0; j < 8; j++) {
                 if (j <= 1) brickImage = brickImageRed;
                 else if (j >= 2 && j < 4) brickImage = brickImageOrange;
                 else if (j >= 4 && j < 6) brickImage = brickImageYellow;
                 else brickImage = brickImageGreen;
                 brick = new Brick(this, "M.5,3.91V28.66c0,3.75,1.37,4.62,4.62,4.62H84c2.25,0,3.44-.75,3.44-3.44s-.08-22.06,0-26.19C87.5.45,88.07.5,84.29.5H3.5C.25.5.5,3.91.5,3.91Z", 0, 0, brickImage);
                 brick.spriteImage.setTranslateX(-WIDTH / 2 + i * (brickImage.getRequestedWidth() + 2) + (brickImage.getRequestedWidth() / 2) + 1);
                 brick.spriteImage.setTranslateY(-HEIGHT / 2 + j * (brickImage.getRequestedHeight() + 2.5) + (brickImage.getRequestedHeight() / 2 + 50));
                    switch (Level.getLevel()) {
                        case 1:
                            root.getChildren().add(brick.spriteImage);
                        break;
                        case 2:
                            if((i % 2 == 0) && (j % 2 == 0) || (i % 2 != 0) && (j % 2 != 0)) {
                                root.getChildren().add(brick.spriteImage);
                            }
                        break;
                        case 3:
                            Random rand = new Random();
                            int r = rand.nextInt(10);
                            if (r <= 5) {
                                root.getChildren().add(brick.spriteImage);
                            }
                        break;
                        case 4:
                            if(i<j) {
                                root.getChildren().add(brick.spriteImage);
                            }
                        break;
                        case 5:
                            if(i>j) {
                                root.getChildren().add(brick.spriteImage);
                            }
                        break;
                    }
                 brickGrid.add(brick);
             }
         }
    }

    void addGameObjectsNodes(){
        createBrickGrid();
        root.getChildren().add(paddle.spriteImage);
        root.getChildren().add(ball.spriteImage);
    }

    void createSpriteManager(){
        spriteManager = new SpriteManager();
        spriteManager.addCurrentObjects(paddle);
        spriteManager.addCurrentObjects(ball);
        for (Brick aBrickGrid : brickGrid) {
            brick = aBrickGrid;
            spriteManager.addCurrentObjects(brick);
            }
    }

    /** First View of the GAME*/
    void addNodesToStackPane(){
        root.getChildren().add(guiNodes.getInfoContainer());
        root.getChildren().add(guiNodes.getPlayBackground());
        root.getChildren().add(guiNodes.getBackgroundLayer());
        root.getChildren().add(guiNodes.getHighscoreContainer());
        root.getChildren().add(guiNodes.getIntroductionContainer());
        root.getChildren().add(guiNodes.getCreditContainer());
        root.getChildren().add(guiNodes.getMenueOverlay());
        root.getChildren().add(guiNodes.getMasterButtonContainer());

    }

    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer(this);
    }

    public GUI getGuiNodes() {
        return guiNodes;
    }

    /** GETTER and SETTER */
    public StackPane getRoot() {
        return root;
    }
    public Paddle getPaddle() {
        return paddle;
    }
    public Image getPaddleImage() {
        return paddleImage;
    }
    public Image getBrickImage() {
         return brickImage;
    }
    public Image getBrickImageGreen(){
        return brickImageGreen;
    }
    public Image getBrickImageRed() {
        return brickImageRed;
    }
    public Image getBrickImageOrange() {
        return brickImageOrange;
    }
    public Image getBrickImageYellow() {
        return brickImageYellow;
    }
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
    public GamePlayTimer getGameTimer(){
        return gameTimer;
    }
    public Ball getBall() {
         return ball;
    }
    public Scene getScene() {
         return scene;
     }
    public GameOver getGameOver(){
        return gameOver;
    }

 }
