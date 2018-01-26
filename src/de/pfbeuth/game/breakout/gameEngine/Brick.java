package de.pfbeuth.game.breakout.gameEngine;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * This class defines and controls the object brick
 * this class inherits from the class AnimatedGameObject
 */
class Brick extends GameObject {
    /** ------ CONSTRUCTOR ------ */
    Brick (String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        spriteImage.setTranslateX(xLocation);
        spriteImage.setTranslateY(yLocation);
    }
    /** animated scale transition, triggered when ball collides with a brick */
    void destroyBrick() {
        ScaleTransition scaleToZero = new ScaleTransition(Duration.millis(400), this.getSpriteImage());
        scaleToZero.setToX(0.0d);
        scaleToZero.setToY(0.0d);
        scaleToZero.play();
    }
}