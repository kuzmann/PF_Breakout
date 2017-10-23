import javafx.scene.shape.Circle;
import javafx.scene.paint.*;

/**
 * Created by Tommy on 22.10.2017.
 */
public class Ball extends Circle {

    private static Ball ball = new Ball();

    private Ball(){
        new Circle();
        this.setRadius(12);
        this.setCenterX(Breakout.scene.getWidth()/2);
        this.setCenterY(Breakout.scene.getHeight()/2);
        this.setFill(Color.RED);
        this.setEffect(Graphic_Styles.getLightFX());
    }

    public static Ball getInstance(){
        return ball;
    }
}
