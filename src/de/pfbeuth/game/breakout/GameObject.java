package de.pfbeuth.game.breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//fixed Sprites
public abstract class GameObject {
    protected List<Image> imageStates = new ArrayList<>();
    protected ImageView spriteImage;
    protected SVGPath spriteCollisionBound;
    protected double positionX, positionY, spritePivotX, spritePivotY;
    protected boolean alive, fixed, bonus, value, flipV, flipH;
    GameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        spriteCollisionBound = new SVGPath();
        spriteCollisionBound.setContent(SVGdata);
        spriteImage = new ImageView(sprites[0]);
        imageStates.addAll(Arrays.asList(sprites));
        positionX = xLocation;
        positionY = yLocation;
        spritePivotX = spritePivotY = 0.0d;
        alive = bonus = value = flipV = flipH = false;
        fixed = true;
    }
    abstract void update();
    public List<Image> getImageStates() {
        return imageStates;
    }
    public void setImageStates(List<Image> imageStates) {
        this.imageStates = imageStates;
    }
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
    public double getSpritePivotX() {
        return spritePivotX;
    }
    public void setSpritePivotX(double spritePivotX) {
        this.spritePivotX = spritePivotX;
    }
    public double getSpritePivotY() {
        return spritePivotY;
    }
    public void setSpritePivotY(double spritePivotY) {
        this.spritePivotY = spritePivotY;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public boolean isFixed() {
        return fixed;
    }
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
    public boolean isBonus() {
        return bonus;
    }
    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }
    public boolean isValue() {
        return value;
    }
    public void setValue(boolean value) {
        this.value = value;
    }
    public boolean isFlipV() {
        return flipV;
    }
    public void setFlipV(boolean flipV) {
        this.flipV = flipV;
    }
    public boolean isFlipH() {
        return flipH;
    }
    public void setFlipH(boolean flipH) {
        this.flipH = flipH;
    }
}
