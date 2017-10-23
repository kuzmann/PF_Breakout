/**
 * Created by Tommy on 22.10.2017.
 */

import javafx.application.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class Breakout extends Application  {

    static Brick brickSceneSetup = new Brick();
    final static BorderPane root = new BorderPane();
    final static Scene scene = new Scene(root, brickSceneSetup.getWidth()*10+9*5, brickSceneSetup.getWidth()*10+9*5);


    @Override
    public void start(Stage primaryStage) throws Exception {
        Paddle paddle = Paddle.getInstance();
        Ball ball = Ball.getInstance();
        BrickGrid brickGrid = new BrickGrid();

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
