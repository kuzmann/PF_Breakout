package de.pfbeuth.game.breakout;

import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/*

class Ball extends Circle {

    private double xSpeed;
    private double ySpeed;
    private double radius;

    //Singleton Design Pattern
    private static Ball ball = new Ball();

    private Ball(){
        super();
        super.setRadius(12);
        super.setCenterX(320);
        super.setCenterY(320);

        this.xSpeed = Math.random() * 8 + 1;
        this.ySpeed = Math.random() * 8 + 1;
        this.setFill(Color.RED);
        this.setEffect(GraphicStyles.getLightFX());
    }

    static Ball getInstance(){
        return ball;
    }


    void move()
    {
        super.setCenterX(super.getCenterX() + this.xSpeed);
        super.setCenterY(super.getCenterY() + this.ySpeed);

        //Collision Detection Left Scene Border
        if(super.getCenterX() <= this.radius)
        {
            super.setCenterX(this.radius);
            this.xSpeed = -this.xSpeed;
        }

        //Collision Detection Right Scene Border
        if(super.getCenterX() >= BreakoutMain.scene.getWidth() - this.radius)
        {
            super.setCenterX(BreakoutMain.scene.getWidth()-this.radius);
            this.xSpeed = -this.xSpeed;
        }

        //Collision Detection Top Scene Border
        if(super.getCenterY() <= this.radius)
        {
            super.setCenterY(this.radius);
            this.ySpeed = -this.ySpeed;
        }

        //Collision Detection Bottom Scene Border
        if(super.getCenterY() >= BreakoutMain.scene.getHeight())
        {
            //TODO
            super.setCenterY(BreakoutMain.scene.getHeight() - this.radius);
            this.ySpeed = -this.ySpeed;
            //restart();
        }

        //Collision Detection with Paddle
        if(BreakoutMain.paddle.intersects(super.getBoundsInLocal())){
            this.xSpeed = -this.xSpeed;
            this.ySpeed = -this.ySpeed;
        }

        boolean brickHit = false;
        for(Brick b : BrickGrid.bricks) {
            if (b.intersects(super.getBoundsInLocal())) {
                this.xSpeed = -this.xSpeed;
                this.ySpeed = -this.ySpeed;
                b.destroyBrick();
                BrickGrid.bricks.remove(b);
                brickHit = true;

            }
         if (brickHit){
             AudioClip destroySound = new AudioClip("assets/Quick_Impact_07.wav");
             destroySound.play();
         }

        }
     }
}
*/