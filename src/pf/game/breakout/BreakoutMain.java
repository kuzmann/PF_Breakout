package pf.game.breakout;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

class BreakoutMain extends Application {

    final static BorderPane root = new BorderPane();
    final static Scene scene = new Scene(root, 640, 640);

    @Override
    public void start(Stage primaryStage) {
        Paddle paddle = Paddle.getInstance();
        Ball ball = Ball.getInstance();
        new BrickGrid();

        scene.setFill(Color.DARKGRAY);
        root.getChildren().addAll(paddle, ball);
        primaryStage.setTitle("BREAKOUT");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
