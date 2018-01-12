package de.pfbeuth.game.breakout.gameEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//fixed Sprites
public abstract class GameObject {
    protected ImageView spriteImage;
    protected SVGPath spriteCollisionBound;
    protected double positionX, positionY, pivotX, pivotY;

    GameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        spriteCollisionBound = new SVGPath();
        spriteCollisionBound.setContent(SVGdata);
        spriteImage = new ImageView(sprites[0]);
        positionX = xLocation;
        positionY = yLocation;
        pivotX = pivotY = 0.0d;
    }

    abstract void update();

    public ImageView getSpriteImage() {
        return spriteImage;
    }
    public void setSpriteImage(ImageView spriteImage) {
        this.spriteImage = spriteImage;
    }
    public SVGPath getSpriteCollisionBound() {
        return spriteCollisionBound;
    }
    public void setSpriteCollisionBound(SVGPath spriteCollisionBound) {
        this.spriteCollisionBound = spriteCollisionBound;
    }
    public double getPositionX() {
        return positionX;
    }
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    public double getPositionY() {
        return positionY;
    }
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    public double getPivotX() {
        return pivotX;
    }
    public void setPivotX(double pivotX) {
        this.pivotX = pivotX;
    }
    public double getPivotY() {
        return pivotY;
    }
    public void setPivotY(double pivotY) {
        this.pivotY = pivotY;
    }

}
