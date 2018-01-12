package de.pfbeuth.game.breakout.gameEngine;

import javafx.scene.image.Image;

//motion sprites
public abstract class AnimatedGameObject extends GameObject {
    protected double velocityX, velocityY;
    protected double offsetX, offsetY;
    protected double friction, gravity, bounce;

    AnimatedGameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        velocityX = velocityY =  offsetX = offsetY = 0;
        friction = gravity = bounce = 0;
    }
    @Override
    void update(){
    }
    //not abstracted because not every animated object needs collision detection
    boolean collision(GameObject object){
        return false;
    }

    public double getVelocityX() {
        return velocityX;
    }
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
    public double getOffsetX() {
        return offsetX;
    }
    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }
    public double getOffsetY() {
        return offsetY;
    }
    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }
    public double getFriction() {
        return friction;
    }
    public void setFriction(double friction) {
        this.friction = friction;
    }
    public double getGravity() {
        return gravity;
    }
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    public double getBounce() {
        return bounce;
    }
    public void setBounce(double bounce) {
        this.bounce = bounce;
    }
}
