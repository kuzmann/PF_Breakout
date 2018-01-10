package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.image.Image;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;

class Paddle extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    private final double PADDLE_INIT_X_POS = 0;
    private final double PADDLE_INIT_Y_POS = HEIGHT*0.4;
    private static final double PADDLE_DIM_X = 200/2;
    private static final double PADDLE_DIM_Y = 12.5;
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - PADDLE_DIM_X/2;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - PADDLE_DIM_X/2);
    private static final double BOTTOM_SCREEN_BOUNDARY = -(HEIGHT/2 - PADDLE_DIM_Y/2);
    private static final double TOP_SCREEN_BOUNDARY = (HEIGHT/2 - PADDLE_DIM_Y/2);

    protected Paddle (Breakout iPaddle, String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iPaddle;

    }

    @Override
    public void update() {
        setXYPosition(15);
        setScreenBoundaries();
        translatePaddle();
    }
    private void setXYPosition(double velocity){
        this.velocityX = velocity;
        this.velocityY = velocity;
        if(breakout.controller.isLeft()) {
            positionX -= velocityX;
        }
        if(breakout.controller.isRight()) {
            positionX += velocityX;
        }
//        if(breakout.isUp()) {
//            positionY -= velocityY;
//        }
//        if(breakout.isDown()) {
//            positionY += velocityY;
//        }
    }
    private void setScreenBoundaries(){
        if(positionX >= RIGHT_SCREEN_BOUNDARY){
            positionX = RIGHT_SCREEN_BOUNDARY;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY){
            positionX = LEFT_SCREEN_BOUNDARY;
        }
//        if(positionY <= BOTTOM_SCREEN_BOUNDARY){
//            positionY = BOTTOM_SCREEN_BOUNDARY;
//        }
//        if(positionY >= TOP_SCREEN_BOUNDARY){
//            positionY = TOP_SCREEN_BOUNDARY;
//        }

    }
    private void translatePaddle() {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }

    void resetState(){
        this.positionX = PADDLE_INIT_X_POS;
        this.positionY = PADDLE_INIT_Y_POS;
        spriteImage.setTranslateX(PADDLE_INIT_X_POS);
        spriteImage.setTranslateY(PADDLE_INIT_Y_POS);
    }

}

