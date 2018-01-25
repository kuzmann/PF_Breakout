package de.pfbeuth.game.breakout.gameEngine;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;


/**
 * This class defines and control the object brick
 * this class inherits from the class AnimatedGameObject
 */
class Brick extends GameObject {
    //TODO: Anna, testen und wenn es nicht mehr genutzt wird, löschen
    private Breakout breakout;  //creates context to Breakout-Class
    private double leftBorder, rightBorder, topBorder, bottomBorder;
    /** ------ CONSTRUCTOR ------ */
    Brick (Breakout breakout, String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        spriteImage.setTranslateX(xLocation);
        spriteImage.setTranslateY(yLocation);
        this.breakout = breakout;
    }

    //TODO: Macht diese Methode etwas ? Prüfen, und wenn nicht löschen
    @Override
    public void update(){
    }

    /**
     * it defines what happens if ball hits brick
     * brick scale to zero size
     * */
    public void destroyBrick() {
        ScaleTransition scaleToZero = new ScaleTransition(Duration.millis(400), this.getSpriteImage());
        scaleToZero.setToX(0.0d);
        scaleToZero.setToY(0.0d);
        scaleToZero.play();
    }
}

