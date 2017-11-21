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

public class Breakout extends Application  {
    private static final double WIDTH = 640, HEIGHT = 360;
    static boolean left, right;
    static StackPane root;
    static HBox buttonContainer;
    static Paddle paddle;
    private Scene scene;
    private Image backgroundImage, helpImage, creditsImage, highscoreImage;
    private Image paddleImage;
    private ImageView backgroundLayer, menuesOverlay;
    private Button playButton, helpButton, highscoreButton, creditsButton;
    private Insets buttonContainerPadding;
    private GamePlayTimer gameTimer;
    private SpriteManager spriteManager;
    private String paddleCollision;

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
        backgroundImage = new Image("/background.png", 640, 360, true, false, true);
        helpImage = new Image("/help.png", 640, 360, true, false, true);
        creditsImage = new Image("/credits.png", 640, 360, true, false, true);
        highscoreImage = new Image("/highscore.png", 640, 360, true, false, true);
        paddleImage = new Image("/paddle.png", 100, 13, true, false, true);
    }
    private void createGameObjects(){
        paddle = new Paddle("M150 0 L75 500 L225 200 Z", 0, 0, paddleImage);
    }
    private void addGameObjectsNodes(){
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
            menuesOverlay.setVisible(false);
        });
        highscoreButton = new Button();
        highscoreButton.setText("HIGH SCORES");
        highscoreButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menuesOverlay.setVisible(true);
            menuesOverlay.setImage(highscoreImage);
        });
        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        helpButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menuesOverlay.setVisible(true);
            menuesOverlay.setImage(helpImage);
        });
        creditsButton = new Button();
        creditsButton.setText("CREDITS");
        creditsButton.setOnAction(e -> {
            backgroundLayer.setVisible(true);
            menuesOverlay.setVisible(true);
            menuesOverlay.setImage(creditsImage);
        });
        buttonContainer.getChildren().addAll(playButton, highscoreButton, helpButton, creditsButton);
        backgroundLayer = new ImageView();
        backgroundLayer.setImage(backgroundImage);
        menuesOverlay = new ImageView();
        menuesOverlay.setImage(highscoreImage);
    }
    private void createObjectCollisionData(){
        paddleCollision = "";
    }
    private void addNodesToStackPane(){
        root.getChildren().add(backgroundLayer);
        root.getChildren().add(menuesOverlay);
        root.getChildren().add(buttonContainer);
    }
    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer();
        gameTimer.start();
    }
}
