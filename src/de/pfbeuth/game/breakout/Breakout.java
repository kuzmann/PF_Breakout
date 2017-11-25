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
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
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
    private Brick brick;
    private Image brickImage;
    private Ball ball;
    private Image ballImage;
    private ArrayList<Brick> brickGrid;

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
        createSpriteManager();
        createStartGamePlayTimer();
    }
    public static void main(String[] args) {
        launch(args);
    }

    //eventhandling for gameobjects contro
    private void createSceneEventHandling(){
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT: left = true;
                    break;
                case RIGHT: right = true;
                    break;
                case UP: up = true;
                    break;
                case DOWN: down = true;
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
                case UP: up = false;
                    break;
                case DOWN: down = false;
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
        paddleImage = new Image("/paddle.png", 200, 25, true, false, true);
        brickImage = new Image("/brick.png", WIDTH/10, HEIGHT/24, true, false, true);
        ballImage = new Image("/ball.png", 200/8, 200/8, true, false, true);
    }
    private void createGameObjects(){
        paddle = new Paddle(this, "M5,0H394C399,0,400,2,400,6V46c0,4-2,5-4,5H7c-7,0-7-4-7-7V6C0,2,1,0,4,0Z", 0, HEIGHT*.4, paddleImage);
        ball = new Ball(this, "M67,0c99,2,94,140,2,141C-22,142-23,1,67,0Z", 0,0, ballImage);
        createBrickGrid();
   }
    //creates bricks which must be destroyed in the game
    private void createBrickGrid(){
        brickGrid = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
               brick = new Brick(this, "M.5,3.91V28.66c0,3.75,1.37,4.62,4.62,4.62H84c2.25,0,3.44-.75,3.44-3.44s-.08-22.06,0-26.19C87.5.45,88.07.5,84.29.5H3.5C.25.5.5,3.91.5,3.91Z", 0, 0, brickImage);
               brick.spriteImage.setTranslateX(-WIDTH/2+i*(brickImage.getRequestedWidth()+1)+(brickImage.getRequestedWidth()/2)+1);
               brick.spriteImage.setTranslateY(-HEIGHT/2+j*(brickImage.getRequestedHeight()+1)+(brickImage.getRequestedHeight()/2+1));
               root.getChildren().add(brick.spriteImage);
               brickGrid.add(brick);
             }
        }
    }
    private void addGameObjectsNodes(){
        //TODO uncomment this to see Paddle
        //root.getChildren().add(paddle.spriteImage);
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
    private void addNodesToStackPane(){
        root.getChildren().add(backgroundLayer);
        root.getChildren().add(menueOverlay);
        root.getChildren().add(buttonContainer);
    }
    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer(this);
        gameTimer.start();
    }

    //GETTER and SETTER
     boolean isUp() {
     return up;
    }
     boolean isDown() {
     return down;
    }
     boolean isLeft() {
    return left;
    }
     boolean isRight() {
    return right;
    }
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
