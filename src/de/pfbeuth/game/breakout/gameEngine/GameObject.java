package de.pfbeuth.game.breakout.gameEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

/** fixed Sprites */
public abstract class GameObject {
    public ImageView spriteImage;
    protected SVGPath spriteCollisionBound;
    protected double positionX, positionY, pivotX, pivotY;
    /** ------ CONSTRUCTOR ------ */
    GameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        spriteCollisionBound = new SVGPath();
        spriteCollisionBound.setContent(SVGdata);
        spriteImage = new ImageView(sprites[0]);
        positionX = xLocation;
        positionY = yLocation;
        pivotX = pivotY = 0.0d;
    }
    abstract void update();
    /** ------ GETTER ------ */
    public ImageView getSpriteImage() {
        return spriteImage;
    }
    public SVGPath getSpriteCollisionBound() {
        return spriteCollisionBound;
    }
    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }
    public double getPivotX() {
        return pivotX;
    }
    public double getPivotY() {
        return pivotY;
    }
    /** ------ SETTER ------ */
    public void setSpriteImage(ImageView spriteImage) {
        this.spriteImage = spriteImage;
    }
    public void setSpriteCollisionBound(SVGPath spriteCollisionBound) {
        this.spriteCollisionBound = spriteCollisionBound;
    }
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    public void setPivotX(double pivotX) {
        this.pivotX = pivotX;
    }
    public void setPivotY(double pivotY) {
        this.pivotY = pivotY;
    }
}
