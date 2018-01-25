package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.image.Image;

/**
 * This class define new object "animatedGameObeject", which inherits from the class GameObject
 * ball and paddle are this kind of object
 *
 */
public abstract class AnimatedGameObject extends GameObject {
    protected double velocityX, velocityY;
    /** ------ CONSTRUCTOR ------ */
    AnimatedGameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        velocityX = velocityY = 0;
    }

    //TODO: Macht diese Methode etwas ? Prüfen, und wenn nicht löschen
    @Override
    void update(){
    }
    /** not abstract because not every animated object needs collision detection */
    boolean collision(GameObject object){
        return false;
    }

    /** ------ GETTER ------ */
    public double getVelocityX() {
        return velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }
    /** ------ SETTER ------ */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}
