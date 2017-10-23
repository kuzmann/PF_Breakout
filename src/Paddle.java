import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Tommy on 22.10.2017.
 */
public class Paddle extends Rectangle {

    //Singelton Design Pattern
    private static Paddle paddle = new Paddle();

    private  Paddle(){
        new Rectangle();
        this.setWidth(150);
        this.setHeight(20);
        this.setArcWidth(40d);
        this.setArcHeight(40d);
        this.setFill(Color.GREY);
        this.setLayoutX((getWidth() / 2) - getWidth());
        this.setLayoutY((getHeight() / 2) - getHeight());
        this.setX(BreakoutMain.scene.getWidth()/2);
        this.setY(BreakoutMain.scene.getHeight()*0.9);
        //this.setEffect(Graphic_Styles.getLightFX());
        this.setEffect(Graphic_Styles.getShadowFX());
        //this.setEffect(Graphic_Styles.getReflFX());
    }
    public static Paddle getInstance(){
        return paddle;
    }
}
