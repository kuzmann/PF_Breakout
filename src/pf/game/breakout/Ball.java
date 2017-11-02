package pf.game.breakout;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

class Ball extends Circle {

    //Singleton Design Pattern
    private static Ball ball = new Ball();

    private Ball(){
        new Circle();
        this.setRadius(12);
        this.setCenterX(BreakoutMain.scene.getWidth()/2);
        this.setCenterY(BreakoutMain.scene.getHeight()/2);
        this.setFill(Color.RED);
        this.setEffect(GraphicStyles.getLightFX());
    }

    static Ball getInstance(){
        return ball;
    }
}
