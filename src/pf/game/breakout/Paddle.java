package pf.game.breakout;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;



class Paddle extends Rectangle implements EventHandler <KeyEvent> {

    //Singleton Design Pattern
    private static Paddle paddle = new Paddle();

    private  Paddle(){
        super();
        super.setWidth(100);
        super.setHeight(20);
        //super.setArcWidth(40d);
        //super.setArcHeight(40d);
        super.setFill(Color.GREY);
        super.setLayoutX((getWidth() / 2) - getWidth());
        this.setLayoutY((getHeight() / 2) - getHeight());
        this.setX(BreakoutMain.SCENE.getWidth()/2);
        this.setY(BreakoutMain.SCENE.getHeight()*0.9);
        //this.setEffect(GraphicStyles.getLightFX());
        this.setEffect(GraphicStyles.getShadowFX());
        //this.setEffect(GraphicStyles.getReflFX());
    }
    static Paddle getInstance(){
        return paddle;
    }


    @Override
    public void handle(KeyEvent event) {
        if((event.getCode() == KeyCode.A || event.getCode() == KeyCode.KP_LEFT) )
            paddleAnimationLeft();

    }

    public void paddleAnimationLeft(){
        TranslateTransition transform = new TranslateTransition(Duration.millis(2000), paddle);

        transform.setInterpolator(Interpolator.LINEAR);
        transform.setFromX(0);
        transform.setToX((BreakoutMain.SCENE.getWidth()/2-this.getWidth()/2)*(-1.0));
        transform.play();
    }

    public void paddleAnimationRight(){
        TranslateTransition transform = new TranslateTransition(Duration.millis(2000), paddle);

        transform.setInterpolator(Interpolator.LINEAR);
        transform.setFromX(0);
        transform.setToX(BreakoutMain.SCENE.getWidth()/2-this.getWidth()/2);
        transform.play();

    }
}
