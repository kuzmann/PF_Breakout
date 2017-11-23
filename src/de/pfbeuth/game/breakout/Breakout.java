 /**
 * @author Thomas Glaesser
 * */
package de.pfbeuth.game.breakout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;

 public class Breakout extends Application  {
    static final double WIDTH = 960, HEIGHT = 540;
    private boolean left, right;
    private StackPane root;
    private HBox buttonContainer;
    private Scene scene;
    private Image backgroundImage, helpImage, creditsImage, highscoreImage;
    private Image paddleImage;
    private ImageView backgroundLayer, menueOverlay;
    private Button playButton, helpButton, highscoreButton, creditsButton;
    private Insets buttonContainerPadding;
    private GamePlayTimer gameTimer;



     private SpriteManager spriteManager;
    private String paddleCollision;
    private Paddle paddle;
    Brick brick;
    private Image brickImage;
    ArrayList<Brick> brickGrid;

    @Override
    public void start(Stage primaryStage) {
        //Stage and Scene Setup
        primaryStage.setTitle("BREAKOUT");
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        createSceneEventHandling();
        loadImageAssets();
        createGameObjects();
        addGameObjectsNodes();
        createStartScreenNodes();
        addNodesToStackPane();
        createStartGamePlayTimer();
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void createSceneEventHandling(){
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT: left = true;
                    break;
                case RIGHT: right = true;
                    break;
                case A: left = true;
                    break;
                case D: right = true;
                    break;
                case NUMPAD4: left = true;
                    break;
                case NUMPAD6: right = true;
                    break;
            }
        });
        scene.setOnKeyReleased(e -> {
            switch(e.getCode()) {
                case LEFT: left = false;
                    break;
                case RIGHT: right = false;
                    break;
                case A: left = false;
                    break;
                case D: right = false;
                    break;
                case NUMPAD4: left = false;
                    break;
                case NUMPAD6: right = false;
                    break;
            }
        });
    }
    private void loadImageAssets(){
        backgroundImage = new Image("/background.png", WIDTH, HEIGHT, true, false, true);
        helpImage = new Image("/help.png", WIDTH, HEIGHT, true, false, true);
        creditsImage = new Image("/credits.png", WIDTH, HEIGHT, true, false, true);
        highscoreImage = new Image("/highscore.png", WIDTH, HEIGHT, true, false, true);
        paddleImage = new Image("/paddle.png", 200/2, 25/2, true, false, true);
        brickImage = new Image("/brick.png", 186.5/2, 32.5/2, true, false, true);
    }
    private void createGameObjects(){
        paddle = new Paddle(this, "M150 0 L75 500 L225 200 Z", 0, HEIGHT/3, paddleImage);
        createBrickGrid();
   }
    private void addGameObjectsNodes(){
        //root.getChildren().add(brick.spriteImage);
        //brickGrid();
        root.getChildren().add(paddle.spriteImage);
    }
    private void createSpriteManager(){
        spriteManager = new SpriteManager();
        spriteManager.addCurrentObjects(paddle);

    }
    private void createStartScreenNodes(){
        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainerPadding = new Insets(0, 0, 12, 20);
        buttonContainer.setPadding(buttonContainerPadding);
        playButton = new Button();
        playButton.setText("PLAY");
        playButton.setOnAction(e -> {
            backgroundLayer.setVisible(false);
            menueOverlay.setVisible(false);
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
        buttonContainer.getChildren().addAll(playButton, highscoreButton, helpButton, creditsButton);
        backgroundLayer = new ImageView();
        backgroundLayer.setImage(backgroundImage);
        menueOverlay = new ImageView();
        menueOverlay.setImage(highscoreImage);
    }
    private void createObjectCollisionData(){
        paddleCollision = "";
    }
    private void addNodesToStackPane(){
        root.getChildren().add(backgroundLayer);
        root.getChildren().add(menueOverlay);
        root.getChildren().add(buttonContainer);
    }
    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer(this);
        gameTimer.start();
    }
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
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
     public SpriteManager getSpriteManager() {
         return spriteManager;
     }

    public void createBrickGrid(){
        brickGrid = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                brick = new Brick(this, "M5,0H394C399,0,400,2,400,6V46c0,4-2,5-4,5H7c-7,0-7-4-7-7V6C0,2,1,0,4,0Z", 0, 0, brickImage);
                brick.spriteImage.setTranslateX(-WIDTH/2+i*(brickImage.getRequestedWidth()+5/2)+(brickImage.getRequestedWidth()/2)+5/2);
                brick.spriteImage.setTranslateY(-HEIGHT/2+j*(brickImage.getRequestedHeight()+5/2)+(brickImage.getRequestedHeight()/2+5/2));
                root.getChildren().add(brick.spriteImage);
                spriteManager.addCurrentObjects(brick);
                //brickGrid.add(brick);
            }
        }
    }



}
