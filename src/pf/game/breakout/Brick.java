package pf.game.breakout;

import javafx.scene.shape.Rectangle;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

class Brick extends Rectangle {

    Brick() {
        new Rectangle();
        setWidth(BreakoutMain.scene.getWidth()/10.75);
        setHeight((BreakoutMain.scene.getHeight()*1/3)/8);
        setArcWidth(10.0d);
        setArcHeight(10.0d);
        setLayoutX((getWidth() / 2) - getWidth());
        setLayoutY((getHeight() / 2) - getHeight());
        this.setEffect(GraphicStyles.getLightFX());
    }

    public void destroyBrick(Brick brick) {
        ScaleTransition scaleToZero = new ScaleTransition(Duration.millis(1000), brick);
        scaleToZero.setToX(0.0d);
        scaleToZero.setToY(0.0d);
        scaleToZero.play();
    }
}
