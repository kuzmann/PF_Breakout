package de.pfbeuth.game.breakout;

import javafx.scene.image.Image;
import static de.pfbeuth.game.breakout.Breakout.WIDTH;
import static de.pfbeuth.game.breakout.Breakout.HEIGHT;

class Paddle extends AnimatedGameObject {

    protected Breakout breakout;
    protected static final double PADDLE_DIM_X = 200/2;
    protected static final double PADDLE_DIM_Y = 12.5;
    protected static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - PADDLE_DIM_X/2;
    protected static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - PADDLE_DIM_X/2);
    protected static final double BOTTOM_SCREEN_BOUNDARY = HEIGHT/2 - PADDLE_DIM_Y/2;
    protected static final double TOP_SCREEN_BOUNDARY = -(HEIGHT/2 - PADDLE_DIM_Y/2);



    public Paddle (Breakout iPaddle, String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iPaddle;
    }

 //   static Paddle getInstance(){
 //       return paddle;    }

    @Override
    public void update() {
        setXYPosition(10);
        setScreenBoundaries();
        translatePaddle();


    }
    private void setXYPosition(double velocityX){
        this.velocityX = velocityX;
        if(breakout.isLeft()) {
            positionX -= velocityX;
            System.out.println("left key");
        }
        // spriteImage.setTranslateX(positionX);
        if(breakout.isRight()) {
            positionX += velocityX;
            System.out.println("right key");
        }

    }
    private void setScreenBoundaries(){
        if(positionX >= RIGHT_SCREEN_BOUNDARY){
            positionX = RIGHT_SCREEN_BOUNDARY;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY){
            positionX = LEFT_SCREEN_BOUNDARY;
        }

    }
    private void translatePaddle() {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }


   @Override
    boolean collision(GameObject object){
        return false;
    }
}

