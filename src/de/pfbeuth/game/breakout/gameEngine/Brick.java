package de.pfbeuth.game.breakout.gameEngine;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

class Brick extends GameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    private double leftBorder, rightBorder, topBorder, bottomBorder;

    Brick (Breakout breakout, String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        spriteImage.setTranslateX(xLocation);
        spriteImage.setTranslateY(yLocation);
        this.breakout = breakout;
    }

    @Override
    public void update(){
    }

    public void destroyBrick() {
        ScaleTransition scaleToZero = new ScaleTransition(Duration.millis(500), this.getSpriteImage());
        scaleToZero.setToX(0.0d);
        scaleToZero.setToY(0.0d);
        scaleToZero.play();

    }
}

