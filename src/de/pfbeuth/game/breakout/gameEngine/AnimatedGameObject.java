package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.image.Image;

/**
 * This abstract class defines the animated game objects in the scene,
 * it inherits from the class GameObject.
 * Ball and Paddle inherit from this class.
 */

public abstract class AnimatedGameObject extends GameObject {
    double velocityX, velocityY;
    /** ------ CONSTRUCTOR ------ */
    AnimatedGameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        velocityX = velocityY = 0;
    }

    abstract void update();

    /** not abstract because not every animated object needs collision detection */
    boolean collision(GameObject object){
        return false;
    }
    /* ------ GETTER ------ */
    /** @return velocityX variable */
    public double getVelocityX() {
        return velocityX;
    }
    /** @return velocityY variable */
    public double getVelocityY() {
        return velocityY;
    }
    /** ------ SETTER ------ */
    /** @param velocityX sets the velocityX value */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    /** @param velocityY sets the velocityY value */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}