package pf.game.breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Paddle extends Rectangle {

    //Singleton Design Pattern
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
        //this.setEffect(GraphicStyles.getLightFX());
        this.setEffect(GraphicStyles.getShadowFX());
        //this.setEffect(GraphicStyles.getReflFX());
    }
    static Paddle getInstance(){
        return paddle;
    }


}
