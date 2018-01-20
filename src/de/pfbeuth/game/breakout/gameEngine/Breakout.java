 /**
 * authors Thomas Glaesser | Isirafil Gülap | Anna Kuzmann | Jan Jasper Wagner
 * */

package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.gamelogic.Level;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import de.pfbeuth.game.breakout.controller.Controller;
import de.pfbeuth.game.breakout.gamelogic.Life;

 public class Breakout extends Application  {
    static final double WIDTH = 540, HEIGHT = 675;
    private StackPane root;
    private Scene scene;
    private Controller controller;
    private ArrayList<Brick> brickGridList;
    private GamePlayTimer gameTimer;

    private SpriteManager spriteManager;
    private Brick brick;
    private Paddle paddle;
    private Ball ball;
    private BrickGrid brickGrid;

    private Image paddleImage, brickImage, brickImageRed, brickImageOrange, brickImageYellow, brickImageGreen, ballImage;
    private GUI guiNodes;

    private GameOver gameOver;
    private Level level;
    private Life life;
    private ScoreCounter scoreCounter;

    @Override
    public void start(Stage primaryStage) {
        /** Stage and Scene Setup */
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setTitle("BREAKOUT");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        /** Create class instances */
        //guiNodes = new GUI(this);
        brickGrid = new BrickGrid(this);
        level = new Level(this);
        life = new Life(this);
        gameOver = new GameOver(this);
        scoreCounter = new ScoreCounter(this);

        /** method calls */
        guiNodes = new GUI(this);
        controller = new Controller(this);
        controller.createSceneEventHandling();
        loadImageAssets();
        //guiNodes.loadImageAssets();
        createGameObjects();
        addGameObjectsNodes();
        //guiNodes.createGUINodes();
        addNodesToStackPane();
        createSpriteManager();
        createStartGamePlayTimer();
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
        paddle = new Paddle(this, "M5,0H394C399,0,400,2,400,6V46c0,4-2,5-4,5H7c-7,0-7-4-7-7V6C0,2,1,0,4,0Z", 0, 0, paddleImage);
        paddle.resetState();
        ball = new Ball(this, "M67,0c99,2,94,140,2,141C-22,142-23,1,67,0Z", 0, 0, ballImage);
        ball.resetState();
    }

     /** creates bricks which must be destroyed in the game */
    void createInitBrickGrid() {
         brickGridList = new ArrayList<>();
         // Comment out for testing
         //brickGrid.createLevelOneGrid();
         //uncomment for Testting
         brickGrid.createTestGrid();
    }

    private void addGameObjectsNodes(){
        createInitBrickGrid();

        root.getChildren().add(paddle.spriteImage);
        root.getChildren().add(ball.spriteImage);
    }

    void createSpriteManager(){
        spriteManager = new SpriteManager();
        spriteManager.addCurrentObjects(paddle);
        spriteManager.addCurrentObjects(ball);
        for (Brick aBrickGrid : brickGridList) {
            brick = aBrickGrid;
            spriteManager.addCurrentObjects(brick);
            }
    }

    /** First View of the GAME*/
    private void addNodesToStackPane(){
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

    /** SETTER */
    public void setBrickImage(Image brickImage){
        this.brickImage = brickImage;
    }
    /** GETTER */
    public ArrayList<Brick> getBrickGridList() {
        return brickGridList;
    }
    public StackPane getRoot() {
        return root;
    }
    public Ball getBall() {
        return ball;
    }
    public GUI getGuiNodes() {
        return guiNodes;
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
    public Controller getController(){
         return controller;
    }
    public GamePlayTimer getGameTimer(){
        return gameTimer;
    }
    public Level getLevel(){
         return level;
    }
    public Life getLife(){
        return life;
    }
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
    public Scene getScene() {
        return scene;
    }
    public GameOver getGameOver(){
        return gameOver;
    }
    public Paddle getPaddle() {
        return paddle;
    }
    public Image getPaddleImage() {
        return paddleImage;
    }
    public ScoreCounter getScoreCounter(){
    	return scoreCounter;
	}
 }
