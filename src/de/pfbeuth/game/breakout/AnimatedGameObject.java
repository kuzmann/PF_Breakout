package de.pfbeuth.game.breakout;

import javafx.scene.image.Image;

//motion sprites
public abstract class AnimatedGameObject extends GameObject {
    protected double velocityX, velocityY;
    protected double lifeSpan = 1000;
    protected double damage, offsetX, offsetY;
    protected double boundScale, boundRot, friction, gravity, bounce;

    AnimatedGameObject(String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        lifeSpan = 1000;
        velocityX = velocityY = damage = offsetX = offsetY = 0;
        boundScale = boundRot = friction = gravity = bounce = 0;
    }
    @Override
    public void update(){
    }
    public boolean collision(GameObject object){
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
    public double getLifeSpan() {
        return lifeSpan;
    }
    public void setLifeSpan(double lifeSpan) {
        this.lifeSpan = lifeSpan;
    }
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
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
    public double getBoundScale() {
        return boundScale;
    }
    public void setBoundScale(double boundScale) {
        this.boundScale = boundScale;
    }
    public double getBoundRot() {
        return boundRot;
    }
    public void setBoundRot(double boundRot) {
        this.boundRot = boundRot;
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
