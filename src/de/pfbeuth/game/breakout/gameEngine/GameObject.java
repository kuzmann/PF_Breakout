package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

/**
 * This class defines non animated game objects.
 */

abstract class GameObject {
    ImageView spriteImage;
    private SVGPath spriteCollisionBound;
    private double positionX, positionY, pivotX, pivotY;
    /** ------ CONSTRUCTOR ------ */
    GameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        spriteCollisionBound = new SVGPath();
        spriteCollisionBound.setContent(SVGdata);
        spriteImage = new ImageView(sprites[0]);
        positionX = xLocation;
        positionY = yLocation;
        pivotX = pivotY = 0.0d;
    }

    /* ------ GETTER ------ */
    /** @return spriteImage object */
    ImageView getSpriteImage() {
        return spriteImage;
    }
    /** @return spriteCollisionBound object */
    SVGPath getSpriteCollisionBound() {
        return spriteCollisionBound;
    }
    /** @return positionX variable */
    double getPositionX() {
        return positionX;
    }
    /** @return positionY variable */
    double getPositionY() {
        return positionY;
    }
    /* ------ SETTER ------ */
    /** @param positionX: sets the X-position of the sprite*/
    void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    /** @param positionY: sets the Y-position of the sprite*/
    void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}
