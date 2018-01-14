package de.pfbeuth.game.breakout.gameEngine;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

class Brick extends GameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    double leftBorder, rightBorder, topBorder, bottomBorder;

    protected Brick (Breakout iBrick, String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        spriteImage.setTranslateX(xLocation);
        spriteImage.setTranslateY(yLocation);
        breakout = iBrick;
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



    //TODO: Machen wir eine Unterscheidung der Farben. Wenn, ja dann müsste ausgegeben werden, welche Farbe getroffen würde
    /* Method: getCollidingBrick */
    /** returns the GObject (i.e. the brick) that the ball has collided with. */
    /*private GObject getCollidingBrick() {
        double x = ball.getX();
        double y = ball.getY();
        GObject collidingBrick = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (getElementAt(x+(i*(BALL_RADIUS/2)),y+(j*(BALL_RADIUS/2))) != null) {
                    collidingBrick = getElementAt(x,y);
                }
            }
        }
        return collidingBrick;
    }*/


}

