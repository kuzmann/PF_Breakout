import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.*;
import javafx.util.Duration;
/**
 * Created by Tommy on 22.10.2017.
 */
public class Brick extends Rectangle {

    //public Brick(){}
    public Brick() {
        new Rectangle();
        setWidth(75.0d);
        setHeight(20.0d);
        setArcWidth(10.0d);
        setArcHeight(10.0d);
        setFill(Color.BLUE);
        setLayoutX((getWidth() / 2) - getWidth());
        setLayoutY((getHeight() / 2) - getHeight());
        this.setEffect(Graphic_Styles.getLightFX());
    }
    public void destroyBrick(Brick brick) {
        ScaleTransition scaleToZero = new ScaleTransition(Duration.millis(1000), brick);
        scaleToZero.setToX(0.0d);
        scaleToZero.setToY(0.0d);
        scaleToZero.play();
    }
}
