package pf.game.breakout;

import javafx.scene.shape.Rectangle;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

class Brick extends Rectangle {

    Brick() {
        super();
        super.setWidth(BreakoutMain.SCENE.getWidth()/11);
        super.setHeight((BreakoutMain.SCENE.getHeight()*1/3)/8);
        super.setArcWidth(10.0d);
        super.setArcHeight(10.0d);
       // super.setLayoutX((getWidth() / 2) - getWidth());
       // super.setLayoutY((getHeight() / 2) - getHeight());
        this.setEffect(GraphicStyles.getLightFX());
    }

    void destroyBrick() {
        ScaleTransition scaleToZero = new ScaleTransition(Duration.millis(1000), this);
        scaleToZero.setToX(0.0d);
        scaleToZero.setToY(0.0d);
        scaleToZero.play();
    }
}
