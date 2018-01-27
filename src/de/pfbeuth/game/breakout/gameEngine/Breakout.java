package de.pfbeuth.game.breakout.gameEngine;
import de.pfbeuth.game.breakout.gamelogic.Level;

/**
 * This is the main class and implements the game "Breakout".
 * This program is part of the Computer Science and Media Bachelor-Module
 * "Patterns and Frameworks" of the Beuth University Berlin.
 * This is the main class of the game and creates, initialize and controls the game.
 *
 * @version 1.0
 * @author Thomas Glaesser | Isirafil Gülap | Anna Kuzmann | Jan Jasper Wagner
 */

import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import de.pfbeuth.game.breakout.controller.Controller;
import de.pfbeuth.game.breakout.gamelogic.Life;

 public class Breakout extends Application {
     /* Width and height of scene in pixels */
    static final double WIDTH = 540, HEIGHT = 675;
    private StackPane root;
    private Scene scene;
    private GamePlayTimer gameTimer;
    private Controller controller;
    private SpriteManager spriteManager;
    private Paddle paddle;
    private Ball ball;
    private BrickGrid brickGrid;
    private Image paddleImage, brickImage, brickImageRed, brickImageOrange,
                  brickImageYellow, brickImageGreen, ballImage;
    private GUI guiNodes;
    private GameStates gameOver;
    private Level level;
    private Life life;
    private ScoreCounter scoreCounter;

	/** create stage, scene and initialize all objects*/
    @Override
    public void start(Stage primaryStage) {
        /* Stage and Scene Setup */
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setTitle("BREAKOUT");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
        /* Create class instances */
        brickGrid = new BrickGrid(this);
        level = new Level(this);
        life = new Life();
        gameOver = new GameStates(this);
        scoreCounter = new ScoreCounter(this);
        guiNodes = new GUI(this);
        controller = new Controller(this);
        controller.createSceneEventHandling();
        spriteManager = new SpriteManager();
        /* method calls */
        loadImageAssets();
        createGameObjects();
        addObjectsToSpriteManager();
        addGameObjectsNodesToScene();
        addGuiNodesToScene();
        createStartGamePlayTimer();
        guiNodes.createHighScoreScreen();
    }

    /** main method calls launch() */
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
        paddle = new Paddle(this, "M0,0V154H103V0Z", 0, 0, paddleImage);
        paddle.resetState();
        ball = new Ball(this, "M98,0C45,0,0,45,0,98S45,196,98,196s97.5-42,97.5-97.5S151.33,0,98,0Z", 0, 0, ballImage);
        ball.resetState();
        ball.resetVelocity();
    }
    private void addObjectsToSpriteManager(){
        spriteManager.addCurrentObjects(paddle);
        spriteManager.addCurrentObjects(ball);
    }
    private void addGameObjectsNodesToScene(){
        brickGrid.createLevelOneGrid();
        root.getChildren().add(paddle.spriteImage);
        root.getChildren().add(ball.spriteImage);
    }
    private void addGuiNodesToScene(){
        root.getChildren().add(guiNodes.getInfoContainer());
        root.getChildren().add(guiNodes.getPlayBackground());
        root.getChildren().add(guiNodes.getBackgroundLayer());
        root.getChildren().add(guiNodes.getHighscoreContainer());
        root.getChildren().add(guiNodes.getHelpContainer());
        root.getChildren().add(guiNodes.getMenueOverlay());
        root.getChildren().add(guiNodes.getGameOverInfo());
        root.getChildren().add(guiNodes.getMasterButtonContainer());
    }
    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer(this);
    }

    /** ------ SETTER ------ */
    /** @param brickImage: sets the Image object */
    void setBrickImage(Image brickImage){
        this.brickImage = brickImage;
    }
    /** ------ GETTER ------ */
    /** @return brickGrid object */
    BrickGrid getBrickGrid(){
		 return brickGrid;
	}
	/** @return Stackpane object */
    StackPane getRoot() {
        return root;
    }
	/** @return brickImage object */
    Image getBrickImage() {
        return brickImage;
    }
	/** @return green brickImage */
	Image getBrickImageGreen(){
        return brickImageGreen;
    }
	/** @return red brickImage */
    Image getBrickImageRed() {
        return brickImageRed;
    }
	/** @return orange brickImage */
	Image getBrickImageOrange() {
        return brickImageOrange;
    }
	/** @return yellow brickImage */
	Image getBrickImageYellow() {
        return brickImageYellow;
    }
	/** @return controller object */
    Controller getController(){
        return controller;
    }
	/** @return gameTimer object */
	GamePlayTimer getGameTimer(){
        return gameTimer;
    }
	/** @return level object */
    Level getLevel(){
       return level;
    }
	/** @return spriterManager object*/
	SpriteManager getSpriteManager() {
       return spriteManager;
    }
	/** @return paddle object */
	Paddle getPaddle() {
        return paddle;
    }
	/** @return ball object */
  	public Ball getBall() {
        return ball;
    }
	/** @return guiNodes object */
	public GUI getGuiNodes() {
        return guiNodes;
    }
	/** @return life object */
    public Life getLife(){
        return life;
    }
	/** @return scene object */
	public Scene getScene() {
        return scene;
    }
	/** @return gameOver object */
	public GameStates getGameStates(){
        return gameOver;
    }
	/** @return scoreCounter object */
	public ScoreCounter getScoreCounter(){
        return scoreCounter;
    }
 }