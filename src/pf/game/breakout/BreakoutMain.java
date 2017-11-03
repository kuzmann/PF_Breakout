 /**
 * @author Thomas Glaesser
 * */
package pf.game.breakout;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

 public class BreakoutMain extends Application  {

    private final static BorderPane ROOT = new BorderPane();
    final static Scene SCENE = new Scene(ROOT, 640, 640);

    static BorderPane gameArea = new BorderPane();
    static Paddle paddle = Paddle.getInstance();

    @Override
    public void start(Stage primaryStage) {

        Ball ball = Ball.getInstance();
        new BrickGrid();

        /*paddle.setOnKeyPressed((EventHandler<KeyEvent> keyEvent) -> {
         paddle.setX(5.0);
        })*/
        // Create the button
//          btn = new Button();
//        btn.setText("Click me to start game!");
//        btn.setOnAction(e -> buttonClick());
//
//        VBox player = new VBox();
//        player.getChildren().add(btn);
//        ROOT.setLeft(player);

        gameArea.setPrefWidth(640);
        gameArea.setPrefHeight(640);
        gameArea.setMinWidth(640);
        gameArea.setMinHeight(640);
        gameArea.setMaxWidth(640);
        gameArea.setMaxHeight(640);
        gameArea.getChildren().addAll(paddle, ball);
        ROOT.setCenter(gameArea);
        SCENE.setFill(Color.DARKGRAY);
        //root.getChildren().add(gameArea);

        //Stage Setup
        primaryStage.setTitle("BREAKOUT");
        primaryStage.setScene(SCENE);
        primaryStage.setResizable(false);
        primaryStage.show();

        //Animation Timeline Setup
        KeyFrame k = new KeyFrame(Duration.millis(10),
                e-> ball.move()
                );

        Timeline t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();


        paddle.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) ->{
           // if((e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.KP_LEFT) )
                paddleAnimationLeft();

        } );




    }

    public static void main(String[] args) {
        launch(args);
    }


     public void paddleAnimationLeft(){
         TranslateTransition transform = new TranslateTransition(Duration.millis(2000), paddle);
         transform.setInterpolator(Interpolator.LINEAR);
         transform.setFromX(0);
         transform.setToX((BreakoutMain.SCENE.getWidth()/2-paddle.getWidth()/2)*(-1.0));
         transform.play();
     }
}
